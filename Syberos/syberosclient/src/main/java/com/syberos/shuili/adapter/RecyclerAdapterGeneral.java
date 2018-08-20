package com.syberos.shuili.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.syberos.shuili.R;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.listener.OnItemClickListener;

import java.util.List;

public class RecyclerAdapterGeneral extends BaseRecyclerAdapter<RecyclerAdapterGeneral.HiddenViewHold> {
    private OnItemClickListener listener;
    List<ProjectEntry> list;
    HiddenViewHold holder;
    String unit = "分";

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RecyclerAdapterGeneral(List<ProjectEntry> list) {
        this.list = list;
    }

    public RecyclerAdapterGeneral(List<ProjectEntry> list, String unit) {
        this.list = list;
        this.unit = unit;
    }
    public void setData(List<ProjectEntry> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public HiddenViewHold getViewHolder(View view) {
        return holder;
    }

    @Override
    public HiddenViewHold onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thematic_hidden_rake_view, null);
        holder = new HiddenViewHold(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HiddenViewHold holder, @SuppressLint("RecyclerView") final int position, boolean isItem) {
        holder.tvName.setText(list.get(position).getProName());
        holder.tvNum.setText(position + "");
        holder.tvScore.setText(list.get(position).getProTroubleCount() + "");
        holder.tvUnit.setText(unit);
        if (listener != null) {
            holder.tvScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });

            holder.tvNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });

        }
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    class HiddenViewHold extends RecyclerView.ViewHolder {
        TextView tvNum;
        TextView tvName;
        TextView tvScore;
        TextView tvUnit;

        public HiddenViewHold(View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_position);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvUnit = itemView.findViewById(R.id.tv_unit);
        }
    }
}