package com.syberos.shuili.activity.dangermanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by Administrator on 2018/4/16.
 */

public class InvestigationSuperviceFormActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_supervise_code)
    TextView tv_supervise_code;
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.rg_supervice_listing)
    RadioGroup rg_supervice_listing;
    @BindView(R.id.rb_supervice_listing_yes)
    RadioButton rb_supervice_listing_yes;
    @BindView(R.id.rb_supervice_listing_no)
    RadioButton rb_supervice_listing_no;
    @BindView(R.id.et_supervise_desc)
    AudioEditView et_supervise_desc;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;

    HiddenInvestigationTaskInfo hiddenInvestigationTaskInfo;


    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_supervise_submit_form;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        hiddenInvestigationTaskInfo = (HiddenInvestigationTaskInfo) bundle.getSerializable("data");
        tv_time.setText(CommonUtils.getCurrentDate());
    }

    @Override
    public void initView() {
        showTitle("隐患督办");
        setActionBarRightVisible(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
             //   submit();
                break;
            case R.id.rl_time:
                setTime();
                break;
        }

    }
    private void setTime(){
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
    private void submit(){
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/maj/bisMajHiddSups/";
        HashMap<String,String>params = new HashMap<>();
        // 隐患GUID
        params.put("hiddGuid",hiddenInvestigationTaskInfo.getHiddGuid());
        //是否挂牌督办
        if(rg_supervice_listing.getCheckedRadioButtonId() == R.id.rb_accident_liability_yes) {
            params.put("isList", "0");
        }else{
            params.put("isList","1");
        }
        //整改期限
        params.put("rectPeri","");
        // 督办文号
        params.put("supLareId",tv_supervise_code.getText().toString());
        //督办日期
        params.put("supDate",tv_time.getText().toString());
        //督办单位
        params.put("supWiunCode",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        //督办负责人
        params.put("supLegPers","");
        //督办意见
        params.put("supOpin",et_supervise_desc.getEditText());
        //备注
        params.put("note","");
        //采集时间
        params.put("collTime","");
        //更新时间
        params.put("updTime","");
        //记录人员
        params.put("recPers","");
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        localCacheEntity.type = 1;
        localCacheEntity.params = params;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
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
}
