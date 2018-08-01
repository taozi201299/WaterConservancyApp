package com.syberos.shuili.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.syberos.shuili.R;
import com.syberos.shuili.entity.userinfo.UserExtendInfo;
import com.syberos.shuili.utils.BitmapUtil;
import com.syberos.shuili.view.TextDrawable;

import java.util.List;

/**
 * Created by songting on 16-3-16.
 */
public class AddMemberAdapter extends BaseAdapter {
    public static final int PRARENT_LAYOUT = 0;
    public static final int CHIRD_LAYOUT = 1;

    private List<UserExtendInfo> data;
    private Context mContext;

    public AddMemberAdapter(List<UserExtendInfo> data, Context mContext, boolean isChoice) {
        this.data = data;
        this.mContext = mContext;
    }

    public void setData(List<UserExtendInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return  CHIRD_LAYOUT;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_layout_addmember, null);
                viewHolder.username = convertView.findViewById(R.id.tv_name);
                viewHolder.ivIcon = convertView.findViewById(R.id.iv_icon);
            convertView.setTag(viewHolder);
        }
        UserExtendInfo user = data.get(position);
        viewHolder.username.setText(user.getUserName());
        loadIcon(viewHolder.ivIcon, user);

        return convertView;
    }

    /**
     * 加载用户icon
     *
     * @param image
     * @param userInformation
     */
    private void loadIcon(final ImageView image, final UserExtendInfo userInformation) {
        if (TextUtils.isEmpty(userInformation.getUserName())) {
            if (TextUtils.isEmpty(userInformation.getUserName())) {
                userInformation.setUserName("未知");
            }
            TextDrawable textDrawable = BitmapUtil.drawableFactory(mContext, userInformation, 13);
            image.setImageDrawable(textDrawable);
            return;
        }

        Glide.with(mContext).load(userInformation.getIconUrl()).asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(new BitmapImageViewTarget(image) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                image.setImageDrawable(circularBitmapDrawable);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                TextDrawable textDrawable = BitmapUtil.drawableFactory(mContext, userInformation, 13);
                image.setImageDrawable(textDrawable);
                Bitmap bitmap = BitmapUtil.getImageBitmap(image);
                BitmapUtil.saveBitmap(bitmap, userInformation.getIconUrl(), true);
            }
        });


    }

    public static class ViewHolder {
        private TextView username;
        private ImageView ivIcon;
    }
}
