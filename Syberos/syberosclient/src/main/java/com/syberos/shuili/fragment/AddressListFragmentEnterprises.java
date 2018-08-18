package com.syberos.shuili.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.addresslist.MyImformationActivity;
import com.syberos.shuili.activity.addresslist.OtherImformationActivity;
import com.syberos.shuili.adapter.IndexListAdapter;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.entity.userinfo.UserExtendInfo;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.StringUtils;
import com.syberos.shuili.view.indexListView.CharacterParser;
import com.syberos.shuili.view.indexListView.ClearEditText;
import com.syberos.shuili.view.indexListView.PinyinComparator;
import com.syberos.shuili.view.indexListView.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by songting on 16-3-2.
 * 通讯录 for 行政
 */
public class AddressListFragmentEnterprises extends BaseFragment implements SideBar.OnTouchingLetterChangedListener, AdapterView.OnItemClickListener, ClearEditText.ITextChanged,ClearEditText.DeleteListener, View.OnClickListener, View.OnFocusChangeListener{
    @BindView(R.id.searchClearEditText)
    ClearEditText etSearch;
    @BindView(R.id.stickListHeadersListView)
    StickyListHeadersListView mStickListHeadersListView;
    @BindView(R.id.dialog)
    TextView mDialog;
    @BindView(R.id.sideBar)
    SideBar mSideBar;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    LinearLayout llEnterpriceList;
    @BindView(R.id.ll_fucse)
    LinearLayout llFucse;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;
    @BindView(R.id.tv_quitSearch)
    TextView tvQuitSearch;
    @BindView(R.id.search_listView)
    ListView searchListView;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.action_bar)
    LinearLayout ll_action_bar;
    private IndexListAdapter indexListAdapter;

    //汉子转换成拼音的类
    private CharacterParser characterParser;
    //数据源
    private List<UserExtendInfo> persions = new ArrayList<UserExtendInfo>();
    //根据拼音来排列listView里的数据类
    private PinyinComparator pinyinComparator;
    private View view;
    private View searchHeadView;
    private TextView tvSearchCount;
    private IndexListAdapter mSearchAdapter;
    private List<UserExtendInfo> filterDataList = new ArrayList<>();

    private final String TAG = AddressListFragment.class.getSimpleName();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_addresslist;
    }

    @Override
    public void initListener() {
        llEnterpriceList.setOnClickListener(this);
        etSearch.setOnFocusChangeListener(this);
        tvQuitSearch.setOnClickListener(this);
        rlBg.setOnClickListener(this);
        mSideBar.setOnTouchingLetterChangedListener(this);
        //mStickListHeadersListView的点击事件
        mStickListHeadersListView.setOnItemClickListener(this);
        searchListView.setOnItemClickListener(this);
        //输入框搜索过滤
        etSearch.setTextChangedListener(this);

    }

    /**
     * 根据数据刷新listview
     *
     * @param lists
     */
    private void updateIndexListDataSource(List<UserExtendInfo> lists) {
        if(lists == null)return;
        persions.clear();
        for (UserExtendInfo userInformation : lists) {
            String pinyin = characterParser.getSelling(userInformation.getPersName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (StringUtils.judgeLetter(sortString.charAt(0))) {
                userInformation.setSortLetter(sortString);
            } else {
                userInformation.setSortLetter("#");
            }
            persions.add(userInformation);
        }
        //根据a-z 排序
        Collections.sort(persions, pinyinComparator);
        getSideBar();
        mProgressBar.setVisibility(View.GONE);
        indexListAdapter.updateList(persions);
    }

    @Override
    public void initData() {
        mProgressBar.setVisibility(View.VISIBLE);
        HashMap<String,String>map = new HashMap<>();
        map.put("userId","11");
        SyberosManagerImpl.getInstance().getLocalAddressList(map, new SyberosManagerImpl.ResultCallback<List<UserExtendInfo>>() {
            @Override
            public void onSuccess(List<UserExtendInfo> var1) {
                updateIndexListDataSource(var1);
            }

            @Override
            public void onError(SyberosManagerImpl.ErrorCode var1) {

            }
        });
    }

    @Override
    protected void initView() {
        etSearch.clearFocus();
        ll_action_bar.findViewById(R.id.iv_action_bar2_right).setVisibility(View.INVISIBLE);
        ll_action_bar.findViewById(R.id.iv_action_bar2_left).setVisibility(View.INVISIBLE);
        ((Activity)mContext).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ((TextView)ll_action_bar.findViewById(R.id.tv_action_bar2_title)).setText(getResources().getString(R.string.address_name));
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSideBar.setTextView(mDialog);
        indexListAdapter = new IndexListAdapter(persions, mContext);
        searchHeadView = layoutInflater.inflate(R.layout.list_head_search_view, null, false);
        tvSearchCount = (TextView) searchHeadView.findViewById(R.id.tv_searchCount);
        view = layoutInflater.inflate(R.layout.head_indexlistview, null, false);
        llEnterpriceList = (LinearLayout) view.findViewById(R.id.ll_enterpriceList);
        mStickListHeadersListView.addHeaderView(view);
        mStickListHeadersListView.setAdapter(indexListAdapter);
        mSearchAdapter = new IndexListAdapter(new ArrayList<UserExtendInfo>(), mContext);
        searchListView.addHeaderView(searchHeadView);

        searchListView.setAdapter(mSearchAdapter);
        // 设置是否可以下拉刷新
        xRefreshView.setPullRefreshEnable(true);
        xRefreshView.setPullLoadEnable(false);
        xRefreshView.setMoveFootWhenDisablePullLoadMore(false);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {

            @Override
            public void onRefresh() {
                HashMap<String,String> map = new HashMap<>();
                map.put("userId",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersId());
                SyberosManagerImpl.getInstance().syncAddressList(map, new SyberosManagerImpl.ResultCallback<List<UserExtendInfo>>() {
                    @Override
                    public void onSuccess(List<UserExtendInfo> var1) {
                        xRefreshView.stopRefresh(true);
                        updateIndexListDataSource(var1);
                    }

                    @Override
                    public void onError(SyberosManagerImpl.ErrorCode var1) {
                        xRefreshView.stopRefresh(false);

                    }
                });

            }

            @Override
            public void onRefresh(boolean isPullDown) {
                LogUtils.d(TAG,"22222");

            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {
                LogUtils.d(TAG,"3333");

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {
                LogUtils.d(TAG,"4444");

            }
        });


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        clear();
    }

    private void  clear(){
        etSearch.clearFocus();
        rlBg.setVisibility(View.GONE);
        hideSoftInput((Activity) mContext);
        etSearch.setText("");
    }
    /**
     * 输入框过滤更新列表
     *
     * @param s
     */
    private void filterData(String s) {
        filterDataList.clear();
        if (TextUtils.isEmpty(s)) {
            filterDataList = persions;
        } else {
            HashMap<String,String> map = new HashMap<>();
            map.put("searchText",s);
            SyberosManagerImpl.getInstance().searchAddressList(map, new SyberosManagerImpl.ResultCallback<List<UserExtendInfo>>() {
                @Override
                public void onSuccess(List<UserExtendInfo> var1) {
                    filterDataList = var1;
                    updateData();

                }

                @Override
                public void onError(SyberosManagerImpl.ErrorCode var1) {

                }
            });
        }
    }


    public void updateData() {
        if(filterDataList == null)return;
        mSearchAdapter.setData(filterDataList);
        tvSearchCount.setText("搜索结果" + filterDataList.size() + "条");
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        //该字母首次出现的位置
        int position = indexListAdapter.getPositionForSection(s.charAt(0));
        if (position != -1) {
            mStickListHeadersListView.setSelection(position + 1);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (searchListView.getVisibility() == View.VISIBLE) {
            if (position == 0)
                return;
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", filterDataList.get(position - 1));
            if (filterDataList.get(position - 1).getPersId().equals(SyberosManagerImpl.getInstance().getCurrentUserId())) {
                intentActivity(getActivity(), MyImformationActivity.class, false, bundle);
            } else {
                intentActivity(getActivity(), OtherImformationActivity.class, false, bundle);
            }
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", persions.get(position - 1));
            if (persions.get(position - 1).getPersId().equals(SyberosManagerImpl.getInstance().getCurrentUserId())) {
                intentActivity(getActivity(), MyImformationActivity.class, false, bundle);
            } else {
                intentActivity(getActivity(), OtherImformationActivity.class, false, bundle);
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_bg:
                tvQuitSearch.setVisibility(View.GONE);
                llFucse.requestFocus();
                rlBg.setVisibility(View.GONE);
                break;
            case R.id.tv_quitSearch:
                etSearch.setText("");
                rlBg.setVisibility(View.GONE);
                tvQuitSearch.setVisibility(View.GONE);
                llFucse.requestFocus();
                break;

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (isHidden()) {
            return;
        }
        if (hasFocus) {
            rlBg.setVisibility(View.VISIBLE);
        } else {
            if (etSearch != null) {
                CommonUtils.hideSoftPan(etSearch);
                rlBg.setVisibility(View.GONE);
                etSearch.setText(" ");
            }
        }

        LogUtils.d("点击了", "焦点变化" + hasFocus);
        if (etSearch != null) {
            etSearch.onFocusChange(v, hasFocus);
        }
    }


    private void getSideBar() {
        TreeSet<String> data = new TreeSet<String>();
        for (UserExtendInfo persion : persions) {

            if (StringUtils.judgeLetter(persion.getSortLetter().charAt(0))) {//A-Z
                data.add(persion.getSortLetter().charAt(0) + "");
            } else {
                data.add("#");
            }
        }
        mSideBar.setData(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onTextChanged(String s) {
        if (TextUtils.isEmpty(s.toString().trim())) {
            if (tvQuitSearch.getVisibility() == View.VISIBLE) {
                rlBg.setVisibility(View.VISIBLE);
            }
            searchListView.setVisibility(View.GONE);
            mStickListHeadersListView.setVisibility(View.VISIBLE);
            mSideBar.setVisibility(View.VISIBLE);
        } else {
            filterData(s.toString().trim());
            searchListView.setVisibility(View.VISIBLE);
            mStickListHeadersListView.setVisibility(View.GONE);
            rlBg.setVisibility(View.GONE);
            mSideBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void delete() {
        etSearch.setText("");
        rlBg.setVisibility(View.GONE);
        tvQuitSearch.setVisibility(View.GONE);
        llFucse.requestFocus();
    }
}
