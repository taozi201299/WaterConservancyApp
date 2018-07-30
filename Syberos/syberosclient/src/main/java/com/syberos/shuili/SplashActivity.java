package com.syberos.shuili;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.syberos.shuili.activity.login.LoginActivity;
import com.syberos.shuili.utils.Singleton;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class SplashActivity extends Activity implements EasyPermissions.PermissionCallbacks {
    public static final String[] requestPermissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };
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
        }else{
            go2ActivityDelay();
        }
    }

    private void go2Activity(){
        // TODO: 2018/4/16 在本地修改此处，此处设计到用户的初始化
        if (Singleton.INSTANCE.isLogin) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
       }
        finish();
    }
    private void go2ActivityDelay(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
             go2Activity();
            }
        },2000);

    }
    /**
     * 请求多个权限
     *
     */
    public void requestMulti() {
        EasyPermissions.requestPermissions(this, "需要申请功能",
                RC_PERM, requestPermissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(requestCode == RC_PERM){
            go2Activity();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if(requestCode == RC_PERM){
            go2Activity();
        }
    }
}
