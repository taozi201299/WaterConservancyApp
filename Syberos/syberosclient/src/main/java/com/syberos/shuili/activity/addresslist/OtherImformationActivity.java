package com.syberos.shuili.activity.addresslist;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.utils.BitmapUtil;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.TextDrawable;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by songting on 16-3-17.
 */
public class OtherImformationActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private static final String TAG = OtherImformationActivity.class.getSimpleName();
    @BindView(R.id.iv_myIcon)
    ImageView ivMyIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_myDepartment)
    TextView tvMyDepartment;
    @BindView(R.id.tv_myJob)
    TextView tvMyJob;
    @BindView(R.id.tv_phone_content)
    TextView tvPhoneContent;
    @BindView(R.id.iv_phone_call)
    ImageView ivPhoneCall;
    @BindView(R.id.iv_phone_email)
    ImageView ivPhoneEmail;
    @BindView(R.id.tv_myEmail)
    TextView tvMyEmail;
    @BindView(R.id.iv_sendEmail)
    ImageView ivSendEmail;
    @BindView(R.id.tv_sex)
    TextView tvSex;

    private UserExtendInformation userInformation;
    private Bundle bundle;
    String fromId;


    private static final int RC_CALL_PERM = 125;
    @Override
    public int getLayoutId() {
        return R.layout.activity_otherimformation;
    }

    public void initData() {
        bundle = getIntent().getExtras().getBundle(Strings.DEFAULT_BUNDLE_NAME);
        userInformation = (UserExtendInformation) bundle.getSerializable("userInfo");
        setData(userInformation);
        fromId = userInformation.getPersId();
    }

    @Override
    public void initView() {
        showTitle("个人信息");
        setActionBarRightVisible(View.INVISIBLE);
    }

    public void initListener() {
        setInitActionBar(true);
        ivPhoneCall.setOnClickListener(this);
        ivPhoneEmail.setOnClickListener(this);
        ivSendEmail.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 加载用户icon
     *
     * @param image
     * @param userInformation
     */
    private void loadIcon(final ImageView image, final UserExtendInformation userInformation) {
        if (TextUtils.isEmpty(userInformation.getIconUrl())) {
            if (TextUtils.isEmpty(userInformation.getUserName())) {
                userInformation.setUserName("未知");
            }

            TextDrawable textDrawable = BitmapUtil.drawableFactory(getApplicationContext(), userInformation, 25);
            image.setImageDrawable(textDrawable);
            return;
        }

        Glide.with(this).load(userInformation.getIconUrl()).asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(new BitmapImageViewTarget(image) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                image.setImageDrawable(circularBitmapDrawable);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                TextDrawable textDrawable = BitmapUtil.drawableFactory(getApplicationContext(), userInformation, 25);
                image.setImageDrawable(textDrawable);
                Bitmap bitmap = BitmapUtil.getImageBitmap(image);
                BitmapUtil.saveBitmap(bitmap, userInformation.getIconUrl(), true);
            }
        });
    }

    /**
     * 填充数据
     *
     * @param userInformation
     */
    private void setData(UserExtendInformation userInformation) {
        if (TextUtils.isEmpty(userInformation.getPhone())) {
            ivPhoneEmail.setVisibility(View.GONE);
            ivPhoneCall.setVisibility(View.GONE);
        } else {
            ivPhoneCall.setVisibility(View.VISIBLE);
            ivPhoneEmail.setVisibility(View.VISIBLE);
        }
        loadIcon(ivMyIcon, userInformation);

        tvName.setText(userInformation.getUserName());
        tvMyDepartment.setText(userInformation.getDepName());
        tvMyJob.setText("");
        tvPhoneContent.setText(userInformation.getPhone());
        tvMyEmail.setText("");
        if (!TextUtils.isEmpty(userInformation.getSex())) {

            if (userInformation.getSex().equals("male")) {
                tvSex.setText("男");
            } else if (userInformation.getSex().equals("female")) {
                tvSex.setText("女");

            } else if (userInformation.getSex().equals("undisclosed")) {
                tvSex.setText("未知");
            } else {
                tvSex.setText(userInformation.getSex());
            }
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_myIcon:
                break;
            case R.id.iv_phone_call:
                requestCallPermission();
                break;
            case R.id.iv_phone_email:
                sendPhoneEmail();
                break;
            case R.id.iv_sendEmail:
                sendEmail("");
                break;
        }
    }
    @AfterPermissionGranted(RC_CALL_PERM)
    private void requestCallPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
            callPhone();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "app需要使用系统录音和相机功能",
                    RC_CALL_PERM, Manifest.permission.CALL_PHONE);
        }

    }
    private void sendPhoneEmail(){
        Uri uri = Uri.parse("smsto:"+tvPhoneContent.getText().toString());
        Intent intentMessage = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intentMessage);

    }
    /**
     * 调用系统邮件
     */
    private void sendEmail(String email) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:" + email));
        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
        startActivity(data);
    }

    /**
     * 拨打电话
     */
    private void callPhone() {
        String phoneNO = tvPhoneContent.getText().toString().trim();
        LogUtils.d("电话号码", phoneNO);
        // 如果输入不为空创建打电话的Intent
        if (!TextUtils.isEmpty(phoneNO)) {
            Intent phoneIntent = new Intent(
                    "android.intent.action.CALL", Uri.parse("tel:"
                    + phoneNO));
            startActivity(phoneIntent);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).setRationale("没有请求权限，此应用程序可能无法正常工作。跳到设置打开应用程序以修改应用程序权限").setTitle("权限请求").build().show();
        }
    }

}
