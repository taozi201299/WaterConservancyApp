package com.syberos.shuili.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.clippingtransforms.WavesClippingTransform;
import com.github.jorgecastillo.listener.OnStateChangeListener;
import com.syberos.shuili.R;
import com.syberos.shuili.listener.OpenDrawerListener;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.config.Paths;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private OpenDrawerListener openDrawerListener = null;
    private final String TAG = BaseFragment.class.getSimpleName();
    protected final int Scan_Code = 100;
    public Context mContext;
    public LayoutInflater layoutInflater;
    private Dialog dialog;
    protected View view;
    protected Unbinder unbinder;
    private boolean isUserVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreateView");
        View view = layoutInflater.inflate(getLayoutID(), null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        if (getUserVisibleHint()) {
            isUserVisible = true;
            initData();
        }

        initListener();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isUserVisible= isVisibleToUser ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected abstract int getLayoutID();

    /**
     * ]
     * 设置监听事件
     */
    protected abstract void initListener();

    /**
     * 记载数据
     */
    protected abstract void initData();

    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 隐藏软件盘
     *
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    /**
     * 弹出软件盘
     *
     * @param et
     * @param context
     */
    public static void showSoftInput(EditText et, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, 0);

    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
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
                if (state == State.FINISHED) {
                    fillableLoader.reset();
                    fillableLoader.start();
                }
            }
        });
    }

    public void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

    }

    /**
     * 获取sp保存在本地得数据
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public Object getSpData(String key, Object defaultObject) {
        return SPUtils.get(key, defaultObject);
    }

    /**
     * 将数据保存在sp本地
     *
     * @param key
     * @param data
     */
    public void putSpData(String key, Object data) {
        SPUtils.put(key, data);
    }

    /**
     * 显示toast信息
     *
     * @param msg
     */
    public void showToast(String msg) {
//        ToastUtils.showMessage(context, msg);
        ToastUtils.show(msg);
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity, Class<? extends Activity> targetActivity, boolean isfinish, boolean isAnim, int flag) {
        if (activity == null) return;
        Intent intent = new Intent(activity, targetActivity);
        intent.setFlags(flag);
        activity.startActivity(intent);
        if (isAnim) {
            activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            if (isfinish) {
                activity.finish();
            }
        } else {
            if (isfinish) {
                activity.finish();
            }
        }
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity, Class<? extends Activity> targetActivity, boolean isfinish, boolean isAnim) {
        if (activity == null) return;
        Intent intent = new Intent(activity, targetActivity);
        activity.startActivity(intent);
        if (isAnim) {
            activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            if (isfinish) {
                activity.finish();
            }
        } else {
            if (isfinish) {
                activity.finish();
            }
        }
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity, Class<? extends Activity> targetActivity, boolean isfinish, int requestCode) {
        if (activity == null) return;
        Intent intent = new Intent(activity, targetActivity);
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        if (isfinish) {
            activity.finish();
        }
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity, Class<? extends Activity> targetActivity, boolean isfinish, Bundle bundle, int requestCode) {
        if (activity == null) return;
        Intent intent = new Intent(activity, targetActivity);
        intent.putExtra(Strings.DEFAULT_BUNDLE_NAME, bundle);
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        if (isfinish) {
            activity.finish();

        }
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param targetActivity
     * @param isfinish
     */
    public void intentActivity(Activity activity, Class<? extends Activity> targetActivity, boolean isfinish, Bundle bundle) {
        if (activity == null) return;
        Intent intent = new Intent(activity, targetActivity);
        intent.putExtra(Strings.DEFAULT_BUNDLE_NAME, bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        if (isfinish) {
            activity.finish();

        }
    }

    public void acvitityFinish(Activity activity) {
        if (activity == null) return;
        activity.finish();
        activity.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    public void setOpenDrawerListener(OpenDrawerListener openDrawerListener) {
        this.openDrawerListener = openDrawerListener;
    }

    public OpenDrawerListener getOpenDrawerListener() {
        return openDrawerListener;
    }
}
