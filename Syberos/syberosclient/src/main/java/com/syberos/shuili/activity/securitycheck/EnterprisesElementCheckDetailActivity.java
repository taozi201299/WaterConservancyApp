package com.syberos.shuili.activity.securitycheck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationEngineForEntActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.entity.securitycheck.BisSeChit;
import com.syberos.shuili.entity.securitycheck.ObjSe;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * 企事业版 元素检查 检查项 8.2.2.1	安全元素检查项信息表（BIS_SE_CHIT）
 */
public class EnterprisesElementCheckDetailActivity extends BaseActivity implements View.OnClickListener{

    /**
     *  安全元素对象信息
     */
    private ObjSe information = null;
    /**
     * 安全元素下的检查项信息
     */
    private BisSeChit bisSeChit = null;
    /**
     * 该检查项下的隐患信息
     */
    private ObjHidden objHidden = null;

    private String[] value = {"是","否"};
    private String[]value1 = {"有","无"};

    @BindView(R.id.ll_object_container_0)
    LinearLayout ll_object_container_0;

    @BindView(R.id.ll_object_container_1)
    LinearLayout ll_object_container_1;
    @BindView(R.id.tv_ok)
    TextView tv_ok;

    HashMap<View,BisSeChit>checkItemView = new HashMap<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_element_check_detail;
    }

    @Override
    public void initListener() {
        tv_ok.setOnClickListener(this);

    }

    /**
     * 1 根据安全元素ID 获取检查项
     * 2 根据安全元素ID 获取隐患，根据工程确认是否属于该用户所在单位
     */
    @Override
    public void initData() {
        showDataLoadingDialog();
        getCheckItemsByElementId();
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (ObjSe) bundle.getSerializable(
                EnterprisesElementCheckListActivity.SEND_BUNDLE_KEY);
        if(information == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            return;
        }
        if (null != information) {
            setActionBarTitle(information.getSeName());
            setActionBarRightVisible(View.INVISIBLE);

        }
    }

    private void getCheckItemsByElementId(){
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/se/bisSeChits/";
        HashMap<String,String>params = new HashMap<>();
      //  params.put("seGuid",information.getGuid());
        params.put("seGuid","B8B9E3C0294A451E8C86A82B0BEF2DE0");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisSeChit = gson.fromJson(result,BisSeChit.class);
                if(bisSeChit == null || bisSeChit.dataSource == null || bisSeChit.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getAllHidden();
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getAllHidden(){
        // 根据单位ID和 安全检查项ID查询检查项下隐患
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objHidds/";
        HashMap<String ,String >params = new HashMap<>();
      //  params.put("orgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("orgGuid","537AD1AB8E7447AAA249AB22A5344955");
     //   params.put("seCheckItemGuid",bisSeChit.getGuid());
        params.put("seCheckItemGuid","a1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objHidden = gson.fromJson(result,ObjHidden.class);
                if(objHidden == null || objHidden.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
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
    private void refreshUI(){
        addCheckItems((ArrayList<BisSeChit>) bisSeChit.dataSource);
        addHiddenItems((ArrayList<ObjHidden>) objHidden.dataSource);

    }
    private void addCheckItems(ArrayList<BisSeChit> checkItemInfos) {
        if (checkItemInfos.size() <= 0) {
            return;
        }
        for (BisSeChit checkItemInfo : checkItemInfos) {
            addCheckItem(checkItemInfo);
        }
    }

    private void addCheckItem(final BisSeChit checkItemInfo) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.activity_enterprises_element_check_detail_check_item, null);

        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        RadioButton rb_left = (RadioButton)view.findViewById(R.id.rb_left);
        RadioButton rb_right = (RadioButton)view.findViewById(R.id.rb_right);
        tv_title.setText(checkItemInfo.getSeChitName());
        final EditText ce_count = (EditText) view.findViewById(R.id.ce_count);
        // 1为是/否，2为无/有，3为否/是，4为有/无。
        String isWhit = checkItemInfo.getIfWhit();
        if("1".equals(isWhit)){
            rb_left.setText(value[0]); // 是
            rb_right.setText(value[1]); //否
        }else if("2".equals(isWhit)){
            rb_left.setText(value1[1]); // 无
            rb_right.setText(value1[0]); // 有
        }
        rb_left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isShown()) {
                    if (isChecked) {
                        ce_count.setEnabled(false);
                        ce_count.setText("0");
                    }
                }
            }
        });

        rb_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isShown()) {
                    if (isChecked) {
                        ce_count.setEnabled(true);
                        ce_count.setText("1");
                    }
                }
                go2HiddenReport(checkItemInfo);
            }
        });
        ll_object_container_0.addView(view);
        checkItemView.put(view,checkItemInfo);
    }

    private void go2HiddenReport(BisSeChit item){
        Bundle bundle = new Bundle();
        bundle.putSerializable("element",information);
        bundle.putSerializable("checkItem",item);
        bundle.putString("type","element");
        intentActivity(EnterprisesElementCheckDetailActivity.this,InvestigationEngineForEntActivity.class,false,bundle);
    }
    private void addHiddenItems(List<ObjHidden> hiddenItemInfos) {
        if (hiddenItemInfos.size() <= 0) {
            return;
        }
        for (ObjHidden hiddenItemInfo : hiddenItemInfos) {
            addHiddenItem(hiddenItemInfo);
        }
    }
    private void addHiddenItem(final ObjHidden hiddenItemInfo) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.activity_enterprises_element_check_detail_hidden_item, null);

        ((TextView) (view.findViewById(R.id.tv_title))).setText(hiddenItemInfo.getHiddName());
        ((TextView) (view.findViewById(R.id.tv_time))).setText(hiddenItemInfo.getCollTime());
        ((TextView) (view.findViewById(R.id.tv_project))).setText(hiddenItemInfo.getEngName());

        String[] levelNames = getResources().getStringArray(
                R.array.enterprises_element_check_level);
        int  level = Integer.valueOf(hiddenItemInfo.getHiddGrad());
        TextView tv_type = (TextView) (view.findViewById(R.id.tv_type));
        tv_type.setText(levelNames[level]);

        LinearLayout ll_type = (LinearLayout) (view.findViewById(R.id.ll_type));
        switch (level) {
            case 0: {
                ll_type.setBackground(getResources().getDrawable(
                        R.drawable.bg_enterprises_element_check_level_normal));
            }
            break;
            case 1: {
                ll_type.setBackground(getResources().getDrawable(
                        R.drawable.bg_enterprises_element_check_level_large));
                break;
            }
        }

        ll_object_container_1.addView(view);
    }

    /**
     * 检查结果提交 提交到8.2.2.3	单位安全元素检查结果
     */
    private void commit(){
        final ArrayList<HashMap<String,String>>results = new ArrayList<>();
        for(View item:checkItemView.keySet()) {
            HashMap<String, String> params = new HashMap<>();
            String checkResu = "";
            TextView tv_count = item.findViewById(R.id.ce_count);
            RadioButton rb_left = item.findViewById(R.id.rb_left);
            RadioButton rb_right = item.findViewById(R.id.rb_right);
            if(!rb_left.isChecked() && !rb_right.isChecked()){
                ToastUtils.show("存在未检查项，无法提交");
                return;
            }
            String count = tv_count.getText().toString();
            BisSeChit value = checkItemView.get(item);
            params.put("seChitGuid",value.getGuid());
            params.put("occuNum", count);
            params.put("fianDeuc", String.valueOf(Integer.valueOf(count) * Integer.valueOf(value.getDeduScore())));
            if("1".equals(value.getIfWhit())){
                if(rb_left.isChecked()){
                    checkResu = "11";
                }else {
                    checkResu = "12";
                }
                // 是、否
            }else if ("2".equals(value.getIfWhit())){
                // 无 、有
                if(rb_left.isChecked()){
                    checkResu = "22";
                }else {
                    checkResu = "21";
                }
            }
            params.put("checkResu", checkResu);
            params.put("note", "客户端提交测试");
            params.put("updDate", "");
            params.put("collDate", CommonUtils.getCurrentDate());
            params.put("recPers", SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());
            results.add(params);
        }
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/se/wiun/bisSeWiunCheck/";
        for(final HashMap<String,String> map :results){
            LocalCacheEntity localCacheEntity = new LocalCacheEntity();
            localCacheEntity.url = url;
            localCacheEntity.type = 1;
            localCacheEntity.attachType = 1;
            localCacheEntity.params = map;
            ArrayList<AttachMentInfoEntity>attachments = new ArrayList<>();
            localCacheEntity.seriesKey = UUID.randomUUID().toString();
            SyberosManagerImpl.getInstance().submit(localCacheEntity,attachments, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    if(results.indexOf(map) == results.size() -1){
                        finish();
                    }

                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok :
                commit();
            break;
        }
    }
}
