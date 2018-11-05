package com.syberos.shuili.entity;

import java.io.Serializable;

/**
 * Created by jidan on 18-3-19.
 */

public class AppUpdateEntity  implements Serializable {

    /**
     * code : 0
     * message : success
     * data : {"updatInfo":{"guid":"D772CA2696EE48C1A2D20C9AF02EB753","verTitle":"安卓出版发布","verType":"0","verNumber":"1.0","note":"第一版本发布，实现基础功能","releaseTime":"Nov 5, 2018 12:48:48 PM","fileUrl":"mapp/MAPP_BIS_VER/2018-11-05/syberosclient-release (1).apk","deleteTag":"0","releasePersGuid":"df6d697edef74999807854e564a48cd7","releasePersName":"0009"},"md5":"31e2f3e94519b7cecabbc9a22a3adc7b"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * updatInfo : {"guid":"D772CA2696EE48C1A2D20C9AF02EB753","verTitle":"安卓出版发布","verType":"0","verNumber":"1.0","note":"第一版本发布，实现基础功能","releaseTime":"Nov 5, 2018 12:48:48 PM","fileUrl":"mapp/MAPP_BIS_VER/2018-11-05/syberosclient-release (1).apk","deleteTag":"0","releasePersGuid":"df6d697edef74999807854e564a48cd7","releasePersName":"0009"}
         * md5 : 31e2f3e94519b7cecabbc9a22a3adc7b
         */

        private UpdatInfoBean updatInfo;
        private String md5;

        public UpdatInfoBean getUpdatInfo() {
            return updatInfo;
        }

        public void setUpdatInfo(UpdatInfoBean updatInfo) {
            this.updatInfo = updatInfo;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public static class UpdatInfoBean implements Serializable{
            /**
             * guid : D772CA2696EE48C1A2D20C9AF02EB753
             * verTitle : 安卓出版发布
             * verType : 0
             * verNumber : 1.0
             * note : 第一版本发布，实现基础功能
             * releaseTime : Nov 5, 2018 12:48:48 PM
             * fileUrl : mapp/MAPP_BIS_VER/2018-11-05/syberosclient-release (1).apk
             * deleteTag : 0
             * releasePersGuid : df6d697edef74999807854e564a48cd7
             * releasePersName : 0009
             */

            private String guid;
            private String verTitle;
            private String verType;
            private String verNumber;
            private String note;
            private String releaseTime;
            private String fileUrl;
            private String deleteTag;
            private String releasePersGuid;
            private String releasePersName;

            public String getGuid() {
                return guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getVerTitle() {
                return verTitle;
            }

            public void setVerTitle(String verTitle) {
                this.verTitle = verTitle;
            }

            public String getVerType() {
                return verType;
            }

            public void setVerType(String verType) {
                this.verType = verType;
            }

            public String getVerNumber() {
                return verNumber;
            }

            public void setVerNumber(String verNumber) {
                this.verNumber = verNumber;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public String getDeleteTag() {
                return deleteTag;
            }

            public void setDeleteTag(String deleteTag) {
                this.deleteTag = deleteTag;
            }

            public String getReleasePersGuid() {
                return releasePersGuid;
            }

            public void setReleasePersGuid(String releasePersGuid) {
                this.releasePersGuid = releasePersGuid;
            }

            public String getReleasePersName() {
                return releasePersName;
            }

            public void setReleasePersName(String releasePersName) {
                this.releasePersName = releasePersName;
            }
        }
    }
}
