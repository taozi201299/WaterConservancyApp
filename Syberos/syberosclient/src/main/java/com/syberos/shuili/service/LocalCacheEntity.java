package com.syberos.shuili.service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.syberos.shuili.service.AttachMentInfoEntity;
/**
 * Created by Administrator on 2018/4/14.
 */

public class LocalCacheEntity implements Parcelable {
    /**
     * 记录的唯一标记
     */
   public String seriesKey;
    /**
     * 接口url
     */
    public String url;
    /**
     * 提交参数
     */
    public Map params;
//    /**
//     * 多媒体附件
//     */
//    public List<AttachMentInfoEntity> attachments;
    /**
     * 0 新增数据 1 修改数据
     */
    public int type;

    /**
     * 0  暂存 1 提交
     */
    public int attachType;

    public  int commitType = 0; // 0 post 1 put


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seriesKey);
        dest.writeString(url);
        dest.writeMap(params);
     //   dest.writeList(attachments);
        dest.writeInt(type);
        dest.writeInt(attachType);
        dest.writeInt(commitType);

    }
   public LocalCacheEntity(){

   }
    public LocalCacheEntity(String seriesKey,String url,Map params,List attachments,int type,int attachType,int commitType){
        this.seriesKey = seriesKey;
        this.url = url;
        this.params = params;
       // this.attachments = attachments;
        this.type = type;
        this.attachType = attachType;
        this.commitType = commitType;

    }
    protected LocalCacheEntity(Parcel in){
        this.seriesKey = in.readString();
        this.url = in.readString();
        this.params = in.readHashMap(HashMap.class.getClassLoader());
       // this.attachments = in.readArrayList(ArrayList.class.getClassLoader());
        this.type = in.readInt();
        this.attachType = in.readInt();
        this.commitType = in.readInt();
    }
    public static final Creator<LocalCacheEntity>CREATOR = new Creator<LocalCacheEntity>() {
        @Override
        public LocalCacheEntity createFromParcel(Parcel source) {
            return new LocalCacheEntity(source);
        }

        @Override
        public LocalCacheEntity[] newArray(int size) {
            return new LocalCacheEntity[size];
        }
    };
}
