package com.example.testmodule;

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
    public OwnerAreaData getData() {
        return data;
    }

    public void setData(OwnerAreaData data) {
        this.data = data;
    }

    static class PointInfo implements Parcelable {
        //地图点的 id
        private String pointID;
        // 地图点的 value
        private String pointValue;
        // 地图点的 描述 返回 json串
        private String pointDescrtion;

        // 地图点 经度
        private String longitude;
        private String latitude;

        public String getLongitude() {
            return longitude == null ? "" : longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude == null ? "" : latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public PointInfo() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.pointID);
            dest.writeString(this.pointValue);
            dest.writeString(this.pointDescrtion);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
        }

        protected PointInfo(Parcel in) {
            this.pointID = in.readString();
            this.pointValue = in.readString();
            this.pointDescrtion = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
        }

        public static final Creator<PointInfo> CREATOR = new Creator<PointInfo>() {
            @Override
            public PointInfo createFromParcel(Parcel source) {
                return new PointInfo(source);
            }

            @Override
            public PointInfo[] newArray(int size) {
                return new PointInfo[size];
            }
        };

        public String getPointID() {
            return pointID == null ? "" : pointID;
        }

        public void setPointID(String pointID) {
            this.pointID = pointID;
        }

        public String getPointValue() {
            return pointValue == null ? "" : pointValue;
        }

        public void setPointValue(String pointValue) {
            this.pointValue = pointValue;
        }

        public String getPointDescrtion() {
            return pointDescrtion == null ? "" : pointDescrtion;
        }

        public void setPointDescrtion(String pointDescrtion) {
            this.pointDescrtion = pointDescrtion;
        }
    }

    /**
     * 地图显示 各个 坐标数据信息
     */
    static class ProjectInfo implements Parcelable {
        /**
         * 工程名的 id
         */
        private String proID;
        /**
         * 工程名
         */
        private String proName;
        /**
         * 工程名的 问题数量
         */
        private String proCount;


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


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.proID);
            dest.writeString(this.proName);
            dest.writeString(this.proCount);

        }

        public ProjectInfo() {
        }

        protected ProjectInfo(Parcel in) {
            this.proID = in.readString();
            this.proName = in.readString();
            this.proCount = in.readString();
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
        List<PointInfo> pointInfos;
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

        public List<PointInfo> getPointInfos() {
            if (pointInfos == null) {
                return new ArrayList<>();
            }
            return pointInfos;
        }

        public void setPointInfos(List<PointInfo> pointInfos) {
            this.pointInfos = pointInfos;
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

        public OwnerAreaData() {
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
            dest.writeTypedList(this.pointInfos);
            dest.writeString(this.charID);
            dest.writeString(this.charName);
            dest.writeTypedList(this.chartData);
        }

        protected OwnerAreaData(Parcel in) {
            this.ownerTypeID = in.readString();
            this.ownerTypeName = in.readString();
            this.projectInfos = in.createTypedArrayList(ProjectInfo.CREATOR);
            this.pointInfos = in.createTypedArrayList(PointInfo.CREATOR);
            this.charID = in.readString();
            this.charName = in.readString();
            this.chartData = in.createTypedArrayList(ChartData.CREATOR);
        }

        public static final Creator<OwnerAreaData> CREATOR = new Creator<OwnerAreaData>() {
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


    public String getDataId() {
        return dataId == null ? "" : dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }


    /**
     * 直管工程、流域、监管  多个区域的 所有数据
     */
    private OwnerAreaData data;
    /**
     * eg: 隐患 给个id值 知道该数据为隐患数据
     */
    private String dataId;

    public ThematicMapEntry() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.dataId);

    }

    protected ThematicMapEntry(Parcel in) {
        this.data = in.readParcelable(OwnerAreaData.class.getClassLoader());
        this.dataId = in.readString();

    }

    public static final Creator<ThematicMapEntry> CREATOR = new Creator<ThematicMapEntry>() {
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
