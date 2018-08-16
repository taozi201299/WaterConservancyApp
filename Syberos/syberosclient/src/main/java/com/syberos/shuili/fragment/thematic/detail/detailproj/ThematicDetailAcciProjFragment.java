package com.syberos.shuili.fragment.thematic.detail.detailproj;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.entity.thematicchart.accident.AccidentItemDetailEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by BZB on 2018/8/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 事故
 */
public class ThematicDetailAcciProjFragment extends BaseLazyFragment {


    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<AccidentItemDetailEntry> dataList;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_acci_proj;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(new AccidentItemDetailEntry("标题", i * 10, i * 10, i * 5, i * 5, "车祸", "碰瓷的"));
        }
        AccRecyclerAdapter adapter = new AccRecyclerAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initView() {


    }


    class AccRecyclerAdapter extends BaseRecyclerAdapter<HiddenViewHold> {

        private OnItemClickListener listener;
        List<AccidentItemDetailEntry> list;
        HiddenViewHold holder;

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        public AccRecyclerAdapter(List<AccidentItemDetailEntry> list) {
            this.list = list;
        }

        @Override
        public HiddenViewHold getViewHolder(View view) {
            return holder;
        }

        @Override
        public HiddenViewHold onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thematic_acc_proj_view, null);
            holder = new HiddenViewHold(view);
            return holder;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(HiddenViewHold holder, @SuppressLint("RecyclerView") final int position, boolean isItem) {
            AccidentItemDetailEntry entry = list.get(position);
            holder.tvTitle1.setText(entry.getAccTitle());
            holder.tvAccType.setText(entry.getAccReason());
            holder.tvValue11.setText(entry.getAccCount()+"");
            holder.tvValue12.setText(entry.getDeadCount()+"");
            holder.tvValue13.setText(entry.getSeriouslyInjured()+"");
            holder.tvValue14.setText(entry.getDirectEconomicLosses()+"");
            holder.tvOverview1.setText(entry.getAccDescription());

        }

        @Override
        public int getAdapterItemCount() {
            return list.size();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class HiddenViewHold extends RecyclerView.ViewHolder {

        TextView tvTitle1;
        TextView tvValue11;
        TextView tvValue12;
        TextView tvValue13;
        TextView tvValue14;
        TextView tvAccType;
        TextView tvOverview1;

        public HiddenViewHold(View itemView) {
            super(itemView);
            tvTitle1 = itemView.findViewById(R.id.tv_title1);
            tvValue11 = itemView.findViewById(R.id.tv_value_1_1);
            tvValue12 = itemView.findViewById(R.id.tv_value_1_2);
            tvValue13 = itemView.findViewById(R.id.tv_value_1_3);
            tvValue14 = itemView.findViewById(R.id.tv_value_1_4);
            tvAccType = itemView.findViewById(R.id.tv_acc_type);
            tvOverview1 = itemView.findViewById(R.id.tv_overview1);

        }
    }
}
