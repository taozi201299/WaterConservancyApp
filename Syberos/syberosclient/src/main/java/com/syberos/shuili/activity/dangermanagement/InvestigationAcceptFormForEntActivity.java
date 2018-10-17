package com.syberos.shuili.activity.dangermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.App;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.TodoWorkInfo;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by jidan on 18-3-23.
 */

public class InvestigationAcceptFormForEntActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    @BindView(R.id.tv_accept_person)
    TextView tv_accept_person;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_RejectBtn)
    TextView tv_RejectBtn;
    @BindView(R.id.tv_commitBtn)
    TextView tv_commitBtn;
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;
    @BindView(R.id.et_accept_desc)
    AudioEditView et_accept_desc;
    ObjHidden investigationInfo;
    TodoWorkInfo todoWorkInfo;
    String type ;  // 0 生成代办 1 修改代办
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_accept_form;
    }

    @Override
    public void initListener() {
        tv_commitBtn.setOnClickListener(this);
        tv_RejectBtn.setOnClickListener(this);
        rl_time.setOnClickListener(this);


    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        investigationInfo = (ObjHidden) bundle.getSerializable("data");
        type = bundle.getString("type");
        if("1".equalsIgnoreCase(type)){
            todoWorkInfo = (TodoWorkInfo) bundle.getSerializable("TodoWorkInfo");
        }
        UserExtendInformation userExtendInformation = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        if(userExtendInformation != null){
            tv_accept_person.setText(userExtendInformation.getPersName());
        }
    }

    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle(InvestigationAccepTaskActivity.Title);
        setActionBarRightVisible(View.INVISIBLE);
        tv_time.setText(CommonUtils.getCurrentDate());

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,requestCode,data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_time:
                setTime();
                break;
            case R.id.tv_RejectBtn:
                submit(0);
                break;
            case R.id.tv_commitBtn:
                submit(1);
                if("1".equalsIgnoreCase(type)) {
                    updateTodoTask();
                }else if("0".equalsIgnoreCase(type)){
                    queryTodoWork();
                }
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

    private boolean checkParam(){
        boolean bRet = false;
        if(et_accept_desc.getEditText() == null || et_accept_desc.getEditText().isEmpty()){
            ToastUtils.show("销号描述不能为空");
            return  bRet;
        }
        return  true;
    }
    /**
     * 0 通过 1 不通过
     * @param type
     */
    private void submit(int type){
        if(!checkParam())return;
        showDataLoadingDialog();
        String url = GlobleConstants.strCJIP +"/cjapi/cj/bis/hidd/rectAcce/addObjHiddRectAcce";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());//隐患GUID
        params.put("accepPers",tv_accept_person.getText().toString()); // 验收人
        params.put("accepDate",tv_time.getText().toString()); // 验收时间
        params.put("accepOpin",et_accept_desc.getEditText()); //验收意见
        params.put("note","移动端接口测试");  //备注
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  ll_multimedia.getBinaryFile();

        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                String time = CommonUtils.getCurrentDateYMD();
                info.medPath = "hidden/BIS_HIDD_RECT_ACCE/"+time+ "/"+info.medName;
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                File file = new File(item.localFile.getPath());
                info.localPath = item.localFile.getPath();
                info.medSize = file.length();
                info.bisTableName = "BIS_HIDD_RECT_ACCE";
                info.bisGuid = investigationInfo.getGuid();
                info.localStatus = "1";
                if(item.type == MultimediaView.LocalAttachmentType.IMAGE){
                    info.medType = "0";
                }else {
                    info.medType = "1";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachMentInfoEntities.add(info);
            }
        }
        SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities,new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                ToastUtils.show("提交成功");
                finish();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });

    }

    private void updateTodoTask(){
        String url = GlobleConstants.strIP + "/pprty/WSRest/service/backlog";
        HashMap<String,String>params = new HashMap<>();
        params.put("appCode",App.sCode);
        params.put("busiCode",investigationInfo.getGuid());
        params.put("busiName",todoWorkInfo.getBusiName());
        params.put("busiUrl",todoWorkInfo.getBusiUrl());
        params.put("userGuid",todoWorkInfo.getUserGuid());
        params.put("isread","1");
        params.put("roleCode",todoWorkInfo.getRoleCode());
        params.put("modName",todoWorkInfo.getModName());
        params.put("orgGuid",todoWorkInfo.getOrgGuid());
        params.put("tableName",todoWorkInfo.getTableName());
        params.put("nextStep",todoWorkInfo.getNextStep());
        HttpUtils.getInstance().requestNet_post(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
    private void createTodoTask(){
        String url = GlobleConstants.strIP + "/pprty/WSRest/service/backlog";
        HashMap<String,String>params = new HashMap<>();
        params.put("appCode",App.sCode);
        params.put("busiCode",investigationInfo.getGuid());
        params.put("busiName","隐患销号");
        params.put("busiUrl","");
        params.put("userGuid",SyberosManagerImpl.getInstance().getCurrentUserId());
        params.put("isread","1");
        params.put("roleCode","");
        params.put("modName",GlobleConstants.hidd);
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("tableName","");
        params.put("nextStep","BIS_HIDD_RECT_ACCE");
        HttpUtils.getInstance().requestNet_post(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });

    }
    private void queryTodoWork(){
        String url = GlobleConstants.strIP + "/pprty/WSRest/service/backlog";
        HashMap<String,String> params = new HashMap<>();
        params.put("busiCode",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                todoWorkInfo = gson.fromJson(result,TodoWorkInfo.class);
                if(todoWorkInfo == null || todoWorkInfo.dataSource == null || todoWorkInfo.dataSource.list.size() == 0){
                  createTodoTask();
                }else {
                    todoWorkInfo = todoWorkInfo.dataSource.list.get(0);
                    updateTodoTask();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
              //  ToastUtils.show("创建代办任务失败");

            }
        });

    }
}
