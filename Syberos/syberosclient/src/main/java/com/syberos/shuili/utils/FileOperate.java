package com.syberos.shuili.utils;

import android.os.Environment;

import com.syberos.shuili.App;

import java.io.File;

/**
 * Created by jidan on 18-3-7.
 */

public class FileOperate {
    public final int iLogFolder = 0;
    public final int iDBFolder = 1;
    public final int iCrashFolder = 2;
    public final int iMediaFolder = 3;
    public static String createFolder(int type){
        String strFolder ="";
        switch (type){
            case 0:
               strFolder =  "/data/data/" +  App.globalContext().getPackageName() + "/log";
                break;
            case 1:
                strFolder =  "/data/data/" +  App.globalContext().getPackageName() + "/databases";
               // strFolder = "/sdcard/";
                break;
            case 2:
             //   strFolder =  "/data/data/" +  App.globalContext().getPackageName() + "/crash";
                strFolder = "/sdcard/";
                break;
            case 3:
                strFolder =  "/data/data/" +  App.globalContext().getPackageName() + "/media";
                break;
            default:
                break;
        }
        File file = new File(strFolder);
        if(!file.exists()){
            file.mkdirs();
        }
       return strFolder;
    }
}
