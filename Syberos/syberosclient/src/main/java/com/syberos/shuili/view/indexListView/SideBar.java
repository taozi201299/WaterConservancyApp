package com.syberos.shuili.view.indexListView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import com.syberos.shuili.utils.LogUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class SideBar extends View {

    //触摸事件
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;


    private int height;
    private int firstPos;

    public List<String> getLetters() {
        return letters;
    }

    //    public static String[] letters = {"",""};
    public List<String> letters = new ArrayList<String>();

    private TextView mTextDialog;

    private Context mContext;
    //设置选中
    private int choose = -1;

    //设置画笔
    private Paint paint = new Paint();

    //构造函数
    public SideBar(Context context) {
        super(context);
        mContext = context;
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    //设置dialog显示字母
    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }


    /**
     * 重写onDraw方法
     * <p/>
     * 获取焦点 改变背景颜色
     */
    int singleHeight = 0;
    int p = 27;//26个字母加#号

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (letters.size() == 0)
            return;
        // 获取焦点改变背景颜色.
        height = getHeight();
//        int p = letters.size() <= 26 ? 26 : letters.size();
        singleHeight = height / p;// 获取每一个字母的高度

        int size = letters.size();

        firstPos = (height - (size * singleHeight)) / 2;

        int width = getWidth(); // 获取对应宽度

        for (int i = 0; i < size; i++) {
            paint.setColor(Color.rgb(33, 65, 98));
            // paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.DEFAULT);
            paint.setAntiAlias(true);
            paint.setTextSize(spToPx(12));
            // 选中的状态
            if (i == choose) {
                paint.setColor(Color.parseColor("#019DD5"));
                paint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(letters.get(i)) / 2;
            float yPos = firstPos + singleHeight * i;
            LogUtils.d("canvas.drawText", "xPos=" + xPos);
            LogUtils.d("canvas.drawText", "yPos=" + yPos);
            canvas.drawText(letters.get(i), xPos, yPos, paint);
            paint.reset();// 重置画笔
        }

    }

    public void setData(TreeSet<String> data) {
        Iterator ite = data.iterator();
        letters.clear();
        while (ite.hasNext()) {
            letters.add(ite.next().toString());
        }
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        final int action = event.getAction();
        //点击获取Y坐标
        final float y = event.getY();
        LogUtils.d("dispatchTouchEvent", "y=" + y);
        final int oldChoose = choose;

        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        int size = letters.size();
        //点击y坐标所占总高度的比例*letters数组的长度就等于点击b中的个数.

        LogUtils.d("dispatchTouchEvent", "letters.size=" + letters.size());
        LogUtils.d("dispatchTouchEvent", "p=" + p);
        int endPosition = firstPos + (size - 1) * singleHeight;
        LogUtils.d("dispatchTouchEvent", "firstPos=" + firstPos);
        LogUtils.d("dispatchTouchEvent", "endPosition=" + endPosition);

        int position = firstPos + singleHeight * size;
        LogUtils.d("dispatchTouchEvent", "position=" + position);
        if (y < firstPos || y > (endPosition+singleHeight)) {
            LogUtils.d("dispatchTouchEvent", "y > position return true");
            setBackgroundDrawable(new ColorDrawable(0x00000000));
            choose = -1;
            //
            invalidate();

            if (mTextDialog != null) {
                mTextDialog.setVisibility(View.INVISIBLE);
            }

            return true;
        }


        int c = (int) ((y - firstPos) / singleHeight);
        LogUtils.d("dispatchTouchEvent", "c=" + c);
        switch (action) {

            case MotionEvent.ACTION_UP:

                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;
                //
                invalidate();

                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }

                break;

            default:
//                setBackgroundResource(R.drawable.sidebar_bg);

                if (oldChoose != c) {
                    if (c >= 0 && c < letters.size()) {

                        if (listener != null) {
                            listener.onTouchingLetterChanged(letters.get(c));
                        }

                        //dialog显示选中的字母
                        if (mTextDialog != null) {
                            mTextDialog.setText(letters.get(c));
                            mTextDialog.setVisibility(View.VISIBLE);

                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 对话公开的方法
     */
    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }


    /**
     * 定义接口  触摸自定义View字母时 改变监听
     */

    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

    public int spToPx(int px) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px, getResources().getDisplayMetrics()));
    }
}
