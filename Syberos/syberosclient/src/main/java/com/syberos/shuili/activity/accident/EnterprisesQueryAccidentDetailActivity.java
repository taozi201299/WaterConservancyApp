package com.syberos.shuili.activity.accident;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件暂不使用
 */

public class EnterprisesQueryAccidentDetailActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = EnterprisesQueryAccidentDetailActivity.class.getSimpleName();
    private final String Title = "事故查询详情";

    @BindView(R.id.tv_accident_unit)
    TextView tv_accident_unit;

    @BindView(R.id.tv_accident_name)
    TextView tv_accident_name;

    @BindView(R.id.tv_serious_injuries_count)
    TextView tv_serious_injuries_count;

    @BindView(R.id.tv_death_count)
    TextView tv_death_count;

    @BindView(R.id.tv_direct_economic_loss)
    TextView tv_direct_economic_loss;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.ae_accident_describe_audio)
    AudioEditView ae_accident_describe_audio;

    @BindView(R.id.mv_accident_multimedia)
    MultimediaView mv_accident_multimedia;

    @OnClick(R.id.iv_location)
    void onLocationAccident() {
        ToastUtils.show("TODO 请求显示事故地点的定位地图");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_query_accident_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        ObjAcci accidentInformation = (ObjAcci)bundle.getSerializable(
                QueryAccidentListActivity.SEND_BUNDLE_KEY);

        if (null != accidentInformation) {
            setActionBarTitle("待补充");
            tv_accident_unit.setText("待补充");
            tv_accident_name.setText("待补充");
            tv_serious_injuries_count.setText(accidentInformation.getSerInjNum());
            tv_death_count.setText(accidentInformation.getCasNum());
            tv_direct_economic_loss.setText(accidentInformation.getEconLoss());
            tv_time.setText(accidentInformation.getOccuTime());


            mv_accident_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);

            ae_accident_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_accident_describe_audio.setLabelText("事故描述");
            ae_accident_describe_audio.setEditText(accidentInformation.getAcciSitu());
        }
    }
}
