package com.syberos.shuili.activity.thematic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BActivity;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailAcciFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailHazFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailHiddenFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.fragment.HematicMapFragment.Acci;
import static com.syberos.shuili.fragment.HematicMapFragment.Haz;
import static com.syberos.shuili.fragment.HematicMapFragment.Hidden;
import static com.syberos.shuili.fragment.HematicMapFragment.Sins;
import static com.syberos.shuili.fragment.HematicMapFragment.Stan;
import static com.syberos.shuili.fragment.HematicMapFragment.Suen;
import static com.syberos.shuili.fragment.HematicMapFragment.Wins;
import static com.syberos.shuili.fragment.HematicMapFragment.Woas;
import static com.syberos.shuili.fragment.HematicMapFragment.tabTitle;


/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.syberos.shuili.activity_accident_query.
 */
public class ThematicDetailActivity extends BActivity {
    @BindView(R.id.ibtn_back)
    ImageButton ibtnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_view_title)
    TextView tvViewTitle;
    @BindView(R.id.iv_mark_pot_2)
    ImageView ivMarkPot2;
    @BindView(R.id.tv_chart_value_1)
    TextView tvChartValue1;
    @BindView(R.id.tv_chart_value_title_1)
    TextView tvChartValueTitle1;
    @BindView(R.id.iv_mark_pot_4)
    ImageView ivMarkPot4;
    @BindView(R.id.tv_chart_value_2)
    TextView tvChartValue2;
    @BindView(R.id.tv_chart_value_title_2)
    TextView tvChartValueTitle2;
    @BindView(R.id.iv_mark_pot_1)
    ImageView ivMarkPot1;
    @BindView(R.id.tv_chart_value_3)
    TextView tvChartValue3;
    @BindView(R.id.tv_chart_value_title_3)
    TextView tvChartValueTitle3;
    @BindView(R.id.iv_mark_pot_3)
    ImageView ivMarkPot3;
    @BindView(R.id.tv_chart_value_4)
    TextView tvChartValue4;
    @BindView(R.id.tv_chart_value_title_4)
    TextView tvChartValueTitle4;
    @BindView(R.id.pie_chart)
    PieChart pieChart;
    @BindView(R.id.ll_data_1)
    LinearLayout llData1;
    @BindView(R.id.tv_data_1)
    TextView tvData1;
    @BindView(R.id.tv_data_title_1)
    TextView tvDataTitle1;
    @BindView(R.id.ll_data_2)
    LinearLayout llData2;
    @BindView(R.id.tv_data_2)
    TextView tvData2;
    @BindView(R.id.tv_data_title_2)
    TextView tvDataTitle2;
    @BindView(R.id.ll_data_3)
    LinearLayout llData3;
    @BindView(R.id.tv_data_3)
    TextView tvData3;
    @BindView(R.id.tv_data_title_3)
    TextView tvDataTitle3;
    @BindView(R.id.ll_data_4)
    LinearLayout llData4;
    @BindView(R.id.tv_data_4)
    TextView tvData4;
    @BindView(R.id.tv_data_title_4)
    TextView tvDataTitle4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_thematic_detail_layout;
    }

    @Override
    public void initListener() {

    }

    ThematicDetailHiddenFragment thematicDetailHiddenFragment = new ThematicDetailHiddenFragment();
    ThematicDetailHazFragment thematicDetailHazFragment = new ThematicDetailHazFragment();
    ThematicDetailAcciFragment thematicDetailAcciFragment = new ThematicDetailAcciFragment();
    BaseLazyFragment[] fragments = {thematicDetailHiddenFragment, thematicDetailAcciFragment, thematicDetailHazFragment};

    @Override
    public void initData() {
//        tabTitle[3]
        switch (tabTitle[getIntent().getIntExtra("typeValue", 0)]) {
            case Hidden:
//                todo 隐患
                tvTitle.setText("隐患");
                switchFragment(fragments[0]);
                break;
            case Acci:
//                todo 事故
                tvTitle.setText("事故");
                switchFragment(fragments[1]);
                break;
            case Haz:
//                todo 危险源
                tvTitle.setText("危险源");
                switchFragment(fragments[3]);
                break;
            case Stan:
//                todo 标准化
                tvTitle.setText("标准化");
                break;
            case Sins:
//                todo 安全检查
                tvTitle.setText("安全检查");
                break;
            case Woas:
//                todo 工作考核
                tvTitle.setText("工作考核");
                break;
            case Wins:
//                todo 水利稽察
                tvTitle.setText("水利稽察");
                break;
            case Suen:
//                todo 安监执法
                tvTitle.setText("安监执法");
                break;
            default:
                switchFragment(fragments[3]);
        }
    }

    private Fragment currentFragment = new Fragment();

    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fragment_content, targetFragment, targetFragment.getClass().getName())
                    .commit();
        } else {
            transaction.hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }

    @Override
    public void initView() {
        tvTitle.setText("隐患");

    }

    @OnClick(R.id.ibtn_back)
    public void backClicked() {
        finish();
    }
}
