package com.syberos.shuili.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/4/16.
 */

public class AccidentInformationEntity implements Parcelable {
    /**
     * 事故单位类型
     */
    public String acciWiunType;
    /**
     * 发生时间
     */
    public String occuTime;
    /**
     * 事故类别
     */
    public String acciCate;
    /**
     * 失踪人数
     */
    public String casNum;
    /**
     * 父GUID
     */
    public String pid;
    /**
     * 采集时间
     */
    public String collTime;
    /**
     * 重伤人数
     */
    public String serInjNum;
    /**
     * GUID
     */
    public String id;
    /**
     * 事故简要情况
     */
    public String acciSitu;
    /**
     * 是否已电话上报
     */
    public String ifPhoRep;
    /**
     *事故单位GUID
     */
    public String acciWindGuid;
    /**
     * 是否责任事故
     */
    public String ifRespAcci;
    /**
     * 直接经济损失
     */
    public String econLoss;

    public AccidentInformationEntity(String acciWiunType,String occuTime,String acciCate,
                                  String casNum,String pid,String collTime,
                                  String serInjNum,String id,String acciSitu,
                                  String ifPhoRep,String acciWindGuid,String ifRespAcci,String econLoss){
        this.acciWiunType = acciWiunType;
        this.occuTime = occuTime;
        this.acciCate = acciCate;
        this.casNum = casNum;
        this.pid = pid;
        this.collTime = collTime;
        this.serInjNum = serInjNum;
        this.id = id;
        this.acciSitu = acciSitu;
        this.ifPhoRep = ifPhoRep;
        this.acciWindGuid = acciWindGuid;
        this.ifRespAcci = ifRespAcci;
        this.econLoss = econLoss;

    }
    public AccidentInformationEntity(Parcel in){
        acciWiunType = in.readString();
        occuTime = in.readString();
        acciCate = in.readString();
        casNum = in.readString();
        pid = in.readString();
        collTime = in.readString();
        serInjNum = in.readString();
        id = in.readString();
        acciSitu = in.readString();
        ifPhoRep = in.readString();
        acciWindGuid = in.readString();
        ifRespAcci = in.readString();
        econLoss = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(acciWiunType);
            dest.writeString(occuTime);
            dest.writeString(acciCate);
            dest.writeString(casNum);
            dest.writeString(pid);
            dest.writeString(collTime);
            dest.writeString(serInjNum);
            dest.writeString(id);
            dest.writeString(acciSitu);
            dest.writeString(ifPhoRep);
            dest.writeString(acciWindGuid);
            dest.writeString(ifRespAcci);
            dest.writeString(econLoss);
    }
    public static final Creator<AccidentInformationEntity>CREATOR = new Creator<AccidentInformationEntity>() {
        @Override
        public AccidentInformationEntity createFromParcel(Parcel source) {
            return new AccidentInformationEntity(source);
        }

        @Override
        public AccidentInformationEntity[] newArray(int size) {
            return new AccidentInformationEntity[size];
        }
    };
}
