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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                query();
                break;
            case R.id.iv_action_bar_right_1:
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

    }

    @Override
    public void initView() {
        tv_action_bar_title.setText("溢洪道监测");
        iv_action_bar_left.setVisibility(View.INVISIBLE);
        iv_action_bar_right_2.setVisibility(View.INVISIBLE);
        listAdapter = new ListAdapter(this,R.layout.test_list_item_layout);
        engine_pullRecycleView.setAdapter(listAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        engine_pullRecycleView.setLayoutManager(layoutManager);
        //  获取json数据
        HashMap<Integer,String>nameValue = new HashMap<>();
        nameValue.put(0,"全部");
        Gson gson = new Gson();
        String jsonData = JsonFileReader.getJson(this, "province_data_shuili.json");
        ArrayList<ProviceNameBean>proviceNameBeans = gson.fromJson(jsonData,new TypeToken<List<ProviceNameBean>>(){}.getType());
        if(proviceNameBeans != null){
            for(ProviceNameBean bean : proviceNameBeans){
                nameValue.put(Integer.valueOf(bean.getAD_CODE()),bean.getPATHNAME());
            }
        }
        ev_unit_type.setEntries(nameValue);
        ev_unit_type.setCurrentDetailText(nameValue.get(0));
        ArrayList<String>liuYuName = new ArrayList<>();
        liuYuName.add("全部");
        liuYuName.add("长江水利委员会");
        liuYuName.add("黄河水利委员会");
        liuYuName.add("淮河水利委员会");
        liuYuName.add("海河水利委员会");
        liuYuName.add("珠江水利委员会");
        liuYuName.add("松江水利委员会");
        liuYuName.add("太湖水利委员会");
        ev_type_liuyu.setEntries(liuYuName);
        ev_type_liuyu.setCurrentDetailText(liuYuName.get(0));
        ArrayList<String>otherName = new ArrayList<>();
        otherName.add("全部");
        otherName.add("疑似无");
        otherName.add("待定");
        ev_type_other.setEntries(otherName);
        ev_type_other.setCurrentDetailText(otherName.get(0));
    }
    private void query(){
        showDataLoadingDialog();
        String url = "http://192.168.1.11:7080/desu/serv/v1/getSpillway";
        HashMap<String,String>params = new HashMap<>();
        params.put("start","");
        params.put("lengthr","");
        params.put("resName","");
        params.put("adCode","");
        params.put("baadCode","");
        params.put("ifSpillway","");
        HttpUtils.getInstance().requestNet_post(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
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
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void  refreshUI(){
        closeDataDialog();
        listAdapter.setData(engineInfo.getData().getData());
        listAdapter.notifyDataSetChanged();
    }
    private void go2Activity(){

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
