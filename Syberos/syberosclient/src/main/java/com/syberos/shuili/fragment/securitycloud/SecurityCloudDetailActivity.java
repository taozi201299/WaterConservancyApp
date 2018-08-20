package com.syberos.shuili.fragment.securitycloud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.syberos.shuili.R;

import butterknife.BindView;

/**
 * Created by BZB on 2018/8/1.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.securitycloud.
 */
public class SecurityCloudDetailActivity extends AppCompatActivity{
    private static SecurityCloudDetailActivity instance ;

    public SecurityCloudDetailActivity(){

    }

    public static SecurityCloudDetailActivity  getInstance(){
        if (instance == null) {
            synchronized (SecurityCloudDetailActivity.class){
                if (instance == null) {
                    instance = new SecurityCloudDetailActivity() ;
                }
            }
        }
        return instance ;
    }
    @BindView(R.id.fragment_content)
    FrameLayout frameLayout;
    String strJsonData1 = "{\"accidentInfoEntry\":{\"accLevelFourCount\":0,\"accLevelOneCount\":10,\"accLevelThreeCount\":10,\"accLevelTwoCount\":10,\"deathCount\":10,\"score\":10,\"totalCount\":10},\"compScoreTrend\":{\"dataList\":[{\"date\":null,\"score\":10}],\"qualifiedScore\":10},\"hiddenInfoEntry\":{\"majorHadSupervisingCount\":10,\"majorHiddenCount\":10,\"majorHiddenHadRectifyCount\":10,\"majorHiddenNoRectifyCount\":10,\"majorLateNoRectifyCount\":10,\"noRectifyCount\":10,\"normalHiddenCount\":10,\"normalHiddenHadRectifyCount\":10,\"normalHiddenNoRectifyCount\":10,\"normalLateNoRectifyCount\":10,\"score\":10,\"totalHiddenCount\":10},\"rankList\":[{\"id\":null,\"name\":\"测试数据\",\"score\":10}],\"riskSourceEntry\":{\"hadControl\":10,\"hadRecord\":10,\"noControl\":10,\"noRecord\":10,\"score\":10},\"straightTubeManageEntry\":{\"dataList\":[{\"taskId\":\"1\",\"partReportCount\":10,\"partUnReportCount\":10},{\"taskId\":\"2\",\"partReportCount\": 10,\"partUnReportCount\": 10},{\"taskId\":\"3\",\"partReportCount\": 10,\"partUnReportCount\": 10}, {\"taskId\":\"4\",\"partReportCount\": 10,\"partUnReportCount\": 10}],\"perTrainingHours\":10,\"score\":10,\"trainingPersonCount\":10},\"supervisionMangeEntry\":{\"score\":10,\"standardLevelOneCount\":10,\"standardLevelThreeCount\":10,\"standardLevelTwoCount\":10,\"workAssessmentScore\":10},\"synthesisInfoEntry\":{\"chainRatio\":\"测试数据\",\"sameTimeRatio\":\"测试数据\",\"score\":88}}";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_security_cloud_detail);

        final BaseSecurityCloudFragment securityCloudFragment = new BaseSecurityCloudFragment(strJsonData1, "4");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (securityCloudFragment.isAdded()) {
            transaction.show(securityCloudFragment).commit();
        } else {
            transaction.add(R.id.fragment_content, securityCloudFragment).commit();
        }

    }

}
