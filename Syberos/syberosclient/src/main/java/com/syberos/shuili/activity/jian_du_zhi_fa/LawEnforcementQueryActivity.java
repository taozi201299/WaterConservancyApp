package com.syberos.shuili.activity.jian_du_zhi_fa;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.App;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.LawQueryInformation;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LawEnforcementQueryActivity extends TranslucentActivity
        implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "LawQueryInformation";

    private ListAdapter listAdapter = null;
    private List<LawQueryInformation> lawQueryInformationList = null;
    private Map<String, LawQueryInformation> lawQueryInformationMap = null;

    private List<String> allLawTitleList = null;
    private List<String> searchResultList = null;
    private SearchResultAdapter searchResultAdapter = null;
    private List<String> searchHistoryList = null;
    private SearchHistoryAdapter searchHistoryAdapter = null;

    @BindView(R.id.searchClearEditText)
    ClearEditText searchClearEditText;

    @BindView(R.id.search_result_background)
    LinearLayout search_result_background;

    @BindView(R.id.search_history_background)
    LinearLayout search_history_background;

    @BindView(R.id.tv_quit_search)
    TextView tv_quit_search;

    @BindView(R.id.search_result_listView)
    ListView search_result_listView;

    @BindView(R.id.search_history_listView)
    ListView search_history_listView;

    @BindView(R.id.ll_normal_view)
    LinearLayout ll_normal_view;

    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView;

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        CommonUtils.hideSoftPan(searchClearEditText);
        searchClearEditText.clearFocus();
        activityFinish();
    }

    @OnClick(R.id.tv_quit_search)
    void onCancelSearchClicked() {
        searchClearEditText.clearFocus();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_law_enforcement_query;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        searchClearEditText.clearFocus();
        searchHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        searchClearEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ll_normal_view.setVisibility(View.GONE);
                    if (searchClearEditText.getText().toString().isEmpty()) {
                        search_history_background.setVisibility(View.VISIBLE);
                    } else {
                        search_result_background.setVisibility(View.VISIBLE);
                    }
                    tv_quit_search.setVisibility(View.VISIBLE);
                } else {
                    if (searchClearEditText != null) {
                        CommonUtils.hideSoftPan(searchClearEditText);
                        searchClearEditText.setText("");
                    }
                    search_history_background.setVisibility(View.GONE);
                    search_result_background.setVisibility(View.GONE);
                    tv_quit_search.setVisibility(View.GONE);
                    ll_normal_view.setVisibility(View.VISIBLE);
                }

                if (searchClearEditText != null) {
                    searchClearEditText.onFocusChange(v, hasFocus);
                }
            }
        });

        searchClearEditText.setTextChangedListener(new ClearEditText.ITextChanged() {
            @Override
            public void onTextChanged(String s) {
                if (TextUtils.isEmpty(s.trim())) {
                    // 显示历史结果
                    search_result_background.setVisibility(View.GONE);
                    search_history_background.setVisibility(View.VISIBLE);
                } else {
                    // 显示搜索结果
                    search_history_background.setVisibility(View.GONE);
                    search_result_background.setVisibility(View.VISIBLE);
                    filterData(s.trim());
                }
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_law_enforcement_query_item);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        listAdapter.setOnItemClickListener(this);

        if (null == lawQueryInformationMap) {
            lawQueryInformationMap = new HashMap<>();
        } else {
            lawQueryInformationMap.clear();
        }

        for (int i = 1; i < 99; ++i) {
            LawQueryInformation information = new LawQueryInformation();
            String title = "堤防工程施工规范" + i;
            information.setTitle(title);
            lawQueryInformationMap.put(title, information);
        }

        // init allLawTitleList
        allLawTitleList = new ArrayList<>();
        allLawTitleList.addAll(lawQueryInformationMap.keySet());

        listAdapter.setData(new ArrayList<>(lawQueryInformationMap.values()));
        listAdapter.notifyDataSetChanged();

        searchResultList = new ArrayList<>();
        searchResultAdapter = new SearchResultAdapter();
        search_result_listView.setAdapter(searchResultAdapter);

        searchHistoryList = App.lawEnforcementQueryHistories();
        searchHistoryAdapter = new SearchHistoryAdapter();
        search_history_listView.setAdapter(searchHistoryAdapter);
    }

    void filterData(final String text) {
        searchResultList.clear();
        for (String lawTitle : allLawTitleList) {
            if (lawTitle.contains(text)) {
                searchResultList.add(lawTitle);
            }
        }
        searchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        LawQueryInformation information = new ArrayList<>(lawQueryInformationMap.values()).get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this,
                LawEnforcementQueryDetailActivity.class, false, bundle);
    }

    private class ListAdapter extends CommonAdapter<LawQueryInformation> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, LawQueryInformation information) {

            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getTitle());

        }
    }

    private static class ViewHolder{
        TextView itemText;
    }


    private class SearchResultAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return null == searchResultList ? 0 : searchResultList.size();
        }

        @Override
        public Object getItem(int position) {
            return null == searchResultList ? null : searchResultList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == searchResultList || searchResultList.size() <= 0) {
                return convertView;
            }

            ViewHolder holder;
            if (null == convertView) {
                convertView = LayoutInflater.from(LawEnforcementQueryActivity.this).inflate(
                        R.layout.activity_law_enforcement_query_history_item, null);
                holder = new ViewHolder();
                holder.itemText = convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewHolder holder = (ViewHolder) v.getTag();
                        String title = holder.itemText.getText().toString();

                        Bundle bundle = new Bundle();
                        LawQueryInformation information = lawQueryInformationMap.get(title);
                        bundle.putSerializable(SEND_BUNDLE_KEY, information);
                        intentActivity(LawEnforcementQueryActivity.this,
                                LawEnforcementQueryDetailActivity.class, false, bundle);

                        searchHistoryList.add(0, title);
                        App.saveLawEnforcementQueryHistories(searchHistoryList);
                    }
                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.itemText.setText(searchResultList.get(position));
            return convertView;
        }
    }

    private class SearchHistoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return null == searchHistoryList ? 0 : searchHistoryList.size();
        }

        @Override
        public Object getItem(int position) {
            return null == searchHistoryList ? null : searchHistoryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == searchHistoryList || searchHistoryList.size() <= 0) {
                return convertView;
            }

            ViewHolder holder;
            if (null == convertView) {
                convertView = LayoutInflater.from(LawEnforcementQueryActivity.this).inflate(
                        R.layout.activity_law_enforcement_query_history_item, null);
                holder = new ViewHolder();
                holder.itemText = convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewHolder holder = (ViewHolder) v.getTag();
                        String title = holder.itemText.getText().toString();

                        Bundle bundle = new Bundle();
                        LawQueryInformation information = lawQueryInformationMap.get(title);
                        bundle.putSerializable(SEND_BUNDLE_KEY, information);
                        intentActivity(LawEnforcementQueryActivity.this,
                                LawEnforcementQueryDetailActivity.class, false, bundle);

                        // move the position to the top of searchHistoryList
                        searchHistoryList.remove(title);
                        searchHistoryList.add(0, title);
                        App.saveLawEnforcementQueryHistories(searchHistoryList);
                    }
                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.itemText.setText(searchHistoryList.get(position));
            return convertView;
        }
    }

}
