package com.syberos.shuili.view.VerticalStepsView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.syberos.shuili.R;

public class VerticalStepsViewIndicator extends View {

    private Paint paint = new Paint();
    private Paint selectedPaint = new Paint();
    private Paint progressTextPaint = new Paint();
    private float mProgressStrokeWidth;
    private float mLineHeight;
    private float mCircleRadius;
    private int mProgressColor;
    private int mBarColor;
    private int mProgressTextColor;
    private boolean hideProgressText;
    private boolean isStepCompleted = true;
    private String stepNumber = "";
    private boolean isCurrentStep = true;

    private float mCenterX;
    private float mTopY = 0;
    private float mCenterY;
    private float mBottomY = 0;
    private float mLineX;
    private OnDrawListener mDrawListener = null;

    public VerticalStepsViewIndicator(Context context) {
        this(context, null);
    }

    public VerticalStepsViewIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalStepsViewIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray arr = context.obtainStyledAttributes(attrs,
                R.styleable.VerticalStepsViewIndicator);
        int barColor = arr.getColor(
                R.styleable.VerticalStepsViewIndicator_barColor, Color.GRAY);
        int progressColor = arr.getColor(
                R.styleable.VerticalStepsViewIndicator_progressColor,
                ContextCompat.getColor(context, R.color.orange));
        int progressTextColor = arr.getColor(
                R.styleable.VerticalStepsViewIndicator_progressTextColor, Color.WHITE);
        boolean hideProgressText = arr.getBoolean(
                R.styleable.VerticalStepsViewIndicator_hideProgressText, false);
        float circleRadius = arr.getFloat(
                R.styleable.VerticalStepsViewIndicator_circleRadius, 40);
        float progressStrokeWidth = arr.getFloat(
                R.styleable.VerticalStepsViewIndicator_progressStrokeWidth, 5);
        boolean isStepCompleted = arr.getBoolean(
                R.styleable.VerticalStepsViewIndicator_stepCompleted, false);
        String stepNumber = arr.getString(
                R.styleable.VerticalStepsViewIndicator_stepNumber);
        boolean isCurrentStep = arr.getBoolean(
                R.styleable.VerticalStepsViewIndicator_isCurrentStep, false);

        this.setBarColor(barColor);
        this.setProgressColor(progressColor);
        this.setProgressTextColor(progressTextColor);
        this.setHideProgressText(hideProgressText);
        this.setCircleRadius(circleRadius);
        this.setProgressStrokeWidth(progressStrokeWidth);
        this.setStepCompleted(isStepCompleted);
        this.setStepNumber(stepNumber);
        this.setIsCurrentStep(isCurrentStep);
        arr.recycle();

        invalidate();

        init();
    }

    private void init() {
        mLineHeight = mProgressStrokeWidth;
    }

    public void setDrawListener(OnDrawListener drawListener) {
        mDrawListener = drawListener;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = 0.5f * getWidth();
        mLineX = mCenterX - (mLineHeight / 2);
        mBottomY = getHeight();
        mCenterY = (mTopY + mBottomY) / 2;

        if (null != mDrawListener) {
            mDrawListener.onReady();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(120, widthMeasureSpec);
        int height = measureDimension(180, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    protected int measureDimension(int defaultSize, int measureSpec) {

        int result = defaultSize;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }

        return result;
    }

    public boolean isStepCompleted() {
        return isStepCompleted;
    }

    public void setStepCompleted(boolean completed) {
        isStepCompleted = completed;
    }

    public int getProgressColor() {
        return mProgressColor;
    }

    public void setProgressColor(int progressColor) {
        mProgressColor = progressColor;
    }

    public int getBarColor() {
        return mBarColor;
    }

    public void setBarColor(int barColor) {
        mBarColor = barColor;
    }

    public void setProgressTextColor(int textColor) {
        mProgressTextColor = textColor;
    }

    public void setProgressStrokeWidth(float width) {
        mProgressStrokeWidth = width;
    }

    public float getCircleRadius() {
        return mCircleRadius;
    }

    public void setCircleRadius(float radius) {
        mCircleRadius = radius;
    }

    public void setHideProgressText(boolean hide) {
        hideProgressText = hide;
    }

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
    }

    public boolean isCurrentStep() {
        return isCurrentStep;
    }

    public void setIsCurrentStep(boolean currentStep) {
        isCurrentStep = currentStep;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (null != mDrawListener) {
            mDrawListener.onReady();
        }
        // bar progress paint
        paint.setAntiAlias(true);
        paint.setColor(mBarColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        // progress paint
        selectedPaint.setAntiAlias(true);
        selectedPaint.setColor(mProgressColor);
        selectedPaint.setStyle(Paint.Style.STROKE);
        selectedPaint.setStrokeWidth(1);

        // progress text
        progressTextPaint.setAntiAlias(true);
        progressTextPaint.setTextSize(mCircleRadius);
        progressTextPaint.setColor(mProgressTextColor);

        paint.setStyle(Paint.Style.FILL);
        selectedPaint.setStyle(Paint.Style.FILL);

        // draw lines
        canvas.drawRect(mLineX, mTopY, mLineX + mProgressStrokeWidth, mCenterY,
                isStepCompleted ? selectedPaint : paint);

        canvas.drawRect(mLineX, mCenterY, mLineX + mProgressStrokeWidth, mBottomY,
                isCurrentStep ? paint : isStepCompleted ? selectedPaint : paint);


        float quarterRadius = mCircleRadius / 4;
        // Draw circles

        canvas.drawCircle(mCenterX, mCenterY, mCircleRadius,
                isStepCompleted ? selectedPaint : paint);

        if (!hideProgressText && null != stepNumber && !stepNumber.isEmpty()) {
            canvas.drawText(stepNumber, mCenterX - quarterRadius,
                    mCenterY + 5 * quarterRadius / 4, progressTextPaint);
        }
        // in current completed position color with alpha
        if (isCurrentStep) {
            selectedPaint.setColor(getColorWithAlpha(mProgressColor, 0.2f));
            canvas.drawCircle(mCenterX, mCenterY, mCircleRadius * 1.3f, selectedPaint);
        }
    }

    public static int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

    public interface OnDrawListener {
        void onReady();
    }
}
