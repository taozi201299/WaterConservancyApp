package com.syberos.shuili.view.indexListView;


import com.syberos.shuili.entity.userinfo.UserExtendInfo;

import java.util.Comparator;

/**
 * Created by dongjunkun on 2015/7/4.
 * <p>
 * 根据拼音来排列HeadListView中的数据
 */
public class PinyinComparator implements Comparator<UserExtendInfo> {
    @Override
    public int compare(UserExtendInfo c1, UserExtendInfo c2) {

        if (c1.getSortLetter().charAt(0) > 90 || c2.getSortLetter().charAt(0) < 65) {
            //A - Z
            return 1;
        }
//        else if (c1.getSortLetter().equals("#") || c2.getSortLetter().equals("@")) {
//            return 1;
//        }
        else {
            return c1.getSortLetter().compareTo(c2.getSortLetter());
        }
    }
}
