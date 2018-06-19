package com.syberos.shuili.utils;

import android.util.Log;

/**
 * @description: 字符串处理
 * @author: ryan
 * @contact: bingh@outlook.com
 * @file: StringUtils
 * @time: 2017/5/10 17:25
 */

public class StringUtils {
    /**
     * 处理中英文字符问题
     *
     * @param etstring
     * @return
     */
    public static int calculateLength(String etstring) {
        if (etstring == null) {
            return 0;
        }
        char[] ch = etstring.toCharArray();

        int varlength = 0;
        for (int i = 0; i < ch.length; i++) {
            // changed by zyf 0825 , bug 6918，加入中文标点范围 ，
            if ((ch[i] >= 0x2E80 && ch[i] <= 0xFE4F) || (ch[i] >= 0xA13F && ch[i] <= 0xAA40) || ch[i] >= 0x80) { // 中文字符范围0x4e00 0x9fbb
                varlength = varlength + 2;
            } else {
                varlength++;
            }
        }
        Log.d("TextChanged", "varlength = " + varlength);
        // 这里也可以使用getBytes,更准确嘛
//         varlength = etstring.getBytes(CharSet.forName("GBK")).lenght;// 编码根据自己的需求，注意u8中文占3个字节...
        return varlength;
    }


    /**
     * 根据用户名生成缩写
     *
     * @param name
     * @return
     */
    public static String generateName(String name) {
        if (name == null) {
            return "未知";
        }
        name = name.trim();
        return name.length() <= 2 ? name : name.substring(name.length() - 2, name.length());
    }

    /**
     * 判断是否是大写A-Z
     *
     * @param a
     * @return
     */
    public static boolean judgeLetter(char a) {

        return a <= 90 && a >= 65;
    }
}
