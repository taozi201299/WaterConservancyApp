package com.syberos.shuili.fragment.securitycloud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.cjt2325.cameralibrary.util.LogUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.entity.securitycloud.SecurityCloudAreaEntry;
import com.syberos.shuili.entity.securitycloud.SecurityCloudEntry;
import com.syberos.shuili.entity.securitycloud.SecurityCloudOrgEntry;
import com.syberos.shuili.entity.securitycloud.StraightTubeManageEntry;
import com.syberos.shuili.entity.securitycloud.SupervisionMangeEntry;
import com.syberos.shuili.network.retrofit.BaseObserver;
import com.syberos.shuili.network.retrofit.RetrofitHttpMethods;
import com.syberos.shuili.network.retrofit.RxApiManager;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.DialPlateView;
import com.syberos.shuili.view.WaterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * Created by BZB on 2018/7/20.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.securitycloud.
 */
@SuppressLint("ValidFragment")
public class BaseSecurityCloudFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener {
    public static final String TAG = "TestData";
    String strJsonData;//= "{\"accidentInfoEntry\":{\"accLevelFourCount\":0,\"accLevelOneCount\":0,\"accLevelThreeCount\":0,\"accLevelTwoCount\":0,\"deathCount\":0,\"score\":0,\"totalCount\":0},\"compScoreTrend\":{\"dataList\":[{\"date\":null,\"score\":0}],\"qualifiedScore\":0},\"hiddenInfoEntry\":{\"majorHadSupervisingCount\":0,\"majorHiddenCount\":0,\"majorHiddenHadRectifyCount\":0,\"majorHiddenNoRectifyCount\":0,\"majorLateNoRectifyCount\":0,\"noRectifyCount\":0,\"normalHiddenCount\":0,\"normalHiddenHadRectifyCount\":0,\"normalHiddenNoRectifyCount\":0,\"normalLateNoRectifyCount\":0,\"score\":0,\"totalHiddenCount\":0},\"rankList\":[{\"id\":null,\"name\":null,\"score\":0}],\"riskSourceEntry\":{\"hadControl\":0,\"hadRecord\":0,\"noControl\":0,\"noRecord\":0,\"score\":0},\"straightTubeManageEntry\":{\"dataList\":[{\"partReportCount\":0,\"partUnReportCount\":0}],\"perTrainingHours\":0,\"score\":0,\"trainingPersonCount\":0},\"supervisionMangeEntry\":{\"score\":0,\"standardLevelOneCount\":0,\"standardLevelThreeCount\":0,\"standardLevelTwoCount\":0,\"workAssessmentScore\":0},\"synthesisInfoEntry\":{\"chainRatio\":null,\"sameTimeRatio\":null,\"score\":0}}";
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_grade_time)
    TextView tvGradeTime;
    @BindView(R.id.view_dial_plate)
    DialPlateView viewDialPlate;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_acc)
    CardView cardViewAcc;

    @BindView(R.id.view_manage)
    CardView cardViewManager;

    @BindView(R.id.view_manage_direct)
    CardView cardViewManagerDirect;


    @BindView(R.id.tv_score_title)
    TextView tvScoreTitle;

    @BindView(R.id.view_hidden)
    CardView viewHidden;
    @BindView(R.id.view_risk_source)
    CardView viewRiskSource;
    @BindView(R.id.view_grade_trend)
    LinearLayout viewGradeTrend;
    @BindView(R.id.view_rank)
    LinearLayout viewRank;

    //    事故
    @BindView(R.id.pie_char_acc)
    PieChart pieCharAcc;
    @BindView(R.id.tv_score_acc)
    TextView tvScoreAcc;
    @BindView(R.id.tv_death_acc_count)
    TextView tvDeathAccCount;
    @BindView(R.id.tv_count_acc)
    TextView tvCountAcc;

    //    风险源
    @BindView(R.id.tv_count_risk)
    TextView tvCountRisk;
    @BindView(R.id.tv_count2_risk)
    TextView tvCount2;
    @BindView(R.id.tv_score_risk)
    TextView tvScoreRiskSource;
    @BindView(R.id.pie_char_risk)
    PieChart pieCharRisk;
    //  隐患
    @BindView(R.id.tv_count1_no_rectify)//未整改隐患
            TextView tvCount1Hidden;
    @BindView(R.id.tv_score_hidden)
    TextView tvScoreHidden;

    @BindView(R.id.tv_count2)//隐患总数量
            TextView tvCount2Hidden;
    @BindView(R.id.tv_count3)//一般隐患 数量
            TextView tvCount3Hidden;
    @BindView(R.id.tv_had_no_rectified_value1)//一般隐患 已整改
            TextView tvHadRectifiedValue1;
    @BindView(R.id.tv_no_rectified_value1)// 一般隐患 未整改
            TextView tvNoRectifiedValue1;
    @BindView(R.id.tv_late_value1)  //一般隐患 逾期未整改
            TextView tvLateValue1;
    @BindView(R.id.tv_count4)   //重大隐患 数量
            TextView tvCount4;
    @BindView(R.id.tv_had_rectified_value2) //重 已整改
            TextView tvHadRectifiedValue2;
    @BindView(R.id.tv_no_rectified_value2) //重 未整改
            TextView tvNoRectifiedValue2;
    @BindView(R.id.tv_late_value2)  //重大隐患 逾期未整改
            TextView tvLateValue2;
    @BindView(R.id.tv_had_supervise_value)  //重大隐患 已督办
            TextView tvHadSuperviseValue;

    //管理
    @BindView(R.id.tv_score_manage)
    TextView tvScoreManage;
    @BindView(R.id.pie_chart_manager)
    PieChart pieChartManager;

    //    管理 -直管
    @BindView(R.id.tv_count_trainer)
    TextView tvCountTrainer;
    @BindView(R.id.tv_count_hours)
    TextView tvCountHours;
    @BindView(R.id.tv_score_manage_direct)
    TextView tvScoreManageDirect;
    @BindView(R.id.tv_check_had_report)
    TextView tvCheckHadReport;
    @BindView(R.id.tv_check_no_report)
    TextView tvCheckNoReport;
    @BindView(R.id.tv_assessment_had_report)
    TextView tvAssessmentHadReport;
    @BindView(R.id.tv_assessment_no_report)
    TextView tvAssessmentNoReport;
    @BindView(R.id.tv_hidden_had_report)
    TextView tvHiddenHadReport;
    @BindView(R.id.tv_hidden_no_report)
    TextView tvHiddenNoReport;
    @BindView(R.id.tv_acc_had_report)
    TextView tvAccHadReport;
    @BindView(R.id.tv_acc_no_report)
    TextView tvAccNoReport;

    @BindView(R.id.water_view)
    WaterView waterView;
    @BindView(R.id.iv_back_tool_bar)
    ImageView ivBackToolBar;
    @BindView(R.id.tv_date)
    TextView tvDate;
    String type;
    String title = "";
    String titleDetail;
    SecurityCloudEntry securityCloudEntry;
    RecyclerAdapter recyclerAdapter;
    private List<PieEntry> accPieEntrys;
    private XAxis xAxis;         //X坐标轴
    private YAxis yAxisLeft;         //Y坐标轴
    private LineChart lineChart;
    int safaType = 0;

    public SecurityCloudOrgEntry getSecurityCloudOrgEntry() {
        return securityCloudOrgEntry;
    }

    public void setSecurityCloudOrgEntry(SecurityCloudOrgEntry securityCloudOrgEntry) {
        this.securityCloudOrgEntry = securityCloudOrgEntry;
    }

    public SecurityCloudOrgEntry securityCloudOrgEntry = null;
    private int orgLevel = BusinessConfig.getOrgLevel();


    public BaseSecurityCloudFragment() {
    }

    public BaseSecurityCloudFragment(@Nullable String strJsonData, String type) {

        this.strJsonData = strJsonData;
        this.type = type;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public void finishActivity(Activity activity) {
        activity.finish();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPause() {
        super.onPause();
        RxApiManager.get().cancel(SecurityCloudOrgEntry.class);
        RxApiManager.get().cancel(SecurityCloudAreaEntry.class);
    }
    //    @Override

    public void initViewData() {
        appBarLayout.addOnOffsetChangedListener(this);
//        Gson gson = new Gson();
//
//        securityCloudEntry = gson.fromJson(strJsonData, SecurityCloudEntry.class);
//
        initTitleAndView(type);


    }

    int sourceType = 1;

    private void initTitleAndView(String type) {
        switch (type) {
            case "1":
                title = "直管工程";
                cardViewManagerDirect.setVisibility(View.VISIBLE);
                sourceType = 1;
                if (orgLevel == 4) {
                    //
                    safaType = 0;
                } else {
                    safaType = 1;
                }
//                getDataZhiGuan();
//                initStraightTubeManage(securityCloudEntry.getStraightTubeManageEntry());
//                initRankView(securityCloudEntry.getRankList());
                break;
            case "2":
                title = "流域机构";
                cardViewManager.setVisibility(View.VISIBLE);
                sourceType = 3;
                safaType = 0;
                break;
            case "3":
                title = "行业监管";
                cardViewManager.setVisibility(View.VISIBLE);
                safaType = 0;
                sourceType = 2;
                break;
        }
        tvTitle.setText(title);
        getSecuritData(sourceType, safaType);
    }


    /**
     * 监管和流域的  管理 返回数据
     *
     * @param supervisionMangeEntry
     */
    private void initSupervisionManage(SupervisionMangeEntry supervisionMangeEntry) {
        List<PieEntry> list = new ArrayList<>();
        int levelOne = supervisionMangeEntry.getStandardLevelOneCount();
        int levelTwo = supervisionMangeEntry.getStandardLevelTwoCount();
        int levelThree = supervisionMangeEntry.getStandardLevelThreeCount();
        if (levelOne + levelTwo + levelThree == 0) {
            initPieChartNoData(pieChartManager, "暂无数据");
        } else {
            list.add(new PieEntry(levelOne, "标准化一级" + String.format("%.1f", (levelOne * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
            list.add(new PieEntry(levelTwo, "标准化二级" + String.format("%.1f", (levelTwo * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
            list.add(new PieEntry(levelThree, "标准化三级" + String.format("%.1f", (levelThree * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
            waterView.setProgress(supervisionMangeEntry.getWorkAssessmentScore());
//            waterView.startAnimation();
            initPieCharView(pieChartManager, list);
        }
    }

    private void getSecuritData(int sourceType, int safaType) {
        RxApiManager.get().cancel(SecurityCloudOrgEntry.class);
        if (safaType == 1) {//工程
            RetrofitHttpMethods.getInstance().getSecurityOrgData(new BaseObserver<SecurityCloudOrgEntry>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(SecurityCloudOrgEntry securityCloudOrgEntry) {
                    setSecurityCloudOrgEntry(securityCloudOrgEntry);
                    if (tvTitle != null && securityCloudOrgEntry != null) {
                        updateView(securityCloudOrgEntry);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    LogUtils.i("SecurityCloudOrgEntry ","onError");
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {
                    LogUtils.i("SecurityCloudOrgEntry ","onComplete");
                }
//            }, sourceType + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId(), safaType + "", "", "");
            }, sourceType + "", "D7862390F88443AE87FA9DD1FE45A8B6", safaType + "", "", "");
        } else if (safaType == 0) {//区域
            RxApiManager.get().cancel(SecurityCloudAreaEntry.class);
            RetrofitHttpMethods.getInstance().getSecurityAreaData(new BaseObserver<SecurityCloudAreaEntry>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(SecurityCloudAreaEntry securityCloudAreaEntry) {
                    if (tvTitle != null && securityCloudAreaEntry != null) {
                        updateView(securityCloudAreaEntry);
                    }
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onComplete() {
                }
//            }, sourceType + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId(), safaType + "", "", "");
            }, sourceType + "", "D7862390F88443AE87FA9DD1FE45A8B6", safaType + "", "", "");

        }
    }

    private void updateView(SecurityCloudAreaEntry securityCloudAreaEntry) {
//        监管 流域 数据
        double score = securityCloudAreaEntry.getData().getAqpgScore().getAQPG();
        if (title.isEmpty()) {
            titleDetail = new String(new StringBuilder("安全评分·").append(score).append("分"));
        } else {
            titleDetail = new String(new StringBuilder(title).append("·安全评分·").append(score).append("分"));
        }
        tvTitle.setText(title);
        collapsingToolbarLayout.setTitle(titleDetail);
        tvScore.setText(score + "");
        viewDialPlate.updateData(score);
        tvDate.setText("");
        SecurityCloudAreaEntry.DataBean.AqpgScoreBean aqpgScoreBean = securityCloudAreaEntry.getData().getAqpgScore();
        SecurityCloudAreaEntry.DataBean.SgxxBean sgxxBean = securityCloudAreaEntry.getData().getSgxx().get(0);
        if (sgxxBean != null) {
            tvCountAcc.setText(sgxxBean.getSGQS() + "");
            tvDeathAccCount.setText(sgxxBean.getSWRS() + "");

            List<PieEntry> accPieEntrys = new ArrayList<>();
            PieChart pieChart = (PieChart) cardViewAcc.findViewById(R.id.pie_char_acc);
            if (sgxxBean.getZDSG() + sgxxBean.getYBSG() + sgxxBean.getJDSG() + sgxxBean.getTDSG() == 0) {

                initPieChartNoData(pieChart, "无事故发生");
            } else {
                accPieEntrys.add(new PieEntry(sgxxBean.getYBSG(), "一般事故 " + sgxxBean.getYBSG()));
                accPieEntrys.add(new PieEntry(sgxxBean.getJDSG(), "较大事故 " + sgxxBean.getJDSG()));
                accPieEntrys.add(new PieEntry(sgxxBean.getZDSG(), "重大事故 " + sgxxBean.getZDSG()));
                accPieEntrys.add(new PieEntry(sgxxBean.getTDSG(), "特大事故 " + sgxxBean.getTDSG()));
                initPieCharView(pieChart, accPieEntrys);
            }
        }
        if (aqpgScoreBean != null) {
            tvScoreAcc.setText(aqpgScoreBean.getSG() + "");
            tvScoreHidden.setText(aqpgScoreBean.getYH() + "");
            tvScoreRiskSource.setText(aqpgScoreBean.getWXY() + "");
            tvScoreManage.setText(aqpgScoreBean.getGLZB1() + "");
        }
        SecurityCloudAreaEntry.DataBean.YhxxBean yhxxBean = securityCloudAreaEntry.getData().getYhxx();
        if (yhxxBean != null) {
            tvCount1Hidden.setText(yhxxBean.getWZG() + "");
            tvCount2Hidden.setText(yhxxBean.getYHZS() + "");
            SecurityCloudAreaEntry.DataBean.YhxxBean.YHXXBean yhxxBean1 = yhxxBean.getYHXX().get(0);
            if (yhxxBean1 != null) {

                tvCount3Hidden.setText(yhxxBean.getYHXX().get(0).getYHZS() + "");
                tvHadRectifiedValue1.setText(yhxxBean1.getYZG() + "");
                tvNoRectifiedValue1.setText(yhxxBean1.getWZG() + "");
                tvLateValue1.setText(yhxxBean1.getYQWG() + "");
            }

            SecurityCloudAreaEntry.DataBean.YhxxBean.YHXXBean yhxxBean2 = yhxxBean.getYHXX().get(1);
            if (yhxxBean2 != null) {

                tvCount4.setText(yhxxBean.getYHXX().get(0).getYHZS() + "");
                tvHadRectifiedValue2.setText(yhxxBean2.getYZG() + "");
                tvNoRectifiedValue2.setText(yhxxBean2.getWZG() + "");
                tvLateValue2.setText(yhxxBean2.getYQWG() + "");
                tvHadSuperviseValue.setText(yhxxBean2.getGPDB() + "");
            }
        }

        SecurityCloudAreaEntry.DataBean.FxyxxBean fxyxxBean = securityCloudAreaEntry.getData().getFxyxx().get(0);
        if (fxyxxBean != null) {
            tvCountRisk.setText(fxyxxBean.getYGK() + "");
            tvCount2.setText(fxyxxBean.getWGK() + "");

            List<PieEntry> riskPieEntrys = new ArrayList<>();

            PieChart pieChart = ((PieChart) viewRiskSource.findViewById(R.id.pie_char_risk));
            if (fxyxxBean.getYBA() + fxyxxBean.getWBA() == 0) {
                initPieChartNoData(pieChart, "无风险源");
            } else {
                riskPieEntrys.add(new PieEntry(fxyxxBean.getYBA(), "已备案 " + fxyxxBean.getYBA()));
                riskPieEntrys.add(new PieEntry(fxyxxBean.getWGK(), "未备案 " + fxyxxBean.getWGK()));
                initPieCharView(pieChart, riskPieEntrys);
            }
        }
        SupervisionMangeEntry supervisionMangeEntry = new SupervisionMangeEntry();
        SecurityCloudAreaEntry.DataBean.BzhBean bzhBean = securityCloudAreaEntry.getData().getBzh().get(0);
//        supervisionMangeEntry.setScore();
        if (bzhBean != null) {
            supervisionMangeEntry.setStandardLevelOneCount(bzhBean.getYJ());
            supervisionMangeEntry.setStandardLevelTwoCount(bzhBean.getEJ());
            supervisionMangeEntry.setStandardLevelThreeCount(bzhBean.getSJ());
            supervisionMangeEntry.setWorkAssessmentScore(securityCloudAreaEntry.getData().getGzkh().get(0).getAVGSCORE());
//            if(sourceType != 1) {
//                initSupervisionManage(supervisionMangeEntry);
//            }
        }
//        SecurityCloudAreaEntry.DataBean.BzhBean bzhBean = securityCloudAreaEntry.getData().getBzh().get(0);
//        if (bzhBean != null) {
//            List<PieEntry> list = new ArrayList<>();
//            int levelOne = bzhBean.getYJ();
//            int levelTwo = bzhBean.getEJ();
//            int levelThree = bzhBean.getSJ();
//            if (levelOne + levelTwo + levelThree == 0) {
//                initPieChartNoData(pieChartManager, "暂无数据");
//            } else {
//                list.add(new PieEntry(levelOne, "标准化一级" + String.format("%.1f", (levelOne * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
//                list.add(new PieEntry(levelTwo, "标准化二级" + String.format("%.1f", (levelTwo * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
//                list.add(new PieEntry(levelThree, "标准化三级" + String.format("%.1f", (levelThree * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
//                waterView.setProgress(60);
//                initPieCharView(pieChartManager, list);
//            }
//        }


        List<SecurityCloudAreaEntry.DataBean.AqpgMonthBean> list = securityCloudAreaEntry.getData().getAqpgMonth();
        lineChart = viewGradeTrend.findViewById(R.id.line_chart);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("选择日期");
            }
        });

        initLineCharView(lineChart, list);

        initRankView(securityCloudAreaEntry.getData().getAqpgPm());
    }

    private void updateView(SecurityCloudOrgEntry securityCloudOrgEntry) {
//        private YhxxBean yhxx;
//        private AqpgScoreBean aqpgScore;
//        private List<GzkhBean> gzkh;
//        private List<FxyxxBean> fxyxx;
//        private List<BzhBean> bzh;
//        private List<AqpgPmBean> aqpgPm;
//        private List<AqpgMonthBean> aqpgMonth;
//        private List<SgxxBean> sgxx;

        double score = securityCloudOrgEntry.getData().getAqpgScore().getAQPG();
        if (title.isEmpty()) {
            titleDetail = new String(new StringBuilder("安全评分·").append(score).append("分"));
        } else {
            titleDetail = new String(new StringBuilder(title).append("·安全评分·").append(score).append("分"));
        }
        tvTitle.setText(title);
        collapsingToolbarLayout.setTitle(titleDetail);
        tvScore.setText(score + "");
        viewDialPlate.updateData(score);
//        // TODO:   时间
        tvDate.setText("");
        SecurityCloudOrgEntry.DataBean.AqpgScoreBean aqpgScoreBean = securityCloudOrgEntry.getData().getAqpgScore();
        SecurityCloudOrgEntry.DataBean.SgxxBean sgxxBean = securityCloudOrgEntry.getData().getSgxx().get(0);
        if (sgxxBean != null) {
            tvCountAcc.setText(sgxxBean.getSGQS() + "");
            tvDeathAccCount.setText(sgxxBean.getSWRS() + "");
            List<PieEntry> accPieEntrys = new ArrayList<>();
//            PieChart pieChart = ((PieChart) cardViewAcc.findViewById(R.id.pie_char_acc));
            if (sgxxBean.getZDSG() + sgxxBean.getYBSG() + sgxxBean.getJDSG() + sgxxBean.getTDSG() == 0) {

                initPieChartNoData(pieCharAcc, "无事故发生");
            } else {
                accPieEntrys.add(new PieEntry(sgxxBean.getYBSG(), "一般事故 " + sgxxBean.getYBSG()));
                accPieEntrys.add(new PieEntry(sgxxBean.getJDSG(), "较大事故 " + sgxxBean.getJDSG()));
                accPieEntrys.add(new PieEntry(sgxxBean.getZDSG(), "重大事故 " + sgxxBean.getZDSG()));
                accPieEntrys.add(new PieEntry(sgxxBean.getTDSG(), "特大事故 " + sgxxBean.getTDSG()));
                initPieCharView(pieCharAcc, accPieEntrys);
            }
        }
        if (aqpgScoreBean != null) {
            tvScoreAcc.setText(aqpgScoreBean.getSG() + "");
            tvScoreHidden.setText(aqpgScoreBean.getYH() + "");
            tvScoreRiskSource.setText(aqpgScoreBean.getWXY() + "");
            tvScoreManage.setText(aqpgScoreBean.getGLZB2() + "");
        }
        SecurityCloudOrgEntry.DataBean.YhxxBean yhxxBean = securityCloudOrgEntry.getData().getYhxx();
        if (yhxxBean != null) {
            tvCount1Hidden.setText(yhxxBean.getWZG() + "");
            tvCount2Hidden.setText(yhxxBean.getYHZS() + "");
            SecurityCloudOrgEntry.DataBean.YhxxBean.YHXXBean yhxxBean1 = yhxxBean.getYHXX().get(0);
            if (yhxxBean1 != null) {

                tvCount3Hidden.setText(yhxxBean.getYHXX().get(0).getYHZS() + "");
                tvHadRectifiedValue1.setText(yhxxBean1.getYZG() + "");
                tvNoRectifiedValue1.setText(yhxxBean1.getWZG() + "");
                tvLateValue1.setText(yhxxBean1.getYQWG() + "");
            }

            SecurityCloudOrgEntry.DataBean.YhxxBean.YHXXBean yhxxBean2 = yhxxBean.getYHXX().get(1);
            if (yhxxBean2 != null) {

                tvCount4.setText(yhxxBean.getYHXX().get(0).getYHZS() + "");
                tvHadRectifiedValue2.setText(yhxxBean2.getYZG() + "");
                tvNoRectifiedValue2.setText(yhxxBean2.getWZG() + "");
                tvLateValue2.setText(yhxxBean2.getYQWG() + "");
                tvHadSuperviseValue.setText(yhxxBean2.getGPDB() + "");
            }
        }

        SecurityCloudOrgEntry.DataBean.FxyxxBean fxyxxBean = securityCloudOrgEntry.getData().getFxyxx().get(0);
        if (fxyxxBean != null) {
            tvCountRisk.setText(fxyxxBean.getYGK() + "");
            tvCount2.setText(fxyxxBean.getWGK() + "");

            List<PieEntry> riskPieEntrys = new ArrayList<>();

//            PieChart pieChart = ((PieChart) viewRiskSource.findViewById(R.id.pie_char_risk));
            if (fxyxxBean.getYBA() + fxyxxBean.getWBA() == 0) {
                initPieChartNoData(pieCharRisk, "无风险源");
            } else {
                riskPieEntrys.add(new PieEntry(fxyxxBean.getYBA(), "已备案 " + fxyxxBean.getYBA()));
                riskPieEntrys.add(new PieEntry(fxyxxBean.getWGK(), "未备案 " + fxyxxBean.getWGK()));
                initPieCharView(pieCharRisk, riskPieEntrys);
            }
        }
//        todo 直管工程的 管理
        StraightTubeManageEntry straightTubeManageEntry = new StraightTubeManageEntry();
//        straightTubeManageEntry.set
//        initStraightTubeManage();
        List<SecurityCloudOrgEntry.DataBean.AqpgMonthBean> list = securityCloudOrgEntry.getData().getAqpgMonth();
        lineChart = viewGradeTrend.findViewById(R.id.line_chart);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("选择日期");
            }
        });

        initLineCharOrgView(lineChart, list);

        initRankOrgView(securityCloudOrgEntry.getData().getAqpgPm());
    }

    /**
     * 初始化 排名
     *
     * @param beans
     */
    private void initRankOrgView(List<SecurityCloudOrgEntry.DataBean.AqpgPmBean> beans) {
        List<SecurityCloudEntry.AreaRank> rankList = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            SecurityCloudEntry.AreaRank rank = new SecurityCloudEntry.AreaRank();
            rank.setId("11");
            rank.setName(beans.get(i).getORG_NAME());
            rank.setScore(beans.get(i).getSAFA_SCORE());
            rankList.add(rank);
        }
        Collections.reverse(rankList);
        RecyclerView recyclerView = viewRank.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerAdapter = new RecyclerAdapter(rankList);
        recyclerAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Bundle bundle=new Bundle();
                Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_SHORT).show();
////                intentActivity(getActivity(), SecurityCloudDetailActivity.class, false, 1100);
//                String title = securityCloudEntry.getRankList().get(position).getName();
//                String id = securityCloudEntry.getRankList().get(position).getId();
                startActivity(new Intent(getActivity(), SecurityCloudDetailActivity.getInstance().getClass()));
            }

        });
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initRankView(List<SecurityCloudAreaEntry.DataBean.AqpgPmBean> beans) {
        List<SecurityCloudEntry.AreaRank> rankList = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            SecurityCloudEntry.AreaRank rank = new SecurityCloudEntry.AreaRank();
            rank.setId("11");
            rank.setName(beans.get(i).getORG_NAME());
            rank.setScore((int) beans.get(i).getSAFA_SCORE());
            rankList.add(rank);
        }
        Collections.reverse(rankList);
        RecyclerView recyclerView = viewRank.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerAdapter = new RecyclerAdapter(rankList);
        recyclerAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Bundle bundle=new Bundle();
                Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_SHORT).show();
//                intentActivity(getActivity(), SecurityCloudDetailActivity.class, false, 1100);
                String title = securityCloudEntry.getRankList().get(position).getName();
                String id = securityCloudEntry.getRankList().get(position).getId();
                startActivity(new Intent(getActivity(), SecurityCloudDetailActivity.getInstance().getClass()));
            }

        });
        recyclerView.setAdapter(recyclerAdapter);
    }

    /**
     * 初始化曲线图
     *
     * @param compScoreTrend
     */
    private void initTrendView(SecurityCloudEntry.CompScoreTrend compScoreTrend) {
        lineChart = viewGradeTrend.findViewById(R.id.line_chart);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("选择日期");
            }
        });
//        initLineCharView(lineChart, securityCloudEntry.getCompScoreTrend().getDataList());

    }

    /**
     * 直管的 管理 返回数据
     *
     * @param straightTubeManageEntry
     */
    private void initStraightTubeManage(StraightTubeManageEntry straightTubeManageEntry) {
        List<StraightTubeManageEntry.ReportInfoItemEntry> dataList = new ArrayList<>();
        dataList.clear();
        tvCountTrainer.setText(straightTubeManageEntry.getTrainingPersonCount() + "");
        tvCountHours.setText(straightTubeManageEntry.getPerTrainingHours() + "");

        dataList = straightTubeManageEntry.getDataList();
        for (StraightTubeManageEntry.ReportInfoItemEntry e : dataList) {
            if (e.getTaskId().equals("1")) {
                tvCheckHadReport.setText(e.getPartReportCount() + "");
                tvCheckNoReport.setText(e.getPartUnReportCount() + "");
            } else if (e.getTaskId().equals("2")) {
                tvAssessmentHadReport.setText(e.getPartReportCount() + "");
                tvAssessmentNoReport.setText(e.getPartUnReportCount() + "");
            } else if (e.getTaskId().equals("3")) {
                tvHiddenHadReport.setText(e.getPartReportCount() + "");
                tvHiddenNoReport.setText(e.getPartUnReportCount() + "");
            } else if (e.getTaskId().equals("4")) {
                tvAccHadReport.setText(e.getPartReportCount() + "");
                tvAccNoReport.setText(e.getPartUnReportCount() + "");
            }
        }


    }


    private void initPieChartNoData(PieChart pieChart, String str) {

        PieChart.LayoutParams params = new PieChart.LayoutParams(PieChart.LayoutParams.MATCH_PARENT, 25);
        pieChart.setLayoutParams(params);
        pieChart.setNoDataText(str);
        pieChart.setTouchEnabled(false);
        pieChart.setNoDataTextColor(R.color.text_black_color);
        cardViewAcc.postInvalidateDelayed(100);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud_river_area;
    }

    @Override
    protected void initListener() {

    }

    private void initLineCharOrgView(LineChart lineChart, List<SecurityCloudOrgEntry.DataBean.AqpgMonthBean> beans) {
        LineData lineData = getLineOrgData(beans);
        xAxis = lineChart.getXAxis();
        yAxisLeft = lineChart.getAxisLeft();
//        lineChart.setBackgroundColor(Color.argb(200, 173, 215, 210));// 设置图表背景 参数是个Color对象
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description); //图表默认右下方的描述，参数是String对象
        lineChart.setNoDataText("暂无数据");   //没有数据时显示在中央的字符串，参数是String对象

        lineChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
//        lineChart.setGridBackgroundColor(Color.rgb(250, 0, 0));//设置格子背景色,参数是Color类型对象

        lineChart.setDrawBorders(false);     //设置图表内格子外的边框是否显示
        //Enabling / disabling interaction
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(false); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(false); //是否可以缩放 仅y轴
//        lineChart.setMarker();
        lineChart.setPinchZoom(false);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
//        lineChart.setHighlightEnabled(false);  //If set to true, highlighting/selecting values via touch is possible for all underlying DataSets.
        lineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setAutoScaleMinMaxEnabled(false);
        // Chart fling / deceleration
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。

        //Highlighting programmatically

//        The Axis 坐标轴相关的,XY轴通用
        xAxis.setEnabled(true);     //是否显示X坐标轴 及 对应的刻度竖线，默认是true
        xAxis.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxis.setDrawGridLines(true); //是否显示X坐标轴上的刻度竖线，默认是true
        xAxis.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        xAxis.setTextColor(getResources().getColor(R.color.text_gray_color)); //X轴上的刻度的颜色
        xAxis.setTextSize(12); //X轴上的刻度的字的大小 单位dp
        xAxis.setTextColor(Color.parseColor("#004e96"));
//      xAxis.setTypeface(Typeface tf); //X轴上的刻度的字体
//        xAxis.setGridColor(getResources().getColor(R.color.color_blue_004e96)); //X轴上的刻度竖线的颜色
//        xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        xAxis.enableGridDashedLine(10, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        xAxis.isDrawLabelsEnabled();
        final List<String> dateList = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            dateList.add(beans.get(i).getCOUNTDATE());
        }

        xAxis.setLabelCount(dateList.size() - 1);
//      X轴专用
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dateList.get((int) ((value + 0.5)));//[(int) value];
            }
        });
        xAxis.setAvoidFirstLastClipping(false);
//        xAxis.setSpaceBetweenLabels(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//把坐标轴放在上下 参数有：TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE or BOTTOM_INSIDE.
        xAxis.setLabelRotationAngle(330);
        xAxis.setDrawLabels(true);
        //可以设置一条警戒线，如下：

//      Y轴专用

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

//        yAxisLeft.addLimitLine(ll);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setEnabled(true);
        yAxisLeft.setDrawLabels(false);
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);
//        yAxis.resetAxisMaxValue();    //重新设置Y轴坐标最大为多少，自动调整
//        yAxis.resetAxisMinValue();    //重新设置Y轴坐标，自动调整
        yAxisLeft.setInverted(false);    //Y轴坐标反转,默认是false,即下小上大
        yAxisLeft.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        yAxisLeft.setSpaceBottom(10);    //Y轴坐标距底有多少距离，即留白
//        yAxis.setShowOnlyMinMax(false);    //参数如果为true Y轴坐标只显示最大值和最小值
        yAxisLeft.setLabelCount(100, false);    //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  //参数是INSIDE_CHART(Y轴坐标在内部) 或 OUTSIDE_CHART(在外部（默认是这个）)
//        yAxis.setValueFormatter(new IAxisValueFormatter() {
        yAxisLeft.setDrawGridLines(false);

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        mLegend.setEnabled(false);
        lineChart.animateX(0); // 立即执行的动画,x轴
    }

    private void initLineCharView(LineChart lineChart, List<SecurityCloudAreaEntry.DataBean.AqpgMonthBean> beans) {
        LineData lineData = getLineData(beans);
        xAxis = lineChart.getXAxis();
        yAxisLeft = lineChart.getAxisLeft();
//        lineChart.setBackgroundColor(Color.argb(200, 173, 215, 210));// 设置图表背景 参数是个Color对象
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description); //图表默认右下方的描述，参数是String对象
        lineChart.setNoDataText("暂无数据");   //没有数据时显示在中央的字符串，参数是String对象

        lineChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
//        lineChart.setGridBackgroundColor(Color.rgb(250, 0, 0));//设置格子背景色,参数是Color类型对象

        lineChart.setDrawBorders(false);     //设置图表内格子外的边框是否显示
        //Enabling / disabling interaction
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(false); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(false); //是否可以缩放 仅y轴
//        lineChart.setMarker();
        lineChart.setPinchZoom(false);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
//        lineChart.setHighlightEnabled(false);  //If set to true, highlighting/selecting values via touch is possible for all underlying DataSets.
        lineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setAutoScaleMinMaxEnabled(false);
        // Chart fling / deceleration
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。

//        The Axis 坐标轴相关的,XY轴通用
        xAxis.setEnabled(true);     //是否显示X坐标轴 及 对应的刻度竖线，默认是true
        xAxis.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxis.setDrawGridLines(true); //是否显示X坐标轴上的刻度竖线，默认是true
        xAxis.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        xAxis.setTextColor(getResources().getColor(R.color.text_gray_color)); //X轴上的刻度的颜色
        xAxis.setTextSize(12); //X轴上的刻度的字的大小 单位dp
        xAxis.setTextColor(Color.parseColor("#004e96"));
//      xAxis.setTypeface(Typeface tf); //X轴上的刻度的字体
//        xAxis.setGridColor(getResources().getColor(R.color.color_blue_004e96)); //X轴上的刻度竖线的颜色
//        xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        xAxis.enableGridDashedLine(10, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        xAxis.isDrawLabelsEnabled();
        final List<String> dateList = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            dateList.add(beans.get(i).getCOUNTDATE());
        }

        xAxis.setLabelCount(dateList.size() - 1);
//      X轴专用
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dateList.get((int) ((value + 0.5)));//[(int) value];
            }
        });
        xAxis.setAvoidFirstLastClipping(false);
//        xAxis.setSpaceBetweenLabels(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//把坐标轴放在上下 参数有：TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE or BOTTOM_INSIDE.
        xAxis.setLabelRotationAngle(330);
        xAxis.setDrawLabels(true);
        //可以设置一条警戒线，如下：

//      Y轴专用

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

//        yAxisLeft.addLimitLine(ll);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setEnabled(true);
        yAxisLeft.setDrawLabels(false);
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);
//        yAxis.resetAxisMaxValue();    //重新设置Y轴坐标最大为多少，自动调整
//        yAxis.resetAxisMinValue();    //重新设置Y轴坐标，自动调整
        yAxisLeft.setInverted(false);    //Y轴坐标反转,默认是false,即下小上大
        yAxisLeft.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        yAxisLeft.setSpaceBottom(10);    //Y轴坐标距底有多少距离，即留白
//        yAxis.setShowOnlyMinMax(false);    //参数如果为true Y轴坐标只显示最大值和最小值
        yAxisLeft.setLabelCount(100, false);    //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  //参数是INSIDE_CHART(Y轴坐标在内部) 或 OUTSIDE_CHART(在外部（默认是这个）)
//        yAxis.setValueFormatter(new IAxisValueFormatter() {
        yAxisLeft.setDrawGridLines(false);

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        mLegend.setEnabled(false);
        lineChart.animateX(0); // 立即执行的动画,x轴
    }

    private LineData getLineOrgData(List<SecurityCloudOrgEntry.DataBean.AqpgMonthBean> beans) {

        ArrayList<Entry> entries = new ArrayList<Entry>();     //坐标点的集合
//        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
        Random random = new Random(10);
        for (int i = 0; i < beans.size(); i++) {
//            Entry entry = new Entry(i, dataList.get(i).getScore());

            Entry entry = new Entry(i, (float) beans.get(i).getAQPG());
            entries.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "得分");    //坐标线，LineDataSet(坐标点的集合, 线的描述或名称);
//        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.isDrawCircleHoleEnabled();
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleHoleRadius(5);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setDrawValues(true);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        lineDataSet.setCircleRadius(10);
        lineDataSet.setLineWidth(2);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);     //以左边坐标轴为准 还是以右边坐标轴为基准
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>(); //坐标线的集合。
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);  //LineData(X坐标轴的集合, 坐标线的集合);
        lineChart.setData(data);   //为图表添加 数据
        lineChart.invalidate(); // 重新更新显示

        return data;
    }

    private LineData getLineData(List<SecurityCloudAreaEntry.DataBean.AqpgMonthBean> beans) {

        ArrayList<Entry> entries = new ArrayList<Entry>();     //坐标点的集合
//        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
        Random random = new Random(10);
        for (int i = 0; i < beans.size(); i++) {
//            Entry entry = new Entry(i, dataList.get(i).getScore());

            Entry entry = new Entry(i, (float) beans.get(i).getAQPG());
            entries.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "得分");    //坐标线，LineDataSet(坐标点的集合, 线的描述或名称);
//        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.isDrawCircleHoleEnabled();
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleHoleRadius(5);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setDrawValues(true);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        lineDataSet.setCircleRadius(10);
        lineDataSet.setLineWidth(2);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);     //以左边坐标轴为准 还是以右边坐标轴为基准
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>(); //坐标线的集合。
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);  //LineData(X坐标轴的集合, 坐标线的集合);
        lineChart.setData(data);   //为图表添加 数据
        lineChart.invalidate(); // 重新更新显示

        return data;
    }


    private void initPieCharView(PieChart pieChart, List<PieEntry> strings) {


        PieDataSet dataSet = new PieDataSet(strings, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.pie_chart_color_level_one));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_two));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_three));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_four));
        dataSet.setColors(colors);
        // 获取pieCahrt图列
        Legend l = pieChart.getLegend();
        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义）
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setForm(Legend.LegendForm.SQUARE); //设置图例的形状
        l.setFormSize(14);                      //设置图例的大小
        l.setFormToTextSpace(12f);              //设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
//        l.setXEntrySpace(10f);				  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(6f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(-20f);                      //设置比例块Y轴偏移量
//        l.setXOffset(-10);
        l.setTextSize(14f);                      //设置图例标签文本的大小
        l.setTextColor(getResources().getColor(R.color.text_gray_color));//设置图例标签文本的颜色
//        pieChart.invalidate();
//        是否允许点击
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(false);

        pieChart.setTouchEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDrawEntryLabels(false);
//        是否允许旋转
        pieChart.setRotationEnabled(false);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
//        pieChart.setHoleRadius(0.8f);

        pieChart.setHoleRadius(65f);
        pieChart.setTransparentCircleRadius(65f);
        pieChart.setData(pieData);
        pieChart.setNoDataTextColor(Color.RED);
//        pieChart.show

        pieChart.setNoDataText("暂无数据");
        pieChart.setExtraOffsets(-10, 0, 10, 0);
//        pieChart.setTranslationX(20);
//        pieChart.setPivotX();
        pieChart.invalidate();
    }


    @Override
    public void onResume() {
        super.onResume();
//        if (strJsonData != null) {

//        }
        if (getUserVisibleHint()) {
            initViewData();
        }

    }


    @Override
    protected void initView() {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float scale = 1.0f - (-verticalOffset) / (float) (appBarLayout.getHeight() - toolbar.getHeight());
        tvScore.setScaleX(scale);
        tvScore.setScaleY(scale);
        tvScore.setAlpha(scale);

        viewDialPlate.setScaleX(scale);
        viewDialPlate.setScaleY(scale);
        viewDialPlate.setAlpha(scale);

        tvScoreTitle.setScaleX(scale);
        tvScoreTitle.setScaleY(scale);
        tvScoreTitle.setAlpha(scale);

        tvGradeTime.setScaleX(scale);
        tvGradeTime.setScaleY(scale);
        tvGradeTime.setAlpha(scale);

    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRank;
        TextView tvRank;
        TextView tvName;
        TextView tvScore;

        public RecyclerViewHolder(View itemView) {

            super(itemView);
            ivRank = itemView.findViewById(R.id.iv_rank);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class RecyclerAdapter extends BaseRecyclerAdapter<RecyclerViewHolder> {
        OnItemClickListener listener;
        List<SecurityCloudEntry.AreaRank> list;
        RecyclerViewHolder holder;

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        public RecyclerAdapter(List<SecurityCloudEntry.AreaRank> list) {
            this.list = list;
        }

        @Override
        public RecyclerViewHolder getViewHolder(View view) {
            return holder;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_rank, parent, false);
            holder = new RecyclerViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, final int position, boolean isItem) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });
            holder.ivRank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });


            if (position == 0) {
                holder.ivRank.setVisibility(View.VISIBLE);
                holder.ivRank.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.security_gold_medal));

            } else if (position == 1) {
                holder.ivRank.setVisibility(View.VISIBLE);
                holder.ivRank.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.security_silver_medal));
            } else if (position == 2) {
                holder.ivRank.setVisibility(View.VISIBLE);
                holder.ivRank.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.security_bronze_medal));
            } else {
                holder.ivRank.setVisibility(View.GONE);
                holder.tvRank.setVisibility(View.VISIBLE);
                holder.tvRank.setText(position + 1 + "");
            }
            holder.tvScore.setText(list.get(position).getScore() + "");
            holder.tvName.setText(list.get(position).getName());
        }

        @Override
        public int getAdapterItemCount() {
            return list.size();
        }

    }
}
