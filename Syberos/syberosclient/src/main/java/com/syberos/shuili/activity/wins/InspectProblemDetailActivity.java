package com.syberos.shuili.activity.wins;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.BuildConfig;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.wins.BisWinsProb;
import com.syberos.shuili.entity.wins.InspectProblemInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.HashMap;

import butterknife.BindView;

/**
 * 稽察问题详情
 */
public class InspectProblemDetailActivity extends BaseActivity {

    private final String Title = "稽察问题详情";

    @BindView(R.id.tv_project)
    TextView tv_project;

    @BindView(R.id.tv_area)
    TextView tv_area;

    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.tv_department)
    TextView tv_department;

    @BindView(R.id.tv_severity)
    TextView tv_severity;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.ae_controls_audio)
    AudioEditView ae_controls_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;
    private BisWinsProb bisWinsProb = null;
    private String projName;



    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_problem_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",bisWinsProb.getProbDep());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                AttOrgBase attOrgBase = gson.fromJson(result,AttOrgBase.class);
                if(attOrgBase != null && attOrgBase.dataSource != null &&attOrgBase.dataSource.size() > 0){
                    tv_department.setText(attOrgBase.dataSource.get(0).getOrgName());
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
            }
        });

    }

    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWinsProb = (BisWinsProb) bundle.getSerializable("prob");
        projName = bundle.getString("projName");

        if (null != bisWinsProb) {
            tv_project.setText(projName);
            int index = 1;
            if(!bisWinsProb.getProbType().isEmpty()){
                index = Integer.valueOf(bisWinsProb.getProbType());
            }
            tv_type.setText(GlobleConstants.winsProbMap.get(index));

            tv_department.setText(bisWinsProb.getProbDep());
            int type = Integer.valueOf(bisWinsProb.getProbCate());

            switch (type) {
                case GlobleConstants.TYPE_NORMAL:
                    tv_severity.setText(R.string.severity_normal);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_normal, null));
                    } else {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_normal));
                    }
                    break;
                case GlobleConstants.TYPE_BIG:
                    tv_severity.setText(R.string.severity_big);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_big, null));
                    } else {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_big));
                    }
                    break;
                case GlobleConstants.TYPE_BIGGER:
                    tv_severity.setText(R.string.severity_large);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_large, null));
                    } else {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_large));
                    }
                    break;
            }


            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("问题描述");
            ae_describe_audio.setEditText(bisWinsProb.getProbDesc());

            ae_controls_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_controls_audio.setLabelText("整改建议");
            ae_controls_audio.setEditText(bisWinsProb.getRectSugg());

            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
            BusinessConfig.getAttachMents(bisWinsProb.getGuid(),"BIS_WINS_PROB",mv_multimedia);
        }
    }
}
