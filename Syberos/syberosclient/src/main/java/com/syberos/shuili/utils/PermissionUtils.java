package com.syberos.shuili.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.syberos.shuili.config.PermissionContants.CODE_ALL_PERMISSION;
import static com.syberos.shuili.config.PermissionContants.CODE_Mutil_PERMISSION;
import static com.syberos.shuili.config.PermissionContants.requestPermissions;

/**
 * Created by jidan on 18-3-10.
 */

public class PermissionUtils {


    private static String TAG = "PermissionUtils";


    private static OnPermissionListener mOnPermissionListener;

    public interface OnPermissionListener {//申请权限回调

        /**
         * 授权成功,把请求码返回(请求码可不处理)
         */
        void onPermissionGranted(int requestCode);

    }

    /**
     * 申请单个权限
     * <p>
     * 只申请权限,不对某个请求的权限返回码作处理
     *
     * @param activity
     * @param requestCode
     */
    public static void requestPermission(final Activity activity, final int requestCode) {
        requestPermission(activity, requestCode, null);

    }


    /**
     * 申请单个权限
     * <p>
     * 部分机型修改过Rom,如小米4,shouldShowRequestPermissionRationale会一直返回false
     *
     * @param activity    上下文
     * @param requestCode 请求码
     * @param listener    监听器
     */
    public static void requestPermission(final Activity activity, final int requestCode, OnPermissionListener listener) {
        mOnPermissionListener = listener;

        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            LogUtils.i(TAG, "非法的请求码(不是需要动态申请的权限)");
            return;
        }

        final String requestPermission = requestPermissions[requestCode];
        if (ActivityCompat.checkSelfPermission(activity, requestPermission) != PackageManager.PERMISSION_GRANTED) {
            //判断权限是否被彻底禁止,首次调用或彻底禁止调用requestPermissions; 没有彻底禁止调用shouldShowRequestPermissionRationale
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                LogUtils.i(TAG, "是否被彻底禁止: " + !ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission));
                shouldShowRationale(activity, requestCode, requestPermission);
            } else {
                /**
                 * 首次申请权限或彻底禁止,系统调用这个方法
                 *
                 * 第一个参数是Context
                 * 第二个参数是需要申请的权限的字符串数组(由此看出支持一次性申请多个权限)
                 * 第三个参数为requestCode,回调的时候检测
                 *
                 */
                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);

            }
        } else {
            LogUtils.i(TAG, "已授权");
            if (mOnPermissionListener != null)
                mOnPermissionListener.onPermissionGranted(requestCode);
        }

    }

    /**
     * 申请一组或多组权限
     * <p>
     * 只申请权限,不对某个请求的权限返回码作处理
     *
     * @param activity
     * @param PermissionGroup PermissionsContants.XX_GROUP
     */
    public static void requestPermissionGroup(final Activity activity, final String[]... PermissionGroup) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < PermissionGroup.length; i++) {
            for (int j = 0; j < PermissionGroup[i].length; j++) {
                list.add(PermissionGroup[i][j]);
            }
        }
        String[] permissions = list.toArray(new String[list.size()]);
        if (permissions.length == list.size()) {
            LogUtils.i(TAG, "requestPermissionGroup: "+list.size());
            requestMultiPermission(activity, permissions, null);
        }
    }

    /**
     * 申请一组或多组权限
     * <p>
     * 只申请权限,不对某个请求的权限返回码作处理
     *
     * @param activity
     * @param PermissionGroup PermissionsContants.XX_GROUP
     */
    public static void requestPermissionGroup(final Activity activity, OnPermissionListener listener, final String[]... PermissionGroup) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < PermissionGroup.length; i++) {
            for (int j = 0; j < PermissionGroup[i].length; j++) {
                list.add(PermissionGroup[i][j]);
            }
        }

        requestMultiPermission(activity, list.toArray(new String[list.size()]), listener);
    }

    /**
     * 申请多个权限
     * <p>
     * 只申请权限,不对某个请求的权限返回码作处理
     *
     * @param activity
     * @param mutilPermissionList
     */
    public static void requestMultiPermission(final Activity activity, final String[] mutilPermissionList) {
        requestMultiPermission(activity, mutilPermissionList, null);

    }

    /**
     * 申请多个权限
     * <p>
     * 部分机型修改过Rom,如小米4,shouldShowRequestPermissionRationale会一直返回false
     *
     * @param activity
     * @param mutilPermissionList
     * @param listener
     */
    public static void requestMultiPermission(final Activity activity, final String[] mutilPermissionList, OnPermissionListener listener) {
        mOnPermissionListener = listener;
        /**首次申请或彻底禁止,没有申请到的权限**/
        final List<String> permissionsList = getMutilPermission(activity, mutilPermissionList, false);
        /**没有彻底禁止,但没有申请到的权限**/
        final List<String> shouldRationalePermissionsList = getMutilPermission(activity, mutilPermissionList, true);
        if (permissionsList == null || shouldRationalePermissionsList == null) {
            return;
        }
        LogUtils.i(TAG, "requestMultiPermissions permissionsList:" + permissionsList.size() + ",shouldRationalePermissionsList:" + shouldRationalePermissionsList.size());

        if (permissionsList.size() > 0) {
            for (int i = 0; i < requestPermissions.length; i++) {
                for (int j = 0; j < mutilPermissionList.length; j++) {
                    if (mutilPermissionList[j].equals(requestPermissions[i])) {
                        LogUtils.i(TAG, mutilPermissionList[j]);
                        ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                                CODE_Mutil_PERMISSION);
                    }
                }
            }

        } else if (shouldRationalePermissionsList.size() > 0) {
            showMessageOKCancel(activity, "这些权限需要授权",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
                                    CODE_Mutil_PERMISSION);
                            LogUtils.i(TAG, "showMessageOKCancel requestPermissions");
                        }
                    });
        } else {
            LogUtils.i(TAG, "已授权");
            if (mOnPermissionListener != null)
                mOnPermissionListener.onPermissionGranted(CODE_Mutil_PERMISSION);
        }

    }

    /**
     * 申请全部权限
     * <p>
     * 只申请权限,不对某个请求的权限返回码作处理
     *
     * @param activity
     */
    public static void requestAllPermissions(final Activity activity) {
        requestAllPermissions(activity, null);

    }


    /**
     * 申请全部权限
     * <p>
     * 部分机型修改过Rom,如小米4,shouldShowRequestPermissionRationale会一直返回false
     *
     * @param activity
     */
    public static void requestAllPermissions(final Activity activity, OnPermissionListener listener) {
        mOnPermissionListener = listener;
        //首次申请或彻底禁止,没有申请到的权限
        final List<String> permissionsList = getDeniedPermission(activity, false);
        //没有彻底禁止,但没有申请到的权限
        final List<String> shouldRationalePermissionsList = getDeniedPermission(activity, true);
        if (permissionsList == null || shouldRationalePermissionsList == null) {
            return;
        }
        LogUtils.i(TAG, "requestMultiPermissions permissionsList:" + permissionsList.size() + ",shouldRationalePermissionsList:" + shouldRationalePermissionsList.size());

        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                    CODE_ALL_PERMISSION);
        } else if (shouldRationalePermissionsList.size() > 0) {
            showMessageOKCancel(activity, "这些权限需要授权",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
                                    CODE_ALL_PERMISSION);
                            LogUtils.i(TAG, "showMessageOKCancel requestPermissions");
                        }
                    });
        } else {
            LogUtils.i(TAG, "已授权");
            if (mOnPermissionListener != null)
                mOnPermissionListener.onPermissionGranted(CODE_ALL_PERMISSION);
        }
    }


    private static void showMessageOKCancel(Activity context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                         .setMessage(message)
                         .setPositiveButton("确认", okListener)
                         .setNegativeButton("取消", null)
                         .create()
                         .show();
    }

    /**
     * 获取没有申请到的权限
     *
     * @param activity
     * @param isShouldRationale true: return Denied and shouldShowRequestPermissionRationale permissions, false:return Denied and !shouldShowRequestPermissionRationale
     * @return
     */
    private static ArrayList<String> getDeniedPermission(Activity activity, boolean isShouldRationale) {

        ArrayList<String> permissions = new ArrayList<>();

        for (int i = 0; i < requestPermissions.length; i++) {
            String requestPermission = requestPermissions[i];

            if (ActivityCompat.checkSelfPermission(activity, requestPermission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                    if (isShouldRationale) {
                        permissions.add(requestPermission);
                    }

                } else {
                    if (!isShouldRationale) {
                        permissions.add(requestPermission);
                    }
                }

            }
        }

        return permissions;
    }

    /**
     * 获取要申请的多组权限
     *
     * @param activity
     * @param isShouldRationale true: return Denied and shouldShowRequestPermissionRationale permissions, false:return Denied and !shouldShowRequestPermissionRationale
     * @return
     */
    private static ArrayList<String> getMutilPermission(Activity activity, String[] mutilPermissionList, boolean isShouldRationale) {

        ArrayList<String> permissions = new ArrayList<>();

        for (int i = 0; i < mutilPermissionList.length; i++) {
            String mutilPermission = mutilPermissionList[i];

            if (ActivityCompat.checkSelfPermission(activity, mutilPermission) != PackageManager.PERMISSION_GRANTED) {
                try {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, mutilPermission)) {
                        if (isShouldRationale) {
                            permissions.add(mutilPermission);
                        }

                    } else {
                        if (!isShouldRationale) {
                            permissions.add(mutilPermission);
                        }
                    }
                } catch (Exception e) {
                    LogUtils.i(TAG, e.getMessage());
                }

            }
        }

        return permissions;
    }

    /**
     * 弹出Dialog向用户解释为何申请权限shouldShowRequestPermissionRationale。
     *
     * @param activity
     * @param requestCode
     * @param requestPermission
     */
    private static void shouldShowRationale(final Activity activity, final int requestCode, final String requestPermission) {
        //申请权限还是调用 ActivityCompat.requestPermissions(activity,new String[]{requestPermission},requestCode);
        showMessageOKCancel(activity, "系统部分功能需要: " + requestPermissions[requestCode] + "权限,才能正常运作,需要现在手动设置权限吗?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{requestPermission},
                        requestCode);
            }
        });
    }


    /**
     * 申请单个权限结果
     *
     * @param activity
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(final Activity activity, final int requestCode, @NonNull String[] permissions,
                                                  @NonNull int[] grantResults) {
        if (requestCode == CODE_ALL_PERMISSION || requestCode == CODE_Mutil_PERMISSION) {
            onRequestMutilPermissionsResult(activity, permissions, grantResults,requestCode);
            return;
        }
        //不是我们规定的权限
        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            LogUtils.i(TAG, "非法的请求码:" + requestCode);
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (mOnPermissionListener != null)
                mOnPermissionListener.onPermissionGranted(requestCode);

        } else {

            openPermissionsSetting(activity, "系统部分功能需要: " + requestPermissions[requestCode] + "权限,才能正常运作,需要现在手动设置权限吗?");
        }


    }

    /**
     * 申请所有(多个)权限结果
     * <p>
     * ps:(多个)所有权限参数基本一致,写到一起了
     *
     * @param activity
     * @param permissions
     * @param grantResults
     */
    private static void onRequestMutilPermissionsResult(Activity activity, String[] permissions, int[] grantResults,int requestCode) {

        Map<String, Integer> perms = new HashMap<>();

        ArrayList<String> notGranted = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            perms.put(permissions[i], grantResults[i]);
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }

        if (notGranted.size() == 0) {
            ToastUtils.show("权限申请成功");
            if (mOnPermissionListener != null)
                mOnPermissionListener.onPermissionGranted(requestCode);
        } else {

            openPermissionsSetting(activity, "部分权限需要手动授权");

        }
    }


    /**
     * 打开系统权限设置页面
     *
     * @param activity
     * @param message
     */

    private static void openPermissionsSetting(final Activity activity, String message) {
        showMessageOKCancel(activity, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                LogUtils.i(TAG, "getPackageName(): " + activity.getPackageName());
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
    }

    /**
     * 判断清单文件是否声明了某项权限的方法
     *
     * @param context
     * @param permissionName
     * @return
     */
    public boolean hasPermissionInManifest(Context context, String permissionName) {
        final String packageName = context.getPackageName();
        try {
            final PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            final String[] declaredPermisisons = packageInfo.requestedPermissions;
            if (declaredPermisisons != null && declaredPermisisons.length > 0) {
                for (String p : declaredPermisisons) {
                    if (p.equals(permissionName)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return false;
    }


}
