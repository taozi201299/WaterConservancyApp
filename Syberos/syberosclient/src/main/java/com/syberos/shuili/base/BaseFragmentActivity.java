package com.syberos.shuili.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDialog;
import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.clippingtransforms.WavesClippingTransform;
import com.github.jorgecastillo.listener.OnStateChangeListener;

import com.syberos.shuili.R;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.config.Paths;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.ScreenManager;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import butterknife.ButterKnife;


public abstract class BaseFragmentActivity extends AppCompatActivity {
    public Context mContext;
    // 是否允许全屏
    private boolean mAllowFullScreen = true;
    private Dialog dialog;
    private LoadingDialog dataDialog;
    private boolean finishOnBackKeyDown = true;

    protected boolean useThemestatusBarColor = true;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useStatusBarColor = true;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        mContext = this;
        ScreenManager.getScreenManager().pushActivity(this);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(getLayoutId());
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ButterKnife.bind(this);
        setStatusBar();
        initView();
        initListener();



    }

    public abstract int getLayoutId();
    /**
     * 设置监听事件
     */
    public abstract void initListener() ;
    /**
     * 记载数据
     */
    public abstract void initData() ;
    public abstract void initView();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            setIntent(intent);
            mContext = this;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        closeDialog();
        ScreenManager.getScreenManager().popActivity(this);
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.button_login_normal));
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !useStatusBarColor ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    /**
     * 隐藏软件盘
     * @param activity
     */
    public static void hideSoftInput(Activity activity){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * 弹出软件盘
     * @param et
     * @param context
     */
    public static void showSoftInput(EditText et,Context context){
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, 0);

    }

    /**
     * 加载对话框
     */
    public void showLoadingDialog(String content) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_login, null);
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(CommonUtils.getWindowDisplay().getWidth());
        view.setMinimumHeight(CommonUtils.dip2px(80));
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvContent.setText(content);
        final FillableLoader fillableLoader = (FillableLoader) view.findViewById(R.id.fillableLoader);

        int viewSize = getResources().getDimensionPixelSize(R.dimen.fourthSampleViewSize);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize, viewSize);
        params.gravity = Gravity.CENTER;
        dialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialog.show();

        fillableLoader.setSvgPath(Paths.YUANXIN);
        fillableLoader.setStrokeDrawingDuration(1000);
        fillableLoader.setFillDuration(1000);
        fillableLoader.setClippingTransform(new WavesClippingTransform());
        fillableLoader.postDelayed(new Runnable() {
            @Override
            public void run() {
                fillableLoader.start();
            }
        }, 250);
        fillableLoader.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if(state == State.FINISHED)
                {
                    fillableLoader.reset();
                    fillableLoader.start();
                }
            }
        });
    }
    public void showDataLoadingDialog(String content){
        LoadingDialog.Builder loadBuilder=new LoadingDialog.Builder(this)
                .setMessage(content)
                .setCancelable(true)
                .setCancelOutside(true);
        LoadingDialog dialog=loadBuilder.create();
        dialog.show();
    }
    public void showDataLoadingDialog(){
        LoadingDialog.Builder loadBuilder=new LoadingDialog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dataDialog=loadBuilder.create();
        dataDialog.show();

    }
    public void closeDialog(){

        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }

    }
    public void closeDataDialog(){
        if (dataDialog != null && dataDialog.isShowing()) {
            dataDialog.dismiss();
        }

    }
    /**
     * 获取sp保存在本地得数据
     * @param key
     * @param defaultObject
     * @return
     */
    public Object getSpData(String key,Object defaultObject){
        return SPUtils.get(key, defaultObject);
    }
    /**
     * 将数据保存在sp本地
     * @param key
     * @param data
     */
    public void putSpData(String key,Object data){
        SPUtils.put(key, data);
    }
    /**
     * 显示toast信息
     * @param msg
     */
    public void showToast(String msg){
        ToastUtils.show(msg);
    }


    /**
     * activity跳转
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void   intentActivity(Activity activity,Class<? extends Activity> targetActivity,boolean isfinish,boolean isAnim){
        Intent intent = new Intent(activity,targetActivity);
        activity.startActivity(intent);
        if(isAnim){
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            if(isfinish){
                activity.finish();
            }
        }else{
            if(isfinish){
                finish();
            }
        }
    }
    /**
     * activity跳转
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void   intentActivity(Activity activity,Class<? extends Activity> targetActivity,boolean isfinish,boolean isAnim,int flag){
        Intent intent = new Intent(activity,targetActivity);
        intent.setFlags(flag);
        activity.startActivity(intent);
        if(isAnim){
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            if(isfinish){
                activity.finish();
            }
        }else{
            if(isfinish){
                finish();
            }
        }
    }
    /**
     * activity跳转
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity,Class<? extends Activity> targetActivity,boolean isfinish,int requestCode){
        Intent intent = new Intent(activity,targetActivity);
        activity.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        if(isfinish){
            activity.finish();
        }
    }

    /**
     * activity跳转
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity,Class<? extends Activity> targetActivity,boolean isfinish,Bundle bundle,int requestCode){
        Intent intent = new Intent(activity,targetActivity);
        intent.putExtra(Strings.DEFAULT_BUNDLE_NAME, bundle);
        activity.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        if(isfinish){
            activity.finish();

        }
    }
    /**
     * activity跳转
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity,Class<? extends Activity> targetActivity,boolean isfinish,Bundle bundle){
        Intent intent = new Intent(activity,targetActivity);
        intent.putExtra(Strings.DEFAULT_BUNDLE_NAME, bundle);
        activity.startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        if(isfinish){
            activity.finish();

        }
    }
    /**
     * activity跳转
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity,Class<? extends Activity> targetActivity,boolean isfinish,int flag,Bundle bundle){
        Intent intent = new Intent(activity,targetActivity);
        intent.putExtra(Strings.DEFAULT_BUNDLE_NAME, bundle);
        intent.setFlags(flag);
        activity.startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        if(isfinish){
            activity.finish();
        }
    }
    public void activityFinish(){
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    //处理后退键的情况
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && finishOnBackKeyDown){

            this.finish();  //finish当前activity
            overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setFinishOnBackKeyDown(boolean finishOnBackKeyDown) {
        this.finishOnBackKeyDown = finishOnBackKeyDown;
    }

}
