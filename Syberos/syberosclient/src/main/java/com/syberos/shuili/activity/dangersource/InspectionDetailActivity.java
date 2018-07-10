package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * 危险源详情
 */
public class InspectionDetailActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener,View.OnClickListener {

    private final String TAG = InspectionDetailActivity.class.getSimpleName();

    private final String Title = "巡查记录";
    private final String ActivityTitle = "危险源详情";

    public static final String SEND_BUNDLE_KEY = "HistoryPatrolInformation";
    private ObjHaz information = null;

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
    AudioEditView ae_describe_audio;
    @BindView(R.id.ae_describe1_audio)
    AudioEditView ae_describe1_audio;
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
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        information = (ObjHaz)bundle.getSerializable(InspectionListForEnterpriseActivity.SEND_BUNDLE_KEY);
        if(information != null){
          tv_unit.setText(information.getOrgName());
          tv_project.setText(information.getEngineName());
          tv_danger_grade.setText((information.getHiddGradName()));
          tv_line_note.setText(information.getIfLiceNoti());
          tv_super_pers.setText(information.getSupPers());
          tv_super_pers_phone.setText(information.getOffiTel());
          ae_describe_audio.setEditText("");
          ae_describe1_audio.setEditText("");
          tv_time.setText("");
        }

    }

    @Override
    public void initView() {
        showTitle("危险源详情");
        setActionBarRightVisible(View.INVISIBLE);
        ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ae_describe1_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);

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
                intentActivity(this,RecordedHistoryPatrolListActivity.class,false,bundle);
                break;
        }

    }
}