package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.dangersource.BisHazReg;
import com.syberos.shuili.entity.dangersource.BisHazRegDetail;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.hazGradeMap;
import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * 危险源详情
 */
public class HazDetailActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener,View.OnClickListener {

    private final String TAG = HazDetailActivity.class.getSimpleName();

    private final String Title = "巡查记录";
    private final String ActivityTitle = "危险源详情";

    public static final String SEND_BUNDLE_KEY = "HistoryPatrolInformation";
    private BisHazReg information = null;
    private BisHazRegDetail bisHazRegDetail = null;


    @BindView(R.id.tv_unit)
    TextView tv_unit;
    @BindView(R.id.tv_project)
    TextView tv_project;
    @BindView(R.id.tv_danger_grade)
    TextView tv_danger_grade;
    @BindView(R.id.tv_lince_note)
    TextView tv_line_note;
    @BindView(R.id.tv_super_pers)
    TextView tv_super_pers;
    @BindView(R.id.tv_super_pers_phone)
    TextView tv_super_pers_phone;
    @BindView(R.id.ae_describe_audio)
    TextView ae_describe_audio;
    @BindView(R.id.ae_describe1_audio)
    TextView ae_describe1_audio;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.rl_patrol)
    RelativeLayout rl_patrol;






    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_detail;
    }

    @Override
    public void initListener() {
        rl_patrol.setOnClickListener(this);

    }

    @Override
    public void initData() {
        if(information == null) {
            Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
            information = (BisHazReg) bundle.getSerializable(HazListForEntActivity.SEND_BUNDLE_KEY);
        }
        showDataLoadingDialog();
        getBisHazRegDetail();


    }

    private void getBisHazRegDetail(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/obj/selectHazInfoDetails/";
        HashMap<String,String>param = new HashMap<>();
        param.put("guid",information.guid);
        param.put("hazGuid",information.guid);
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisHazRegDetail = gson.fromJson(result,BisHazRegDetail.class);
                if(bisHazRegDetail == null || bisHazRegDetail.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    refreshUI();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });

    }
    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle("危险源详情");
        setActionBarRightVisible(View.INVISIBLE);

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_patrol:
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",information);
                bundle.putString("type","admin");
                intentActivity(this,RecordedHistoryPatrolListActivity.class,false,bundle);
                break;
        }

    }
    private void refreshUI(){
        BisHazRegDetail item = bisHazRegDetail.dataSource.get(0);
        tv_unit.setText(item.wiunName);
        tv_project.setText(item.engName);
        int grad = Integer.valueOf(item.hiddGrad) == null? 1:Integer.valueOf(item.hiddGrad);
        tv_danger_grade.setText(hazGradeMap.get(grad));
        String strLiceNoti = item.ifLiceNoti;
        if("1".equals(strLiceNoti)){
            tv_line_note.setText("是");
        }else if("2".equals(strLiceNoti)){
            tv_line_note.setText("否");
        }else {
            tv_line_note.setText("未知");
        }
        tv_super_pers.setText(item.supPers);
        tv_super_pers_phone.setText(item.offiTel);
        ae_describe_audio.setText(item.note);
        tv_time.setText(item.regTime);
    }
}