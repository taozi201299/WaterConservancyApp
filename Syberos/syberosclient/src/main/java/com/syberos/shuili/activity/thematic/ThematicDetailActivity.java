package com.syberos.shuili.activity.thematic;

import android.os.Bundle;
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
import com.syberos.shuili.fragment.thematic.SuenChartFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailAcciFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailHazFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailHiddenFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailSinsFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailStanFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailSuenFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailWinsFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailWoasFragment;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_thematic_detail_layout;
    }

    @Override
    public void initListener() {

    }

    //    隐患
    ThematicDetailHiddenFragment thematicDetailHiddenFragment = new ThematicDetailHiddenFragment();
    //    事故
    ThematicDetailAcciFragment thematicDetailAcciFragment = new ThematicDetailAcciFragment();
    //    风险源
    ThematicDetailHazFragment thematicDetailHazFragment = new ThematicDetailHazFragment();
    //    标准化
    ThematicDetailStanFragment thematicDetailStanFragment = new ThematicDetailStanFragment();
    //    安全检查
    ThematicDetailSinsFragment thematicDetailSinsFragment = new ThematicDetailSinsFragment();
    //    工作考核
    ThematicDetailWoasFragment thematicDetailWoasFragment = new ThematicDetailWoasFragment();
    //    水利稽查
    ThematicDetailWinsFragment thematicDetailWinsFragment = new ThematicDetailWinsFragment();
    //    安检执法
    ThematicDetailSuenFragment thematicDetailSuenFragment = new ThematicDetailSuenFragment();


    BaseLazyFragment[] fragments = {
            thematicDetailHiddenFragment, thematicDetailAcciFragment,
            thematicDetailHazFragment, thematicDetailStanFragment,
            thematicDetailSinsFragment, thematicDetailWoasFragment,
            thematicDetailWinsFragment, thematicDetailSuenFragment};

    @Override
    public void initData() {
//        tabTitle[3]
        switch (getIntent().getStringExtra("typeValue")) {
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
                switchFragment(fragments[2]);
                break;
            case Stan:
//                todo 标准化
                tvTitle.setText("标准化");
                switchFragment(fragments[3]);
                break;
            case Sins:
//                todo 安全检查
                tvTitle.setText("安全检查");
                switchFragment(fragments[4]);
                break;
            case Woas:
//                todo 工作考核
                tvTitle.setText("工作考核");
                switchFragment(fragments[5]);
                break;
            case Wins:
//                todo 水利稽察
                tvTitle.setText("水利稽察");
                switchFragment(fragments[6]);
                break;
            case Suen:
//                todo 安监执法
                tvTitle.setText("安监执法");
                Bundle bundle=new Bundle();
                bundle.putInt("statusKey", SuenChartFragment.getStatus1());
                fragments[7].setArguments(bundle);
                switchFragment(fragments[7]);
                break;
            default:
                switchFragment(fragments[1]);
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
