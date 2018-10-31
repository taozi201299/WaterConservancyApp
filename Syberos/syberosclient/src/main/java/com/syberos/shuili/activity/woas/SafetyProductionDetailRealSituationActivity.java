package com.syberos.shuili.activity.woas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.woas.BisWoasObj;
import com.syberos.shuili.entity.woas.InspectAssessHistory;
import com.syberos.shuili.entity.woas.InspectAssessInfo;
import com.syberos.shuili.entity.woas.InspectAssessPlanBatchInfo;
import com.syberos.shuili.entity.woas.InspectAssessPlanInfo;
import com.syberos.shuili.utils.Strings;

import java.util.List;

import butterknife.BindView;

/**
 * 安全检查对象 考前摸底 水利稽察
 */
public class SafetyProductionDetailRealSituationActivity extends BaseActivity {

    private BisWoasObj info = null;

    @BindView(R.id.ll_container_0)
    LinearLayout ll_container_0;

    @BindView(R.id.ll_container_1)
    LinearLayout ll_container_1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_detail_real_situation;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setInitActionBar(true);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        info = (BisWoasObj) bundle.getSerializable(
                InspectAssessDetailActivity.SEND_BUNDLE_KEY_0);

        if (null != info) {

            setActionBarTitle(info.getWoasWiunName());
            setActionBarRightVisible(View.INVISIBLE);
        }
    }
}
