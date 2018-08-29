package com.syberos.shuili.config;

import com.syberos.shuili.App;
import com.syberos.shuili.SyberosManagerImpl;

/**
 * Created by Administrator on 2018/8/28.
 */

public class BusinessConfig {
    public static  int getOrgLevel(){
        int count = getNum(App.orgJurd);
        switch (count){
            case 12:
                return 1;
            case 10:
                return 2;
            case 8:
                return 3;
            case 6:
                return 4;
            default:
                return 1 ;
        }


    }

    private static int getNum(String src){
        int count = 0;
        char[] array = src.toCharArray();
        int size = array.length;
        for(int i = size -1 ; i >0; i--){
            if(String.valueOf(array[i]).equals("0")){
                count ++;
            }else {
                break;
            }
        }
        return count;


    }
}


