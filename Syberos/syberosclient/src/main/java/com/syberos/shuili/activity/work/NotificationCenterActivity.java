package com.syberos.shuili.activity.work;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.NoticeFormInfo;
import com.syberos.shuili.entity.NoticeInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.PullRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.syberos.shuili.activity.work.NotificationCenterActivity.DeleteType.DELETE_ALL;
import static com.syberos.shuili.activity.work.NotificationCenterActivity.DeleteType.DELETE_ONE;
import static com.syberos.shuili.config.GlobleConstants.strCJIP;
import static com.syberos.shuili.config.GlobleConstants.strZJIP;

public class NotificationCenterActivity extends BaseActivity implements CommonAdapter.OnItemClickListener {

    private static final String TAG = NotificationCenterActivity.class.getSimpleName();

    private NotificationsListAdapter notificationsListAdapter = null;
    private RecyclerView.AdapterDataObserver dataSetObserver = null;
    private List<NoticeInfo>datas = new ArrayList<>();
    private void clearData(){
        datas.clear();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearData();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("msgInfo",datas.get(position));
        intentActivity(NotificationCenterActivity.this,NotificationDetailActivity.class,false,bundle);
    }

    enum DeleteType{
        DELETE_ONE,DELETE_ALL
    }

    @BindView(R.id.lv_all_notifications)
    RecyclerView lv_all_notifications;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    @BindView(R.id.tv_action_bar_editStatus)
    TextView tv_action_bar_editStatus;

    @OnClick(R.id.iv_action_bar_back)
    void go2back() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notification_center;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getNotices();
    }

    @Override
    public void initView() {
        setInitActionBar(false);
        tv_action_bar_title.setText("通知提醒");
        tv_action_bar_editStatus.setText("清空");
        tv_action_bar_editStatus.setVisibility(View.GONE);
        tv_action_bar_editStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog customDialog
                        = new CustomDialog(NotificationCenterActivity.this);
                customDialog.setDialogMessage("消息管理", null, null);
                customDialog.setMessage("确认清空通知列表？");
                customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notificationsListAdapter.notifyDataSetChanged();
                        customDialog.dismiss();
                        ArrayList<String>ids = new ArrayList<>();
                        for(NoticeInfo info : datas){
                            ids.add(info.getGuid());
                        }
                        deleteNotice(DELETE_ALL,ids);
                    }
                });
                customDialog.show();
            }
        });
        tv_action_bar_title.setGravity(Gravity.LEFT);
        notificationsListAdapter = new NotificationsListAdapter(this,R.layout.layout_notification_center_item,datas);
        lv_all_notifications.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        lv_all_notifications.setLayoutManager(new LinearLayoutManager(mContext));
        lv_all_notifications.setAdapter(notificationsListAdapter);
        dataSetObserver = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                tv_action_bar_editStatus.setEnabled(datas.size() > 0);
            }
        };
        notificationsListAdapter.registerAdapterDataObserver(dataSetObserver);
        notificationsListAdapter.setOnItemClickListener(this);
    }
    private void deleteNotice(DeleteType type,List<String> noticeIds){
        showDataLoadingDialog();
        String url = strZJIP+"/pprty/WSRest/service/notice/del_all";
        NoticeFormInfo formInfo = new NoticeFormInfo();
        formInfo.orgGuid = SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId();
        if(type == DeleteType.DELETE_ALL)
        formInfo.all = true;
        else formInfo.all = false;
        formInfo.list = noticeIds;
        Gson gson = new Gson();
        String jsonStr = gson.toJson(formInfo);
        OkHttpUtils.delete().url(url).requestBody(RequestBody.create(MediaType.parse("application/json"),jsonStr))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                closeDataDialog();
                ToastUtils.show("消息删除失败");
            }

            @Override
            public void onResponse(String response, int id) {
                closeDataDialog();
                ToastUtils.show("消息删除成功");
            }
        });
    }
    private void getNotices(){
        String url = strZJIP+"/pprty/WSRest/service/notice/pagelist";
        HashMap<String,String> params = new HashMap<>();
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, TAG, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                NoticeInfo noticeInfo = gson.fromJson(result, NoticeInfo.class);
                if(noticeInfo.dataSource.list!=null) {
                    datas = noticeInfo.dataSource.list;
                }
                if(datas.size() == 0){
                    ToastUtils.show("没有更多消息了");
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
        notificationsListAdapter.setData(datas);
        notificationsListAdapter.notifyDataSetChanged();
    }
    private class NotificationsListAdapter extends CommonAdapter<NoticeInfo> {
        public NotificationsListAdapter(Context context, int layoutId, List<NoticeInfo> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final NoticeInfo noticeInfo) {
            ((TextView)holder.getView(R.id.tv_notification_title)).setText(noticeInfo.getNoticeTitle());
            ((TextView)holder.getView(R.id.tv_notification_time)).setText(noticeInfo.getFromDate());
            if("0".equals(noticeInfo.getIsRead())){
                holder.getView(R.id.iv_dot).setVisibility(View.VISIBLE);
            }else {
                holder.getView(R.id.iv_dot).setVisibility(View.INVISIBLE);
            }
            TextView del = holder.getView(R.id.tv_delete);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CustomDialog customDialog
                            = new CustomDialog(NotificationCenterActivity.this);
                    customDialog.setDialogMessage("消息管理", null, null);
                    customDialog.setMessage("确认删除该通知？");
                    customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArrayList<String> ids = new ArrayList<>();
                            ids.add(noticeInfo.getGuid());
                            deleteNotice(DELETE_ONE,ids);
                            customDialog.dismiss();
                        }
                    });
                    customDialog.show();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != notificationsListAdapter && null != dataSetObserver) {
            notificationsListAdapter.unregisterAdapterDataObserver(dataSetObserver);
        }
    }
}
