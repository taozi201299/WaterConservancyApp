package com.syberos.shuili.media;;

import android.media.MediaPlayer;

import com.syberos.shuili.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

/**
 * @description:
 * @author: ryan
 * @contact: bingh@outlook.com
 * @file: AudioUtils
 * @time: 2017/12/12 19:59
 */

public class AudioUtils {

    /**
     * 得到amr的时长
     *
     * @param filePath
     * @return
     */
    public static int getAudioDuration(String filePath) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            int duration = mediaPlayer.getDuration();
            return duration / 1000;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 得到amr的时长
     *
     * @param file
     * @return amr文件时间长度
     * @throws IOException
     */
    public static int getAudioDuration(File file) throws IOException {
        long duration = -1;
        int[] packedSize = {12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0,
                0, 0};
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            // 文件的长度
            long length = file.length();
            // 设置初始位置
            int pos = 6;
            // 初始帧数
            int frameCount = 0;
            int packedPos = -1;
            // 初始数据值
            byte[] datas = new byte[1];
            while (pos <= length) {
                randomAccessFile.seek(pos);
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = length > 0 ? ((length - 6) / 650) : 0;
                    break;
                }
                packedPos = (datas[0] >> 3) & 0x0F;
                pos += packedSize[packedPos] + 1;
                frameCount++;
            }
            // 帧数*20
            duration += frameCount * 20;
        } finally {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
        return (int) ((duration / 1000) + 1);
    }

    /**
     * not work for amr
     *
     * @param mUri
     * @return
     */
    public static String getRingDuring(String mUri) {
        String duration = null;
        android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();
        try {
            if (mUri != null) {
                HashMap<String, String> headers = null;
                if (headers == null) {
                    headers = new HashMap<String, String>();
                    headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
                }
                mmr.setDataSource(mUri, headers);
            }
            duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
        } catch (Exception ex) {
        } finally {
            mmr.release();
        }
        LogUtils.d("ryan", "duration " + duration);
        return duration;
    }

}
