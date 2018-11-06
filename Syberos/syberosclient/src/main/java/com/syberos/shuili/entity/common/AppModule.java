package com.syberos.shuili.entity.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/6.
 */

public class AppModule implements Serializable {
    /**
     * code : 0
     * message : success
     * data : [{"guid":"F0C57C96F31F474C828F6B02F8E24BBC","userTypeCode":"0100","userTypeName":"企事业用户","bisModCode":"2004","bisModName":"安全检查（企）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"D4E57D1E0A8A4AC6928EF9F3C8E3BE5A","userTypeCode":"0100","userTypeName":"企事业用户","bisModCode":"2003","bisModName":"危险源（企）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"35BA365A58CB41AC9042BB220D91BF9C","userTypeCode":"0100","userTypeName":"企事业用户","bisModCode":"2002","bisModName":"事故（企）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"998192F6B0D9495BAE5BAC68B6C612C7","userTypeCode":"0100","userTypeName":"企事业用户","bisModCode":"2001","bisModName":"隐患（企）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"F19C2A36F9184CD0967197EB61DAC6D6","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3010","bisModName":"业务专题（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"8A8D99DC676446AEBBAC95385FFCFECD","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3009","bisModName":"安全评估（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"3EE2F4301BDB4E9A8317BC67C250A507","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3008","bisModName":"监督执法（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"90011160E93F4FF28D4C9663A4F10606","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3007","bisModName":"水利稽察（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"4131467991BF41858FBCCF8A6A599DB5","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3006","bisModName":"工作考核（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"E93D0BF01E69497783D59C27C2EB0A0A","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3005","bisModName":"标准化（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"37AD55F6D1D84869AB91F0FDEEBA67E0","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3004","bisModName":"安全检查（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"0ABC96E0F50544E0A1023DE14C521CAB","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3003","bisModName":"危险源（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"2371CE46F6B64E08B98924D2DD347633","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3002","bisModName":"事故管理（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"},{"guid":"98BCA7B08787453CA7C783EF1844F7CE","userTypeCode":"0101","userTypeName":"水行政用户","bisModCode":"3001","bisModName":"隐患管理（水）","modActive":"1","collTime":"Oct 30, 2018 12:05:53 PM"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * guid : F0C57C96F31F474C828F6B02F8E24BBC
         * userTypeCode : 0100
         * userTypeName : 企事业用户
         * bisModCode : 2004
         * bisModName : 安全检查（企）
         * modActive : 1
         * collTime : Oct 30, 2018 12:05:53 PM
         */

        private String guid;
        private String userTypeCode;
        private String userTypeName;
        private String bisModCode;
        private String bisModName;
        private String modActive;
        private String collTime;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getUserTypeCode() {
            return userTypeCode;
        }

        public void setUserTypeCode(String userTypeCode) {
            this.userTypeCode = userTypeCode;
        }

        public String getUserTypeName() {
            return userTypeName;
        }

        public void setUserTypeName(String userTypeName) {
            this.userTypeName = userTypeName;
        }

        public String getBisModCode() {
            return bisModCode;
        }

        public void setBisModCode(String bisModCode) {
            this.bisModCode = bisModCode;
        }

        public String getBisModName() {
            return bisModName;
        }

        public void setBisModName(String bisModName) {
            this.bisModName = bisModName;
        }

        public String getModActive() {
            return modActive;
        }

        public void setModActive(String modActive) {
            this.modActive = modActive;
        }

        public String getCollTime() {
            return collTime;
        }

        public void setCollTime(String collTime) {
            this.collTime = collTime;
        }
    }
}
