package com.syberos.shuili.entity.thematic.hidden;

import com.google.gson.annotations.SerializedName;
import com.syberos.shuili.network.MetaData;

import java.io.Serializable;
import java.util.List;
public class _HiddenEntry extends MetaData {

    private HiddenItemEntry totleEntry;
    private List<HiddenItemEntry> hiddenItemEntryList;

    @Override
    public String toString() {
        return "_HiddenEntry{" +
                "totleEntry=" + totleEntry +
                ", hiddenItemEntryList=" + hiddenItemEntryList +
                '}';
    }

    public HiddenItemEntry getTotleEntry() {
        return totleEntry;
    }

    public void setTotleEntry(HiddenItemEntry totleEntry) {
        this.totleEntry = totleEntry;
    }

    public List<HiddenItemEntry> getHiddenItemEntryList() {
        return hiddenItemEntryList;
    }

    public void setHiddenItemEntryList(List<HiddenItemEntry> hiddenItemEntryList) {
        this.hiddenItemEntryList = hiddenItemEntryList;
    }

    protected class HiddenItemEntry implements Serializable {
        /**
         * ENG_GUID : c98c6d7aa8784adb8684947a50101314
         * ENG_LAT : 37.916475
         * ENG_LONG : 107.074607
         * ENG_NAME : 大家好，我是中国第一美女陈晓洁！
         * HIDDGRA1RECTQUA : 0
         * HIDDGRAD0LATEQUA : 0
         * HIDDGRAD0LISTQUA : 0
         * HIDDGRAD0RECTQUA : 0
         * HIDDGRAD0TOTALQUA : 0
         * HIDDGRAD1LATEQUA : 0
         * HIDDGRAD1LISTQUA : 0
         * HIDDGRAD1TOTALQUA : 1
         * HIDDLATEQUA : 0
         * HIDDLISTQUA : 0
         * HIDDRECTQUA : 0
         * HIDDTOTALQUA : 1
         */

        private String ENG_GUID;
        private double ENG_LAT;
        private double ENG_LONG;
        private String ENG_NAME;
        private int HIDDGRA1RECTQUA;
        private int HIDDGRAD0LATEQUA;
        private int HIDDGRAD0LISTQUA;
        private int HIDDGRAD0RECTQUA;
        private int HIDDGRAD0TOTALQUA;
        private int HIDDGRAD1LATEQUA;
        private int HIDDGRAD1LISTQUA;
        private int HIDDGRAD1TOTALQUA;
        private int HIDDLATEQUA;
        private int HIDDLISTQUA;
        private int HIDDRECTQUA;
        private int HIDDTOTALQUA;

        public String getENG_GUID() {
            return ENG_GUID;
        }

        public void setENG_GUID(String ENG_GUID) {
            this.ENG_GUID = ENG_GUID;
        }

        public double getENG_LAT() {
            return ENG_LAT;
        }

        public void setENG_LAT(double ENG_LAT) {
            this.ENG_LAT = ENG_LAT;
        }

        public double getENG_LONG() {
            return ENG_LONG;
        }

        public void setENG_LONG(double ENG_LONG) {
            this.ENG_LONG = ENG_LONG;
        }

        public String getENG_NAME() {
            return ENG_NAME;
        }

        public void setENG_NAME(String ENG_NAME) {
            this.ENG_NAME = ENG_NAME;
        }

        public int getHIDDGRA1RECTQUA() {
            return HIDDGRA1RECTQUA;
        }

        public void setHIDDGRA1RECTQUA(int HIDDGRA1RECTQUA) {
            this.HIDDGRA1RECTQUA = HIDDGRA1RECTQUA;
        }

        public int getHIDDGRAD0LATEQUA() {
            return HIDDGRAD0LATEQUA;
        }

        public void setHIDDGRAD0LATEQUA(int HIDDGRAD0LATEQUA) {
            this.HIDDGRAD0LATEQUA = HIDDGRAD0LATEQUA;
        }

        public int getHIDDGRAD0LISTQUA() {
            return HIDDGRAD0LISTQUA;
        }

        public void setHIDDGRAD0LISTQUA(int HIDDGRAD0LISTQUA) {
            this.HIDDGRAD0LISTQUA = HIDDGRAD0LISTQUA;
        }

        public int getHIDDGRAD0RECTQUA() {
            return HIDDGRAD0RECTQUA;
        }

        public void setHIDDGRAD0RECTQUA(int HIDDGRAD0RECTQUA) {
            this.HIDDGRAD0RECTQUA = HIDDGRAD0RECTQUA;
        }

        public int getHIDDGRAD0TOTALQUA() {
            return HIDDGRAD0TOTALQUA;
        }

        public void setHIDDGRAD0TOTALQUA(int HIDDGRAD0TOTALQUA) {
            this.HIDDGRAD0TOTALQUA = HIDDGRAD0TOTALQUA;
        }

        public int getHIDDGRAD1LATEQUA() {
            return HIDDGRAD1LATEQUA;
        }

        public void setHIDDGRAD1LATEQUA(int HIDDGRAD1LATEQUA) {
            this.HIDDGRAD1LATEQUA = HIDDGRAD1LATEQUA;
        }

        public int getHIDDGRAD1LISTQUA() {
            return HIDDGRAD1LISTQUA;
        }

        public void setHIDDGRAD1LISTQUA(int HIDDGRAD1LISTQUA) {
            this.HIDDGRAD1LISTQUA = HIDDGRAD1LISTQUA;
        }

        public int getHIDDGRAD1TOTALQUA() {
            return HIDDGRAD1TOTALQUA;
        }

        public void setHIDDGRAD1TOTALQUA(int HIDDGRAD1TOTALQUA) {
            this.HIDDGRAD1TOTALQUA = HIDDGRAD1TOTALQUA;
        }

        public int getHIDDLATEQUA() {
            return HIDDLATEQUA;
        }

        public void setHIDDLATEQUA(int HIDDLATEQUA) {
            this.HIDDLATEQUA = HIDDLATEQUA;
        }

        public int getHIDDLISTQUA() {
            return HIDDLISTQUA;
        }

        public void setHIDDLISTQUA(int HIDDLISTQUA) {
            this.HIDDLISTQUA = HIDDLISTQUA;
        }

        public int getHIDDRECTQUA() {
            return HIDDRECTQUA;
        }

        public void setHIDDRECTQUA(int HIDDRECTQUA) {
            this.HIDDRECTQUA = HIDDRECTQUA;
        }

        public int getHIDDTOTALQUA() {
            return HIDDTOTALQUA;
        }

        public void setHIDDTOTALQUA(int HIDDTOTALQUA) {
            this.HIDDTOTALQUA = HIDDTOTALQUA;
        }
    }
}
