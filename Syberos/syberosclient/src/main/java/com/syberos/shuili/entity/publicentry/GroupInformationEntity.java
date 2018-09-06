package com.syberos.shuili.entity.publicentry;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/6.
 */

public class GroupInformationEntity<T>implements Serializable {

        private String header;
        private ArrayList<T> children;

        public GroupInformationEntity(String header, ArrayList<T> children) {
            this.header = header;
            this.children = children;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public ArrayList<T> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<T> children) {
            this.children = children;
        }
}
