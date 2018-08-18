package com.syberos.shuili.activity.wins;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.wins.BisWinsProj;
import com.syberos.shuili.entity.wins.BisWinsProjAll;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/30.
 * 分组显示 每个工程下的稽查问题
 */

public class InspectionProblemsAcitvity extends BaseActivity {
    private final String Title = "稽查问题";

    @BindView(R.id.recyclerView_inspection_prob)
    RecyclerView recyclerView_inspection_prob;
    private BisWinsProjAll bisWinsProjAll = null;
    private BisWinsGroup bisWinsGroup = null;

    ArrayList<InspectionProblemGroup> groups = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_problem_layout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWinsGroup = (BisWinsGroup) bundle.getSerializable("bisWinsGroup");
        if(bisWinsGroup == null){
            ToastUtils.show("参数错误");
            activityFinish();
        }
    }


    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);

    }

    /**
     * 从稽查项目表中获取稽查对象 根据稽查组ID
     */
    private void getInspectionProject(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/proj/bisWinsProjs";
        HashMap<String,String>params = new HashMap<>();
        params.put("winsGroupGuid",bisWinsGroup.getBwgGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisWinsProjAll = gson.fromJson(result,BisWinsProjAll.class);
                if(bisWinsProjAll == null || bisWinsProjAll.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(bisWinsProjAll.dataSource.size() == 0){
                    ToastUtils.show("没有稽查项目");
                }else {
                    getWinsProblems();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    /**
     * 根据稽察组GUID和稽察地区名称查询稽察问题详情
     */
    private void getWinsProblems(){
        int size = bisWinsProjAll.dataSource.size();
        ArrayList<BisWinsProjAll>projAlls = (ArrayList<BisWinsProjAll>) bisWinsProjAll.dataSource;
        for(int i = 0 ; i < size ; i++){
            String  url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/proj/selectDetailsInspectionQuestions/";
            HashMap<String,String>params = new HashMap<>();
            params.put("bwgGuid",bisWinsGroup.getBwgGuid());
            params.put("adminWiunName",projAlls.get(i).getAdminWiunName());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {

                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {

                }
            });



        }
    }
    private static class InspectionProblemGroup implements Serializable {

        private String header;
        private ArrayList<ObjAcci> children;

        public InspectionProblemGroup(String header, ArrayList<ObjAcci> children) {
            this.header = header;
            this.children = children;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public ArrayList<ObjAcci> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<ObjAcci> children) {
            this.children = children;
        }
    }
    private static class GroupedWinsProbListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<InspectionProblemGroup> mGroups;

        public GroupedWinsProbListAdapter(
                Context context, ArrayList<InspectionProblemGroup> groups) {
            super(context);
            mGroups = groups;
        }
        public void setData(ArrayList<InspectionProblemGroup> groups){
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<ObjAcci> children = mGroups.get(groupPosition).getChildren();
            return children == null ? 0 : children.size();
        }

        @Override
        public boolean hasHeader(int groupPosition) {
            return true;
        }

        /**
         * 返回false表示没有组尾
         *
         * @param groupPosition
         * @return
         */
        @Override
        public boolean hasFooter(int groupPosition) {
            return false;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @return
         */
        @Override
        public int getFooterLayout(int viewType) {
            return 0;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @param holder
         * @param groupPosition
         */
        @Override
        public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

        }

        @Override
        public int getHeaderLayout(int viewType) {
            return R.layout.adapter_header;
        }

        @Override
        public int getChildLayout(int viewType) {
            return R.layout.activity_enterprises_express_accident_list_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            InspectionProblemGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            final ObjAcci accidentInformation
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
          //  DicInfo item  = getAccidentTypeItem(accidentInformation.getAcciCate());
            RelativeLayout ll_report_after = holder.get(R.id.ll_report_after);
            ll_report_after.setVisibility(View.GONE);
            String grade = accidentInformation.getAcciGrad() == null ?"0":accidentInformation.getAcciGrad();
            int type = Integer.valueOf(grade);
            switch (type) {
                case GlobleConstants.TYPE_NORMAL: {
                    holder.setText(R.id.tv_type,R.string.accident_type_normal);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_normal_shape);
                }
                break;
                case GlobleConstants.TYPE_BIG: {
                    holder.setText(R.id.tv_type,R.string.accident_type_big);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_big_shape);
                }
                break;
                case GlobleConstants.TYPE_BIGGER: {
                    holder.setText(R.id.tv_type,R.string.accident_type_bigger);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_bigger_shape);
                }
                break;
                case GlobleConstants.TYPE_LARGE: {
                    holder.setText(R.id.tv_type,R.string.accident_type_large);
                    holder.setTextColor(R.id.tv_type, R.color.black);

                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_large_shape);
                }
                break;
            }

         //   holder.setText(R.id.tv_title, item.getDcItemName());
            holder.setText(R.id.tv_time, accidentInformation.getOccuTime());
            holder.setText(R.id.tv_name, accidentInformation.getAccidentUnitName());
        }
    }
}
