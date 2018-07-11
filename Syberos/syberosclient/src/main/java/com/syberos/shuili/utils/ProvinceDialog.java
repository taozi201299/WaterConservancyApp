package com.syberos.shuili.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.ProvinceJsonBean;
import com.syberos.shuili.entity.ProvinceJsonBean;

import org.json.JSONArray;

import com.syberos.shuili.listener.ProvinceCall;

import java.util.ArrayList;


/**
 * Created by BZB on 2018/7/10.
 * Project: Syberos.
 * Package：com.syberos.shuili.utils.
 */
public class ProvinceDialog {
    ProvinceCall provinceCall;
    Context context;

    public ProvinceDialog(Context context, ProvinceCall provinceCall) {
        this.context = context;
        this.provinceCall = provinceCall;

        initJsonData();
    }


    private ArrayList<ProvinceJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<ProvinceJsonBean>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<ProvinceJsonBean>>> options3Items = new ArrayList<>();

    public void showProvinceDialog() {
        showPickerView();
    }

    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get(option2).getPickerViewText() + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                tvOptions.setText(tx);
//                provinceCall.successGetAreaValue(tx);
                if (options3Items.get(options1).get(option2).get(options3).getAD_CODE().equals(GlobleConstants.ALL_COUNTER)) {
                    if (options2Items.get(options1).get(option2).getAD_CODE().equals(GlobleConstants.ALL_PROVINCE)) {
                        provinceCall.successGetAreaValue(options1Items.get(options1));
                    } else {
                        provinceCall.successGetAreaValue(options2Items.get(options1).get(option2));

                    }
                } else {
                    provinceCall.successGetAreaValue(options3Items.get(options1).get(option2).get(options3));

                }

            }
        }).setDividerColor(Color.GRAY).setTextColorCenter(Color.GRAY).setContentTextSize(18).setOutSideCancelable(false).build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();

//        OptionsPickerView pvOptions=new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String text = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2) +
//                        options3Items.get(options1).get(options2).get(options3);
////                mTvAddress.setText(text);
//            }
//        }).setTitleText("")
//                .setDividerColor(Color.GRAY)
//                .setTextColorCenter(Color.GRAY)
//                .setContentTextSize(13)
//                .setOutSideCancelable(false)
//                .build();
//          /*pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
//        pvOptions.show();
    }


    private void initJsonData() {   //解析数据
        options1Items.clear();
        options2Items.clear();
        options3Items.clear();
        ProvinceJsonBean beanChina = new ProvinceJsonBean();
        ProvinceJsonBean beanHoleProvince = new ProvinceJsonBean();
        ProvinceJsonBean beanHoleCounty = new ProvinceJsonBean();
        ArrayList<ProvinceJsonBean> prvinceList = new ArrayList<>();
        ArrayList<ArrayList<ProvinceJsonBean>> countyList = new ArrayList<>();
//        UP_AD_GUID 上级 guid
//        AD_CODE 行政编码
//        AD_NAME 行政名称
//        AD_GRAD  行政级别
//        GUID 本级的guid
//        PATHNAME 省名称
//        LEVELS  行政级别（以省为1级）
//        PATH 最高省级的节点
        beanChina.setAD_CODE(GlobleConstants.ALL_CHINA);
        beanChina.setAD_NAME("全国");

        beanHoleProvince.setAD_CODE(GlobleConstants.ALL_PROVINCE);
        beanHoleProvince.setAD_NAME("全省/市");

        beanHoleCounty.setAD_CODE(GlobleConstants.ALL_COUNTER);
        beanHoleCounty.setAD_NAME("全县/区");

        prvinceList.add(beanHoleProvince);
        options2Items.add(0, prvinceList);

        prvinceList = new ArrayList<>();
        prvinceList.add(beanHoleCounty);
        countyList.add(prvinceList);
        options3Items.add(0, countyList);
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
        String JsonData = JsonFileReader.getJson(context, "province_data_shuili.json");
        ArrayList<ProvinceJsonBean> jsonBeanList = parseData(JsonData);//用 Gson 转成实体
        for (int i = 0; i < jsonBeanList.size(); i++) {
            if (jsonBeanList.get(i).getLEVELS() == 1) {
                options1Items.add(jsonBeanList.get(i));
            }
        }
        options1Items.add(0, beanChina);
        jsonBeanList.removeAll(options1Items);
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        ArrayList<ProvinceJsonBean> cityList = new ArrayList<>();//该省的城市列表（第二级）
        ArrayList<ArrayList<ProvinceJsonBean>> provinceAreaList = new ArrayList<>();//该省的所有地区列表（第三极）
        cityList.clear();
        provinceAreaList.clear();
        for (int i = 1; i < options1Items.size(); i++) {
            cityList = new ArrayList<>();
            cityList.clear();
            for (int j = 1; j < jsonBeanList.size(); j++) {//遍历省份

                if (jsonBeanList.get(j).getLEVELS() == 2) {
                    if (jsonBeanList.get(j).getUP_AD_GUID().equals(options1Items.get(i).getGUID())) {
                        cityList.add(jsonBeanList.get(j));
                    }
                }
            }
            cityList.add(0, beanHoleProvince);
            options2Items.add(cityList);

            jsonBeanList.removeAll(options2Items);
        }
        provinceAreaList.clear();
        for (int i = 1; i < options1Items.size(); i++) {
            provinceAreaList = new ArrayList<>();
            provinceAreaList.clear();
            for (int j = 1; j < options2Items.get(i).size(); j++) {
                cityList = new ArrayList<>();
                cityList.clear();
                for (ProvinceJsonBean bean : jsonBeanList) {
                    if (bean.getLEVELS() == 3) {
                        if (bean.getUP_AD_GUID().equals(options2Items.get(i).get(j).getGUID())) {
                            cityList.add(bean);
                        }
                    }
                }
                cityList.add(0, beanHoleCounty);
                provinceAreaList.add(cityList);
            }
            provinceAreaList.add(0, prvinceList);
            options3Items.add(provinceAreaList);
        }
//
//            for (int c = 0; c < jsonBeanList.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
//                String CityName = jsonBeanList.get(i).getCityList().get(c).getName();
//                cityList.add(CityName);//添加城市
//
//                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
//
//                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
//                if (jsonBeanList.get(i).getCityList().get(c).getArea() == null || jsonBeanList.get(i).getCityList().get(c).getArea().size() == 0) {
//                    City_AreaList.add("");
//                } else {
//
//                    for (int d = 0; d < jsonBeanList.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
//                        String AreaName = jsonBeanList.get(i).getCityList().get(c).getArea().get(d);
//
//                        City_AreaList.add(AreaName);//添加该城市所有地区数据
//                    }
//                }
//                provinceAreaList.add(City_AreaList);//添加该省所有地区数据
//            }
//
//            /**
//             * 添加城市数据
//             */
//            options2Items.add(cityList);
//
//            /**
//             * 添加地区数据
//             */
//            options3Items.add(provinceAreaList);
//        }

    }

    public ArrayList<ProvinceJsonBean> parseData(String result) {//Gson 解析
        ArrayList<ProvinceJsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceJsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceJsonBean.class);
                detail.add(entity);

            }
        } catch (Exception e) {
            e.printStackTrace();
            // mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }
}
