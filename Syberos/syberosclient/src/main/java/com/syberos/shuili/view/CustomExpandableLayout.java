package com.syberos.shuili.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.syberos.shuili.R;

/**
 * Created by jidan on 18-3-22.
 */

public class CustomExpandableLayout extends LinearLayout {
    private ImageView ivArrow;
    private View child;


    public CustomExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        post(new Runnable() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            @Override
            public void run() {
                if (ivArrow != null)
                    ivArrow.callOnClick();//默认设置子控件为隐藏状态
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //            final Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.test);//测试动画效果
        child = findViewById(R.id.expandable_layout_child);//ids里定义的id
//            child.setAnimation(a);
        ivArrow = (ImageView) findViewById(R.id.expandable_layout_arrow);//ids里定义的id
        if (child != null && ivArrow != null) {
            if ((child.getVisibility() == View.GONE)) {
                dismissChild();
            } else {
                showChild();
            }
            for(int i = 0; i < this.getChildCount(); i++){
                View v = this.getChildAt(i);
                if(v.getId() != R.id.expandable_layout_child) {
                    v.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if ("on".equals(ivArrow.getContentDescription().toString())) {
                                dismissChild();
                            } else {
                                showChild();
                            }
                        }
                    });
                    break;
                }
            }
        }
    }

    public void showChild() {
        if (child != null) {
            child.setVisibility(View.VISIBLE);
//                            child.startAnimation(a);
            ivArrow.setImageResource(R.mipmap.drop_down_selected_icon);
            ivArrow.setContentDescription("on");
        }

    }

    public void dismissChild() {
        if (child != null)
            child.setVisibility(View.GONE);
        ivArrow.setContentDescription("off");
        ivArrow.setImageResource(R.mipmap.drop_down_unselected_icon);
    }

}
