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
import com.syberos.shuili.entity.NoticeFormInfo;
import com.syberos.shuili.entity.NoticeInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.syberos.shuili.config.GlobleConstants.strCJIP;

public class NotificationDetailActivity extends BaseActivity {

    private final String TAG = NotificationDetailActivity.class.getSimpleName();

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

    private NoticeInfo noticeInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notification_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        noticeInfo = (NoticeInfo) bundle.getSerializable("msgInfo");
        if (null != bundle) {
            detail_title.setText(noticeInfo.getNoticeTitle());
            detail_time.setText(noticeInfo.getFromDate());
            detail_content.setText(noticeInfo.getNoticeContent());
        }
    }

    @Override
    public void initView() {
        setInitActionBar(false);
        tv_action_bar2_title.setText("通知详情");
        tv_action_bar2_title.setGravity(Gravity.LEFT);
        iv_action_bar2_right.setImageResource(R.mipmap.icon_delete);
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
                        customDialog.dismiss();
                        deleteNotice();
                    }
                });
                customDialog.show();
            }
        });
    }
    private void deleteNotice(){
        showDataLoadingDialog();
        ArrayList<String>noticeIds = new ArrayList<>();
        noticeIds.add(noticeInfo.getGuid());
        String url = strCJIP+"/pprty/WSRest/service/notice/del_all";
        NoticeFormInfo formInfo = new NoticeFormInfo();
        formInfo.userGuid = "4444444444446774444             ";
        formInfo.all = false;
        formInfo.list = noticeIds;
        Gson gson = new Gson();
        String jsonStr = gson.toJson(formInfo);
        ToastUtils.show(jsonStr);

        OkHttpUtils.delete().url(url).requestBody(jsonStr).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                closeDataDialog();
                ToastUtils.show("消息删除失败");
            }

            @Override
            public void onResponse(String response, int id) {
                closeDataDialog();
                ToastUtils.show("消息删除成功");
                activityFinish();
            }
        });
    }

}
