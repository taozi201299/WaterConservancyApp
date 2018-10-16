package com.syberos.shuili.activity.securitycheck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.securitycheck.BisSinsSche;
import com.syberos.shuili.entity.securitycheck.BisSinsScheGroup;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.syberos.shuili.activity.securitycheck.SecurityCheckTaskActivity.SEND_BUNDLE_KEY;
import static com.syberos.shuili.config.GlobleConstants.strIP;

public class SecurityCheckQueryDetailActivity extends BaseActivity {

    private BisSinsSche bisSinsSche = null;

    @BindView(R.id.tv_check_time)
    TextView tv_check_time;

    @BindView(R.id.tv_check_content)
    TextView tv_check_content;

    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_check_plan)
    TextView tv_check_plan;

    @BindView(R.id.ll_child_groups_container)
    LinearLayout ll_child_groups_container;

    /**
     * 检查方案下的所有检查组信息
     */
    private BisSinsScheGroup bisSinsScheGroup;

    @OnClick(R.id.tv_detail)
    void onDetailClicked() {
        intentActivity(this, SecurityCheckQueryProblemsActivity.class,
                false, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_security_check_query_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        if(bisSinsSche == null){
            Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
            bisSinsSche = (BisSinsSche) bundle.getSerializable(SEND_BUNDLE_KEY);
        }
        if(bisSinsSche == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            return;
        }
        showTitle(bisSinsSche.getScheName());
        tv_check_content.setText(bisSinsSche.getScheCont());
        tv_check_plan.setText(bisSinsSche.getScheName());
        tv_check_time.setText(bisSinsSche.getScheMakeTime());

        showDataLoadingDialog();
        /**
         * 1 检查方案信息
         * 2 检查方案分组信息
         * 3 检查组信息
         * 4 该检查组下隐患列表
         */
        getGroupByPlanId();
    }

    /**
     * 获取该方案下的检查组信息
     */
    private void getGroupByPlanId(){
        String url = strIP +"/sjjk/v1/bis/sins/sche/grop/bisSinsScheGrops/";
        HashMap<String, String> params = new HashMap<>();
        params.put("scheGuid",bisSinsSche.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisSinsScheGroup = gson.fromJson(result,BisSinsScheGroup.class);
                if(bisSinsScheGroup == null || bisSinsScheGroup.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
                refreshUI();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });

    }

    private void refreshUI(){
        // 动态加载view
        ll_child_groups_container.removeAllViews();
        for(final BisSinsScheGroup item : bisSinsScheGroup.dataSource){
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_with_right_arrow,null);
            TextView tv_item_name = (TextView)view.findViewById(R.id.tv_item_name);
            TextView tv_arrow_right = (TextView)view.findViewById(R.id.tv_arrow_right);
            ImageView iv_right_arrow = (ImageView) view.findViewById(R.id.iv_right_arrow);
            tv_arrow_right.setVisibility(View.INVISIBLE);
            iv_right_arrow.setVisibility(View.VISIBLE);
            tv_item_name.setText(item.getGroupName());
            LinearLayout ll_item = (LinearLayout)view.findViewById(R.id.ll_item);
            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2018/4/26  检查组下的详情信息
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SEND_BUNDLE_KEY,item);
                    bundle.putSerializable("bisSinsSche",bisSinsSche);
                    intentActivity(SecurityCheckQueryDetailActivity.this,SecurityCheckDetailActivity.class,
                            false,bundle);
                }
            });
            ll_child_groups_container.addView(view);

        }

    }

    @Override
    public void initView() {
        setActionBarRightVisible(View.INVISIBLE);
        setInitActionBar(true);
    }
}
