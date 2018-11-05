package com.syberos.shuili.activity.dangermanagement;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.entity.hidden.HiddenSupervice;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by Administrator on 2018/4/16.
 */

public class InvestigationSuperviceFormActivity extends BaseActivity implements View.OnClickListener,BaseActivity.IDialogInterface {
    @BindView(R.id.tv_supervise_code)
    TextView tv_supervise_code;
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.rg_supervice_listing)
    RadioGroup rg_supervice_listing;
    @BindView(R.id.rb_supervice_listing_yes)
    RadioButton rb_supervice_listing_yes;
    @BindView(R.id.rb_supervice_listing_no)
    RadioButton rb_supervice_listing_no;
    @BindView(R.id.et_supervise_desc)
    AudioEditView et_supervise_desc;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    @BindView(R.id.tv_time_label)
    TextView tv_time_label;

    HiddenSupervice hiddenInvestigationTaskInfo;


    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_supervise_submit_form;
    }

    @Override
    public void initListener() {
        rl_time.setOnClickListener(this);
        ll_commit.setOnClickListener(this);
        setDialogInterface(this);

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        hiddenInvestigationTaskInfo = (HiddenSupervice) bundle.getSerializable("data");
        tv_time.setText(CommonUtils.getCurrentDate());
    }

    @Override
    public void initView() {
        showTitle("隐患督办");
        setInitActionBar(true);
        setActionBarRightVisible(View.INVISIBLE);
        setFinishOnBackKeyDown(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                commit();
                break;
            case R.id.rl_time:
                setTime();
                break;
        }

    }
    private void setTime(){
        //时间选择器
        boolean[] type = {true, true, true, true, true, true};

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() > System.currentTimeMillis()) {
                    ToastUtils.show("提示：所选时间不应大于系统当前时间");
                    return;
                }
                tv_time.setText(Strings.formatDatetime(date));
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message,0);
    }

    /**
     * 隐患督办 生成消息
     */
    private void submitForm(){
        showDataLoadingDialog();
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/maj/bisMajHiddSup/";
        HashMap<String,String>params = new HashMap<>();
        // 隐患GUID
        params.put("hiddGuid",hiddenInvestigationTaskInfo.getGuid());
        //是否挂牌督办
        if(rg_supervice_listing.getCheckedRadioButtonId() == R.id.rb_accident_liability_yes) {
            params.put("isList", "1");
        }else{
            params.put("isList","2");
        }
        //整改期限
        params.put("rectPeri",tv_time.getText().toString());
        // 督办文号
        params.put("supLareId",tv_supervise_code.getText().toString());
        //督办日期
        params.put("supDate",CommonUtils.getCurrentDate());
        //督办单位
        params.put("supWiunCode",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        //督办负责人
        params.put("supLegPers",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());
        //督办意见
        params.put("supOpin",et_supervise_desc.getEditText());
        //备注
        params.put("note","移动客户端测试数据");
        //采集时间
        params.put("collTime",CommonUtils.getCurrentDate());
        //更新时间
        params.put("updTime","");
        //记录人员
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());

        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        localCacheEntity.type = 0;
        localCacheEntity.params = params;
        localCacheEntity.commitType = 0;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                // 生成督办消息
                sendMessage();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        });

    }
    @Override
    public void dialogClick() {
        submitForm();
    }

    @Override
    public void dialogCancel() {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    InvestigationSuperviceFormActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前内容未提交，确定退出？");
            customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activityFinish();
                    customDialog.dismiss();
                }
            });
            customDialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void sendMessage(){
        String url = GlobleConstants.strZJIP + "/pprty/WSRest/service/notice";
        HashMap<String,String>params = new HashMap<>();
        params.put("noticeCode","");
        params.put("noticeTitle",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName() + "隐患督办信息");
        params.put("noticeContent",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName() + "隐患督办信息");
        params.put("appCode", App.sCodes.get(0));
        params.put("fromDate",CommonUtils.getCurrentDateYMD());
        params.put("userGuid","");
        params.put("toDate",CommonUtils.getCurrentDateYMD());
        params.put("roleCode","");
        params.put("orgGuid",hiddenInvestigationTaskInfo.getOrgGuid());
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        localCacheEntity.type = 0;
        localCacheEntity.params = params;
        localCacheEntity.commitType = 0;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                ToastUtils.show("提交成功");
                activityFinish();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });

    }

}
