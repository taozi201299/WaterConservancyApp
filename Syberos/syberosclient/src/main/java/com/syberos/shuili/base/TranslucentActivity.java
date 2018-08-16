package com.syberos.shuili.base;

import android.app.Dialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.syberos.shuili.R;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.ScreenManager;
import com.syberos.shuili.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-12.
 */

public abstract class TranslucentActivity extends BaseFragmentActivity {

    private IbtnClicked m_btnClicked;
    private Dialog shareDialog;
    protected final static String Msg_Recv =  "MsgRecv";
    protected final static String Allow_ScreenShot = "AllowScreenShot";
    /**
     * 初始化分享模块
     *
     * @param text 分享的文本
     * @param url  分享的url
     */
    public TranslucentActivity initShare(final String text, final String url) {
//        初始化分享的view视图
        shareDialog = new Dialog(this);
        View v = LayoutInflater.from(this).inflate(R.layout.pop_share_layout, null);
        shareDialog.setContentView(v);
        ((TextView)v.findViewById(R.id.tvTitle)).setText(text);
        Window dialogWindow = shareDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialogWindow.setBackgroundDrawableResource(R.drawable.dialog_bg_shape);
        return this;
    }
    /**
     * 显示分享的view
     */
    public void showShareView() {
        shareDialog.show();
    }

    public TranslucentActivity showTranslucentDialog(int resId){
        shareDialog = new Dialog(this);
        View v = LayoutInflater.from(this).inflate(resId, null);
        shareDialog.setContentView(v);
        Window dialogWindow = shareDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        shareDialog.setCancelable(false);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp);
        Button bt_cancel = (Button)v.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button)v.findViewById(R.id.btn_confirm);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_btnClicked != null){
                    m_btnClicked.onBtnClicked(0);
                    shareDialog.dismiss();
                }

            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_btnClicked != null){
                    m_btnClicked.onBtnClicked(1);
                    shareDialog.dismiss();
                }

            }
        });
        return this;
    }
    public interface IbtnClicked{
        void onBtnClicked(int type);
    }
    public void setbtnClicked(IbtnClicked ibtnClicked){
        if(ibtnClicked != null){
            this.m_btnClicked = ibtnClicked;
        }
    }

}
