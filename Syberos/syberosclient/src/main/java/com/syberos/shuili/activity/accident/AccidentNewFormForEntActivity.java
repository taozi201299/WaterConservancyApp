package com.syberos.shuili.activity.accident;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.activity.accident.AccidentListForEntAcitvity.SEND_BUNDLE_KEY;
import static com.syberos.shuili.activity.accident.AccidentListForEntAcitvity.DIC_ACCIDENT_KEY;
import static com.syberos.shuili.activity.accident.AccidentListForEntAcitvity.DIC_UNIT_KEY;

public class AccidentNewFormForEntActivity extends BaseActivity implements BaseActivity.IDialogInterface {

    private final String TAG = AccidentNewFormForEntActivity.class.getSimpleName();

    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.ev_unit_type)
    EnumView ev_unit_type;

    @BindView(R.id.ev_type)
    EnumView ev_type;

    @BindView(R.id.ll_enum_level)
    EnumView ll_enum_level;
    @BindView(R.id.ce_accident_unit)
    ClearableEditText ce_accident_unit;

    @BindView(R.id.ce_accident_name)
    ClearableEditText ce_accident_name;
    @BindView(R.id.ce_occo_loc)
    ClearableEditText ce_occo_loc;

    @BindView(R.id.ce_serious_injuries_count)
    ClearableEditText ce_serious_injuries_count;

    @BindView(R.id.ce_death_count)
    ClearableEditText ce_death_count;

    @BindView(R.id.ce_direct_economic_loss)
    ClearableEditText ce_direct_economic_loss;

    @BindView(R.id.aev_accident_description)
    AudioEditView aev_accident_description;

    @BindView(R.id.rg_accident_liability)
    RadioGroup rg_accident_liability;

    @BindView(R.id.rg_accident_phone_report)
    RadioGroup rg_accident_phone_report;

    private DicInfo m_dicUnitType;
    private DicInfo m_dicAccidentType;
    private HashMap<String,String> m_unitMap;
    private HashMap<String,String> m_acciTypeMap;
    private HashMap<Integer,String>m_acciGradeMap;
    ArrayList<String>m_acciGradMapList = new ArrayList<>();
    private ObjAcci objAcci = null;
    private int type ;
    MvEngColl item;

    @OnClick(R.id.tv_accident_report_quick)
    void onAccidentReportQuickClicked() {
        commit();
    }

    @OnClick(R.id.rl_time)
    void onSetAccidentTimeClicked() {
        //时间选择器
        boolean[] type = {true, true, true, true, true, true};

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() > System.currentTimeMillis()) {
                    ToastUtils.show("提示：所选时间不应大于系统当前时间");
                    return;
                }
                tv_time.setText(Strings.formatDatetime(date));
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_new_accident;
    }

    @Override
    public void initListener() {
        setDialogInterface(this);


    }

    @Override
    public void initData() {

    }


    @Override
    public void initView() {
        setInitActionBar(true);
        String strTitleName = "新建事故";
        if(m_dicUnitType == null || m_dicAccidentType == null) {
            Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
            m_dicUnitType = (DicInfo) bundle.getSerializable(DIC_UNIT_KEY);
            m_dicAccidentType = (DicInfo) bundle.getSerializable(DIC_ACCIDENT_KEY);
            tv_time.setText(CommonUtils.getCurrentDate());
            type = bundle.getInt("type");
            item = (MvEngColl) bundle.getSerializable("engColls");
            initViewData();
            switch (type) {
                case GlobleConstants.reportAcci_0:
                case GlobleConstants.reportAcci_1:
                case GlobleConstants.reportAcci_2:
                    objAcci = (ObjAcci) bundle.getSerializable(SEND_BUNDLE_KEY);
                    if(objAcci == null){
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                        finish();
                        return;
                    }
                    if( GlobleConstants.reportAcci_0 == type){
                        strTitleName = "事故快报";
                    }else if(GlobleConstants.reportAcci_1 == type || GlobleConstants.reportAcci_2 == type){
                        strTitleName = "事故补报";
                    }
                    ce_accident_unit.setText(objAcci.getAccidentUnitName());
                    ce_accident_name.setText(getAcciTypeName(objAcci.getAcciCate()));
                    ce_occo_loc.setText(objAcci.getOccuLoc());
                    if(objAcci.getAcciGrad() != null && !objAcci.getAcciGrad().isEmpty()) {
                        ll_enum_level.setCurrentDetailText(m_acciGradeMap.get(Integer.valueOf(objAcci.getAcciGrad()) -1));
                    }
                    ce_serious_injuries_count.setText(objAcci.getSerInjNum());
                    ce_death_count.setText(objAcci.getCasNum());
                    ce_direct_economic_loss.setText(objAcci.getEconLoss());
                    tv_time.setText(objAcci.getOccuTime());
                    aev_accident_description.setEditText(objAcci.getAcciSitu());
                    if("1".equals(objAcci.getIfRespAcci())){
                        rg_accident_liability.check(R.id.rb_accident_liability_yes);
                    }else {
                        rg_accident_liability.check(R.id.rb_accident_liability_no);
                    }
                    if("1".equals(objAcci.getIfPhoRep())){
                        rg_accident_phone_report.check(R.id.rb_accident_phone_report_yes);
                    }else {
                        rg_accident_phone_report.check(R.id.rb_accident_phone_report_no);
                    }
                    if(objAcci.getAcciWiunType() != null) {
                        ev_unit_type.setCurrentDetailText(getAcciUnitTypeName(objAcci.getAcciWiunType()));
                    }
                    if(objAcci.getAcciCate() != null){
                        ev_type.setCurrentDetailText(getAcciTypeName(objAcci.getAcciCate()));
                    }
                    break;
                case GlobleConstants.NEW_ACCI:
                    ce_accident_name.setText(item.getName());
                    break;
                default:
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                    finish();
                    break;
            }
        }
        setActionBarTitle(strTitleName);
        setActionBarRightVisible(View.INVISIBLE);
        setFinishOnBackKeyDown(false);
    }
    private void initViewData(){
        ce_accident_unit.setText(SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName());
        m_acciTypeMap = new HashMap<>();
        m_unitMap = new HashMap<>();
        m_acciGradeMap = new HashMap<>();
        HashMap<Integer, String> unitTypeMap = new HashMap<>();
        HashMap<Integer, String> accidentTypeMap = new HashMap<>();
        if (m_dicUnitType != null && m_dicUnitType.dataSource.size() > 0) {
            int size = m_dicUnitType.dataSource.size();
            for (int i = 0; i < size; i++) {
                DicInfo item = m_dicUnitType.dataSource.get(i);
                unitTypeMap.put(i, item.getDcItemName());
                m_unitMap.put(item.getDcItemName(),item.getDcItemCode());
            }
            ev_unit_type.setEntries(unitTypeMap);
        }
        if (m_dicAccidentType != null && m_dicUnitType.dataSource.size() > 0) {
            int size = m_dicAccidentType.dataSource.size();
            for (int i = 0; i < size; i++) {
                DicInfo item = m_dicAccidentType.dataSource.get(i);
                accidentTypeMap.put(i, item.getDcItemName());
                m_acciTypeMap.put(item.getDcItemName(),item.getDcItemCode());
            }
            ev_type.setEntries(accidentTypeMap);
        }
        String [] acciGrade = getResources().getStringArray(R.array.accident_type);
        if(acciGrade != null){
            for(int i  = 0; i < acciGrade.length;i ++){
                m_acciGradeMap.put(i,acciGrade[i]);
                m_acciGradMapList.add(acciGrade[i]);
            }
            ll_enum_level.setEntries(m_acciGradMapList);
            ll_enum_level.setCurrentDetailText(m_acciGradMapList.get(0));
        }
    }
    private String  getAcciTypeName(String code){
        for(String key:m_acciTypeMap.keySet()){
            if(m_acciTypeMap.get(key).equals(code)){
                return key;
            }
        }
        return "";
    }
    private String getAcciUnitTypeName(String code){
        for(String key:m_unitMap.keySet()){
            if(m_unitMap.get(key).equals(code)){
                return key;
            }
        }
        return "";
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode, requestCode, data);
    }
    private boolean checkParam(){
        boolean bRet = true;
        String accUnit = ce_accident_unit.getText().toString();
        if(accUnit == null || accUnit.isEmpty()){
            ToastUtils.show("事故单位不能为空");
            bRet = false;
            return  bRet;
        }
        String inJuriesCount = ce_serious_injuries_count.getText().toString();
        if(inJuriesCount == null || inJuriesCount.isEmpty()){
            ToastUtils.show("重伤人数不能为空");
            bRet = false;
            return bRet;
        }
        String deatchCount = ce_death_count.getText().toString();
        if(deatchCount == null || deatchCount.isEmpty()){
            ToastUtils.show("死亡人数不能为空");
            bRet = false;
            return bRet;
        }
        String economicLoss = ce_direct_economic_loss.getText().toString();
        if(economicLoss == null || economicLoss.isEmpty()){
            ToastUtils.show("直接经济损失不能为空");
            bRet = false;
            return bRet;
        }
        String level = ll_enum_level.getCurrentDetailText();
        if(level == null || level.isEmpty()){
            ToastUtils.show("事故级别不能为空");
            bRet = false;
            return  bRet;
        }
        String accidentDesc = aev_accident_description.getEditText().toString();
        if(accidentDesc == null || accidentDesc.isEmpty()){
            ToastUtils.show("事故描述不能为空");
            bRet = false;
            return bRet;
        }
        String unitType = ev_unit_type.getCurrentDetailText();
        if(unitType == null || unitType.isEmpty()){
            ToastUtils.show("事故单位类型不能为空");
            bRet = false;
            return bRet;
        }
        String accidentType = ev_type.getCurrentDetailText();
        if(accidentType == null || unitType.isEmpty()){
            ToastUtils.show("事故类型不能为空");
            bRet = false;
            return bRet;
        }
        return bRet;

    }
    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message,0);
    }
    /**
     * 0 保存在本地 1 提交
     * 新增事故 post  快报 put 补报 post pGuid
     */
    private void accidentReport() {
        if(!checkParam())return;;
        int localStatus = 0;
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        String url = GlobleConstants.strCJIP + "/cjapi/cj/yuanXin/Accident/create";
        HashMap<String, String> params = new HashMap<>();
        params.put("acciWiunType", m_unitMap.get(ev_unit_type.getCurrentDetailText())); // 事故单位类型
        params.put("acciCate",m_acciTypeMap.get(ev_type.getCurrentDetailText()) );
        params.put("occuTime", tv_time.getText().toString()); // 发生时间
        params.put("occuLoc", ce_occo_loc.getText().toString()); // 事故地点
        params.put("serInjNum", ce_serious_injuries_count.getText().toString()); // 重伤人数
        params.put("casNum", ce_death_count.getText().toString()); // 死亡人数
        params.put("econLoss", ce_direct_economic_loss.getText().toString()); // 直接经济损失
        params.put("acciSitu", aev_accident_description.getEditText()); // 事故简要情况
        params.put("note", "移动端接口测试");
        params.put("recPers", SyberosManagerImpl.getInstance().getCurrentUserId());
        params.put("acciGrad", String.valueOf(ll_enum_level.getCurrentIndex()+1));
        params.put("ifRespAcci",rg_accident_liability.getCheckedRadioButtonId() == R.id.rb_accident_liability_yes ?"1":"0");
        params.put("ifPhoRep",rg_accident_phone_report.getCheckedRadioButtonId() == R.id.rb_accident_phone_report_yes?"1":"0");
        switch (this.type){
            case GlobleConstants.NEW_ACCI:
                params.put("acciWiunGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
                localStatus = 0;
                params.put("repStat", String.valueOf(GlobleConstants.reportAcci_0));
                localCacheEntity.commitType = 0;
                break;
            case GlobleConstants.reportAcci_1:
            case GlobleConstants.reportAcci_2:
                params.put("acciGrad", objAcci.getAcciGrad()== null?"1":objAcci.getAcciGrad());
                params.put("acciWiunGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
                params.put("repStat", String.valueOf(GlobleConstants.reportAcci_1));
                params.put("pGuid",objAcci.getId());
                url = GlobleConstants.strCJIP +"/cjapi/cj/yuanXin/Accident/repay";
                localStatus = 0;
                localCacheEntity.commitType = 0;
                break;
            case GlobleConstants.reportAcci_0:
                url = GlobleConstants.strCJIP +"/cjapi/cj/yuanXin/Accident/fastReport";
                localStatus = 1;
                localCacheEntity.commitType = 1;
                params.put("acciGrad", objAcci.getAcciGrad()== null?"1":objAcci.getAcciGrad());
                params.put("missNum","");
                params.put("rescTreaMeas","");
                params.put("contaPers","");
                params.put("offiTel","");
                params.put("updTime","");
                params.put("guid",objAcci.getId());
                params.put("repStat", String.valueOf(GlobleConstants.reportAcci_1));
                break;
        }
        localCacheEntity.url = url;
        localCacheEntity.type = localStatus;
        localCacheEntity.attachType = localStatus; // 0 暫存 1 提交
        localCacheEntity.params = params;
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
                info.medPath = item.localFile.getPath();
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                File file = new File(info.medPath);
                info.medSize = file.length();
                info.bisTableName = "OBJ_ACCI";
                if(objAcci != null) {
                    info.bisGuid = objAcci.getId() == null ? "" : objAcci.getId();
                }else {
                    info.bisGuid = "";
                }
                info.localStatus = String.valueOf(localStatus);
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
                finish();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    AccidentNewFormForEntActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前事故内容未保存，确定退出？");
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

    @Override
    public void dialogClick() {
        accidentReport();
    }

    @Override
    public void dialogCancel() {

    }
}
