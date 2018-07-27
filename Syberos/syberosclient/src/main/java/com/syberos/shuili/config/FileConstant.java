package com.syberos.shuili.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by jidan on 18-3-21.
 */

public class FileConstant {

    /**
     * 根路径
     */
    public static String rootPath = Environment.getExternalStorageDirectory() + File.separator + "shuili";
    /**
     * 下载路径
     */
    public static String downloadPath = rootPath + File.separator + "download";
    /**
     * 图片路径
     */
    public static String imagePath = downloadPath + File.separator + "image";
    /**
     * 视频路径
     */
    public static String videoPath = downloadPath + File.separator + "video";
    /**
     * 录音保存路径
     */
    public static String recordAudioPath = "/sdcard";
    /**
     * 录音保存路径
     */
    public static String latestApkPath = rootPath +File.separator+ "apk";

    public static String SHLOG=rootPath+File.separator+"log";
    public static String dbPath=rootPath+File.separator+"db";
}

