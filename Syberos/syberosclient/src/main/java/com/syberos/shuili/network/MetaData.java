package com.syberos.shuili.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MetaData implements Serializable {
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean isSuccess;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
