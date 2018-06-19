package com.syberos.shuili.media.record;

import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AudioRecorder implements RecordStrategy {


    private int maxSecond = 60;


    private AudioListener audioListener;
    private MediaRecorder recorder;
    private String fileName;
    private String fileFolder = Environment.getExternalStorageDirectory().getPath() + "/ldhz";

    private boolean isRecording = false;


    @Override
    public void ready() {
        // TODO Auto-generated method stub
        File file = new File(fileFolder);
        if (!file.exists()) {
            file.mkdir();
        }
        fileName = getCurrentDate();
        recorder = new MediaRecorder();
        recorder.setOutputFile(fileFolder + "/" + fileName + ".amr");
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置MediaRecorder的音频源为麦克风
        // 设置音频格式为AAC_ADTS
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        // 设置音频编码为AAC
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);


    }

    // 以当前时间作为文件名
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    private int recodeTime = 0; // 录音时长，如果录音时间太短则录音失败

    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 开启录音计时
     */
    private void startTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isRecording) {
                    recodeTime += 1;
                    LogUtils.d("recodeTime", recodeTime);
                    audioListener.progress(recodeTime);
                    if (recodeTime >= maxSecond) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                stop();
                            }
                        });

                    }
                } else {

                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    private void cancelTimerTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask = null;
        }
    }


    @Override
    public boolean start() {
        // TODO Auto-generated method stub
        boolean bSuccess = false;
        if (!isRecording) {
            try {
                recorder.prepare();
                recorder.start();
                isRecording = true;
                startTimerTask();
                bSuccess = true;
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                audioListener.fail();
                isRecording = false;
                if (recorder != null) {
                    recorder.release();
                }
                recorder = null;
                cancelTimerTask();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                audioListener.fail();
                isRecording = false;
                if (recorder != null) {
                    recorder.release();
                }
                recorder = null;
                cancelTimerTask();
            }
        }

        return bSuccess;
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        if (isRecording) {
            cancelTimerTask();
            if (recodeTime <= 1) {
                ToastUtils.show("录音时长太短");
                audioListener.stop("", recodeTime);
            } else {
                audioListener.stop(getFilePath(), recodeTime);

            }
            recorder.stop();
            recorder.release();
            isRecording = false;
            recodeTime = 0;
        }

    }

    @Override
    public void deleteOldFile() {
        // TODO Auto-generated method stub
        File file = new File(fileFolder + "/" + fileName + ".amr");
        file.deleteOnExit();
    }

    @Override
    public double getAmplitude() {
        // TODO Auto-generated method stub
        if (!isRecording) {
            return 0;
        }
        try {
            return recorder.getMaxAmplitude();
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public String getFilePath() {
        // TODO Auto-generated method stub
        return fileFolder + "/" + fileName + ".amr";
    }

    @Override
    public void setMaxSecond(int maxSecond) {
        this.maxSecond = maxSecond;
    }


    @Override
    public void setAudioListener(AudioListener audioListener) {
        this.audioListener = audioListener;
    }

}
