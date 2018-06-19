package com.syberos.shuili.activity.securitycheck;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;

/**
 * Created by jidan on 18-4-6.
 * 该文件暂不使用
 */

public class SecurityHiddenTracePushActivity extends TranslucentActivity implements View.OnClickListener {

    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    @BindView(R.id.ev_des_audio)
    AudioEditView ev_des_audio;
    @BindView(R.id.layout_supervice_info)
    LinearLayout layout_supervice_info;
    @BindView(R.id.tv_RejectBtn)
    TextView tv_RejectBtn;
    @BindView(R.id.tv_commitBtn)
    TextView tv_commitBtn;

    private final int type = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_security_hiddlen_trace_push_layout;
    }

    @Override
    public void initListener() {
        tv_commitBtn.setOnClickListener(this);
        tv_RejectBtn.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        ((TextView)findViewById(R.id.tv_action_bar2_title)).setText("隐患跟踪-未推送");
        ((ImageView)findViewById(R.id.iv_action_bar2_left)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.iv_action_bar2_right)).setVisibility(View.INVISIBLE);
        ll_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_des_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_des_audio.setLabelText("核实描述");
        ev_des_audio.setEditText("核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述");
        ev_des_audio.setAudio("111",3);
        ev_des_audio.setAudio("222",4);
        if(type == 0){
            layout_supervice_info.setVisibility(View.GONE);
        }else {
            layout_supervice_info.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_commitBtn:
                ((TranslucentActivity)mContext).showTranslucentDialog(R.layout.activity_investigation_supervise_form).showShareView();
                ((TranslucentActivity)mContext).setbtnClicked(new TranslucentActivity.IbtnClicked() {
                    @Override
                    public void onBtnClicked(int type) {
                        switch (type){
                            case 0:
                                // TODO: 18-3-26 cancel
                                break;
                            case 1:
                                // TODO: 18-3-26 confirm
                                break;
                        }
                    }
                });
                break;
            case R.id.tv_RejectBtn:
                break;
        }
    }
}
