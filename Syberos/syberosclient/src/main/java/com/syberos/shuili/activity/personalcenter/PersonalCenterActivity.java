package com.syberos.shuili.activity.personalcenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.entity.RoleBaseInfo;
import com.syberos.shuili.entity.userinfo.RoleExtInfo;
import com.syberos.shuili.entity.userinfo.RoleExtInfoList;
import com.syberos.shuili.utils.LoginUtil;
import com.syberos.shuili.utils.Singleton;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.login.SendSMSCodeCountDownTimer;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.entity.userinfo.UserExtendInfo;
import com.syberos.shuili.listener.TextChangedListener;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人中心 不支持修改
 */
public class PersonalCenterActivity extends BaseActivity {

    private boolean editing = false;
    private Button btn_dialog_send_sms_code;
    private UserExtendInformation userInfo;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    @BindView(R.id.tv_action_bar_editStatus)
    TextView tv_action_bar_editStatus;

    @BindView(R.id.tv_username_show)
    TextView tv_username_show;

    @BindView(R.id.et_username_edit)
    EditText et_username_edit;

    @BindView(R.id.tv_phoneNumber_show)
    TextView tv_phoneNumber_show;

    @BindView(R.id.et_phoneNumber_edit)
    EditText et_phoneNumber_edit;

    @BindView(R.id.tv_email_show)
    TextView tv_email_show;

    @BindView(R.id.et_email_edit)
    EditText et_email_edit;

    @BindView(R.id.tv_unitName_show)
    TextView tv_unitName_show;

    @BindView(R.id.et_unitName_edit)
    EditText et_unitName_edit;

    @BindView(R.id.tv_departmentName_show)
    TextView tv_departmentName_show;

    @BindView(R.id.et_departmentName_edit)
    EditText et_departmentName_edit;

    @BindView(R.id.tv_positionName_show)
    TextView tv_positionName_show;

    @BindView(R.id.et_positionName_edit)
    EditText et_positionName_edit;

    @OnClick(R.id.iv_action_bar_back)
    void go2Back() {
        activityFinish();
    }

    @OnClick(R.id.tv_action_bar_editStatus)
    void onActionBarEditStatusTriggered() {
        if (editing) {
            String newPhoneNumber = et_phoneNumber_edit.getText().toString();
            if (Strings.isValidPhoneNum(newPhoneNumber)) {
                if (!Objects.equals(newPhoneNumber, userInfo.getPhone())) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.dialog_verify_phone_number, null);
                    TextView tv_dialog_phoneNumber = layout.findViewById(R.id.tv_dialog_phoneNumber);
                    tv_dialog_phoneNumber.setText(newPhoneNumber);
                    final ClearableEditText smsEdit = layout.findViewById(R.id.smsCodeEdit);
                    btn_dialog_send_sms_code = layout.findViewById(R.id.btn_dialog_send_sms_code);
                    btn_dialog_send_sms_code.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getVerfyCode();
                            SendSMSCodeCountDownTimer timer
                                    = new SendSMSCodeCountDownTimer(btn_dialog_send_sms_code,
                                    "重新获取");
                            timer.start();
                        }
                    });

                    final AlertDialog dialog = new AlertDialog.Builder(this).setView(layout)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Singleton.INSTANCE.smsCodeVerify(smsEdit.getText().toString())) {
                                        saveChange();
                                        editing = false;
                                        changeEditStatus(false);
                                        ToastUtils.show("修改成功");
                                    } else {
                                        ToastUtils.show("验证码不正确");
                                    }
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create();

                    smsEdit.setTextChangedListener(new TextChangedListener() {
                        @Override
                        public void onTextChanged(String text) {
                            if (null != dialog) {
                                if (!text.isEmpty()
                                        && text.length() == Singleton.INSTANCE.SMS_VERIFY_CODE_LENGTH) {
                                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                } else {
                                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                }
                            }
                        }
                    });

                    dialog.show();
                    // the follow code must after the dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    saveChange();
                    editing = false;
                    changeEditStatus(false);
                }
            } else {
                ToastUtils.show(getResources().getText(R.string.invalid_phone_number));
            }
        } else {
            editing = true;
            changeEditStatus(true);
        }
    }

    private void changeEditStatus(boolean isEdit) {
        tv_action_bar_title.setText(isEdit ? "编辑个人信息" : "个人信息");
        tv_action_bar_editStatus.setText(isEdit ? "完成" : "编辑");

        tv_username_show.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        tv_phoneNumber_show.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        tv_email_show.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        tv_unitName_show.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        tv_departmentName_show.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        tv_positionName_show.setVisibility(isEdit ? View.GONE : View.VISIBLE);

        et_username_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        et_phoneNumber_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        et_email_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        et_unitName_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        et_departmentName_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        et_positionName_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);
    }

    /**
     * 获取手机验证码
     */
    private void getVerfyCode(){

    }
    public static UserExtendInfo setUpdateInfo(UserExtendInformation userInfo){
        UserExtendInfo updateUserExtendInfo = new UserExtendInfo();
        updateUserExtendInfo.setProperty(0,userInfo.getAdmDutyLevel());
        updateUserExtendInfo.setProperty(1,userInfo.getDepCode());
        updateUserExtendInfo.setProperty(2,userInfo.getDepId());
        updateUserExtendInfo.setProperty(3,userInfo.getDepName());
        updateUserExtendInfo.setProperty(4,userInfo.getId());
        updateUserExtendInfo.setProperty(5,userInfo.getModifier());
        updateUserExtendInfo.setProperty(6,userInfo.getNote());
        updateUserExtendInfo.setProperty(7,userInfo.getOrgCode());
        updateUserExtendInfo.setProperty(8,userInfo.getOrgId());
        updateUserExtendInfo.setProperty(9,userInfo.getOrgName());
        updateUserExtendInfo.setProperty(10,userInfo.getPassword());
        updateUserExtendInfo.setProperty(11,userInfo.getPersId());
        updateUserExtendInfo.setProperty(12,userInfo.getPersName());
        updateUserExtendInfo.setProperty(13,userInfo.getPersType());
        updateUserExtendInfo.setProperty(14,userInfo.getPhone());
        RoleExtInfoList list = new RoleExtInfoList();
        ArrayList<RoleBaseInfo> roles = LoginUtil.getRoleList();
        if(roles != null){
            for(RoleBaseInfo info : roles){
                RoleExtInfo roleInfo = new RoleExtInfo();
                roleInfo.setProperty(0,info.getRoleId());
                roleInfo.setProperty(1,info.getRoleCode());
                roleInfo.setProperty(2,info.getRoleName());
                roleInfo.setProperty(3,info.getRoleDesc());
                roleInfo.setProperty(4,info.getRoleType());
                roleInfo.setProperty(5,"");
                roleInfo.setProperty(6,"");
                roleInfo.setProperty(7,info.getStatus());
                roleInfo.setProperty(8,info.getModifier());
                roleInfo.setProperty(9,"");
                roleInfo.setProperty(10,info.getTs());
                roleInfo.setProperty(11,"");
                list.setProperty(0,roleInfo);
                if(list.size() == 1)break;
            }
        }
        updateUserExtendInfo.setProperty(15,list);
        updateUserExtendInfo.setProperty(16,userInfo.getStatus());
        updateUserExtendInfo.setProperty(17,userInfo.getTs());
        updateUserExtendInfo.setProperty(18,userInfo.getUserCode());
        updateUserExtendInfo.setProperty(19,userInfo.getUserName());
        updateUserExtendInfo.setProperty(20,userInfo.getUserType());
        return updateUserExtendInfo;

    }
    private void saveChange() {
        userInfo.setUserName(et_username_edit.getText().toString());
        userInfo.setPhone(et_phoneNumber_edit.getText().toString());
        userInfo.setOrgName(et_unitName_edit.getText().toString());
        userInfo.setDepName(et_departmentName_edit.getText().toString());

        SyberosManagerImpl.getInstance().setCurrentUserInfo(userInfo);
        UserExtendInfo updateUserExtendInfo = setUpdateInfo(userInfo);
        updateUserInfo(updateUserExtendInfo);
        updateShowWidgets();
    }

    private void updateUserInfo(final UserExtendInfo userExtendInfo){
        String methodName = "updateUser";
        SyberosManagerImpl.getInstance().updateUserInfo(userExtendInfo, methodName, new RequestCallback<UserExtendInfo>() {
            @Override
            public void onResponse(UserExtendInfo result) {
                ToastUtils.show("修改成功");
                // TODO: 2018/6/4 修改本地信息

            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                // TODO: 2018/6/4 回滾 

            }
        });

    }
    private void updateShowWidgets() {
        tv_username_show.setText(userInfo.getPersName());
        tv_phoneNumber_show.setText(userInfo.getPhone());
        tv_email_show.setText("");
        tv_unitName_show.setText(userInfo.getOrgName());
        tv_departmentName_show.setText(userInfo.getDepName());
        tv_positionName_show.setText("");
    }

    private void updateEditWidgets() {
        et_username_edit.setText(userInfo.getPersName());
        et_phoneNumber_edit.setText(userInfo.getPhone());
        et_email_edit.setText("");
        et_unitName_edit.setText(userInfo.getOrgName());
        et_departmentName_edit.setText(userInfo.getDepName());
        et_positionName_edit.setText("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_center;
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
        userInfo = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        tv_action_bar_editStatus.setVisibility(View.GONE);

        tv_action_bar_title.setText("个人信息");
        tv_action_bar_editStatus.setText("编辑");

        updateShowWidgets();
        updateEditWidgets();
    }
}
