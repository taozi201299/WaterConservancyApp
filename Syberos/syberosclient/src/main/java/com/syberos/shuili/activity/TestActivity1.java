package com.syberos.shuili.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.test.EngineInfoBean;
import com.syberos.shuili.utils.JsonFileReader;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;
import com.syberos.shuili.view.EnumView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/17.
 */



public class TestActivity1 extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.ev_unit_type)
    EnumView ev_unit_type;
    @BindView(R.id.ev_type_liuyu)
    EnumView ev_type_liuyu;
    @BindView(R.id.ev_type_other)
    EnumView ev_type_other;
    @BindView(R.id.ce_engine_name)
    ClearableEditText ce_engine_name;
    @BindView(R.id.engine_pullRecycleView)
    RecyclerView engine_pullRecycleView;

    @BindView(R.id.iv_action_bar_left)
    ImageView iv_action_bar_left;
    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;
    @BindView(R.id.iv_action_bar_right_1)
    ImageView iv_action_bar_right_1;
    @BindView(R.id.iv_action_bar_right_2)
    ImageView iv_action_bar_right_2;


    ListAdapter listAdapter = null;
    EngineInfoBean engineInfo = null;
    private final int size = 10;
    ArrayList<ProviceNameBean>proviceNameBeans = null;
    HashMap<Integer,String>liuYuName = new HashMap<>();
    HashMap<Integer,String>nameValue = new HashMap<>();
    ArrayList<String>otherName = new ArrayList<>();
    public static final String TestIP = "http://10.1.194.89";

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                query();
                break;
            case R.id.iv_action_bar_right_2:
                go2Map();
                break;
        }

    }
  private void go2Map(){
        go2Activity();
  }
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_layout;
    }

    @Override
    public void initListener() {
        btn_search.setOnClickListener(this);
        iv_action_bar_right_2.setOnClickListener(this);
        iv_action_bar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityFinish();
            }
        });
        listAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",engineInfo.getData().getData().get(position));
                intentActivity(TestActivity1.this,TestFormActivity.class,false,bundle);

            }
        });
    }

    @Override
    public void initData() {
      query();
    }

    @Override
    public void initView() {
        ArrayList<String>nameList = new ArrayList<>();
        tv_action_bar_title.setText("溢洪道监测");
        iv_action_bar_left.setImageResource(R.mipmap.back);
        iv_action_bar_right_1.setVisibility(View.GONE);
        iv_action_bar_right_2.setImageResource(R.mipmap.icon_loc);
        listAdapter = new ListAdapter(this,R.layout.test_list_item_layout);
        engine_pullRecycleView.setAdapter(listAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        engine_pullRecycleView.setLayoutManager(layoutManager);
        //  获取json数据
        nameValue.put(0,"全部");
        nameList.add("全部");
        Gson gson = new Gson();
        String jsonData = JsonFileReader.getJson(this, "province_data_shuili.json");
        proviceNameBeans = gson.fromJson(jsonData,new TypeToken<List<ProviceNameBean>>(){}.getType());
        if(proviceNameBeans != null){
            for(ProviceNameBean bean : proviceNameBeans){
                nameValue.put(Integer.valueOf(bean.getAD_CODE()),bean.getPATHNAME());
                nameList.add(bean.getPATHNAME());
            }
        }
        ev_unit_type.setEntries(nameList);
        ev_unit_type.setCurrentDetailText(nameList.get(0));
        liuYuName.put(0,"全部");
        liuYuName.put(010000,"长江水利委员会");
        liuYuName.put(020000,"黄河水利委员会");
        liuYuName.put(030000,"淮河水利委员会");
        liuYuName.put(040000,"海河水利委员会");
        liuYuName.put(050000,"珠江水利委员会");
        liuYuName.put(060000,"松江水利委员会");
        liuYuName.put(070000,"太湖水利委员会");
        ev_type_liuyu.setEntries(liuYuName);
        ev_type_liuyu.setCurrentDetailText(liuYuName.get(0));

        otherName.add("全部");
        otherName.add("待定");
        otherName.add("疑似无");
        otherName.add("疑似有");

        ev_type_other.setEntries(otherName);
        ev_type_other.setCurrentDetailText(otherName.get(0));
    }
    private void query(){
        showDataLoadingDialog();
        String url = TestIP + "/desu/serv/v1/getSpillway";
        HashMap<String,String>params = new HashMap<>();
        params.put("start","0");
        params.put("length","99999");
        params.put("resName",ce_engine_name.getText().toString());
        params.put("baadCode",getAdCode(ev_type_liuyu.getCurrentIndex())); // 流域编码
        params.put("adCode",getProviceCode(ev_unit_type.getCurrentDetailText()));  // 政区编码
        int index = getIndex(ev_type_other.getCurrentDetailText());
        params.put("ifSpillway",index == -1 ?"":String.valueOf(index -1));
        HttpUtils.getInstance().requestNet_post(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                engineInfo = gson.fromJson(result,EngineInfoBean.class);
                if(engineInfo == null){
                    ToastUtils.show("查询失败，请检查网络情况");
                }else if(engineInfo.getData().getRecordsTotal() == 0){
                    ToastUtils.show("未找到匹配的数据");
                }
                refreshUI();
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void  refreshUI(){
        listAdapter.setData(engineInfo.getData().getData());
        listAdapter.notifyDataSetChanged();
    }
    private int getIndex(String name){
        int index = -1;
        for(String item :otherName ){
            if(item.equals(name)){
                index = otherName.indexOf(item);
            }
        }
        if(index == 0){
            index = -1;
        }
        return index;

    }
    private String getProviceCode(String name){
        if(name== null)return "";
        String proviceCode = "";
        for(Integer key :nameValue.keySet()){
            if(name.equals(nameValue.get(key))){
                proviceCode = String.valueOf(key);
            }
        }
        if("0".equals(proviceCode)){
            proviceCode = "";
        }
        return proviceCode;
    }
    private String getAdCode(int index){
        if(index== 0)return "";
        if(index == 1){
            return "010000";
        }else if(index == 2){
            return "020000";
        }else if(index == 3){
            return  "030000";
        }
        if(index == 4){
            return "040000";
        }else if(index == 5){
            return "050000";
        }else if(index == 6){
            return  "060000";
        }else if(index == 7){
            return  "070000";
        }
        return "";

    }
    private void go2Activity(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",engineInfo);
        intentActivity(TestActivity1.this,TestMapActivity.class,false,bundle);

    }
    public static class ProviceNameBean implements Serializable{
        String UP_AD_GUID ;
        String AD_CODE;
        String GUID;
        String PATHNAME;
        String LEVELS;
        String PATH;
        String AD_NAME;
        String AD_GRAD;

        public String getUP_AD_GUID() {
            return UP_AD_GUID == null ? "" : UP_AD_GUID;
        }

        public void setUP_AD_GUID(String UP_AD_GUID) {
            this.UP_AD_GUID = UP_AD_GUID;
        }

        public String getAD_CODE() {
            return AD_CODE == null ? "" : AD_CODE;
        }

        public void setAD_CODE(String AD_CODE) {
            this.AD_CODE = AD_CODE;
        }

        public String getGUID() {
            return GUID == null ? "" : GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getPATHNAME() {
            return PATHNAME == null ? "" : PATHNAME;
        }

        public void setPATHNAME(String PATHNAME) {
            this.PATHNAME = PATHNAME;
        }

        public String getLEVELS() {
            return LEVELS == null ? "" : LEVELS;
        }

        public void setLEVELS(String LEVELS) {
            this.LEVELS = LEVELS;
        }

        public String getPATH() {
            return PATH == null ? "" : PATH;
        }

        public void setPATH(String PATH) {
            this.PATH = PATH;
        }

        public String getAD_NAME() {
            return AD_NAME == null ? "" : AD_NAME;
        }

        public void setAD_NAME(String AD_NAME) {
            this.AD_NAME = AD_NAME;
        }

        public String getAD_GRAD() {
            return AD_GRAD == null ? "" : AD_GRAD;
        }

        public void setAD_GRAD(String AD_GRAD) {
            this.AD_GRAD = AD_GRAD;
        }
    }

    private class ListAdapter extends CommonAdapter<EngineInfoBean.EngineBean> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, EngineInfoBean.EngineBean engineBean) {
            TextView tv_title = holder.getView(R.id.tv_title);
            TextView tv_provice = holder.getView(R.id.tv_provice);
            TextView tv_has = holder.getView(R.id.tv_has);
            tv_title.setText(engineBean.getRES_NAME());
            tv_provice.setText(engineBean.getPROVINCE());
            tv_has.setText(engineBean.getIFSPILLWAYNAME());
        }
    }
}
