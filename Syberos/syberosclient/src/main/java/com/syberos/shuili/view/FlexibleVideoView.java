package com.syberos.shuili.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by jidan on 18-3-28.
 */

public class FlexibleVideoView extends VideoView {
    public FlexibleVideoView(Context context) {
        super(context);
    }

    public FlexibleVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
