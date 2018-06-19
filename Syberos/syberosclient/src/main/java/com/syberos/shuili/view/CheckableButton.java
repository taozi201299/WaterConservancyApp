package com.syberos.shuili.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/**
 * created by：toby on 18-4-18 13:45
 * email：zhaodongshuang@syberos.com
 * 带选中状态的按钮
 */
public class CheckableButton extends android.support.v7.widget.AppCompatButton implements Checkable {
    private boolean mChecked = false;
    private static final int[] CHECKED_STATE_LIST = new int[] { android.R.attr.state_checked };

    private CheckedStatusChangedListener checkedStatusChangedListener;

    public CheckableButton(Context context) {
        super(context);
    }

    public CheckableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (mChecked) {
            mergeDrawableStates(drawableState, CHECKED_STATE_LIST);
        }
        return drawableState;
    }


    /**
     * <p>Changes the checked state of this button.</p>
     *
     * @param checked true to check the button, false to uncheck it.
     */
    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();

            if (null != checkedStatusChangedListener) {
                checkedStatusChangedListener.onCheckedChanged(this, mChecked);
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    // 暂时不需要控件自身支持点击时的选中和取消选中效果
//    @Override
//    public boolean performClick() {
//        toggle();
//        return super.performClick();
//    }

    void setOnCheckedStatusChangedListener(CheckedStatusChangedListener listener) {
        checkedStatusChangedListener = listener;
    }

    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a checkable button changed.
     */
    public interface CheckedStatusChangedListener {
        /**
         * Called when the checked state of a checkable button has changed.
         *
         * @param view The checkable button view whose state has changed.
         * @param isChecked The new checked state of buttonView.
         */
        void onCheckedChanged(View view, boolean isChecked);
    }
}
