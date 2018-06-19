package com.syberos.shuili.listener;

import android.view.MotionEvent;
import android.view.View;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/10
 * @time: 上午11:04
 * @email: ZhaoDongshuang@syberos.com
 */
public class ItemClickedAlphaChangeListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setAlpha(0.5f);
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.setAlpha(1.0f);
        }
        return false;
    }
}
