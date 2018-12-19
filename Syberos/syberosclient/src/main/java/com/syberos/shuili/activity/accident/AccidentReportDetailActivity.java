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

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.accident.ObjAcciReport;
import com.syberos.shuili.entity.common.AttachMentInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.activity.accident.AccidentListForEntAcitvity.SEND_BUNDLE_KEY;

/**
 * Created by Administrator on 2018/12/19.
 */

public class AccidentReportDetailActivity extends BaseActivity {
    private final String TAG = AccidentDetailActivity.class.getSimpleName();
    private final String Title = "快报事故详情";
    private ObjAcciReport.DataBean accidentInformation = null;

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
    @BindView(R.id.If_Resp_Acci)
    TextView If_Resp_Acci;
    @BindView(R.id.If_Pho_Rep)
    TextView If_Pho_Rep;

    @BindView(R.id.ae_accident_describe_audio)
    TextView ae_accident_describe_audio;

    @BindView(R.id.mv_accident_multimedia)
    MultimediaView mv_accident_multimedia;
    @BindView(R.id.ll_report_item)
    LinearLayout ll_report_item;
    @BindView(R.id.rl_describe)
    LinearLayout rl_describe;



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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv_accident_multimedia.cancel();
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle("事故详情");
        setActionBarRightVisible(View.INVISIBLE);
        rl_describe.setVisibility(View.GONE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        accidentInformation = (ObjAcciReport.DataBean)bundle.getSerializable(
                SEND_BUNDLE_KEY);
        updateView();
    }

    private void updateView() {
        showDataLoadingDialog();
        getAttachMents();
        if (null != accidentInformation ) {
            tv_accident_unit.setText(accidentInformation.getWiunName());
            tv_accident_name.setText(accidentInformation.getOccuTime()+" 事故");
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
            If_Pho_Rep.setText(GlobleConstants.YES.equals(accidentInformation.getIsRep()) ?"是":"否");
            If_Resp_Acci.setText(GlobleConstants.YES.equals(accidentInformation.getIsRep()) ?"是":"否");


            mv_accident_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_accident_describe_audio.setText("");
        }
    }
    private void getAttachMents(){
        final ArrayList<MultimediaView.LocalAttachment> attachments = new ArrayList<>();
        String url = GlobleConstants.strIP + "/sjjk/v1/jck/attMedBases/";
        urlTags.add(url);
        HashMap<String,String> params = new HashMap<>();
        params.put("bisGuid",accidentInformation.getGuid());
        params.put("bisTableName","OBJ_ACCI");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                AttachMentInfo attachMentInfo = gson.fromJson(result,AttachMentInfo.class);
                if(attachMentInfo != null && attachMentInfo.getData().size() > 0){

                    for(AttachMentInfo.DataBean item : attachMentInfo.getData()){
                        MultimediaView.LocalAttachment localAttachment = new MultimediaView.LocalAttachment();
                        if("2".equals(item.getMedType())) {
                            localAttachment.type = MultimediaView.LocalAttachmentType.IMAGE;
                        }else if("3".equals(item.getMedType())){
                            localAttachment.type = MultimediaView.LocalAttachmentType.AUDIO;
                        }else if("4".equals(item.getMedType())){
                            localAttachment.type = MultimediaView.LocalAttachmentType.VIDEO;
                        }else {
                            continue;
                        }
                        localAttachment.filePath = item.getMedPath();
                        localAttachment.bExist = true;
                        attachments.add(localAttachment);
                    }
                }
                mv_accident_multimedia.addTags(attachments);
                mv_accident_multimedia.setData(attachments);
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();

            }
        });
    }
}
