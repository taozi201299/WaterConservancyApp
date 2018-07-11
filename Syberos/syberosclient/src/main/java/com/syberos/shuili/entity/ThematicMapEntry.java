package com.syberos.shuili.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/9.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.
 */
public class ThematicMapEntry implements Parcelable {
/**



 */

    /**
     * 地图显示 各个 坐标数据信息
     */
    static class ProjectInfo implements Parcelable {
        /**
         * 区域点的 id
         */
        private String proID;
        /**
         * 工程名
         */
        private String proName;
        /**
         * 区域点的 问题数量
         */
        private String proCount;
        /**
         * 区域点的 问题描述;
         */
        private String proInfo;

        public String getProID() {
            return proID == null ? "" : proID;
        }

        public void setProID(String proID) {
            this.proID = proID;
        }

        public String getProName() {
            return proName == null ? "" : proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getProCount() {
            return proCount == null ? "" : proCount;
        }

        public void setProCount(String proCount) {
            this.proCount = proCount;
        }

        public String getProInfo() {
            return proInfo == null ? "" : proInfo;
        }

        public void setProInfo(String proInfo) {
            this.proInfo = proInfo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.proID);
            dest.writeString(this.proName);
            dest.writeString(this.proCount);
            dest.writeString(this.proInfo);
        }

        public ProjectInfo() {
        }

        protected ProjectInfo(Parcel in) {
            this.proID = in.readString();
            this.proName = in.readString();
            this.proCount = in.readString();
            this.proInfo = in.readString();
        }

        public static final Parcelable.Creator<ProjectInfo> CREATOR = new Parcelable.Creator<ProjectInfo>() {
            @Override
            public ProjectInfo createFromParcel(Parcel source) {
                return new ProjectInfo(source);
            }

            @Override
            public ProjectInfo[] newArray(int size) {
                return new ProjectInfo[size];
            }
        };
    }

    /**
     * 数据以饼状图 显示：
     */
    static class ChartData implements Parcelable {
        //        饼状图每个item的 id
        String charItemID;
        //        饼状图每个item的 name
        String charItemName;
        //        饼状图每个item的 值
        String charItemValue;


        public String getCharItemID() {
            return charItemID == null ? "" : charItemID;
        }

        public void setCharItemID(String charItemID) {
            this.charItemID = charItemID;
        }

        public String getCharItemName() {
            return charItemName == null ? "" : charItemName;
        }

        public void setCharItemName(String charItemName) {
            this.charItemName = charItemName;
        }

        public String getCharItemValue() {
            return charItemValue == null ? "" : charItemValue;
        }

        public void setCharItemValue(String charItemValue) {
            this.charItemValue = charItemValue;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.charItemID);
            dest.writeString(this.charItemName);
            dest.writeString(this.charItemValue);

        }

        public ChartData() {
        }

        protected ChartData(Parcel in) {
            this.charItemID = in.readString();
            this.charItemName = in.readString();
            this.charItemValue = in.readString();

        }

        public static final Parcelable.Creator<ChartData> CREATOR = new Parcelable.Creator<ChartData>() {
            @Override
            public ChartData createFromParcel(Parcel source) {
                return new ChartData(source);
            }

            @Override
            public ChartData[] newArray(int size) {
                return new ChartData[size];
            }
        };
    }

    /**
     * 直管工程、流域、监管
     */
    static class OwnerAreaData implements Parcelable {
        /**
         * 上报问题工程的 id
         */
        String ownerTypeID;
        /**
         * 上报问题工程的 name
         */
        String ownerTypeName;
        /**
         * 上报问题工程的 list
         */
        List<ProjectInfo> projectInfos;
        /**
         * 饼状图的 id
         */
        String charID;
        /**
         * 饼状图的 name
         */
        String charName;
        /**
         * 饼状图的数据 list
         */
        List<ChartData> chartData;

        public String getOwnerTypeID() {
            return ownerTypeID == null ? "" : ownerTypeID;
        }

        public void setOwnerTypeID(String ownerTypeID) {
            this.ownerTypeID = ownerTypeID;
        }

        public String getOwnerTypeName() {
            return ownerTypeName == null ? "" : ownerTypeName;
        }

        public void setOwnerTypeName(String ownerTypeName) {
            this.ownerTypeName = ownerTypeName;
        }

        public List<ProjectInfo> getProjectInfos() {
            if (projectInfos == null) {
                return new ArrayList<>();
            }
            return projectInfos;
        }

        public void setProjectInfos(List<ProjectInfo> projectInfos) {
            this.projectInfos = projectInfos;
        }

        public String getCharID() {
            return charID == null ? "" : charID;
        }

        public void setCharID(String charID) {
            this.charID = charID;
        }

        public String getCharName() {
            return charName == null ? "" : charName;
        }

        public void setCharName(String charName) {
            this.charName = charName;
        }

        public List<ChartData> getChartData() {
            if (chartData == null) {
                return new ArrayList<>();
            }
            return chartData;
        }

        public void setChartData(List<ChartData> chartData) {
            this.chartData = chartData;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ownerTypeID);
            dest.writeString(this.ownerTypeName);
            dest.writeTypedList(this.projectInfos);
            dest.writeString(this.charID);
            dest.writeString(this.charName);
            dest.writeTypedList(this.chartData);
        }

        public OwnerAreaData() {
        }

        protected OwnerAreaData(Parcel in) {
            this.ownerTypeID = in.readString();
            this.ownerTypeName = in.readString();
            this.projectInfos = in.createTypedArrayList(ProjectInfo.CREATOR);
            this.charID = in.readString();
            this.charName = in.readString();
            this.chartData = in.createTypedArrayList(ChartData.CREATOR);
        }

        public static final Parcelable.Creator<OwnerAreaData> CREATOR = new Parcelable.Creator<OwnerAreaData>() {
            @Override
            public OwnerAreaData createFromParcel(Parcel source) {
                return new OwnerAreaData(source);
            }

            @Override
            public OwnerAreaData[] newArray(int size) {
                return new OwnerAreaData[size];
            }
        };
    }

    public List<OwnerAreaData> getDataList() {
        if (dataList == null) {
            return new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<OwnerAreaData> dataList) {
        this.dataList = dataList;
    }

    public String getDataId() {
        return dataId == null ? "" : dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName == null ? "" : dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 直管工程、流域、监管  多个区域的 所有数据
     */
    private List<OwnerAreaData> dataList;
    /**
     * eg: 隐患 给个id值 知道该数据为隐患数据
     */
    private String dataId;
    /**
     * eg: 隐患数据
     */
    private String dataName;

    /**
     * 备用 暂时可以为空
     */
    private String content;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.dataList);
        dest.writeString(this.dataId);
        dest.writeString(this.dataName);
        dest.writeString(this.content);
    }

    public ThematicMapEntry() {
    }

    protected ThematicMapEntry(Parcel in) {
        this.dataList = in.createTypedArrayList(OwnerAreaData.CREATOR);
        this.dataId = in.readString();
        this.dataName = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<ThematicMapEntry> CREATOR = new Parcelable.Creator<ThematicMapEntry>() {
        @Override
        public ThematicMapEntry createFromParcel(Parcel source) {
            return new ThematicMapEntry(source);
        }

        @Override
        public ThematicMapEntry[] newArray(int size) {
            return new ThematicMapEntry[size];
        }
    };
}
