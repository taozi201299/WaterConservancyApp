package com.syberos.shuili.activity.accident;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.activity.accident.EnterprisesExpressAccidentListActivity.SEND_BUNDLE_KEY;

public class EnterprisesExpressAccidentDetailActivity extends BaseActivity {

    private final String TAG = EnterprisesExpressAccidentDetailActivity.class.getSimpleName();
    private final String Title = "快报事故详情";
    private ObjAcci accidentInformation = null;

    // 补报记录
    private ArrayList<ObjAcci> reportInfo = null;

    public static final int REQUEST_CODE = 1539;

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
    @BindView(R.id.ll_report_item)
    LinearLayout ll_report_item;

    @OnClick(R.id.iv_location)
    void onLocationAccident() {
        ToastUtils.show("TODO 请求显示事故地点的定位地图");
    }

    @OnClick(R.id.tv_accident_report_after)
    void onAccidentReportAfterClicked() {
        if (null != accidentInformation) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(SEND_BUNDLE_KEY,
                    accidentInformation);

            intentActivity(EnterprisesExpressAccidentDetailActivity.this,
                    EnterprisesReportAfterAccidentActivity.class,
                    false, bundle, REQUEST_CODE);
        } else {
            ToastUtils.show("Error: EnterprisesExpressAccidentDetailActivity" +
                    " accidentInformation is null");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_express_accident_detail;
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
        showTitle("事故详情");
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        accidentInformation = (ObjAcci)bundle.getSerializable(
                SEND_BUNDLE_KEY);
        reportInfo = (ArrayList<ObjAcci>) bundle.getSerializable("data") ;
        updateView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode) {
             accidentInformation =
                    (ObjAcci) data.getSerializableExtra(
                            SEND_BUNDLE_KEY);
            updateView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void updateView() {
        if (null != accidentInformation ) {
            setActionBarTitle(accidentInformation.getAcciCateName());
            tv_accident_unit.setText(accidentInformation.getAccidentUnitName());
            tv_accident_name.setText(accidentInformation.getCollTime()+" 事故");
            tv_serious_injuries_count.setText(
                    String.valueOf(accidentInformation.getSerInjNum())
            );
            tv_death_count.setText(
                    String.valueOf(accidentInformation.getCasNum())
            );
            tv_direct_economic_loss.setText(
                    String.valueOf(accidentInformation.getEconLoss())
            );
            tv_time.setText(accidentInformation.getOccuTime());


            mv_accident_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);

            ae_accident_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_accident_describe_audio.setEditText(accidentInformation.getAcciSitu());
            if(reportInfo != null){
                ll_report_item.removeAllViews();
                for(final ObjAcci item : reportInfo) {
                    if(!item.getPID().equals(accidentInformation.getId()))continue;
                    View view = LayoutInflater.from(mContext).inflate(R.layout.simple_list_row, null);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ll_report_item.getLayoutParams());
                    lp.setMargins(0, 0, 0, 15);
                    TextView textView = (TextView) view.findViewById(R.id.textView);
                    textView.setText(item.getCollTime() + " " + "补报");
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(SEND_BUNDLE_KEY, item);
                            bundle.putSerializable("data",null);
                            intentActivity(EnterprisesExpressAccidentDetailActivity.this,EnterprisesExpressAccidentDetailActivity.class
                            ,false,bundle);
                        }
                    });
                    ll_report_item.addView(view);
                }
            }
        }
    }
}
