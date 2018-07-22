package com.syberos.shuili.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.syberos.shuili.utils.dpUtil.dp2px;

public class DialPlateView extends View {
    //弧形开始的角度
    private static final int startAngle = 135;
    //弧面所跨的弧度
    private static final int sweepAngle = 270;
    //里面数字的单位
    private static final String unint = "mmhg";
    //每隔多少画一个刻度
    private static final int angPre = 15;
    //总刻度
    private static final int totalDial = 90;
    //进度条的底色
    private static final int PROGRESS_COLOR = 0x55000000;
    Canvas canvas;
    int persent = 1;
    //画普通的线用的笔
    private Paint linePaint;
    //画文字用的笔
    private Paint textPaint;
    //画进度条用的笔
    private Paint progressPaint;
    //里面半圆的半径
    private int innerRadius = dp2px(68);
    //最内层的padding
    private int innerPadding = dp2px(6);
    //外两层的padding
    private int outerPadding = dp2px(8);
    //进度条的宽度
    private int progressLineW = dp2px(10);
    //最里面跟最外面的线的宽度
    private int innerLineWidth = dp2px(1);
    //文字的size
//    private float textSize = sp2px(18);
    //单位文字的size
//    private float textSizeUnit = sp2px(13);
    //刻度线的宽度
    private int outerLineWidth = dp2px(1);
    //刻度线的高度
    private int outerLineHeight = dp2px(10);
    //当前进度
    private float currProgress = 0.5f;
    //起始值
    private float start = 0;
    //最终值
    private float end = 150;

    public DialPlateView(Context context) {
        super(context, null);
    }

    public DialPlateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialPlateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public void initPaint() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.GRAY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        //最里面圆的半径计算
//        //半径＝控件的宽度／2-最里层的padding－刻度线的高度－里第二层的padding－进度条的宽度－第二层padding－最外层线宽度
//        innerRadius = getMeasuredWidth() / 2 - innerPadding - outerLineHeight -
//                outerPadding - progressLineW - outerPadding - innerLineWidth;
//        //高度＝刻度线的高度＋padding＋最里面一层的半径＋padding＋进度条宽度＋padding＋最外层线宽度
//        int height = (outerLineHeight + innerPadding + innerRadius + outerPadding + progressLineW + outerPadding + innerLineWidth);
//        //重新生成高度
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        //画出最里面的弧线
//        drawInnerLine(canvas);
        linePaint.setColor(Color.GRAY);
        drawDial(startAngle, sweepAngle, totalDial, angPre, outerLineHeight, outerLineHeight / 2, innerRadius + innerPadding + outerLineHeight, canvas);
        linePaint.setColor(Color.WHITE);
        drawDial(startAngle, sweepAngle* persent / 100, totalDial* persent / 100 , angPre, outerLineHeight, outerLineHeight / 2, innerRadius + innerPadding + outerLineHeight, canvas);
    }

    public void upData(int percent) {
        this.persent = percent;
        this.postInvalidate();
    }

    /**
     * 画刻度盘
     */

    private void drawDial(int startAngle, int allAngle, int dialCount, int per, int longLength, int shortLength, int radius, Canvas canvas) {
        linePaint.setStrokeWidth(outerLineWidth);
        int length;
        int angle;
        //根据需要显示的刻度总个数遍历
        for (int i = 0; i <= dialCount; i++) {
            //每一个刻度对应的起始角度为180度＋（总度数／个数）＊对应刻度的位置
            angle = (int) ((allAngle) / (dialCount * 1f) * i) + startAngle;
            //线条的起始点位置
            int[] startP;
            //线条的end点的位置
            int[] endP;
            //当i％per＝＝0，每一个需要显示短刻度的时候（因为设计稿第一个为短的刻度条）
            if (i % per == 0) {
                length = longLength;
                startP = getPointFromAngleAndRadius(angle, radius);
                endP = getPointFromAngleAndRadius(angle, radius - length);

            } else {
                //短刻度条的长度为长刻度条的一半
                length = shortLength;
                //获取刻度条起始点位置
                startP = getPointFromAngleAndRadius(angle, radius);
                endP = getPointFromAngleAndRadius(angle, radius - length);
            }
            //画出对应的刻度条
            canvas.drawLine(startP[0], startP[1], endP[0], endP[1], linePaint);
        }
    }

    /**
     * 根据刻度条相应的角度算出点位置
     *
     * @param angle
     * @param radius
     * @return
     */
    private int[] getPointFromAngleAndRadius(int angle, int radius) {
        //根据三角函数公式可以知道，横坐标值为（刻度条＋innnerradius）也就是刻度条对应圆的半径
        //乘以一个cos（angle），因为我们是以（getWidth() / 2，控件的高度）位置建的坐标系
        //而真正的坐标系的位置为控件左上角，所以算出的值后需要＋getWidth() / 2或者getHeight()
        double x = radius * Math.cos(angle * Math.PI / 180) + getWidth() / 2;
        double y = radius * Math.sin(angle * Math.PI / 180) + getHeight() / 2;
        return new int[]{(int) x, (int) y};
    }
}
