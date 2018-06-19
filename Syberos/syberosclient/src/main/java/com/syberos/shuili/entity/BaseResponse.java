package com.syberos.shuili.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jidan on 18-3-19.
 */
public class BaseResponse<T> extends AbstractResponse {

    @SerializedName("Head")
    public HeadBean head;
    @SerializedName("Summary")
    public SummaryBean summary;
    @SerializedName("DataSource")
    public DataSourceBean<T> dataSource;

    @Override
    public boolean isSuccess() {
        return null != summary && "100".equals(summary.StatusCode);
    }

    public static class HeadBean implements Serializable {

        public String DataKey;
        public String DEXType;
        public String DataObjects;
        public String DEXTime;
        public String DEXVersion;
        public String Version;
    }

    public static class SummaryBean implements Serializable {

        public String DEXResult;
        public String StatusCode;
        public String Message;
    }

    public static class DataSourceBean<D> implements Serializable {
        public List<TablesBean<D>> Tables;

        public static class TablesBean<B> implements Serializable {
            public String Name;
            public String Key;
            public List<B> Datas;
        }
    }
}

