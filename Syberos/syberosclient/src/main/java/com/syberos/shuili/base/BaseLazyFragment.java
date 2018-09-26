package com.syberos.shuili.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.tu.loadingdialog.LoadingDialog;
import com.syberos.shuili.R;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jidan on 18-3-11.
 */

public abstract class BaseLazyFragment extends Fragment {
    private final String TAG = BaseLazyFragment.class.getSimpleName();
    public Context mContext;
    public LayoutInflater layoutInflater;
    private Dialog dialog;
    protected View view;
    protected Unbinder unbinder;
    protected boolean hasStarted = false;
    protected boolean isPrepared = false;
    private Dialog dataDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        setUseEventBus(false);
    }

    private boolean isUseEventBus = false;

    protected boolean isUseEventBus() {
        return isUseEventBus;
    }

    protected void setUseEventBus(boolean useEventBus) {
        isUseEventBus = useEventBus;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isUseEventBus())
            EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isUseEventBus())
            EventBus.getDefault().unregister(this);
    }

    public static int getStatus1() {
        return status1;
    }

    public void setStatus1(int status1) {
        this.status1 = status1;
    }

    public int getStatus2() {
        return status2;
    }

    public void setStatus2(int status2) {
        this.status2 = status2;
    }

    static int status1;
    static int status2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreateView");
        View view = layoutInflater.inflate(getLayoutID(), null);
        this.view = view;
        setStatus1(1);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initListener();

        if (getUserVisibleHint()) {
            hasStarted = true;
        }
        if (hasStarted && !isPrepared) {

            initData();
        }

        isPrepared = true;
        return view;
    }

    protected abstract int getLayoutID();

    /**
     * 设置监听事件
     */
    protected abstract void initListener();

    /**
     * 记载数据
     */
    protected abstract void initData();

    protected abstract void initView();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            hasStarted = true;
            if (hasStarted && isPrepared) {
                initListener();
                initData();
            }
        } else {
            if (hasStarted) {
                hasStarted = false;
            } else {
                isPrepared = false;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

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
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 加载对话框
     */
    public void showDataLoadingDialog() {
        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(mContext)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dataDialog = loadBuilder.create();
        dataDialog.show();

    }
    public void showDataLoadingDialog(String text) {
        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(mContext)
                .setMessage(text)
                .setCancelable(true)
                .setCancelOutside(true);
        dataDialog = loadBuilder.create();
        dataDialog.show();

    }

    public void closeDataDialog() {
        if (dataDialog != null && dataDialog.isShowing()) {
            dataDialog.dismiss();
        }
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


}
