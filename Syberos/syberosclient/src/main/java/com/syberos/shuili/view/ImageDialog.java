package com.syberos.shuili.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.syberos.shuili.R;

/**
 * Created by jidan on 18-3-28.
 */

public class ImageDialog extends Dialog {

    private Window window = null;
    private ImageView iv;
    private Bitmap bms;
    public ImageDialog(Context context, boolean cancelable,
                         DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    public ImageDialog(Context context, int cancelable,int x,int y,Bitmap bm) {
        super(context, cancelable);
        windowDeploy(x, y);
        bms = bm;

    }
    public ImageDialog(Context context) {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        //初始化布局
        View loadingview= LayoutInflater.from(getContext()).inflate(R.layout.dialog_img_view,null);
        iv=(ImageView) loadingview.findViewById(R.id.imageview_head_big);
        ViewGroup.LayoutParams params = iv.getLayoutParams();
        params.height=800;
        params.width =800;
        iv.setLayoutParams(params);
        iv.setImageBitmap(bms);
        //设置dialog的布局
        setContentView(loadingview);
        //如果需要放大或者缩小时的动画，可以直接在此出对loadingview或iv操作，在下面SHOW或者dismiss中操作
        super.onCreate(savedInstanceState);
    }

    //设置窗口显示
    public void windowDeploy(int x, int y){
        window = getWindow(); //得到对话框
        window.setWindowAnimations(R.style.dialogMediaAnim); //设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.transparent); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
        wl.x = x; //x小于0左移，大于0右移
        wl.y = y; //y小于0上移，大于0下移
        window.setAttributes(wl);
    }

    public void show() {
        //设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);
        super.show();
    }
    public void dismiss() {
        super.dismiss();
    }
}