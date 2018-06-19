package com.syberos.shuili.media.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.naman14.androidlame.AndroidLame;
import com.naman14.androidlame.LameBuilder;
import com.syberos.shuili.config.FileConstant;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @description:
 * @author: ryan
 * @contact: bingh@outlook.com
 * @file: Mp3Recorder
 * @time: 2018/1/12 16:24
 */

public class Mp3Recorder implements RecordStrategy {

    private int minBuffer;
    private int inSamplerate = 8000;
    private String fileName;
    private String fileFolder = FileConstant.recordAudioPath;


    private int maxSecond = 60;


    private AudioListener audioListener;


    boolean isRecording = false;
    private static final String TAG = Mp3Recorder.class.getSimpleName();
    AudioRecord audioRecord;
    AndroidLame androidLame;
    FileOutputStream outputStream;
    private static Mp3Recorder mp3Recorder;

    private int recodeTime = 0; // 录音时长，如果录音时间太短则录音失败
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler(Looper.getMainLooper());

    private Mp3Recorder() {

    }

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


    public static Mp3Recorder getInstance() {
        if (mp3Recorder == null) {
            mp3Recorder = new Mp3Recorder();
        }
        return mp3Recorder;
    }


    @Override
    public void ready() {
        recodeTime = 0;
        File file = new File(fileFolder);
        if (!file.exists()) {
            file.mkdirs();
        }
        fileName = getCurrentDate();
        minBuffer = AudioRecord.getMinBufferSize(inSamplerate, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        Log.d(TAG, "Initialising audio recorder..");
        audioRecord = new AudioRecord(
                MediaRecorder.AudioSource.MIC, inSamplerate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, minBuffer * 2);

    }

    // 以当前时间作为文件名
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str.concat(".mp3");
    }

    @Override
    public boolean start() {
        isRecording = true;
        //5 seconds data
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "creating short buffer array");
                short[] buffer = new short[inSamplerate * 2 * 5];

                // 'mp3buf' should be at least 7200 bytes long
                // to hold all possible emitted data.
                Log.d(TAG, "creating mp3 buffer");
                byte[] mp3buffer = new byte[(int) (7200 + buffer.length * 2 * 1.25)];

                try {
                    File file = new File(fileFolder, fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    outputStream = new FileOutputStream(new File(fileFolder, fileName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "Initialising Andorid Lame");
                androidLame = new LameBuilder()
                        .setInSampleRate(inSamplerate)
                        .setOutChannels(1)
                        .setOutBitrate(32)
                        .setOutSampleRate(inSamplerate)
                        .build();

                Log.d(TAG, "started audio recording");
                Log.d(TAG, "Recording...");
                audioRecord.startRecording();
                startTimerTask();
                int bytesRead = 0;

                while (isRecording) {

                    Log.d(TAG, "reading to short array buffer, buffer sze- " + minBuffer);
                    bytesRead = audioRecord.read(buffer, 0, minBuffer);
                    Log.d(TAG, "bytes read=" + bytesRead);

                    if (bytesRead > 0) {

                        Log.d(TAG, "encoding bytes to mp3 buffer..");
                        int bytesEncoded = androidLame.encode(buffer, buffer, bytesRead, mp3buffer);
                        Log.d(TAG, "bytes encoded=" + bytesEncoded);

                        if (bytesEncoded > 0) {
                            try {
                                Log.d(TAG, "writing mp3 buffer to outputstream with " + bytesEncoded + " bytes");
                                outputStream.write(mp3buffer, 0, bytesEncoded);
                            } catch (IOException e) {
                                e.printStackTrace();
                                audioListener.fail();
                                cancelTimerTask();
                            }
                        }
                    }
                }

                Log.d(TAG, "stopped recording");
                Log.d(TAG, "Recording stopped");

                Log.d(TAG, "flushing final mp3buffer");
                int outputMp3buf = androidLame.flush(mp3buffer);
                Log.d(TAG, "flushed " + outputMp3buf + " bytes");

                if (outputMp3buf > 0) {
                    try {
                        Log.d(TAG, "writing final mp3buffer to outputstream");
                        outputStream.write(mp3buffer, 0, outputMp3buf);
                        Log.d(TAG, "closing output stream");
                        outputStream.close();
                        Log.d(TAG, "Output recording saved in " + fileFolder + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                        audioListener.fail();
                        cancelTimerTask();
                    }
                }

                Log.d(TAG, "releasing audio recorder");
                audioRecord.stop();
                audioRecord.release();

                Log.d(TAG, "closing android lame");
                androidLame.close();

                isRecording = false;


            }
        }).start();
        return false;
    }

    @Override
    public void stop() {
        if (isRecording) {
            isRecording = false;
            cancelTimerTask();
            if (recodeTime <= 2) {
                ToastUtils.show("录音时长太短");
                audioListener.stop("", recodeTime);
            } else {
                audioListener.stop(getFilePath(), recodeTime);

            }

        }

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
    public void deleteOldFile() {
        File file = new File(fileFolder + "/" + fileName);
        file.deleteOnExit();

    }

    @Override
    public double getAmplitude() {
        return 0;
    }

    @Override
    public String getFilePath() {
        return fileFolder + "/" + fileName;
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
