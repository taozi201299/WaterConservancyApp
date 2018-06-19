package com.syberos.shuili.activity.login;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import com.syberos.shuili.R;
import com.syberos.shuili.view.RelativePopupWindow.RelativePopupWindow;
import com.syberos.shuili.view.WrapListView;

public class LoginRecordSelectPopup extends RelativePopupWindow {

    CardView cardView;
    WrapListView wrapListView;
    private Context mContext;

    public LoginRecordSelectPopup(Context context) {
        mContext = context;
        cardView = (CardView)LayoutInflater.from(context).inflate(
                R.layout.popup_login_record_infos, null);
        setContentView(cardView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        wrapListView = cardView.findViewById(R.id.login_records_list_view);

        // Disable default animation for circular reveal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setAnimationStyle(0);
        }

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                cardView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.dd_menu_out));
            }
        });
    }

    @Override
    public void showOnAnchor(@NonNull View anchor, int vertPos, int horizPos, int x, int y, boolean fitInScreen) {
        super.showOnAnchor(anchor, vertPos, horizPos, x, y, fitInScreen);
        cardView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.dd_menu_in));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            circularReveal(anchor);
//        }
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void circularReveal(@NonNull final View anchor) {
//        final View contentView = getContentView();
//        contentView.post(new Runnable() {
//            @Override
//            public void run() {
//                final int[] myLocation = new int[2];
//                final int[] anchorLocation = new int[2];
//                contentView.getLocationOnScreen(myLocation);
//                anchor.getLocationOnScreen(anchorLocation);
//                final int cx = anchorLocation[0] - myLocation[0] + anchor.getWidth()/2;
//                final int cy = anchorLocation[1] - myLocation[1] + anchor.getHeight()/2;
//
//                contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//                final int dx = Math.max(cx, contentView.getMeasuredWidth() - cx);
//                final int dy = Math.max(cy, contentView.getMeasuredHeight() - cy);
//                final float finalRadius = (float) Math.hypot(dx, dy);
//                Animator animator = ViewAnimationUtils.createCircularReveal(contentView, cx, cy, 0f, finalRadius);
//                animator.setDuration(500);
//                animator.start();
//            }
//        });
//    }

    /**
     * 设置Adapter
     *
     * @param adapter ListView的Adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        wrapListView.setAdapter(adapter);
    }
}