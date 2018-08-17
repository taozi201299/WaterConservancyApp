package com.syberos.shuili;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.syberos.shuili.activity.login.LoginActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.service.SyberosAidlClient;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.Singleton;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public class SplashActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    public static final String[] requestPermissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    private static final int RC_PERM = 110;
    private static final String TAG = SplashActivity.class.getSimpleName();
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestMulti();
        } else {
            go2ActivityDelay();
        }
    }

    private void go2Activity() {
        // TODO: 2018/4/16 在本地修改此处，此处设计到用户的初始化
        String type = App.userType;
        if("-1".equals(type)){
            if(!SPUtils.get("pwd","").toString().isEmpty()) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }else {
                go2MessageActivity();
            }
        }else {
            if("0".equals(type)){
                startActivity(new Intent(SplashActivity.this, MainEnterpriseActivity.class));
            }else if("1".equals(type)){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }
        finish();
    }
    private void go2ActivityDelay() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                go2Activity();
            }
        }, 2000);

    }

    /**
     * 请求多个权限
     */
    public void requestMulti() {
        EasyPermissions.requestPermissions(this, "需要申请功能", RC_PERM, requestPermissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == RC_PERM) {
            if(perms.size() == requestPermissions.length) {
                go2Activity();
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_PERM) {
            boolean bShowSetting = false;
            StringBuffer sb = new StringBuffer();
            for (String str : perms) {

                if (EasyPermissions.permissionPermanentlyDenied(this, str)) {
                    bShowSetting = true;
                }
                sb.append(str);
                sb.append("\n");
            }
            sb.replace(sb.length() - 2, sb.length(), "");
            if (bShowSetting) {
                new AppSettingsDialog
                        .Builder(this)
                        .setTitle("权限设置")
                        .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                        .setPositiveButton("是")
                        .setNegativeButton("否")
                        .build()
                        .show();
            }
        }

    }
    private void go2MessageActivity(){
        startActivity(new Intent(SplashActivity.this, MainUnLoginActivity.class));
    }
}
