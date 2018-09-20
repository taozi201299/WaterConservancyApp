package com.syberos.shuili.entity.test;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/20.
 */

public class PostBean implements Serializable {

    /**
     * meta : {"success":false,"message":null}
     * data : null
     */

    private MetaBean meta;
    private Object data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * success : false
         * message : null
         */

        private boolean success;
        private Object message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }
    }
}
