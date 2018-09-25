package com.syberos.shuili.activity.dangermanagement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.accident.AccidentNewFormForEntActivity;
import com.syberos.shuili.activity.securitycheck.EnterprisesElementCheckCreateHiddenActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.securitycheck.BisSeChit;
import com.syberos.shuili.entity.securitycheck.BisSinsRec;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import static com.syberos.shuili.activity.accident.AccidentListForEntAcitvity.DIC_ACCIDENT_KEY;
import static com.syberos.shuili.activity.accident.AccidentListForEntAcitvity.DIC_UNIT_KEY;
import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by Administrator on 2018/6/6.
 * 隐患排查 单位工程列表
 */

public class InvestigationEngineForEntActivity extends BaseActivity implements AdapterView.OnItemClickListener {
   private final String Tag = InvestigationEngineForEntActivity.class.getSimpleName();
   private final String Title = "隐患排查";
    /**
     * 元素检查检查项对象
     */
   private BisSeChit bisSeChit = null;
    /**
     * 现场检查 安全检查记录对象
     */
   private BisSinsRec bisSinsRec = null;

   private HashMap<String,String> map = new HashMap(){
        {
            put("01","水库");
            put("02","水闸");
            put("03","泵站");
            put("04","水电站");
            put("05","堤防");
            put("06","灌区");
            put("07","引调水");
            put("08","淤地坝");
            put("09","农村供水");
            put("10","其他");


        }
    };

   @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
   @BindView(R.id.stickListHeadersListView)
    StickyListHeadersListView stickyListHeadersListView;

    /**
     * 工程对象
     */
   private MvEngColl mvEngColl = null;
   private ArrayList<MvEngColl>mvEngColls = new ArrayList<>();
   private EngineListAdapter engineListAdapter;
   private String type ;
   private Bundle mBundle ;

    public int getLayoutId() {
        return R.layout.activity_investigation_engin_ent_layout;
    }

    @Override
    public void initListener() {
        stickyListHeadersListView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarRightVisible(View.INVISIBLE);
        showTitle(Title);
        mBundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        type = mBundle.getString("type");
        if("element".equals(type)){
            // 元素检查
            bisSeChit = (BisSeChit) mBundle.getSerializable("checkItem");
        }else if("check".equals(type)){
            // 现场检查
            bisSinsRec = (BisSinsRec)mBundle.getSerializable("bisSinsRec");
        }
        else {
            // 隐患模块
            type = "hidden";
        }
        /**
         * 根据type获取不同的参数信息
         */
        showDataLoadingDialog();
        getEngineList();
        engineListAdapter =  new EngineListAdapter(this);
        stickyListHeadersListView.setAdapter(engineListAdapter);
        xRefreshView.setPullRefreshEnable(true);
        xRefreshView.setPullLoadEnable(false);
        xRefreshView.setMoveHeadWhenDisablePullRefresh(false);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getEngineList();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    private void getEngineList(){
        String url =  GlobleConstants.strIP +"/sjjk/v1/mv/eng/coll/mvEngColls/";
        HashMap<String,String>params = new HashMap<>();
        params.put("orgguid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeLoadingDialog();
                Gson gson = new Gson();
                mvEngColl = gson.fromJson(result,MvEngColl.class);
                if(mvEngColl == null || mvEngColl.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(mvEngColl.dataSource.size() == 0){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                }else {
                    processResult();
                    refreshUI();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeLoadingDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });

    }
    private void processResult(){
        mvEngColls.clear();
        for(MvEngColl item : mvEngColl.dataSource){
            String typeName = map.get(item.getEngtype());
            item.setEngTypeName(typeName == null ?"未知":typeName);
            if("CJFR".equalsIgnoreCase(App.sCode) || "CJJL".equalsIgnoreCase(App.sCode) ||"CJSG".equalsIgnoreCase(App.sCode)){
                // 在建工程 1
                if(!"1".equals(item.getStat())){
                    continue;
                }
            }else {
                // 已建工程 2
                if(!"2".equals(item.getStat())){
                    continue;
                }
            }
            mvEngColls.add(item);
        }

    }
    private void closeLoadingDialog(){
        closeDataDialog();
        xRefreshView.stopRefresh(false);
    }
    private boolean hasTend(){
        if(GlobleConstants.CJFR.equalsIgnoreCase(App.sCode) || GlobleConstants.CJSG.equalsIgnoreCase(App.sCode)
                || GlobleConstants.CJJL.equalsIgnoreCase(App.sCode)) {
            return true;
        }
        return false;
    }
    private void  refreshUI(){
        engineListAdapter.setData(mvEngColls);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MvEngColl item = mvEngColls.get(position);
        Bundle bundle = new Bundle();

        if(String.valueOf(GlobleConstants.NEW_ACCI).equals(type)){
            bundle.putInt("type",GlobleConstants.NEW_ACCI);
            bundle.putSerializable("engColls",item);
            bundle.putSerializable(DIC_UNIT_KEY,mBundle.getSerializable(DIC_UNIT_KEY));
            bundle.putSerializable(DIC_ACCIDENT_KEY,bundle.getSerializable(DIC_ACCIDENT_KEY));
            intentActivity(InvestigationEngineForEntActivity.this, AccidentNewFormForEntActivity.class,
                    false, bundle);
            return;
        }
        bundle.putSerializable("data",item);
        bundle.putSerializable("type",type);
        if(hasTend()) {
            if(type.equalsIgnoreCase("check")){
                bundle.putSerializable("checkItem",bisSinsRec);
            }else if(type.equalsIgnoreCase("element")){
                bundle.putSerializable("checkItem",bisSeChit);
            }
            intentActivity(InvestigationEngineForEntActivity.this, InvestigationEngineTendForEntActivity.class,
                    false, bundle);
        }else {
            bundle.putBoolean("hasTend",false);
            if(type.equals("element")) {
                bundle.putSerializable("checkItem",bisSeChit);
                intentActivity(InvestigationEngineForEntActivity.this,EnterprisesElementCheckCreateHiddenActivity.class,false,bundle);
            }else if(type.equals("check")) {
                bundle.putSerializable("checkItem",bisSinsRec);
                intentActivity(InvestigationEngineForEntActivity.this,EnterprisesElementCheckCreateHiddenActivity.class,false,bundle);
            }else {
                intentActivity(InvestigationEngineForEntActivity.this,InvestigationAddForEnterpriseActivity.class,false,bundle);
            }

        }
    }
    public class EngineListAdapter extends BaseAdapter implements StickyListHeadersAdapter,SectionIndexer {

        List<MvEngColl> tasks;
        private MvEngColl[] sectionIndices;
        private String[] sectionHeaders;
        HashMap<String, Integer> map = new HashMap<>();
        private Context mContext;
        public EngineListAdapter(Context context) {
            this.mContext = context;
        }

        public void setData(List<MvEngColl> data) {
            if (data == null) return;
            map.clear();
            if (tasks != null) tasks.clear();
            tasks = data;
            sectionIndices = getSectionIndices();
            if(sectionIndices == null)return;
            sectionHeaders = getSectionHeaders();
            if(sectionHeaders == null) return;
            notifyDataSetChanged();
        }


        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeadViewHolder viewHolder;
            if (convertView != null) {
                viewHolder = (HeadViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_parent_layout, null);
                viewHolder = new HeadViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            viewHolder.mHeadText.setText("" + tasks.get(position).getEngTypeName());

            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            return map.get(tasks.get(position).getEngTypeName());
        }

        @Override
        public int getCount() {
            return tasks == null ? 0 : tasks.size();
        }

        @Override
        public Object getItem(int position) {
            return tasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView != null) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_engine_item_layout, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            viewHolder.tv_name.setText(tasks.get(position).getName());
            return convertView;
        }

        private MvEngColl[] getSectionIndices() {
            if (tasks == null || tasks.size() == 0) return null;
            List<MvEngColl> sectionIndices = new ArrayList<MvEngColl>();
            String engTypeName = tasks.get(0).getEngTypeName();
            sectionIndices.add(tasks.get(0));
            map.put(engTypeName, 0);
            for (int i = 1; i < tasks.size(); i++) {
                String item = tasks.get(i).getEngTypeName();
                if (!item.equals(engTypeName)) {
                    engTypeName = item;
                    sectionIndices.add(tasks.get(i));
                    map.put(engTypeName, i);
                }
            }
            MvEngColl[] sections = new MvEngColl[sectionIndices.size()];
            for (int i = 0; i < sectionIndices.size(); i++) {
                sections[i] = sectionIndices.get(i);
            }
            return sections;
        }
        private String[] getSectionHeaders() {
            String[] sectionHeaders = new String[sectionIndices.length];
            for (int i = 0; i < sectionIndices.length; i++) {
                sectionHeaders[i] = ((MvEngColl)sectionIndices[i]).getEngTypeName();
            }
            return sectionHeaders;
        }

        @Override
        public Object[] getSections() {
            return sectionHeaders;
        }

        @Override
        public int getPositionForSection(int sectionIndex) {
            for (int i = 0; i < getCount(); i++) {
                String engTypeName = tasks.get(i).getEngTypeName();
                int id = map.get(engTypeName);
                if (id == sectionIndex) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public int getSectionForPosition(int position) {
            return position;
        }

        class ViewHolder {
            @BindView(R.id.tv_name)
            TextView tv_name;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        class HeadViewHolder {
            @BindView(R.id.headText)
            TextView mHeadText;

            HeadViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
