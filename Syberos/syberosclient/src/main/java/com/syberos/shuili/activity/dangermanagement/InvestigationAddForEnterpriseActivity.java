package com.syberos.shuili.activity.dangermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.basicbusiness.ObjectTend;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.indexListView.ClearEditText;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by jidan on 18-3-27.
 * 新建隐患  隐患排查
 */

public class InvestigationAddForEnterpriseActivity extends BaseActivity implements BaseActivity.IDialogInterface{

   @BindView(R.id.investigation_add_layout)
    LinearLayout ll_investigation_add_layout;
   @BindView(R.id.ll_enum_level)
   EnumView ll_enum_level;
   @BindView(R.id.ll_commit)
   LinearLayout ll_commit;
   @BindView(R.id.tv_hidden_part)
   ClearEditText tv_hidden_part;
   @BindView(R.id.tv_hidden_name)
   ClearEditText tv_hidden_name;
   @BindView(R.id.ll_multimedia)
   MultimediaView ll_multimedia;
   @BindView(R.id.ev_des_audio)
    AudioEditView ev_des_audio;
   @BindView(R.id.tv_project_name)
    TextView tv_project_name;
   @BindView(R.id.ll_project_tend)
    RelativeLayout ll_project_tend;
   @BindView(R.id.line)
   View line;
   @BindView(R.id.tv_tend)
   TextView tv_tend;
   private DicInfo hiddenGrade;
    MvEngColl objectEngine;
    ObjectTend objectTend;
    boolean hasTend;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_enterprise_add_layout;
    }

    @Override
    public void initListener() {
        setDialogInterface(this);
        ll_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommitDialog("确定要提交数据?",0);
            }
        });

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        objectEngine = (MvEngColl)bundle.getSerializable("data");
        hasTend = bundle.getBoolean("hasTend");
        if(hasTend){
            if (objectTend == null) {
                objectTend = (ObjectTend) bundle.getSerializable("tend");
                tv_tend.setText(objectTend.getTendName());
            }
        }
        else {
            ll_project_tend.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }
        tv_project_name.setText(objectEngine == null ?"未知":objectEngine.getName());
        getHiddenDic();

    }

    @Override
    public void initView() {
        showTitle("新建隐患");
        setActionBarRightVisible(View.INVISIBLE);
        ev_des_audio.setLabelText("隐患描述");
        ll_multimedia = (MultimediaView)ll_investigation_add_layout.findViewById(R.id.ll_multimedia);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,requestCode,data);
    }
    private void getHiddenDic(){
        String url  = "http://192.168.1.8:8080/wcsps-supervision/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String> params = new HashMap<>();
        params.put("attTabCode","OBJ_HIDD");
        params.put("attColCode","HIDD_GRAD");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                hiddenGrade  = gson.fromJson(result,DicInfo.class);
                if(hiddenGrade != null || hiddenGrade.dataSource != null){
                    setEnumData();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void setEnumData(){
        HashMap<Integer,String> values= new HashMap<>();
        int size = hiddenGrade.dataSource.size();
        for(int i = 0; i < size ; i ++){
            values.put(i,hiddenGrade.dataSource.get(i).getDcItemName());
        }
        ll_enum_level.setEntries(values);
        ll_enum_level.setCurrentDetailText(values.get(0));
        ll_enum_level.setCurrentIndex(0);
    }
    private boolean checkParam(){
        boolean bRet = false;
        if(tv_hidden_name.getText().toString() == null || tv_hidden_name.getText().toString().isEmpty()){
            ToastUtils.show("隐患名称不能为空");
            return bRet;
        }
        return true;
    }
    private void commit(){
        if(!checkParam())return;
        String  url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objHidd/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddName",tv_hidden_name.getText().toString()); // 隐患名称
        params.put("engGuid",objectEngine.getEngId()); // 所属工程
        if(hasTend) {
            params.put("tendGuid", objectTend.getGuid()); // 所属标段
        }else {
            params.put("tendGuid","");
        }
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("hiddGrad",String.valueOf(ll_enum_level.getCurrentIndex())); // 隐患级别
        params.put("hiddClas","");
        params.put("proPart",tv_hidden_part.getText().toString()); // 隐患部位
        params.put("hiddDesc",ev_des_audio.getEditText()); // 隐患描述
        params.put("hiddStat","1");
        params.put("collTime", CommonUtils.getCurrentDate());
        params.put("note","移动端测试");
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserId());
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity>attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                ToastUtils.show("提交成功");
                finish();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    @Override
    public void dialogClick() {
        commit();
    }

    @Override
    public void dialogCancel() {

    }
}
