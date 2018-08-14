package com.syberos.shuili.activity.thematic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageButton;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BActivity;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailAcciFragment;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailHazFragment;
import com.syberos.shuili.fragment.thematic.detail.detailproj.ThematicDetailHiddenProjFragment;

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
public class ThematicDetailProjActivity extends BActivity {
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

    ThematicDetailHiddenProjFragment thematicDetailHiddenFragment = new ThematicDetailHiddenProjFragment();
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
