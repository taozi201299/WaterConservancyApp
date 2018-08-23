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
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.utils.BitmapUtil;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.view.TextDrawable;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by dongjunkun on 2015/7/4.
 */
public class IndexListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<UserExtendInformation> persions;

    private Context mContext;


    public IndexListAdapter(List<UserExtendInformation> persions, Context mContext) {
        this.persions = persions;
        this.mContext = mContext;
    }
    public void setData(List<UserExtendInformation> data) {
        this.persions = data;
        notifyDataSetChanged();
    }
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (HeadViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent_layout, null);
            viewHolder = new HeadViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        //必须加""连接，否则会出现空指针异常，原因是setText方法传入整型参数会被解析成资源类型
        viewHolder.mHeadText.setText("" + persions.get(position).getSortLetter().charAt(0));
        return convertView;
    }

    /**
     * 更新列表
     *
     * @param persions
     */
    public void updateList(List<UserExtendInformation> persions) {
        this.persions = persions;
        LogUtils.d("通讯录", persions.size() + "--------------------------");
        notifyDataSetChanged();
    }

    @Override
    public long getHeaderId(int i) {
        return persions.get(i).getSortLetter().charAt(0);
    }

    @Override
    public int getCount() {
        return persions.size();
    }

    @Override
    public Object getItem(int position) {
        return persions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        UserExtendInformation user = persions.get(position);
        String name = user.getPersName();
        viewHolder.mName.setText(name);
        viewHolder.unitName.setText(user.getOrgName());
        viewHolder.departName.setText(user.getDepName());

        loadIcon(viewHolder.ivIcon, user);

        return convertView;
    }

    /**
     * 加载用户icon
     *
     * @param image
     * @param userInformation
     */
    private void loadIcon(final ImageView image, final UserExtendInformation userInformation) {
        if (TextUtils.isEmpty(userInformation.getPersName())) {
            if (TextUtils.isEmpty(userInformation.getPersName())) {
                userInformation.setPersName("未知");
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


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_unitName)
        TextView unitName;
        @BindView(R.id.tv_department)
        TextView departName;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class HeadViewHolder {
        @BindView(R.id.headText)
        TextView mHeadText;

        HeadViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    //根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = persions.get(i).getSortLetter();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
