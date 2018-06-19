package com.syberos.shuili.activity.work;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.google.gson.Gson;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.NoticeFormInfo;
import com.syberos.shuili.entity.NoticeInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationCenterActivity extends BaseActivity {

    private static final String TAG = NotificationCenterActivity.class.getSimpleName();
    private static final int SHOW_DETAIL_REQUEST_CODE = 1454;

    private static final int INDEX_TITLE = 0;
    private static final int INDEX_CONTENT = 1;
    private static final int INDEX_TIME = 2;

    static final String DETAIL_TITLE = "DETAIL_TITLE";
    static final String DETAIL_CONTENT = "DETAIL_CONTENT";
    static final String DETAIL_TIME = "DETAIL_TIME";
    static final String DETAIL_POSITION = "DETAIL_POSITION";
    static final String DETAIL_FINISH_RESULT = "DETAIL_FINISH_RESULT";


    private List<List<String>> notificationsList = null;
    private NotificationsListAdapter notificationsListAdapter = null;
    private DataSetObserver dataSetObserver = null;
    private List<NoticeInfo>datas;

    private int pageIndex = 1;

    enum DeleteType{
        DELETE_ONE,DELETE_ALL
    }

    @BindView(R.id.lv_all_notifications)
    ListView lv_all_notifications;

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
    }

    @Override
    public void initView() {
        setInitActionBar(false);
        tv_action_bar_title.setText("通知提醒");
        tv_action_bar_editStatus.setText("清空");
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
                        notificationsList.clear();
                        notificationsListAdapter.notifyDataSetChanged();
                        customDialog.dismiss();
                    }
                });
                customDialog.show();
            }
        });
        tv_action_bar_title.setGravity(Gravity.LEFT);
        notificationsListAdapter = new NotificationsListAdapter();
        dataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                tv_action_bar_editStatus.setEnabled(notificationsList.size() > 0);
            }
        };
        notificationsListAdapter.registerDataSetObserver(dataSetObserver);
        lv_all_notifications.setAdapter(notificationsListAdapter);


        // TODO: 18-3-19 下面仅是测试数据，需要使用真实数据
        if (null == notificationsList) {
            notificationsList = new ArrayList<>();
        } else {
            notificationsList.clear();
        }
        for (int i=0; i<10; ++i){
            List<String> tmpList = new ArrayList<>();
            tmpList.add("隐患整改确认");
            tmpList.add("内容内容内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容内容内容" +
                    "内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容" +
                    "内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容" +
                    "内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容" +
                    "内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容" +
                    "内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容" +
                    "内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容" +
                    "内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容" +
                    "内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容" +
                    "内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容" +
                    "内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容" +
                    "内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容" +
                    "内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容" +
                    "内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容" +
                    "内容内容内容内容内容内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容" +
                    "内容内内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容" +
                    "内容内容内容内容内容内容内容内容内容内容内容内容内内容内容内容内容内容内容内");
            tmpList.add("10:2" + String.valueOf(i));
            notificationsList.add(tmpList);
        }
    }
    private void deleteNotice(DeleteType type,List<String> noticeIds){
        String url = "http://192.168.1.110:8080/pprty/WSRest/service/notice/del_all";
        NoticeFormInfo formInfo = new NoticeFormInfo();
        formInfo.user_gid = "EFB8D92EEA1542C39BB437201659DC1D";
        if(type == DeleteType.DELETE_ALL)
        formInfo.all = true;
        else formInfo.all = false;
        formInfo.list = noticeIds;
        Gson gson = new Gson();
        String jsonStr = gson.toJson(formInfo);
        ToastUtils.show(jsonStr);
//        HttpsUtils.getInstance().requestNet_json(url, jsonStr, "", new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                ToastUtils.show(e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response) {
//                ToastUtils.show(response);
//            }
//        });
    }
    private void getNotices(){
        String url = "http://192.168.1.110:8080/pprty/WSRest/service/notice/pagelist";
        HashMap<String,String> params = new HashMap<>();
        params.put("userGuid","EFB8D92EEA1542C39BB437201659DC1D");
        params.put("page",String.valueOf(pageIndex));
//        HttpsUtils.getInstance().requestNet(url, params, TAG, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                NoticeInfo noticeInfo = gson.fromJson(response, NoticeInfo.class);
//                if(noticeInfo.dataSource.list!=null) {
//                    datas = noticeInfo.dataSource.list;
//                }else{
//                    // TODO: 2018/4/4 no data
//                }
//
//            }
//        });
    }
    private static class ViewHolder{
        TextView notificationTitle;
        TextView notificationContent;
        TextView notificationTime;
    }

    private class NotificationsListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return null == notificationsList ? 0 : notificationsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null == notificationsList ? null : notificationsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (null == notificationsList || notificationsList.size() <= 0) {
                return convertView;
            }

            ViewHolder holder;
            if (null == convertView) {
                convertView = LayoutInflater.from(NotificationCenterActivity.this).inflate(
                        R.layout.layout_notification_center_item, null);
                holder = new ViewHolder();
                holder.notificationTitle
                        = convertView.findViewById(R.id.tv_notification_title);
                holder.notificationContent
                        = convertView.findViewById(R.id.tv_notification_content);
                holder.notificationTime
                        = convertView.findViewById(R.id.tv_notification_time);
                convertView.setTag(holder);

                TextView del = convertView.findViewById(R.id.tv_delete);
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
                                notificationsList.remove(position);
                                notificationsListAdapter.notifyDataSetChanged();
                                customDialog.dismiss();
                            }
                        });
                        customDialog.show();
                    }
                });

                final SwipeLayout swipeLayout = convertView.findViewById(R.id.sl_notification);
                swipeLayout.setClickToClose(true);
                swipeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SwipeLayout.Status.Close == swipeLayout.getOpenStatus()) {

                            Bundle bundle = new Bundle();
                            bundle.putString(DETAIL_TITLE,
                                    notificationsList.get(position).get(INDEX_TITLE));
                            bundle.putString(DETAIL_CONTENT,
                                    notificationsList.get(position).get(INDEX_CONTENT));
                            bundle.putString(DETAIL_TIME,
                                    notificationsList.get(position).get(INDEX_TIME));
                            bundle.putInt(DETAIL_POSITION, position);
                            // 显示详情页
                            intentActivity(NotificationCenterActivity.this,
                                    NotificationDetailActivity.class, false, bundle,
                                    SHOW_DETAIL_REQUEST_CODE);

                        }
                    }
                });

            }  else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.notificationTitle.setText(notificationsList.get(position).get(INDEX_TITLE));
            holder.notificationContent.setText(notificationsList.get(position).get(INDEX_CONTENT));
            holder.notificationTime.setText(notificationsList.get(position).get(INDEX_TIME));
            return convertView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != notificationsListAdapter && null != dataSetObserver) {
            notificationsListAdapter.unregisterDataSetObserver(dataSetObserver);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SHOW_DETAIL_REQUEST_CODE && Activity.RESULT_OK == resultCode) {
            Bundle bundle = data.getExtras();
            if (null != bundle && bundle.getBoolean(DETAIL_FINISH_RESULT)) {
                notificationsList.remove(bundle.getInt(DETAIL_POSITION));
                notificationsListAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
