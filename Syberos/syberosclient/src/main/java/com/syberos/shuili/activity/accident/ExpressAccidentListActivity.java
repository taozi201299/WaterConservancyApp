package com.syberos.shuili.activity.accident;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by toby on 18-3-26.
 * 该文件暂不使用
 */

public class ExpressAccidentListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {
    private final String TAG = ExpressAccidentListActivity.class.getSimpleName();
    private final String Title = "快报事故";

    public static final String SEND_BUNDLE_KEY = "ObjAcci";

    @BindView(R.id.recyclerView_express_accident)
    RecyclerView recyclerView;
    ExpressAccidentListAdapter accidentInformationListAdapter;
    List<ObjAcci> accidentInformationList = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_express_accident_list_layout;
    }

    @Override
    public void initListener() {


    }

    @Override
    public void initData() {
        getAccidentList();

    }
    /**
     * 获取快报事故列表
     */
    private void getAccidentList(){
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/obj/getAccidentManagements/";
        HashMap<String,String> param = new HashMap<>();
        param.put("acciWiunGuid","6EA3DB09FF964094A816246703CE7649");
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                ObjAcci accidentListInfo = gson.fromJson(result,ObjAcci.class);
                if(accidentListInfo == null)return;
                accidentInformationList = accidentListInfo.dataSource;
                refreshUI();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    @Override
    public void initView() {
        showDataLoadingDialog();
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        accidentInformationListAdapter = new ExpressAccidentListAdapter(this,
                R.layout.activity_express_accident_list_item);
        recyclerView.setAdapter(accidentInformationListAdapter);
        accidentInformationListAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjAcci accidentInformation = accidentInformationList.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, accidentInformation);

        intentActivity(this, ExpressAccidentDetailActivity.class, false, bundle);
    }

    private void refreshUI(){
        accidentInformationListAdapter.setData(accidentInformationList);
        accidentInformationListAdapter.notifyDataSetChanged();
    }
    private class ExpressAccidentListAdapter extends CommonAdapter<ObjAcci> {
        public ExpressAccidentListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, ObjAcci accidentInformation) {
            // TODO: 2018/4/12 事故級別
            int type = 0;
            LinearLayout ll_type = (LinearLayout) (holder.getView(R.id.ll_type));
            switch (type) {
                case ObjAcci.TYPE_NORMAL: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.accident_type_normal);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_accident_type_normal_shape));
                }
                break;
                case ObjAcci.TYPE_BIG: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.accident_type_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_accident_type_big_shape));
                }
                break;
                case ObjAcci.TYPE_BIGGER: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.accident_type_bigger);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_accident_type_bigger_shape));
                }
                break;
                case ObjAcci.TYPE_LARGE: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.accident_type_large);

                    ((TextView) (holder.getView(R.id.tv_type))).setTextColor(R.color.black);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_accident_type_large_shape));
                }
                break;
            }
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                   "缺少該字段");
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    accidentInformation.getOccuTime());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    accidentInformation.getAcciWindGuid());
            ((TextView) (holder.getView(R.id.tv_content))).setText(
                    accidentInformation.getAcciSitu());
        }
    }
}
