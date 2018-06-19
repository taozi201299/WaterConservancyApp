package com.syberos.shuili.activity.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.NoticeDetailInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomDialog;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationDetailActivity extends BaseActivity {

    private final String TAG = NotificationDetailActivity.class.getSimpleName();

    @OnClick(R.id.iv_action_bar2_left)
    void go2back() {
        gotoBack(false);
    }

    @BindView(R.id.tv_action_bar2_title)
    TextView tv_action_bar2_title;

    @BindView(R.id.iv_action_bar2_right)
    ImageView iv_action_bar2_right;

    @BindView(R.id.detail_title)
    TextView detail_title;

    @BindView(R.id.detail_time)
    TextView detail_time;

    @BindView(R.id.detail_content)
    TextView detail_content;

    private int detail_position = -1;

    private NoticeDetailInfo m_datas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notification_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setInitActionBar(false);

        tv_action_bar2_title.setText("通知详情");
        tv_action_bar2_title.setGravity(Gravity.LEFT);
        iv_action_bar2_right.setImageResource(R.mipmap.icon_button_notification_item_delete);
        iv_action_bar2_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CustomDialog customDialog
                        = new CustomDialog(NotificationDetailActivity.this);
                customDialog.setDialogMessage("消息管理", null, null);
                customDialog.setMessage("确认删除该通知？");
                customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoBack(true);
                        customDialog.dismiss();
                    }
                });
                customDialog.show();
            }
        });

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        if (null != bundle) {
            detail_title.setText(bundle.getString(NotificationCenterActivity.DETAIL_TITLE));
            detail_time.setText(bundle.getString(NotificationCenterActivity.DETAIL_TIME));
            detail_content.setText(bundle.getString(NotificationCenterActivity.DETAIL_CONTENT));
            detail_position = bundle.getInt(NotificationCenterActivity.DETAIL_POSITION);
        }
    }

    private void gotoBack(final boolean del) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putBoolean(NotificationCenterActivity.DETAIL_FINISH_RESULT, del);
        bundle.putInt(NotificationCenterActivity.DETAIL_POSITION, detail_position);
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);
        activityFinish();
    }

    private void getNotices(){
        String url = "http://192.168.1.110:8080/pprty/WSRest/service/notice/pagelist";
        HashMap<String,String> params = new HashMap<>();
        params.put("userGuid","EFB8D92EEA1542C39BB437201659DC1D");
        HttpUtils.getInstance().requestGet(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                NoticeDetailInfo noticeInDetailfo = gson.fromJson(result, NoticeDetailInfo.class);
                if(noticeInDetailfo != null){
                    m_datas = noticeInDetailfo.dataSource;
                }else{
                    // TODO: 2018/4/4 沒有詳細信息
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        }, CacheMode.DEFAULT);
    }
}
