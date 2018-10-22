package com.syberos.shuili.view;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ResolutionNode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.cjt2325.cameralibrary.CameraActivity;
import com.cjt2325.cameralibrary.JCameraView;
import com.imnjh.imagepicker.PickerConfig;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.R;
import com.syberos.shuili.App;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.media.GlideImageLoader;
import com.syberos.shuili.media.PreviewActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.media.record.Mp3Recorder;
import com.syberos.shuili.media.record.RecordStrategy;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.DialogHelper;
import com.syberos.shuili.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.Request;
import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by jidan on 18-3-21.
 */

public class MultimediaView extends LinearLayout implements View.OnClickListener,MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    ImageView ivMultimediaAddBtn;
    private Context mContext;
    private PopupWindow popupWindow;
    private LinearLayout ll_audio_container;
    private RecyclerView recyclerView_image;
    private ImageViewAdatper imageViewAdatper;
    private ArrayList<LocalAttachment> images;
    private HashMap<String, View> audioList;
    private ImageView iv_open_camera;
    private ImageView iv_open_file;
    RunningMode runningMode = RunningMode.ADD_EDIT_MODE;
    private MediaPlayer audioPlayer = new MediaPlayer();
    private boolean audioPrepared = false;
    private boolean playAudioRecord = false;
    private TextView tvPhotoInfo = null;

    private RecordStrategy mAudioRecorder = Mp3Recorder.getInstance();
    public static final int REQUEST_CODE_IMAGE = 101;
    public static final int REQUEST_CODE_CAMERA = 102;
    private static final int RC_CAMERA_PERM = 126;
    private static final int RC_RECORD_PERM = 124;

    private static final int RC_FLODER_PERM = 125;

    private static final int MEDIA_MAX_SIZE = 5;

    public MultimediaView(Context context) {
        this(context, null);
    }

    public MultimediaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultimediaView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        audioPlayer.stop();
        audioPlayer.release();
        audioPlayer.setOnCompletionListener(this);
        audioPlayer.setOnPreparedListener(this);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.MultimediaView, 0, 0);

        images = new ArrayList<>();
        audioList = new HashMap<>();
        View view = View.inflate(mContext, R.layout.attachment_add_layout, this);
        ivMultimediaAddBtn = (ImageView) view.findViewById(R.id.multimediaAddBtn);
        iv_open_camera = (ImageView) view.findViewById(R.id.multimedia_menu_start_camera);
        iv_open_file = (ImageView) view.findViewById(R.id.multimedia_menu_start_video);
        ll_audio_container = (LinearLayout) view.findViewById(R.id.audio_container);
        recyclerView_image = (RecyclerView) view.findViewById(R.id.recyclerView);
        tvPhotoInfo = (TextView) view.findViewById(R.id.tvPhotoInfo);
        LinearLayoutManager ms = new LinearLayoutManager(mContext);
        ms.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_image.setLayoutManager(ms);
        int space = 8;
        recyclerView_image.addItemDecoration(new SpacesItemDecoration(space));
        imageViewAdatper = new ImageViewAdatper(mContext, R.layout.attachment_image_item, images);
        ivMultimediaAddBtn.setOnClickListener(this);
        iv_open_camera.setOnClickListener(this);
        iv_open_file.setOnClickListener(this);
        recyclerView_image.setAdapter(imageViewAdatper);

        try {
            String label = a.getString(R.styleable.MultimediaView_mv_label);
            if (null != label) {
                tvPhotoInfo.setText(label);
            }

        } finally {
            a.recycle();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.multimediaAddBtn:
                break;
            case R.id.multimedia_menu_start_camera:
                requestCameraPermission();
                break;
            case R.id.multimedia_menu_start_video:
                startFile();
                break;

        }
    }
    /**
     * 选择相册图片
     */
    private void toPhotoPicker(Activity activity) {
        initPhotoPicker();
        SImagePicker
                .from(activity)
                .pickText(com.imnjh.imagepicker.R.string.general_ok)
                .maxCount(3)
                .rowCount(3)
                .showCamera(false)
                .pickMode(SImagePicker.MODE_IMAGE)
                .forResult(REQUEST_CODE_IMAGE);
    }
    /**
     * 初始化相册选择器
     */
    private void initPhotoPicker() {
        SImagePicker.init(new PickerConfig.Builder().setAppContext(App.globalContext()).setImageLoader(new GlideImageLoader())
                .setToolbaseColor(getResources().getColor(R.color.white)).build());
    }
    private void showPopWindow(){
        ImageView iv_startCamera;
        ImageView iv_startAudio;
        ImageView iv_openFile;
        if (popupWindow == null) {
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View contentView = layoutInflater.inflate(R.layout.multimedia_pop_window_layout, null);
            popupWindow = new PopupWindow(contentView, width, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            //拍照按钮
            iv_startCamera = (ImageView) contentView.findViewById(R.id.multimedia_menu_start_camera);
            iv_startCamera.setVisibility(VISIBLE);
            iv_startCamera.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestCameraPermission();
                    popupWindow.dismiss();
                }
            });

            //声音按钮
            iv_startAudio = (ImageView) contentView.findViewById(R.id.multimedia_menu_start_audio);
            iv_startAudio.setVisibility(VISIBLE);

            iv_startAudio.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("multimedia menu", "onClick: start audio");
                    requestRecordPermission();
                    popupWindow.dismiss();
                }
            });
            //本地文件按钮
            iv_openFile = (ImageView) contentView.findViewById(R.id.multimedia_menu_start_video);
            iv_openFile.setVisibility(VISIBLE);
            iv_openFile.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("multimedia menu", "onClick: start video");
                    startFile();
                    popupWindow.dismiss();
                }
            });
        }
        ColorDrawable cd = new ColorDrawable(0x222222);
        popupWindow.setBackgroundDrawable(cd);
        popupWindow.setAnimationStyle(R.style.bottom_layout_in_out_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(ivMultimediaAddBtn, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        backgroundAlpha(mContext, 0.5f);
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                backgroundAlpha(mContext, 1f);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 透明度
     *                An alpha value to apply to this entire window.
     *                An alpha of 1.0 means fully opaque and 0.0 means fully transparent
     */
    public void backgroundAlpha(Context context, float bgAlpha) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = bgAlpha;
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            activity.getWindow().setAttributes(lp);
        }
    }

    @AfterPermissionGranted(RC_RECORD_PERM)
    private void requestRecordPermission() {
        String[]permissions = {Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(mContext, permissions)) {

            Dialog dialog = DialogHelper.showRecordDialog(mContext, 0, mAudioRecorder, new DialogHelper.RecordListener() {
                @Override
                public void recordFile(String file, int length) {
                    if (!TextUtils.isEmpty(file)) {
                       saveAudio(file, length);
                    }
                }
            });
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions((Activity) mContext, "app需要使用系统录音功能功能",
                    RC_RECORD_PERM, permissions);
        }

    }

    private void requestStoragePermission(){
        String[]permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(mContext, permissions)) {
            toPhotoPicker((Activity) mContext);
        } else {
            // Ask for one permission

            EasyPermissions.requestPermissions((Activity) mContext, "需要使用系统相机功能",
                    RC_FLODER_PERM, permissions);
        }
    }
    @AfterPermissionGranted(RC_CAMERA_PERM)
    private void requestCameraPermission() {
        String[]permissions = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(mContext, permissions)) {
            Intent intent = new Intent(mContext, CameraActivity.class);
            intent.putExtra("createFeatures", JCameraView.BUTTON_STATE_BOTH);
            ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE_CAMERA);
        } else {
            // Ask for one permission

            EasyPermissions.requestPermissions((Activity) mContext, "需要使用系统相机功能",
                    RC_CAMERA_PERM, permissions);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_IMAGE == requestCode) {
            if (null != data) addFormGallery(data);
        } else if (REQUEST_CODE_CAMERA == requestCode) {
            if (null != data) addFromCamera(data,null);
        }
    }
    private void startFile(){
        requestStoragePermission();
    }
    private  void addFormGallery(Intent data){
        if(!checkAttachSize()){
            return;
        }
        ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
        if (null != list && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                LocalAttachment attachment = new LocalAttachment();
                attachment.localFile = new File(list.get(i));
                attachment.filePath = list.get(i);
                images.add(attachment);
                if(images.size() >= MEDIA_MAX_SIZE){
                    break;
                }
            }
        }
        imageViewAdatper.setData(images);
        imageViewAdatper.notifyDataSetChanged();

    }
    private void addFromCamera(Intent data,LocalAttachment localAttachment){
        if(!checkAttachSize()){
            return;
        }
        LocalAttachment attachment = new LocalAttachment();
        String path = data.getStringExtra("path");
        String desc = data.getStringExtra("desc") ;
        if (TextUtils.isEmpty(path)) return;
        attachment.localFile = new File(path);
        attachment.filePath = path;
        attachment.desc = desc == null ?"":desc;
        if(path.endsWith("mp4")){
            attachment.type = LocalAttachmentType.VIDEO;
//            if(!checkVideo()){
//                return;
//            }
        }else {
            attachment.type = LocalAttachmentType.IMAGE;
        }
        if(localAttachment != null){
            attachment.bExist = localAttachment.bExist;
        }
        if(!images.contains(localAttachment)) {
            images.add(attachment);
        }else {
            int index = images.indexOf(localAttachment);
            images.remove(localAttachment);

            images.add(index,attachment);
        }
        imageViewAdatper.setData(images);
        imageViewAdatper.notifyDataSetChanged();

    }
    private void saveAudio(String file,int length){
        if(!checkAttachSize()){
            return;
        }
        if(audioList.size()== 1){
            ToastUtils.show("最多支持1个音频文件");
            return;
        }
        View view = View.inflate(mContext,R.layout.view_audio_item,null);
        AudioViewHolder audioViewHolder = new AudioViewHolder(mContext,view,null);
        audioViewHolder.tv_audio_text.setVisibility(VISIBLE);
        audioViewHolder.iv_delete.setVisibility(VISIBLE);
        audioViewHolder.tv_audio_text.setText(String.valueOf(length));
        audioViewHolder.iv_delete.setTag(file);
        ll_audio_container.addView(view);
        audioList.put(file,view);
        audioPlayer.stop();
        audioPlayer.reset();
        audioPrepared = false;
        try {
            audioPlayer.setDataSource(file);
        } catch (IOException e) {
        }

    }
    private boolean checkVideo(){
        for(LocalAttachment localAttachment :images){
            if(localAttachment.type == LocalAttachmentType.VIDEO) {
                ToastUtils.show("最多支持1个视频文件");
                return false;
            }
        }
        return true;
    }
    private boolean checkAttachSize(){
        if(images.size() >= MEDIA_MAX_SIZE){
            ToastUtils.show("最多支持5个附件");
        }
        return  images.size()< MEDIA_MAX_SIZE;
    }
    public class AudioViewHolder extends CommonAdapter.ViewHolder{
        private TextView tv_audio_text;
        private ImageView iv_delete;

        public AudioViewHolder(Context context, View itemView, ViewGroup parent) {
            super(context, itemView, parent);
            tv_audio_text = (TextView)itemView.findViewById(R.id.attachment_audio_text);
            iv_delete = (ImageView)itemView.findViewById(R.id.attachment_audio_delete);
            if(runningMode == RunningMode.READ_ONLY_MODE){
                iv_delete.setVisibility(GONE);
            }
            iv_delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = (String)v.getTag();
                    audioList.remove(key);
                    removeFile(key);
                    updateAudioItem();
                    updateAttachCount();

                }
            });

        }
    }
    public ArrayList<LocalAttachment> getBinaryFile(){
        return images;
    }
    private void removeFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }
    private void updateAudioItem(){
        ll_audio_container.removeAllViews();
        for (Map.Entry entry : audioList.entrySet()) {
            View view =(View) entry.getValue();
            ll_audio_container.addView(view);
        }

    }
    private void updateAttachItem(){
        imageViewAdatper.setData(images);
        imageViewAdatper.notifyDataSetChanged();
    }
    private void updateAttachCount(){

    }
    public  enum LocalAttachmentType {
        DOC,
        IMAGE,
        AUDIO,
        VIDEO


    }
    public static class LocalAttachment implements Serializable {
        public File localFile;
        public long size;
        public LocalAttachmentType type;
        public boolean bExist;
        public String fileName;
        public String filePath;
        public String desc;


        @Override
        public boolean equals(Object object) {
            if(localFile == null)return false;
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            LocalAttachment that = (LocalAttachment) object;

            return localFile.equals(that.localFile);
        }

        @Override
        public int hashCode() {
            return localFile.hashCode();
        }
    }

    public class ImageViewAdatper extends CommonAdapter<LocalAttachment>{

        public ImageViewAdatper(Context context, int layoutId,List<LocalAttachment> datas) {
            super(context, layoutId,datas);
        }

        @Override
        public void convert(ViewHolder holder, final LocalAttachment localAttachment) {
            final ImageView iv_attachImage = (ImageView)holder.getView(R.id.attachment_image);
//            ViewGroup.LayoutParams params = iv_attachImage.getLayoutParams();
//            params.height=200;
//            params.width =200;
//            iv_attachImage.setLayoutParams(params);
            final ImageView iv_attachVideo = holder.getView(R.id.attachment_video);
            ImageView iv_delete = holder.getView(R.id.attachment_image_delete);
            final TextView tv_attachment_text = holder.getView(R.id.attachment_text);
            TextView tv_attach_time = holder.getView(R.id.tv_attach_time);
            TextView tv_attach_desc = holder.getView(R.id.tv_attach_desc);

            if(runningMode == RunningMode.ADD_EDIT_MODE) {
                Glide.with(mContext).load(localAttachment.localFile).into(iv_attachImage);
                iv_attachVideo.setVisibility(localAttachment.localFile.getName().contains("mp4") ? View.VISIBLE : View.GONE);
                tv_attach_time.setText(CommonUtils.getCurrentDate());
                tv_attach_desc.setText(localAttachment.desc);

            }
            if(runningMode == RunningMode.READ_ONLY_MODE){
                iv_delete.setVisibility(GONE);
                String url = GlobleConstants.strAppIP + "/mappservice/downloadMedia";
                HashMap<String,String>params = new HashMap<>();
                params.put("medUrl",localAttachment.filePath);

                HttpUtils.getInstance().requestNet_download(url, params, url, new FileCallback("/sdcard/app") {
                    @Override
                    public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                        if(localAttachment.bExist) {
                            localAttachment.filePath = GlobleConstants.strIP + "/" +localAttachment.filePath;
                            iv_attachImage.setEnabled(true);
                            tv_attachment_text.setVisibility(GONE);
                            Glide.with(mContext).load(file.getPath()).into(iv_attachImage);
                            iv_attachVideo.setVisibility(localAttachment.localFile.getName().contains("mp4") ? View.VISIBLE : View.GONE);

                        }else {
                            iv_attachImage.setEnabled(false);
                            iv_attachVideo.setVisibility(GONE);
                            tv_attachment_text.setVisibility(VISIBLE);

                        }
                    }
                });

            }
            iv_delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    images.remove(localAttachment);
                    removeFile(localAttachment.localFile.getPath());
                    updateAttachItem();
                }
            });
            iv_attachImage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(localAttachment.type == LocalAttachmentType.VIDEO) {
                        Intent intent = new Intent();
                        intent.putExtra("attachment",localAttachment);
                        intent.setClass(mContext, PreviewActivity.class);
                        ((Activity)mContext).startActivity(intent);
                    }else {
                        loadImageSimpleTarget(localAttachment.filePath);
                    }
                }
            });

        }
    }
    private SimpleTarget<Bitmap> mSimpleTarget = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> animation) {
            ImageDialog imageDialog = new ImageDialog(mContext,0,0,0, resource);
            imageDialog.show();
        }
    };

    private void loadImageSimpleTarget(String url) {
        Glide.with( mContext)
                .load( url )
                .asBitmap()
                .into(mSimpleTarget);
    }
    /**
     * 运行模式
     */
    public enum RunningMode {
        /**
         * 只读模式，没有add的按钮
         */
        READ_ONLY_MODE,
        /**
         * 编辑模式，可以增删
         */
        ADD_EDIT_MODE,
    }

    /**
     * 设置运行模式
     *
     * @param runningMode 运行模式，可选值有  READ_ONLY_MODE, ADD_EDIT_MODE,
     */
    public void setRunningMode(RunningMode runningMode) {
        switch (runningMode) {
            case READ_ONLY_MODE:
                this.runningMode = RunningMode.READ_ONLY_MODE;
                findViewById(R.id.layoutMGVHeader).setVisibility(View.GONE);
                break;
            case ADD_EDIT_MODE:
            default:
                this.runningMode = RunningMode.ADD_EDIT_MODE;
                break;
        }
    }

    /**
     * 设置数据源
     *
     * @param data 数据源
     */
    public void setData(List<LocalAttachment> data) {
        images.clear();
        audioList.clear();
        if (data == null || data.isEmpty()) return;
        for (LocalAttachment item : data) {
            if(item.type == LocalAttachmentType.VIDEO || item.type == LocalAttachmentType.IMAGE){
                Intent intent = new Intent();
                intent.putExtra("path",item.filePath);
                addFromCamera(intent,item);
            }else {
                saveAudio(item.localFile.getPath(),(int)item.size);
            }
        }


    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        if (((Activity)mContext).isFinishing()) return;
        audioPrepared = true;
        playAttachmentAudio();
    }

    private void playAttachmentAudio() {
        if (audioPrepared && playAudioRecord) {
            audioPlayer.start();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (((Activity)mContext).isFinishing()) return;
    }

    public class SpacesItemDecoration extends ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }

    public void setLabelText(String text) {
        if (null != tvPhotoInfo) {
            tvPhotoInfo.setText(text);
        }
    }
}
