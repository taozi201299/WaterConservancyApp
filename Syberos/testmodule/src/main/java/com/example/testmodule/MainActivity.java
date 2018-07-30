package com.example.testmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.testmodule.securitycloud.SecurityCloudEntry;
import com.example.testmodule.securitycloud.StraightTubeManageEntry;
import com.example.testmodule.securitycloud.SupervisionMangeEntry;
import com.example.testmodule.testrxjavaretrofit.HttpRequestResultActivity;

import com.example.testmodule.thematicchart.RankRateItem;
import com.example.testmodule.thematicchart.accident.AccidentDetailEntry;
import com.example.testmodule.thematicchart.accident.AccidentItemDetailEntry;
import com.example.testmodule.thematicchart.accident.AccidentPointEntry;
import com.example.testmodule.thematicchart.accident.AccidentPointInfoEntry;
import com.example.testmodule.thematicchart.accident.AccidentStatisticEntry;
import com.example.testmodule.thematicchart.accident.SituationEntry;
import com.example.testmodule.thematicchart.hidden.ChartEntry;
import com.example.testmodule.thematicchart.hidden.HiddenDetailEntry;
import com.example.testmodule.thematicchart.hidden.HiddenOrgDetailEntry;
import com.example.testmodule.thematicchart.hidden.HiddenPointEntry;
import com.example.testmodule.thematicchart.hidden.HiddenPointInfoEntry;
import com.example.testmodule.thematicchart.hidden.MajorHiddenEntry;
import com.example.testmodule.thematicchart.hidden.NormalHiddenEntry;
import com.example.testmodule.thematicchart.hidden.RankRateEntry;
import com.example.testmodule.thematicchart.risksource.OrgRiskSourceEntry;
import com.example.testmodule.thematicchart.risksource.RiskPointEntry;
import com.example.testmodule.thematicchart.risksource.RiskRankRateEntry;
import com.example.testmodule.thematicchart.risksource.RiskSituationEntry;
import com.example.testmodule.thematicchart.risksource.RiskSourceEntry;
import com.example.testmodule.thematicchart.risksource.RiskSourceInfoEntry;
import com.example.testmodule.thematicchart.risksource.RiskStatisticsEntry;
import com.example.testmodule.thematicchart.securitychecks.OrgSecurityCheckEntry;
import com.example.testmodule.thematicchart.securitychecks.SecurityCheckEntry;
import com.example.testmodule.thematicchart.securitychecks.SecurityCheckInfoEntry;
import com.example.testmodule.thematicchart.securitychecks.SecurityCheckPointEntry;
import com.example.testmodule.thematicchart.securitychecks.SecurityRankRateEntry;
import com.example.testmodule.thematicchart.securitychecks.SecuritySituationEntry;
import com.example.testmodule.thematicchart.standardization.OrgStandardizationEntry;
import com.example.testmodule.thematicchart.standardization.StandardizationAssessment;
import com.example.testmodule.thematicchart.standardization.StandardizationCertificateEntry;
import com.example.testmodule.thematicchart.standardization.StandardizationEntry;
import com.example.testmodule.thematicchart.standardization.StandardizationInfoEntry;
import com.example.testmodule.thematicchart.standardization.StandardizationPointEntry;
import com.example.testmodule.thematicchart.standardization.StandardizationSituationEntry;
import com.example.testmodule.thematicchart.supervisionenforcement.SupervisionEnforcementEntry;
import com.example.testmodule.thematicchart.supervisionenforcement.SupervisionOrgEntry;
import com.example.testmodule.thematicchart.waterinspection.OrgWaterInspectionEntry;
import com.example.testmodule.thematicchart.waterinspection.WIProblemEntry;
import com.example.testmodule.thematicchart.waterinspection.WISituationEntry;
import com.example.testmodule.thematicchart.waterinspection.WISortEntry;
import com.example.testmodule.thematicchart.waterinspection.WaterInspectionEntry;
import com.example.testmodule.thematicchart.waterinspection.WaterInspectionInfoEntry;
import com.example.testmodule.thematicchart.waterinspection.WaterInspectionPointEntry;
import com.example.testmodule.thematicchart.workassessment.WARecentlyScoreEntry;
import com.example.testmodule.thematicchart.workassessment.WASituationEntry;
import com.example.testmodule.thematicchart.workassessment.WorkAssessmentEntry;
import com.example.testmodule.thematicchart.workassessment.WorkAssessmentInfoEntry;
import com.example.testmodule.thematicchart.workassessment.WorkPointEntry;
import com.example.testmodule.view.WaterViewActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GSON FORMATE";
    private Gson gsonSerializeNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gsonSerializeNull = new GsonBuilder().serializeNulls().create();
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_hidden:
                covertJava2Json_hidden();
                break;
            case R.id.btn_accident:
                covertJava2Json_accident();
                break;
            case R.id.btn_risk_source:
                covertJava2Json_risk_source();
                break;
            case R.id.btn_regular://标准化
                covertJava2Json_regular();
                break;
            case R.id.btn_safe_check:
                covertJava2Json_safe_check();
                break;
            case R.id.btn_work:
                covertJava2Json_work();
                break;
            case R.id.btn_ji_cha:
                covertJava2Json_ji_cha();
                break;
            case R.id.btn_zhi_fa:
                covertJava2Json_zhi_fa();
                break;

            case R.id.btn_security_cloud:
                covertJava2Json_security_cloud();
                break;


            case R.id.btn_request_data:
                startActivity(new Intent(this, HttpRequestResultActivity.class));
                break;

            case R.id.btn_water_view:
                startActivity(new Intent(this, WaterViewActivity.class));
                break;


        }
    }

    /**
     * 安全云
     */
    private void covertJava2Json_security_cloud() {
        SecurityCloudEntry.SynthesisInfoEntry synthesisInfoEntry = new SecurityCloudEntry.SynthesisInfoEntry();

        SecurityCloudEntry.AccidentInfoEntry accidentInfoEntry = new SecurityCloudEntry.AccidentInfoEntry();//事故

        SecurityCloudEntry.HiddenInfoEntry hiddenInfoEntry = new SecurityCloudEntry.HiddenInfoEntry();//隐患

        SecurityCloudEntry.RiskSourceEntry riskSourceEntry = new SecurityCloudEntry.RiskSourceEntry();//危险源

        StraightTubeManageEntry straightTubeManageEntry = new StraightTubeManageEntry();//直管的 “管理”数据
        StraightTubeManageEntry.ReportInfoItemEntry reportInfoItemEntry = new StraightTubeManageEntry.ReportInfoItemEntry();
        List<StraightTubeManageEntry.ReportInfoItemEntry> reportInfoItemEntryList = new ArrayList<>();
        reportInfoItemEntryList.add(reportInfoItemEntry);
        straightTubeManageEntry.setDataList(reportInfoItemEntryList);

        SupervisionMangeEntry supervisionMangeEntry = new SupervisionMangeEntry();//监管 和 流域的 “管理”数据

        SecurityCloudEntry.CompScoreTrend compScoreTrend = new SecurityCloudEntry.CompScoreTrend();  //综合得分 趋势
        SecurityCloudEntry.SingleMonthScore singleMonthScore = new SecurityCloudEntry.SingleMonthScore();
        List<SecurityCloudEntry.SingleMonthScore> singleMonthScoreList = new ArrayList<>();
        singleMonthScoreList.add(singleMonthScore);
        compScoreTrend.setDataList(singleMonthScoreList);

        List<SecurityCloudEntry.AreaRank> rankList = new ArrayList<>();    //排名
        SecurityCloudEntry.AreaRank areaRank = new SecurityCloudEntry.AreaRank();
        rankList.add(areaRank);


        SecurityCloudEntry securityCloudEntry = new SecurityCloudEntry();
        securityCloudEntry.setAccidentInfoEntry(accidentInfoEntry);
        securityCloudEntry.setCompScoreTrend(compScoreTrend);
        securityCloudEntry.setHiddenInfoEntry(hiddenInfoEntry);
        securityCloudEntry.setRankList(rankList);
        securityCloudEntry.setRiskSourceEntry(riskSourceEntry);
        securityCloudEntry.setStraightTubeManageEntry(straightTubeManageEntry);
        securityCloudEntry.setSupervisionMangeEntry(supervisionMangeEntry);
        securityCloudEntry.setSynthesisInfoEntry(synthesisInfoEntry);

        Log.e(TAG, "安全云: " + gsonSerializeNull.toJson(securityCloudEntry));

    }

    private void covertJava2Json_zhi_fa() {
        SupervisionEnforcementEntry supervisionEnforcementEntry = new SupervisionEnforcementEntry();
        List<SupervisionOrgEntry> pointEntryList = new ArrayList<>();
        SupervisionOrgEntry supervisionOrgEntry = new SupervisionOrgEntry();

        pointEntryList.add(supervisionOrgEntry);

        supervisionEnforcementEntry.setPointEntryList(pointEntryList);

        Log.e(TAG, "监督执法: " + gsonSerializeNull.toJson(supervisionEnforcementEntry));
    }

    //水利稽查
    private void covertJava2Json_ji_cha() {
        WaterInspectionEntry waterInspectionEntry = new WaterInspectionEntry();

        //    水利稽查
        List<WaterInspectionPointEntry> pointEntryList = new ArrayList<>();
        WaterInspectionPointEntry waterInspectionPointEntry = new WaterInspectionPointEntry();
        WaterInspectionInfoEntry waterInspectionInfoEntry = new WaterInspectionInfoEntry();
        Map<String, WaterInspectionInfoEntry> waterInspectionInfoEntryMap = new HashMap<>();
        waterInspectionInfoEntryMap.put("0", waterInspectionInfoEntry);
        waterInspectionPointEntry.setWaterInspectionInfoEntryMap(waterInspectionInfoEntryMap);
        pointEntryList.add(waterInspectionPointEntry);

        //  xxxx年本部门水利稽察情况
        WISituationEntry wiSituationEntry = new WISituationEntry();
        //稽察问题分类统计占比图
        WISortEntry wiClassisEntry = new WISortEntry();
        //稽察问题等级统计
        WIProblemEntry wiProblemEntry = new WIProblemEntry();

        waterInspectionEntry.setPointEntryList(pointEntryList);
        waterInspectionEntry.setWiClassisEntry(wiClassisEntry);
        waterInspectionEntry.setWiProblemEntry(wiProblemEntry);
        waterInspectionEntry.setWiSituationEntry(wiSituationEntry);
        Log.e(TAG, "水利稽查" + gsonSerializeNull.toJson(waterInspectionEntry));

        OrgWaterInspectionEntry orgWaterInspectionEntry = new OrgWaterInspectionEntry();
        orgWaterInspectionEntry.setWiClassisEntry(wiClassisEntry);
        orgWaterInspectionEntry.setWiProblemEntry(wiProblemEntry);
        orgWaterInspectionEntry.setWiSituationEntry(wiSituationEntry);
        Log.e(TAG, "水利稽查-item" + gsonSerializeNull.toJson(orgWaterInspectionEntry));

    }

    private void covertJava2Json_regular() {
//  标准化检查
        List<StandardizationPointEntry> pointEntryList = new ArrayList<>();
        StandardizationPointEntry standardizationPointEntry = new StandardizationPointEntry();
        StandardizationInfoEntry standardizationInfoEntry = new StandardizationInfoEntry();

        //  xxxx年直管工程标准化情况
        StandardizationSituationEntry standardizationRiskSituationEntry = new StandardizationSituationEntry();//xxxx年直管工程标准化情况
        //标准化等级占比
        List<RankRateItem> standardizationRankRateList = new ArrayList<>();//标准化等级占比
        //   标准化证书情况
        StandardizationCertificateEntry standardizationCertificateEntry = new StandardizationCertificateEntry();//标准化证书情况
        //    标准化考核情况
        StandardizationAssessment standardizationAssessment = new StandardizationAssessment();// 标准化考核

        RankRateItem rankRateItem = new RankRateItem();
        standardizationRankRateList.add(rankRateItem);

        standardizationPointEntry.setStandardizationAssessment(standardizationAssessment);
        standardizationPointEntry.setStandardizationInfoEntry(standardizationInfoEntry);
        pointEntryList.add(standardizationPointEntry);

        StandardizationEntry standardizationEntry = new StandardizationEntry();
        standardizationEntry.setStandardizationAssessment(standardizationAssessment);
        standardizationEntry.setStandardizationCertificateEntry(standardizationCertificateEntry);
        standardizationEntry.setStandardizationRankRateList(standardizationRankRateList);
        standardizationEntry.setStandardizationRiskSituationEntry(standardizationRiskSituationEntry);
        standardizationEntry.setPointEntryList(pointEntryList);

        Log.e(TAG, "标准化: " + gsonSerializeNull.toJson(standardizationEntry));

        OrgStandardizationEntry orgStandardizationEntry = new OrgStandardizationEntry();
        orgStandardizationEntry.setStandardizationAssessment(standardizationAssessment);
        orgStandardizationEntry.setStandardizationCertificateEntry(standardizationCertificateEntry);
        orgStandardizationEntry.setStandardizationRankRateList(standardizationRankRateList);
        orgStandardizationEntry.setStandardizationRiskSituationEntry(standardizationRiskSituationEntry);
        Log.e(TAG, "标准化-item " + gsonSerializeNull.toJson(orgStandardizationEntry));

    }

    private void covertJava2Json_safe_check() {
        //  安全检查
        List<SecurityCheckPointEntry> pointEntryList = new ArrayList<>();

        SecurityCheckInfoEntry securityCheckInfoEntry = new SecurityCheckInfoEntry();
        ArrayList<SecurityCheckInfoEntry> list = new ArrayList<>();
        list.add(securityCheckInfoEntry);

        SecurityCheckPointEntry securityCheckPointEntry = new SecurityCheckPointEntry();
        Map<String, List<SecurityCheckInfoEntry>> listMap = new HashMap<>();
        listMap.put("1", list);

        securityCheckPointEntry.setSecurityCheckInfoEntry(listMap);
        pointEntryList.add(securityCheckPointEntry);


        //部门检查情况
        SecuritySituationEntry securitySituationEntry = new SecuritySituationEntry();
        //检查完成情况统计
        SecurityRankRateEntry securityRankRateEntry = new SecurityRankRateEntry();


        SecurityCheckEntry securityCheckEntry = new SecurityCheckEntry();
        securityCheckEntry.setPointEntryList(pointEntryList);
        securityCheckEntry.setSecuritySituationEntry(securitySituationEntry);
        securityCheckEntry.setSecurityRankRateEntry(securityRankRateEntry);

        Log.e(TAG, "安全检查:" + gsonSerializeNull.toJson(securityCheckEntry));

        OrgSecurityCheckEntry orgSecurityCheckEntry = new OrgSecurityCheckEntry();
        orgSecurityCheckEntry.setSecurityRankRateEntry(securityRankRateEntry);
        orgSecurityCheckEntry.setSecuritySituationEntry(securitySituationEntry);

        Log.e(TAG, "安全检查:-item" + gsonSerializeNull.toJson(orgSecurityCheckEntry));

    }

    private void covertJava2Json_work() {
        List<WorkPointEntry> pointEntryList = new ArrayList<>();

        //    xxxx年直管工程工作考核情况
        WASituationEntry waSituationEntry = new WASituationEntry();
        //近5年个工作考核得分情况
        WARecentlyScoreEntry waRecentlyScoreEntry = new WARecentlyScoreEntry();
        WARecentlyScoreEntry.ScoreItem scoreItem = new WARecentlyScoreEntry.ScoreItem();
        ArrayList<WARecentlyScoreEntry.ScoreItem> scoreItems = new ArrayList<>();
        scoreItems.add(scoreItem);
        waRecentlyScoreEntry.setDataList(scoreItems);

        WorkAssessmentEntry workAssessmentEntry = new WorkAssessmentEntry();

        WorkPointEntry workPointEntry = new WorkPointEntry();
        WorkAssessmentInfoEntry workAssessmentInfoEntry = new WorkAssessmentInfoEntry();
        workPointEntry.setWorkAssessmentInfoEntry(workAssessmentInfoEntry);
        pointEntryList.add(workPointEntry);
        workAssessmentEntry.setPointEntryList(pointEntryList);

        workAssessmentEntry.setWaRecentlyScoreEntry(waRecentlyScoreEntry);
        workAssessmentEntry.setWaSituationEntry(waSituationEntry);

        Log.e(TAG, "工作考核:" + gsonSerializeNull.toJson(workAssessmentEntry));


    }

    private void covertJava2Json_risk_source() {
        List<RiskPointEntry> pointEntryList = new ArrayList<>();
        RiskSituationEntry riskRiskSituationEntry = new RiskSituationEntry();
        RiskRankRateEntry riskRankRateEntry = new RiskRankRateEntry();
        List<RiskStatisticsEntry> riskSourceEntryList = new ArrayList<>();

        RiskSourceEntry riskSourceEntry = new RiskSourceEntry();

        RiskPointEntry riskPointEntry = new RiskPointEntry();
        RiskSourceInfoEntry riskSourceInfoEntry = new RiskSourceInfoEntry();
        riskPointEntry.setRiskSourceInfoEntry(riskSourceInfoEntry);
        pointEntryList.add(riskPointEntry);
        riskSourceEntry.setPointEntryList(pointEntryList);

        riskSourceEntry.setRiskRiskSituationEntry(riskRiskSituationEntry);

        riskSourceEntry.setRiskRankRateEntry(riskRankRateEntry);

        RiskStatisticsEntry riskStatisticsEntry = new RiskStatisticsEntry();
        riskSourceEntryList.add(riskStatisticsEntry);
        riskSourceEntry.setRiskSourceEntryList(riskSourceEntryList);

        Log.e(TAG, "危险源" + gsonSerializeNull.toJson(riskSourceEntry));


//        private RiskSituationEntry riskRiskSituationEntry;
//        private RiskRankRateEntry riskRankRateEntry;
//        private List<RiskStatisticsEntry> riskSourceEntryList;//一般风险源\重大风险源\危化品危险源
        OrgRiskSourceEntry orgRiskSourceEntry = new OrgRiskSourceEntry();
        orgRiskSourceEntry.setRiskRiskSituationEntry(riskRiskSituationEntry);
        orgRiskSourceEntry.setRiskRankRateEntry(riskRankRateEntry);
        orgRiskSourceEntry.setRiskSourceEntryList(riskSourceEntryList);
        Log.e(TAG, "危险源-item" + gsonSerializeNull.toJson(orgRiskSourceEntry));
    }

    private void covertJava2Json_accident() {
        AccidentPointInfoEntry accidentPointInfoEntry = new AccidentPointInfoEntry();
        AccidentPointEntry accidentPointEntry = new AccidentPointEntry();
        accidentPointEntry.setAccidentPointInfoEntry(accidentPointInfoEntry);
        List<AccidentPointEntry> accidentPointEntries = new ArrayList<>();
        accidentPointEntries.add(accidentPointEntry);

        SituationEntry situationEntry = new SituationEntry();
        com.example.testmodule.thematicchart.accident.RankRateEntry rankRateEntry = new com.example.testmodule.thematicchart.accident.RankRateEntry();
        com.example.testmodule.thematicchart.accident.RankRateEntry.ItemEntry itemEntry = new com.example.testmodule.thematicchart.accident.RankRateEntry.ItemEntry();
        ArrayList<com.example.testmodule.thematicchart.accident.RankRateEntry.ItemEntry> itemEntries = new ArrayList<>();
        itemEntries.add(itemEntry);
        rankRateEntry.setDataList(itemEntries);
        AccidentStatisticEntry normalAccidentDataEntry = new AccidentStatisticEntry();
        AccidentStatisticEntry majorAccidentDataEntry = new AccidentStatisticEntry();

        AccidentDetailEntry accidentDetailEntry = new AccidentDetailEntry();

        accidentDetailEntry.setAccidentPointEntries(accidentPointEntries);
        accidentDetailEntry.setSituationEntry(situationEntry);
        accidentDetailEntry.setRankRateEntry(rankRateEntry);
        accidentDetailEntry.setNormalAccidentDataEntry(normalAccidentDataEntry);
        accidentDetailEntry.setMajorAccidentDataEntry(majorAccidentDataEntry);
        Log.e(TAG, "事故: " + gsonSerializeNull.toJson(accidentDetailEntry));

        AccidentItemDetailEntry accidentItemDetailEntry = new AccidentItemDetailEntry();
        ArrayList<AccidentItemDetailEntry> accidentDetailEntries = new ArrayList<>();
        accidentDetailEntries.add(accidentItemDetailEntry);
        Log.e(TAG, "事故:-item  " + gsonSerializeNull.toJson(accidentDetailEntries));


    }

    private void covertJava2Json_hidden() {
        HiddenDetailEntry hiddenDetailEntry = new HiddenDetailEntry();
        hiddenDetailEntry.setChartEntry(new ChartEntry());
        hiddenDetailEntry.setMajorHiddenEntry(new MajorHiddenEntry());
        hiddenDetailEntry.setNormalHiddenEntry(new NormalHiddenEntry());

        HiddenPointEntry hiddenPointEntry = new HiddenPointEntry();
        hiddenPointEntry.setHiddenPointInfoEntry(new HiddenPointInfoEntry());
        ArrayList<HiddenPointEntry> list = new ArrayList<>();
        list.add(hiddenPointEntry);

        hiddenDetailEntry.setPointEntryList(list);
        hiddenDetailEntry.setRankRateEntry(new RankRateEntry());

        Log.e(TAG, "隐患: " + gsonSerializeNull.toJson(hiddenDetailEntry));
        HiddenOrgDetailEntry hiddenOrgDetailEntry = new HiddenOrgDetailEntry();
//        ChartEntry chartEntry;
//        RankRateEntry rankRateEntry;
//        NormalHiddenEntry normalHiddenEntry;
//        MajorHiddenEntry majorHiddenEntry;
        hiddenOrgDetailEntry.setChartEntry(new ChartEntry());
        hiddenOrgDetailEntry.setRankRateEntry(new RankRateEntry());
        hiddenOrgDetailEntry.setNormalHiddenEntry(new NormalHiddenEntry());
        hiddenOrgDetailEntry.setMajorHiddenEntry(new MajorHiddenEntry());
        Log.e(TAG, "隐患-item: " + gsonSerializeNull.toJson(hiddenOrgDetailEntry));
    }
}
