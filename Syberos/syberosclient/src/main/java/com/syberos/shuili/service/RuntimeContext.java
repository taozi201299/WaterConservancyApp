package com.syberos.shuili.service;

/**
 * Created by jidan on 18-3-16.
 */

public class RuntimeContext {
    private static UserInformationEntity m_UserInfomation;
    public static void setCurrentUserInfo(UserInformationEntity information){
        m_UserInfomation = information;
    }
    public static String getCurrentUserId(){
        return m_UserInfomation.persId;
    }
    public static UserInformationEntity getCurrentUserInfo(){
        return m_UserInfomation;
    }
}
