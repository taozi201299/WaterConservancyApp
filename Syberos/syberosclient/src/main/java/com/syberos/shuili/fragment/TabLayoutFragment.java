package com.syberos.shuili.fragment;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.view.PullRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * Created by jidan on 18-3-11.
 */

public abstract class TabLayoutFragment extends BaseLazyFragment {
    public static final String TABLAYOUT_FRAGMENT = "TabLayout_Fragment";

    protected int type;
    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;

    public TabLayoutFragment() {

    }

    public void setType(Bundle bundle) {
        this.setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gateway_tab1;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
