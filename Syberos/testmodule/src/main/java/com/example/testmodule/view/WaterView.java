package com.example.testmodule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by BZB on 2018/7/30.
 * Project: Syberos.
 * Package：com.example.testmodule.view.
 */
public class WaterView extends View {
    public WaterView(Context context) {
        super(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width=canvas.getWidth();
        float height=canvas.getHeight();

        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        float radius=width>height?height:width;
        canvas.drawCircle(width/2,height/2,radius/2-5,paint);

    }
}
