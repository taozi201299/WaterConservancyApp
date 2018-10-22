package com.syberos.shuili.activity.dangermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.basicbusiness.ObjectTend;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.indexListView.ClearEditText;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.io.File;
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
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        objectEngine = (MvEngColl)bundle.getSerializable("data");
        hasTend = bundle.getBoolean("hasTend");
        if(hasTend){
            if (objectTend == null) {
                ll_project_tend.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                objectTend = (ObjectTend) bundle.getSerializable("tend");
                tv_tend.setText(objectTend.getTendName());
            }
        }
        else {
            ll_project_tend.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }
        tv_project_name.setText(objectEngine == null ?"未知":objectEngine.getName());

    }

    @Override
    public void initView() {
        showDataLoadingDialog();
        setInitActionBar(true);
        showTitle("新建隐患");
        setActionBarRightVisible(View.INVISIBLE);
        ev_des_audio.setLabelText("隐患描述");
        ll_multimedia = (MultimediaView)ll_investigation_add_layout.findViewById(R.id.ll_multimedia);
        setFinishOnBackKeyDown(false);
        getHiddenDic();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,requestCode,data);
    }
    private void getHiddenDic(){
        String url  = GlobleConstants.strIP + "/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
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
        ArrayList<String>valueList = new ArrayList<>();
        int size = hiddenGrade.dataSource.size();
        for(int i = 0; i < size ; i ++){
            values.put(i,hiddenGrade.dataSource.get(i).getDcItemName());
            valueList.add(hiddenGrade.dataSource.get(i).getDcItemName());
        }
        ll_enum_level.setEntries(valueList);
        ll_enum_level.setCurrentDetailText(valueList.get(0));
        ll_enum_level.setCurrentIndex(0);
    }
    private boolean checkParam(){
        boolean bRet = false;
        if(tv_hidden_name.getText().toString() == null || tv_hidden_name.getText().toString().isEmpty()){
            ToastUtils.show("隐患名称不能为空");
            return bRet;
        }
        if(ll_enum_level.getCurrentDetailText() == null || ll_enum_level.getCurrentDetailText().isEmpty()){
            ToastUtils.show("隐患级别不能为空");
            return  bRet;
        }
        if(tv_hidden_part.getText().toString() == null || tv_hidden_part.getText().toString().isEmpty()){
            ToastUtils.show("隐患部位不能空");
            return bRet;
        }
        if(ev_des_audio.getEditText() == null || ev_des_audio.getEditText().isEmpty()){
            ToastUtils.show("隐患描述不能为空");
            return  bRet;
        }
        return true;
    }
    private void commit(){
        if(!checkParam())return;
        showDataLoadingDialog();
        String url = GlobleConstants.strCJIP +"/cjapi/cj/obj/hidd/addObjHidd";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddName",tv_hidden_name.getText().toString()); // 隐患名称
        params.put("engGuid",objectEngine.getId()); // 所属工程
        if(hasTend) {
            params.put("tendGuid", objectTend.getGuid()); // 所属标段
        }else {
            params.put("tendGuid","");
        }
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("hiddGrad",String.valueOf(ll_enum_level.getCurrentIndex() +1)); // 隐患级别
        params.put("hiddClas","");
        params.put("proPart",tv_hidden_part.getText().toString()); // 隐患部位
        params.put("hiddDesc",ev_des_audio.getEditText()); // 隐患描述
        params.put("hiddStat","0");
        params.put("note","移动端测试");
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserId());
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        localCacheEntity.params = params;
        localCacheEntity.type = 0;
        localCacheEntity.commitType = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<AttachMentInfoEntity>attachments = new ArrayList<>();
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  ll_multimedia.getBinaryFile();


/**
 * 1多媒体关系表 2 多媒体基础信息表
 * 先提交多媒体基础信息表，提交成功后，提交多媒体关系表
 * 文档	1  图片	2  音频	3  视频 4 其他	9

 */
        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                String time = CommonUtils.getCurrentDateYMD();
                time = time.replace("-","");
                info.medPath = App.roleCode + "/OBJ_HIDD/"+time+ "/"+info.medName;
                File file = new File(info.medPath);
                info.medSize = file.length();
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                info.bisTableName = "OBJ_HIDD";
                info.bisGuid = "";
                info.localStatus = "0";
                if(item.type == MultimediaView.LocalAttachmentType.IMAGE){
                    info.medType = "2"; // 图片
                }else if(item.type == MultimediaView.LocalAttachmentType.AUDIO) {
                    info.medType = "3"; // 音频
                }else if(item.type == MultimediaView.LocalAttachmentType.VIDEO){
                    info.medType = "4";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachments.add(info);
            }
        }
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachments, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDialog();
                ToastUtils.show("提交成功");
                finish();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDialog();
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    InvestigationAddForEnterpriseActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前内容未提交，确定退出？");
            customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activityFinish();
                    customDialog.dismiss();
                }
            });
            customDialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
