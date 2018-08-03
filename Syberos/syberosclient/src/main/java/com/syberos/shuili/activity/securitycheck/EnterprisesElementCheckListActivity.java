package com.syberos.shuili.activity.securitycheck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.an_quan_jian_cha.EnterprisesElementCheckInfo;
import com.syberos.shuili.entity.securitycheck.BisSeWiun;
import com.syberos.shuili.entity.securitycheck.BisSeWiunDeco;
import com.syberos.shuili.entity.securitycheck.ObjSe;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.HttpUrl;

/**
 * 企事业版本 元素检查 列表 元素/元素组
 * 1 从8.2.2.4	单位安全元素分解（BIS_SE_WIUN_DECO） 中获取检查元素GUID
 * 2 从8.2.2.2	单位安全元素（BIS_SE_WIUN）中获取元素信息，将元素进行分组
 * 3 从8.2.1.4	安全元素库对象表（OBJ_SE）中获取到元素名称
 * * 1 大中型已建工程运行管理单位 CJYJ
 * 2 大中型在建工程项目法人 CJFR
 */
public class EnterprisesElementCheckListActivity extends BaseActivity implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "EnterprisesElementCheckInfo";
    private ListAdapter adapter;

    private BisSeWiunDeco bisSeWiunDeco = null;
    private BisSeWiun bisSeWiun = null;
    private ObjSe objSe = null;
    ArrayList<BisSeWiun> bisSeWiuns = new ArrayList<>();
    ArrayList<ObjSe> objSes = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_element_check_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
    }

    /**
     * 根据用户ID 从BIS_SE_WIUN_DECO表中获取元素guid
     */
    private void getBisSeWiunByUserId() {
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/se/wiun/bisSeWiunDecos/";
        HashMap<String, String> params = new HashMap<>();
        // params.put("legPersGuid",SyberosManagerImpl.getInstance().getCurrentUserId());
        params.put("legPersGuid", "1eb5493bfdb74ecea7337ba73f32c92c");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisSeWiunDeco = gson.fromJson(result, BisSeWiunDeco.class);
                if (bisSeWiunDeco == null || bisSeWiunDeco.dataSource == null) {
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if (bisSeWiunDeco.dataSource.size() == 0) {
                    closeDataDialog();
                    ToastUtils.show("没有相关数据");
                    return;
                }
                getSeElementByElementId();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    private void getSeElementByElementId() {
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/se/bisSeWiuns/";
        HashMap<String, String> params = new HashMap<>();
        final int count = bisSeWiunDeco.dataSource.size();
        for (int i = 0; i < count; i++) {
            BisSeWiunDeco item = bisSeWiunDeco.dataSource.get(i);
            params.put("guid", item.getSeWiunGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    bisSeWiun = gson.fromJson(result, BisSeWiun.class);
                    if (bisSeWiun == null || bisSeWiun.dataSource == null || bisSeWiun.dataSource.size() == 0) {
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    bisSeWiuns.add(bisSeWiun.dataSource.get(0));
                    if (finalI == count - 1) {
                        getSeElementInfo();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }
    }

    /**
     * 根据元素ID 获取元素名称等详细信息
     */
    private void getSeElementInfo() {
        String url = "http://192.168.1.8:8080/sjjk/v1/obj/objSes/";
        HashMap<String, String> params = new HashMap<>();
        final int count = bisSeWiuns.size();
        for (int i = 0; i < count; i++) {
            BisSeWiun item = bisSeWiuns.get(i);
            params.put("guid", item.getSeGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    objSe = gson.fromJson(result, ObjSe.class);
                    if (objSe == null || objSe.dataSource == null || objSe.dataSource.size() == 0) {
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    objSes.add(objSe.dataSource.get(0));
                    if (finalI == count - 1) {
                        closeDataDialog();
                        paraseResult();
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }

    }

    /**
     * 解析数据结果，去掉元素组
     */
    boolean isGroup(String id) {
        for (ObjSe item : objSes) {
            if (item.getpGuid() != null && item.getpGuid().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private void paraseResult() {
        for (ObjSe item : objSes) {
            item.setGroup(isGroup(item.getGuid()));
        }
    }

    private void refreshUI() {
        adapter.setData(objSes);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void initView() {
        showTitle(getResources().getString(R.string.module_child_anquan_jianchayuansu));
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(this, R.layout.activity_enterprises_element_check_list_item);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);
        if (bisSeWiuns != null) bisSeWiuns.clear();
        if (objSes != null) objSes.clear();
        showDataLoadingDialog();
        getBisSeWiunByUserId();

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjSe info = objSes.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, info);
        intentActivity((Activity) mContext, EnterprisesElementCheckDetailActivity.class, false, bundle);
    }

    private class ListAdapter extends CommonAdapter<ObjSe> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjSe information) {

            ((TextView) (holder.getView(R.id.tv_title))).setText(information.getSeName());

            String[] levelNames = getResources().getStringArray(R.array.enterprises_element_check_level);
            int level = Integer.valueOf(information.getSeType() == null ? "1" : information.getSeType());
            TextView tv_type = (TextView) (holder.getView(R.id.tv_type));
            tv_type.setText(levelNames[level - 1]);

            LinearLayout ll_type = (LinearLayout) (holder.getView(R.id.ll_type));
            switch (level) {
                case EnterprisesElementCheckInfo.LEVEL_NORMAL: {//正常
                    ll_type.setBackground(getResources().getDrawable(R.drawable.bg_enterprises_element_check_level_normal));
                }
                break;
                case EnterprisesElementCheckInfo.LEVEL_COMMON: {//一般隐患
                    ll_type.setBackground(getResources().getDrawable(R.drawable.bg_enterprises_element_check_level_common));
                }
                break;
                case EnterprisesElementCheckInfo.LEVEL_NOT_SURE: {//待查
                    ll_type.setBackground(getResources().getDrawable(R.drawable.bg_enterprises_element_check_level_not_sure));
                }
                break;
                case EnterprisesElementCheckInfo.LEVEL_LARGE:   //重大隐患
                case EnterprisesElementCheckInfo.LEVEL_OTHER: {
                    ll_type.setBackground(getResources().getDrawable(R.drawable.bg_enterprises_element_check_level_large));
                }
                break;

            }

        }
    }
}
