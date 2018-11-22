package com.syberos.shuili.activity.thematic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageButton;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BActivity;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematic.acci.AcciEntry;
import com.syberos.shuili.entity.thematic.haz.HazEntry;
import com.syberos.shuili.entity.thematic.stans.StanDirectEntry;
import com.syberos.shuili.entity.thematic.stans.StanSuperviseEntry;
import com.syberos.shuili.entity.thematic.suen.SuenEntry;
import com.syberos.shuili.entity.thematic.wins.WinsEntry;
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //    隐患
    ThematicDetailHiddenFragment thematicDetailHiddenFragment = new ThematicDetailHiddenFragment();
    //    事故
    ThematicDetailAcciFragment thematicDetailAcciFragment = new ThematicDetailAcciFragment();
    //    危险源
    ThematicDetailHazFragment thematicDetailHazFragment = new ThematicDetailHazFragment();
    //    标准化
    ThematicDetailStanFragment thematicDetailStanFragment = new ThematicDetailStanFragment();
    //    安全检查
    ThematicDetailSinsFragment thematicDetailSinsFragment = new ThematicDetailSinsFragment();
    //    工作考核
    ThematicDetailWoasFragment thematicDetailWoasFragment = new ThematicDetailWoasFragment();
    //    水利稽察
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
        switch (getIntent().getStringExtra("typeValue")) {
            case Hidden:
                tvTitle.setText("隐患");
                switchFragment(fragments[0]);
                break;
            case Acci:
                tvTitle.setText("事故");
                AcciEntry acciEntry = (AcciEntry)(getIntent().getBundleExtra("acciData").getSerializable("acciData"));
                ((ThematicDetailAcciFragment)fragments[1]).setData(acciEntry);
                switchFragment(fragments[1]);
                break;
            case Haz:
                tvTitle.setText("危险源");
                HazEntry hazEntry = (HazEntry)(getIntent().getBundleExtra("hazData").getSerializable("hazData"));
                ((ThematicDetailHazFragment)fragments[2]).setData(hazEntry);
                switchFragment(fragments[2]);
                break;
            case Stan:
                tvTitle.setText("标准化");
                int type = getIntent().getIntExtra("ownerType",1);
                if(type == 1) {
                    StanDirectEntry stanDirectEntry = (StanDirectEntry) (getIntent().getBundleExtra("stanData").getSerializable("stanData"));
                    ((ThematicDetailStanFragment)fragments[3]).setData_Direct(stanDirectEntry);
                }else {
                    StanSuperviseEntry stanSuperviseEntry = (StanSuperviseEntry)(getIntent().getBundleExtra("stanData").getSerializable("stanData"));
                    ((ThematicDetailStanFragment)fragments[3]).setData_Supervise(stanSuperviseEntry);
                }
                switchFragment(fragments[3]);
                break;
            case Sins:
                tvTitle.setText("安全检查");
                switchFragment(fragments[4]);
                break;
            case Woas:
                tvTitle.setText("工作考核");
                switchFragment(fragments[5]);
                break;
            case Wins:
                tvTitle.setText("水利稽察");
                WinsEntry winsEntry = (WinsEntry)(getIntent().getBundleExtra("winsData").getSerializable("winsData"));
                ((ThematicDetailWinsFragment)fragments[6]).setData(winsEntry);
                switchFragment(fragments[6]);
                break;
            case Suen:
                tvTitle.setText("监督执法");
//                Bundle bundle = new Bundle();
//                bundle.putInt("statusKey", SuenChartFragment.getStatus1());
//                fragments[7].setArguments(bundle);
//                SuenEntry suenEntry = (SuenEntry)(getIntent().getBundleExtra("suenData").getSerializable("suenData"));
//                ((ThematicDetailSuenFragment)fragments[7]).setData(suenEntry);
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
