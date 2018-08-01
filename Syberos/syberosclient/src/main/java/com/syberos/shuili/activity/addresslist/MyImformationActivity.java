package com.syberos.shuili.activity.addresslist;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.syberos.shuili.entity.userinfo.UserExtendInfo;
import com.syberos.shuili.utils.BitmapUtil;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.TextDrawable;
import com.syberos.shuili.view.indexListView.ClearEditText;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


/**
 * Created by songting on 16-3-17.
 */
public class MyImformationActivity extends BaseActivity {


    private static final String TAG = MyImformationActivity.class.getSimpleName();
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


    private ClearEditText etContent;
    private File mTmpFile;

    private String iconUrl;
    private UserExtendInfo userInformation;

    public static String path = "/sdcard/";// sd路径
    public static String user_photo = "syberos_user.jpg";
    public static final int MY_PERMISSIONS_REQUEST_CALL_CAMERA = 1;

    private int genderIndex = 0;
    String [] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    List<String> mPermissionList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_myinformation;
    }

    public void initData() {
        Bundle bundle = getIntent().getExtras().getBundle(Strings.DEFAULT_BUNDLE_NAME);
        userInformation = (UserExtendInfo) bundle.getSerializable("userInfo");
        setData(userInformation);
        if("female".equals(userInformation.getSex())){
            genderIndex = 1;
        }else{
            genderIndex = 0;
        }
    }

    @Override
    public void initView() {
        showTitle("个人信息");
        setActionBarRightVisible(View.INVISIBLE);
        ivPhoneCall.setVisibility(View.GONE);
        ivPhoneEmail.setVisibility(View.GONE);

    }

    /**
     * 加载用户icon
     *
     * @param image
     * @param userInformation
     */
    private void loadIcon(final ImageView image, final UserExtendInfo userInformation) {
        if (TextUtils.isEmpty(userInformation.getIconUrl())) {
            if (TextUtils.isEmpty(userInformation.getUserName())) {
                userInformation.setUserName("未知");
            }

            TextDrawable textDrawable = BitmapUtil.drawableFactory(this, userInformation, 25);
            image.setImageDrawable(textDrawable);
            return;
        }
        Glide.with(this).load(userInformation.getIconUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).centerCrop().into(new BitmapImageViewTarget(image) {
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
                image.buildDrawingCache();
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
    private void setData(UserExtendInfo userInformation) {
        loadIcon(ivMyIcon, userInformation);
        LogUtils.d("图像url", userInformation.getIconUrl());
        tvName.setText(userInformation.getUserName());
        tvMyDepartment.setText(userInformation.getDepName());
        tvMyJob.setText("");
        tvPhoneContent.setText(userInformation.getPhone());
        if (TextUtils.isEmpty(userInformation.getNote())) {
            tvMyEmail.setText("未填写");
        } else {
            tvMyEmail.setText(userInformation.getNote());
        }

        if (userInformation.getSex().equals("male")) {
            tvSex.setText("男");
        } else if (userInformation.getSex().equals("female")) {
            tvSex.setText("女");
        } else {
            tvSex.setText("未知");
        }
    }
    public void initListener() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
