package com.syberos.shuili.utils;

/**
 * Created by jidan on 18-3-21.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.media.record.AudioListener;
import com.syberos.shuili.media.record.RecordStrategy;
import com.syberos.shuili.view.RoundProgressBar;

/**
 * @description: 对话框
 * @author: ryan
 * @contact: bingh@outlook.com
 * @file: DialogHelper
 * @time: 2017/9/29 18:25
 */

public class DialogHelper {


    public interface RecordListener {
        void recordFile(String filePath, int recordDuration);
    }

    /**
     * @param context
     * @param type          0 只是录音 1 语音转文字
     * @param audioRecorder
     * @return
     */
    public static Dialog showRecordDialog(Context context, int type, final RecordStrategy audioRecorder, final RecordListener recordListener) {
        int maxSecond = 60;
        final Dialog dialog = new Dialog(context, R.style.record_dialog);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View viewDialog = inflater.inflate(R.layout.dialog_audio_record, null);
        Button finish = viewDialog.findViewById(R.id.finish);
        ImageView bg0 = viewDialog.findViewById(R.id.bg_0);
        ImageView bg1 = viewDialog.findViewById(R.id.bg_1);
        ImageView bg2 = viewDialog.findViewById(R.id.bg_2);
        ImageView bg3 = viewDialog.findViewById(R.id.bg_3);
        ImageView recordAnim = viewDialog.findViewById(R.id.record_anim);
        final RoundProgressBar roundProgressBar = viewDialog.findViewById(R.id.rb_percent);
        final  TextView timeCountText = viewDialog.findViewById(R.id.record_time_count);
        timeCountText.setAlpha(0.6f);
        roundProgressBar.setMax(maxSecond);
        roundProgressBar.setProgress(0);

        final  AnimationDrawable animationDrawable = (AnimationDrawable) recordAnim.getBackground();
        animationDrawable.start();

        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        //设置dialog的宽高为屏幕的宽高
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
        dialog.setContentView(viewDialog, layoutParams);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        audioRecorder.setMaxSecond(maxSecond);
        audioRecorder.setAudioListener(new AudioListener() {
            @Override
            public void progress(final  int time) {
                viewDialog.post(new Runnable() {
                    @Override
                    public void run() {
                        roundProgressBar.setProgress(time);
                        timeCountText.setText((time) + "秒");

                    }
                });
            }

            @Override
            public void stop(String filePath, int recordDuration) {

                recordListener.recordFile(filePath, recordDuration);
                stopAnima(animationDrawable);
                dialog.dismiss();
            }


            @Override
            public void fail() {
                stopAnima(animationDrawable);
                dialog.dismiss();
            }
        });
//        audioRecorder.ready();
//        audioRecorder.start();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // audioRecorder.stop();
                stopAnima(animationDrawable);
                dialog.dismiss();

            }
        });
        AnimationSet set = getAnimationSet(0);
        bg3.startAnimation(set);
        AnimationSet set1 = getAnimationSet(250);
        bg2.startAnimation(set1);
        AnimationSet set2 = getAnimationSet(500);
        bg1.startAnimation(set2);
        AnimationSet set3 = getAnimationSet(750);
        bg0.startAnimation(set3);


        return dialog;

    }

    private static void stopAnima(AnimationDrawable animationDrawable) {
        if (animationDrawable != null) {
            animationDrawable.stop();
            animationDrawable.selectDrawable(0);
        }
    }

    @NonNull
    private static AnimationSet getAnimationSet(long startTime) {
        Animation scaleAnimation = new ScaleAnimation(1.0f, 3.0f, 1.0f, 3.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f); // 由完全显示 --> 完全透明
        alpha.setDuration(1000); // 3秒完成动画
        alpha.setRepeatCount(Animation.INFINITE);
        alpha.setRepeatMode(Animation.RESTART);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.RESTART);
        //动画集
        AnimationSet set = new AnimationSet(true);
//        set.addAnimation(alphaAnimation);
        set.addAnimation(alpha);
//        set.setStartTime(startTime);
        set.setStartOffset(startTime);
        set.addAnimation(scaleAnimation);
        //设置动画时间 (作用到每个动画)
        set.setDuration(1000);
        return set;
    }
}
