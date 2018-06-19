package com.syberos.shuili.media;

/**
 * Created by jidan on 18-3-28.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.view.FlexibleVideoView;
import com.syberos.shuili.view.MultimediaView;

/**
 * Company: SyberOS BeiJing
 * Project: SDIS
 * Created by 秘振博 on 2016/9/7.
 */
public class PreviewActivity extends Activity {
    /**
     * 多媒体类
     */
    private MultimediaView.LocalAttachment me;
    /**
     * 各类型 多媒体的容器 上的组件按纽
     */
    private ImageView ivWave, ivPhoto, ivCancle;
    /**
     * 播放 重播按钮
     */
    private ImageButton ibtnPlayAgain, ibtnPlay;
    /**
     * 底部描述信息
     */
    private TextView tvTitle, tvTime, tvDescription;
    /**
     * 多媒体类型类
     */
    private MultimediaView.LocalAttachmentType type;
    /**
     * 自定义VideoView类型--根据视频尺寸显示播放视频
     */
    private FlexibleVideoView videoView;
    /**
     * 当前手机播放音量
     */
    private int volume;
    /**
     * 创建多媒体类
     */
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private BitmapDrawable drawable;
    /**
     * 播放按钮布局--用于设置播放按钮的背景
     */
    private RelativeLayout rlView;
    /**
     * 获取类型
     *
     * @return 类型
     */
    public MultimediaView.LocalAttachmentType getType(MultimediaView.LocalAttachment me) {
        return me.type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        me = (MultimediaView.LocalAttachment) getIntent().getSerializableExtra("attachment");
        try {
            type = getType(me);
            if (type == MultimediaView.LocalAttachmentType.VIDEO) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      setContentView(R.layout.activity_preview_media_layout);
        initView();
        initListener();
        initData();
    }
    public void initListener() {
        ibtnPlay.setOnClickListener(new ButtonClickListener());
        ibtnPlayAgain.setOnClickListener(new ButtonClickListener());
        ivCancle.setOnClickListener(new ButtonClickListener());
    }

    public void initData() {
        try {
            if (me != null) {
                type = getType(me);
                Bitmap bitmap = BitmapFactory.decodeFile(me.localFile.getAbsolutePath());
                if (type == MultimediaView.LocalAttachmentType.IMAGE) {
                    //                图片
                    ivPhoto.setVisibility(View.VISIBLE);
                    ivPhoto.setImageBitmap(bitmap);
                } else if (type == MultimediaView.LocalAttachmentType.AUDIO) {
                } else if (type == MultimediaView.LocalAttachmentType.VIDEO) {
                    //                视频
                    MediaMetadataRetriever retr = new MediaMetadataRetriever();
                    retr.setDataSource(me.localFile.getAbsolutePath());


                    ibtnPlay.setVisibility(View.VISIBLE);
                    bitmap = ThumbnailUtils.createVideoThumbnail(me.localFile.getAbsolutePath(), MediaStore.Video.Thumbnails.MINI_KIND);
                    drawable = new BitmapDrawable(null, bitmap);
                    drawable.setDither(true);
                    View thumbView = findViewById(R.id.Thumbnail);
                    if (thumbView != null) {
                        thumbView.setVisibility(View.VISIBLE);
                        thumbView.setBackgroundDrawable(drawable);
                    }
                    /**
                     * 方案1，全屏播放
                     */
                    WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    int screenWidth = windowManager.getDefaultDisplay().getWidth();
                    int screenHeight = windowManager.getDefaultDisplay().getHeight();
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenWidth, screenHeight);
                    videoView.setLayoutParams(lp);
                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            rlView.setBackgroundDrawable(drawable);
                            videoView.stopPlayback();
                            videoView.setVisibility(View.GONE);
                            ibtnPlayAgain.setVisibility(View.VISIBLE);
                        }
                    });
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化布局
     */
    public void initView() {

        ibtnPlay = (ImageButton) findViewById(R.id.ivPlay);
        ibtnPlayAgain = (ImageButton) findViewById(R.id.ivPlayAgin);
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        ivCancle = (ImageView) findViewById(R.id.ivCancle);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        videoView = (FlexibleVideoView) findViewById(R.id.video_view);
        rlView = (RelativeLayout) findViewById(R.id.rl_view);

//      先将组件隐藏，当用到那个再显示哪个
        ibtnPlay.setVisibility(View.GONE);
        ibtnPlayAgain.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);

    }

    /**
     * Button 监听
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == ivCancle) {
                stopPlay();
                finish();
            } else if (v == ibtnPlay) {
                if (type == MultimediaView.LocalAttachmentType.AUDIO) {
                } else {
                    playVideoMedia();
                }
            } else if (v == ibtnPlayAgain) {
                if (type == MultimediaView.LocalAttachmentType.AUDIO) {
                } else {
                    playVideoMedia();
                }
            }
        }
    }

    /**
     * 播放视频
     */
    private void playVideoMedia() {
        ibtnPlay.setVisibility(View.GONE);
        ibtnPlayAgain.setVisibility(View.GONE);
        rlView.setBackground(null);
        videoView.setVisibility(View.VISIBLE);
        if (me.localFile.getAbsoluteFile() != null) {
            // 播放相应的视频
            videoView.setMediaController(new MediaController(PreviewActivity.this));
            videoView.setVideoURI(Uri.parse(me.localFile.getAbsolutePath()));
        }
        videoView.start();
    }



    Visualizer v;

    @Override
    protected void onPause() {
        super.onPause();
        stopPlay();
    }




    private void stopPlay() {
        if (v != null)
            v.setEnabled(false);
        if (mediaPlayer != null && me.type == MultimediaView.LocalAttachmentType.VIDEO) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        } else if (videoView != null && me.type == MultimediaView.LocalAttachmentType.VIDEO) {
            videoView.stopPlayback();
        }
    }
}
