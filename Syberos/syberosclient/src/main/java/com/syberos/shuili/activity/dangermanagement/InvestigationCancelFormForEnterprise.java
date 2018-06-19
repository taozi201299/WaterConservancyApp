package com.syberos.shuili.activity.dangermanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.App;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by Administrator on 2018/4/16.
 * 隐患销号 类似于隐患验收
 */

public class InvestigationCancelFormForEnterprise  extends BaseActivity{
    @BindView(R.id.ev_cancel_des)
    AudioEditView ev_cancel_des;
    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    ObjHidden item;

    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_cancel_enterprise_form;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        item = (ObjHidden)bundle.getSerializable("data");

    }

    @Override
    public void initView() {
        showTitle("隐患销号");
        setActionBarRightVisible(View.INVISIBLE);

    }
    private void  commit() {
        String  url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/hidd/rect/bisHiddRectAcce/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",item.getGuid());//隐患GUID
        params.put("requCompDate",""); //治理完成日期
        params.put("accepLegPers","");// 验收责任人
        params.put("accepPers",""); // 验收人
        params.put("accepDate",""); // 验收时间
        params.put("accepOpin",ev_cancel_des.getEditText()); //验收意见
        params.put("note","");  //备注
        params.put("collTime", CommonUtils.getCurrentDate()); //采集时间
        params.put("updTime",""); // 更新时间
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName()); // 记录人员
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  ll_multimedia.getBinaryFile();

        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                info.medPath = item.localFile.getPath();
                info.url = "http://" + App.strIP + "/wcsps-supervision/v1/jck/attMedBase/";
                info.bisTableName = "BIS_HIDD_RECT_ACCE";
                info.bisGuid = this.item.getGuid();
                info.localStatus = "1";
                if(item.type == MultimediaView.LocalAttachmentType.IMAGE){
                    info.medType = "0";
                }else {
                    info.medType = "1";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachMentInfoEntities.add(info);
            }
        }
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
