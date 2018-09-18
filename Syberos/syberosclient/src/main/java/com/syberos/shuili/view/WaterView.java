package com.syberos.shuili.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.syberos.shuili.R;

/**
 * Created by BZB on 2018/7/30.
 * Project: Syberos.
 * Package：com.example.testmodule.view.
 */
public class WaterView extends View {
    RectF rectF;
    Rect rect;
    Paint paint;
    Path path;
    Path pathW1;
    Path pathW2;
    Path pathW3;
    Paint paintWater1;
    Paint paintWater2;
    Paint paintWater3;
    Point startPoint;
    double progress = 30;
    int startX=0;
    private float radius;

    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rectF = new RectF();
        rect = new Rect();
        path = new Path();
        pathW1 = new Path();
        pathW2 = new Path();
        pathW3 = new Path();
        startPoint = new Point();
        paint = new Paint();
        paintWater1 = new Paint(Color.RED);
        paintWater1.setStyle(Paint.Style.STROKE);
        paintWater1.setStrokeWidth(2);
        paintWater2 = new Paint(Color.parseColor("#92CCF2"));
        paintWater2.setStyle(Paint.Style.STROKE);
        paintWater2.setStrokeWidth(2);
        paintWater3 = new Paint(Color.parseColor("#3EAEFA"));
        paintWater3.setStyle(Paint.Style.STROKE);
        paintWater3.setStrokeWidth(2);

//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cat);
    }

    public void setProgress(double progress) {
        this.progress = progress;
        invalidate();
    }
    public void setStartX(int startX){
        this.startX=-startX;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathW1.reset();
        pathW2.reset();
        pathW3.reset();
//        canvas.setBitmap(bitmap);
//        获取画布大小
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        float shadowWidth = 10;
        paint.setColor(Color.parseColor("#22222222"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        radius = (width > height ? height : width) / 2;
//        canvas.drawCircle(width / 2, height / 2, radius / 2 - 5, paint);
        rectF.left = shadowWidth;
        rectF.top = shadowWidth;
        rectF.right = radius * 2 - shadowWidth;
        rectF.bottom = radius * 2 - shadowWidth;

        rect.left = (int) shadowWidth;
        rect.top = (int) shadowWidth;

        rect.right = (int) (radius * 2 - shadowWidth);
        rect.bottom = (int) (radius * 2 - shadowWidth);
        path.addArc(rectF, 0, 360);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            canvas.clipOutRect(rectF);
//        }else {
        canvas.clipPath(path);
//        }
//        canvas.drawBitmap(bitmap, rect, rectF, paint);
        canvas.drawArc(rectF, 0, 360, true, paint);
        /**
         * Math.sin(double d) d:采用的是弧度制，不是角度制   关于弧度制：弧长=半径 =1弧度  180 o = π rad
         */


        startPoint.y = (int) (radius *2 * (1 - progress / 100.0));
        startPoint.x=startX;
        /*sin曲线 1/4个周期的宽度*/
        int cycle = (int) (radius / 2);
        /*sin曲线振幅的高度*/
        int waveHeight = (int) ((radius / 4)*(55-Math.abs(50-progress))/50);//; Math.abs(
//初始化的时候将起点移至屏幕外一个周期


        pathW1.moveTo(startPoint.x, startPoint.y);
        pathW2.moveTo(startPoint.x - radius / 3, startPoint.y);
        pathW3.moveTo(startPoint.x - radius / 2, startPoint.y);
        int j = 1;
        //循环绘制正弦曲线 循环一次半个周期
        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                //波峰
                pathW1.quadTo(startPoint.x + (cycle * j), startPoint.y + waveHeight, startPoint.x + (cycle * 2) * i, startPoint.y);

                pathW2.quadTo(startPoint.x - radius * 2 / 3 + (cycle * j), startPoint.y + waveHeight, startPoint.x + (cycle * 2) * i - radius * 2 / 3, startPoint.y);
//
                pathW3.quadTo(startPoint.x - radius + (cycle * j), startPoint.y + waveHeight, startPoint.x + (cycle * 2) * i - radius, startPoint.y);
            } else {
                //波谷
                pathW1.quadTo(startPoint.x + (cycle * j), startPoint.y - waveHeight, startPoint.x + (cycle * 2) * i, startPoint.y);
                //波谷
                pathW2.quadTo(startPoint.x - radius * 2 / 3 + (cycle * j), startPoint.y - waveHeight, startPoint.x + (cycle * 2) * i - radius / 3, startPoint.y);
                //波谷
                pathW3.quadTo(startPoint.x - radius + (cycle * j), startPoint.y - waveHeight, startPoint.x + (cycle * 2) * i - radius, startPoint.y);

            }
            j += 2;
        }
        //绘制封闭的曲线
//        pathW1.lineTo(width, height);//右下角
//        pathW1.lineTo(startPoint.x, height);//左下角
//        pathW1.lineTo(startPoint.x, startPoint.y);//起点


        pathW3.lineTo(width, height);
        pathW3.lineTo(0, height);
        pathW3.lineTo(startPoint.x - radius, startPoint.y);
        pathW3.close();
        paintWater3.setColor(Color.parseColor("#DEF0FE"));
        paintWater3.setStyle(Paint.Style.FILL);
        canvas.drawPath(pathW3, paintWater3);

        pathW2.lineTo(width, height);
        pathW2.lineTo(0, height);
        pathW2.lineTo(startPoint.x - radius * 2 / 3, startPoint.y);
        pathW2.close();
        paintWater2.setColor(Color.parseColor("#92CCF2"));
        paintWater2.setStyle(Paint.Style.FILL);
        canvas.drawPath(pathW2, paintWater2);

        pathW1.lineTo(width, height);
        pathW1.lineTo(0, height);
        pathW1.lineTo(startPoint.x, startPoint.y);
        pathW1.close();
        paintWater1.setColor(Color.parseColor("#40AEF9"));
        paintWater1.setStyle(Paint.Style.FILL);
        canvas.drawPath(pathW1, paintWater1);
        paint.setColor(getResources().getColor(R.color.text_black_color));
        paint.setTextSize(radius);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(progress + "", radius -getTextWidth(paint,progress+"")/2, radius +shadowWidth*2, paint);

//判断是不是平移完了一个周期
//        if (startPoint.x + 40 >= 0) {
//            //满了一个周期则恢复默认起点继续平移
//            startPoint.x = -cycle * 4;
//        }
//        //每次波形的平移量 40
//        startPoint.x += 40;
//        postInvalidateDelayed(150);


    }
    public void startAnimation(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-radius*4,0);
        valueAnimator.setDuration(2500);
        //设置插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        //设置无线循环
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                startX= (int) value;
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }
    public double Sin(Double i) {
        double result = 0;
        //在这里我是写sin函数，其实可以用cos，tan等函数的，不信大家尽管试试
        //result = Math.cos(i * Math.PI / 180);
        result = Math.sin(i * Math.PI / 180);
        //result = Math.tan(i * Math.PI / 180);
        return result;

    }

    public double Cos(Double i) {
        double result = 0;
        //在这里我是写sin函数，其实可以用cos，tan等函数的，不信大家尽管试试
        //result = Math.cos(i * Math.PI / 180);
        result = Math.cos(i * Math.PI / 180);
        //result = Math.tan(i * Math.PI / 180);
        return result;

    }

}