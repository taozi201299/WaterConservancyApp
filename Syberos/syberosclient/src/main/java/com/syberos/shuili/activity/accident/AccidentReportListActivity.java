package com.syberos.shuili.activity.accident;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.reports.AcciReportActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.accident.ObjAcciReport;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/19.
 */

public class AccidentReportListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_action_bar_back)
    ImageView ivActionBarBack;
    @BindView(R.id.tv_action_bar_title)
    TextView tvActionBarTitle;
    @BindView(R.id.iv_action_right)
    LinearLayout ivActionRight;
    @BindView(R.id.action_bar)
    RelativeLayout actionBar;
    @BindView(R.id.recyclerView_express_accident)
    RecyclerView recyclerViewExpressAccident;
    @BindView(R.id.rb_accident_zhiguan)
    RadioButton rbAccidentZhiguan;
    @BindView(R.id.rb_accident_child)
    RadioButton rbAccidentChild;
    @BindView(R.id.rb_accident_liuyu)
    RadioButton rbAccidentLiuyu;
    @BindView(R.id.rg_accident)
    RadioGroup rgAccident;
    @BindView(R.id.tv_rejected)
    TextView tvRejected;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    @BindView(R.id.tv_current_month)
    TextView tv_current_month;
    @OnClick(R.id.tv_current_month)
    void onCurrentMonthClicked() {
        onSelectMonthClicked();
    }

    @OnClick(R.id.iv_action_right)
    void onActionBarRightClicked() {
        onSelectMonthClicked();
    }

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    int searchType; // 0 直管 1 下级水行政 2 流域
    ListAdapter listAdapter = null;
    public  final String SEND_BUNDLE_KEY = "ObjAcci";
    private ArrayList<ObjAcciReport.DataBean> acciList ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_acci_report_list_layout;
    }

    @Override
    public void initListener() {
        llCommit.setOnClickListener(this);
        rgAccident.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_accident_zhiguan)
                    searchType = 0;
                else if(checkedId == R.id.rb_accident_child)
                    searchType = 1;
                else if(checkedId == R.id.rb_accident_liuyu)
                    searchType = 2;
            }
        });
        listAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position >= acciList.size())return;
                Bundle bundle = new Bundle();
                bundle.putSerializable(SEND_BUNDLE_KEY, acciList.get(position));
                intentActivity(AccidentReportListActivity.this,AccidentReportDetailActivity.class,false,bundle);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        int orgLevel = BusinessConfig.getOrgLevel();
        rbAccidentZhiguan.setVisibility(View.GONE);
        rbAccidentChild.setVisibility(View.GONE);
        rbAccidentLiuyu.setVisibility(View.GONE);
        if(orgLevel == 1){
            rbAccidentZhiguan.setVisibility(View.VISIBLE);
            rbAccidentChild.setVisibility(View.VISIBLE);
            rbAccidentLiuyu.setVisibility(View.VISIBLE);
        }else if(orgLevel == 2){
            rbAccidentZhiguan.setVisibility(View.VISIBLE);
            rbAccidentChild.setVisibility(View.VISIBLE);
        }else if(orgLevel == 3){
            rbAccidentZhiguan.setVisibility(View.VISIBLE);
            rbAccidentChild.setVisibility(View.VISIBLE);
        }else  if(orgLevel == 4){
            rbAccidentZhiguan.setVisibility(View.VISIBLE);
        }
        if ("1".equals(App.jurdAreaType)) {

        } else if ("3".equals(App.jurdAreaType) || "4".equals(App.jurdAreaType)) {
            rbAccidentZhiguan.setVisibility(View.GONE);
            rbAccidentChild.setVisibility(View.GONE);
            rbAccidentLiuyu.setVisibility(View.GONE);
        }
        String currentTime = CommonUtils.getCurrentDate();
        String[]arrTime = currentTime.split("-");
        tv_current_month.setText(arrTime[0] +"年" +arrTime[1] +"月");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerViewExpressAccident.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_enterprises_express_accident_list_item);
        recyclerViewExpressAccident.setAdapter(listAdapter);
        acciList = new ArrayList<>();
        getAcciList();

    }

    private String getStartTime(){
        String searchTime = "";
        String time = tv_current_month.getText().toString();
        time = time.replace("年","-");
        time = time.replace("月","");
        String[]arrayTime = time.split("-");
        String year = arrayTime[0];
        String month = arrayTime[1];
        if(month.length() == 0){
            searchTime = year + "-"+"0"+month;
        }else {
            searchTime = year +"-"+ month;
        }
        return searchTime;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ll_commit:
                getAcciList();
                break;
        }
    }
    private void getAcciList(){
        showDataLoadingDialog();
        String url = "";
        HashMap<String,String>params = new HashMap<>();
        if(searchType == 0){
            url = GlobleConstants.strIP + "/sjjk/v1/bis/acci/exp/rep/selectAccidentBulletinList/";
            params.put("adminWiunGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
            params.put("repOrgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
            params.put("startTime",getStartTime());
        }else if(searchType == 1){
            url = GlobleConstants.strIP + "/sjjk/v1/bis/acci/exp/rep/selectAccidentListJuniorWater/";
            params.put("pguid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
            params.put("repOrgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
            params.put("startTime",getStartTime());
        }else if(searchType == 2){
            url = GlobleConstants.strIP + "/sjjk/v1/bis/acci/exp/rep/selectAccidentListRiverBasin/";
            params.put("repOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
            params.put("startTime",getStartTime());
        }
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                ObjAcciReport objAcciReport = gson.fromJson(result,ObjAcciReport.class);
                if(objAcciReport == null ){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
                refreshUI(objAcciReport);
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            }
        });

    }
    private void refreshUI(ObjAcciReport objAcciReport){
        acciList.clear();
        acciList.addAll(objAcciReport.getData());
        if(acciList.size() == 0){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
        }
        listAdapter.setData(acciList);
        listAdapter.notifyDataSetChanged();
    }
    private void onSelectMonthClicked() {
        //时间选择器
        boolean[] type = {true, true, false, false, false, false};

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() > System.currentTimeMillis()) {
                    ToastUtils.show("提示：所选月份不应大于系统当前月份");
                    return;
                }
                String time = Strings.formatDate(date);
                String[] arrayTime = time.split("-");
                tv_current_month.setText(arrayTime[0]+"年"+arrayTime[1]+"月");
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
    private class ListAdapter extends CommonAdapter<ObjAcciReport.DataBean> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjAcciReport.DataBean item) {
            RelativeLayout ll_report_after = holder.getView(R.id.ll_report_after);
            ll_report_after.setVisibility(View.GONE);
            String grade = item.getAcciGrad() == null || item.getAcciGrad().isEmpty() ?"1":item.getAcciGrad();
            int type = Integer.valueOf(grade);
            switch (type) {
                case GlobleConstants.TYPE_NORMAL: {
                    ((TextView)holder.getView(R.id.tv_type)).setText(R.string.accident_type_normal);
                    (holder.getView(R.id.ll_type)).setBackgroundResource( R.drawable.btn_accident_type_normal_shape);
                }
                break;
                case GlobleConstants.TYPE_BIG: {
                    ((TextView)holder.getView(R.id.tv_type)).setText(R.string.accident_type_big);
                    (holder.getView(R.id.ll_type)).setBackgroundResource( R.drawable.btn_accident_type_big_shape);
                }
                break;
                case GlobleConstants.TYPE_BIGGER: {
                    ((TextView)holder.getView(R.id.tv_type)).setText(R.string.accident_type_bigger);
                    (holder.getView(R.id.ll_type)).setBackgroundResource( R.drawable.btn_accident_type_bigger_shape);
                }
                break;
                case GlobleConstants.TYPE_LARGE: {
                    ((TextView)holder.getView(R.id.tv_type)).setText(R.string.accident_type_large);
                    ((TextView)holder.getView(R.id.tv_type)).setTextColor(getResources().getColor(R.color.black));
                    (holder.getView(R.id.ll_type)).setBackgroundResource( R.drawable.btn_accident_type_large_shape);
                }
                break;
                default:
                    ((TextView)holder.getView(R.id.tv_type)).setText(R.string.accident_type_big);
                    (holder.getView(R.id.ll_type)).setBackgroundResource( R.drawable.btn_accident_type_big_shape);
            }

            ((TextView)holder.getView(R.id.tv_title)).setText(GlobleConstants.acciClassMap.get(item.getAcciCate()));
            ((TextView)holder.getView(R.id.tv_time)).setText(item.getOccuTime());
            ((TextView)holder.getView(R.id.tv_name)).setText(item.getWiunName());
        }
    }
}
