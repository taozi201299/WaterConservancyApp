package com.syberos.shuili.media;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imnjh.imagepicker.ImageLoader;
import com.syberos.shuili.App;

/**
 * Created by Administrator on 2018/5/10.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void bindImage(ImageView imageView, Uri uri, int width, int height) {
//        Glide.with(App.app).load(uri).override(width, height).into(imageView);
        Glide.with(App.app).load(uri).into(imageView);
    }

    @Override
    public void bindImage(ImageView imageView, Uri uri) {
        Glide.with(App.app).load(uri).into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public ImageView createFakeImageView(Context context) {
        return new ImageView(context);
    }
}

