package com.syberos.shuili.view;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.syberos.shuili.R;
import com.syberos.shuili.utils.Singleton;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.media.record.Mp3Recorder;
import com.syberos.shuili.media.record.RecordStrategy;
import com.syberos.shuili.utils.DialogHelper;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by jidan on 18-3-22.
 */

public class AudioEditView extends LinearLayout implements View.OnClickListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener{

    private RecordStrategy mAudioRecorder = Mp3Recorder.getInstance();
    private HashMap<String,View> audioList;
    private Context mContext;
    private LinearLayout ll_audio_container;
    private ImageView iv_audio_btn;
    private TextView tv_label;
    private ClearEditText et_content;
    private MediaPlayer audioPlayer = new MediaPlayer();
    private boolean audioPrepared = false;
    private boolean playAudioRecord = false;

    private AudioViewHolder mHolder;
    private MultimediaView.RunningMode runningMode = MultimediaView.RunningMode.ADD_EDIT_MODE;
    public static final int REQUEST_CODE_IMAGE = 101;
    public static final int REQUEST_CODE_CAMERA = 102;
    private static final int RC_CAMERA_PERM = 126;
    private static final int RC_RECORD_PERM = 124;
    String[]permissions = {Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public AudioEditView(Context context) {
        this(context, null);
    }

    public AudioEditView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudioEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.AudioEditView, 0, 0);
        audioPlayer.setOnPreparedListener(this);
        audioPlayer.setOnCompletionListener(this);
        audioList = new HashMap<>();
        View view = View.inflate(mContext, R.layout.edit_audio_item, this);
        ll_audio_container = (LinearLayout) view.findViewById(R.id.ll_audio);
        iv_audio_btn = (ImageView) view.findViewById(R.id.iv_audio_btn);
        tv_label = (TextView) view.findViewById(R.id.tv_label);
        et_content = (ClearEditText) view.findViewById(R.id.edit_content);
        iv_audio_btn.setOnClickListener(this);


        try {
            String label = a.getString(R.styleable.AudioEditView_view_label);
            if (null != label) {
                tv_label.setText(label);
            }

            int labelWidth = (int)a.getDimension(R.styleable.AudioEditView_view_labelWidth, 0f);
            if (labelWidth > 0) {
                tv_label.setWidth(labelWidth);
            }

            String content = a.getString(R.styleable.AudioEditView_view_content);
            if (null != content) {
                et_content.setText(content);
            }

            String content_hint = a.getString(R.styleable.AudioEditView_view_content_hint);
            if (null != content_hint) {
                et_content.setHint(content_hint);
            }

        } finally {
            a.recycle();
        }
    }

    @AfterPermissionGranted(RC_RECORD_PERM)
    private void requestRecordPermission() {
        AudioViewHolder viewHolder = null;
        final AudioViewHolder finalViewHolder = viewHolder;
        if (EasyPermissions.hasPermissions(mContext, permissions)) {

           Dialog dialog =  DialogHelper.showRecordDialog(mContext, 0, mAudioRecorder, new DialogHelper.RecordListener() {
                @Override
                public void recordFile(String file, int length) {
                    if (!TextUtils.isEmpty(file)) {
                        saveAudio(file, length);
                        finalViewHolder.rec.stopListening();
                    }
                }
            });
            View view = View.inflate(mContext,R.layout.view_audio_item,null);
            viewHolder = new AudioViewHolder(mContext,view,null,dialog);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions((Activity) mContext, "app需要使用系统录音功能功能",
                    RC_RECORD_PERM, permissions);
        }

    }

    private void saveAudio(String file, int length) {
        if (!checkAttachSize()) {
            return;
        }
        View view = null;
        if (audioList.containsKey(file)) {
            view = audioList.get(file);
        } else {
            view = View.inflate(mContext, R.layout.view_audio_item, null);
        }
        AudioViewHolder audioViewHolder = new AudioViewHolder(mContext, view, null,null);
        audioViewHolder.tv_audio_text.setVisibility(VISIBLE);
        audioViewHolder.iv_delete.setVisibility(VISIBLE);
        view.setEnabled(false);
        if (length == 0) {
            audioViewHolder.tv_audio_text.setText("下载中");
        } else if (length > 0) {
            audioViewHolder.tv_audio_text.setText(String.valueOf(length));
            view.setEnabled(true);
        } else if (length == -1) {
            audioViewHolder.tv_audio_text.setText("下载失败");
        }
        audioViewHolder.iv_delete.setTag(file);
        if (runningMode == MultimediaView.RunningMode.READ_ONLY_MODE) {
            audioViewHolder.iv_delete.setVisibility(GONE);
        }

        if (!audioList.containsKey(file)) {
            ll_audio_container.addView(view);
            audioList.put(file, view);
        }
        audioPlayer.stop();
        audioPlayer.reset();
        audioPrepared = false;
        try {
            audioPlayer.setDataSource(file);
        } catch (IOException e) {
        }
    }

    private boolean checkAttachSize() {
        if (audioList.size() >= Singleton.INSTANCE.MAX_ATTACHMENT_NUMBER) {
            ToastUtils.show("提示：最多支持 5 个附件");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_audio_btn:
                requestRecordPermission();
                break;
        }

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (((Activity)mContext).isFinishing()) return;
        audioPrepared = true;
        playAudioRecord = true;
        playAttachmentAudio();
    }

    private void playAttachmentAudio() {
        if (audioPrepared && playAudioRecord) {
            audioPlayer.start();
            AnimationDrawable ani = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_voice, mContext.getTheme());
            (mHolder.tv_audio_text).setCompoundDrawablesWithIntrinsicBounds(ani, null, null, null);
            ani.start();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (((Activity)mContext).isFinishing()) return;
        audioPlayer.stop();
        mHolder.tv_audio_text.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_audio_play_2, 0, 0, 0);
    }
    public class AudioViewHolder extends CommonAdapter.ViewHolder {
        private TextView tv_audio_text;
        private ImageView iv_delete;
        SpeechRecognizer rec;
        private Dialog dialog;

        public AudioViewHolder(Context context, View itemView, ViewGroup parent , final Dialog dialog) {
            super(context, itemView, parent);
            tv_audio_text = (TextView) itemView.findViewById(R.id.attachment_audio_text);
            iv_delete = (ImageView) itemView.findViewById(R.id.attachment_audio_delete);
            iv_delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = (String) v.getTag();
                    audioList.remove(key);
                    removeFile(key);
                    updateAudioItem();

                }
            });
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHolder = AudioViewHolder.this;
                    try {
                        if(!audioPlayer.isPlaying())
                            audioPlayer.prepareAsync();
                    }catch (RuntimeException e){

                    }finally {
                        return;
                    }

                }
            });
            SpeechUtility.createUtility(getContext(), SpeechConstant.APPID + "=5a153eeb");
            rec = SpeechRecognizer.createRecognizer(getContext(), null);
            rec.setParameter(SpeechConstant.NET_TIMEOUT, "10000");
            rec.setParameter(SpeechConstant.RESULT_TYPE, "plain");
            rec.startListening(new RecognizerListener() {
                @Override
                public void onVolumeChanged(int i, byte[] bytes) {
                }

                @Override
                public void onBeginOfSpeech() {
                }

                @Override
                public void onEndOfSpeech() {
                    dialog.dismiss();
                    rec.stopListening();
                }

                @Override
                public void onResult(RecognizerResult recognizerResult, boolean b) {
                    et_content.append(recognizerResult.getResultString());
                }

                @Override
                public void onError(SpeechError speechError) {
                    dialog.dismiss();
                    rec.stopListening();
                    ToastUtils.show("语音识别错误");
                }

                @Override
                public void onEvent(int i, int i1, int i2, Bundle bundle) {
                }
            });

        }
    }
    private void updateAudioItem(){
        ll_audio_container.removeAllViews();
        for (Map.Entry entry : audioList.entrySet()) {
            View view =(View) entry.getValue();
            ll_audio_container.addView(view);
        }

    }
    private void removeFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }
    public void setModel(MultimediaView.RunningMode runningMode){
        this.runningMode = runningMode;
        if(runningMode == MultimediaView.RunningMode.READ_ONLY_MODE) {
            et_content.setEnabled(false);
            et_content.setFocusable(false);
            iv_audio_btn.setVisibility(View.GONE);
            et_content.setHint("");
        }

    }

    public void setLabelText(String label) {
        this.tv_label.setText(label);
    }

    public void setEditText(String text) {
        this.et_content.setHint("");
        this.et_content.setText(text);
    }

    public void setAudio(String file, int length) {
        saveAudio(file, length);
    }

    public String getEditText() {
        return this.et_content.getText().toString();
    }

    public List<String> getAudioList() {
        return new ArrayList<>(this.audioList.keySet());
    }

    public void setEditTextHint(String text) {
        this.et_content.setHint(text);
    }

    public String getEditTextHint() {
        return this.et_content.getHint().toString();
    }
}
