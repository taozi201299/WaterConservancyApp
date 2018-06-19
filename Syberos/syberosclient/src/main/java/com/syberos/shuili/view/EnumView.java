package com.syberos.shuili.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.syberos.shuili.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by jidan on 18-3-22.
 */

public class EnumView extends LinearLayout{

    public final static int INDEX_NONE = -1;
    private TextView tv_enum_detail;
    private List<String> optionsItems = null;
    private OptionsPickerView pvOptions;
    private int currentIndex = INDEX_NONE;

    public EnumView(Context context) {
        this(context, null);
    }

    public EnumView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EnumView(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.EnumViewButtonGroup, 0, 0);

        View view = View.inflate(context, R.layout.view_enum_item, this);
        TextView tv_enum_title = (TextView) view.findViewById(R.id.tv_enum_title);

        ImageView iv_enum_arrow = (ImageView) view.findViewById(R.id.iv_enum_arrow);
        iv_enum_arrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pvOptions!=null)
                pvOptions.show();
                else {
                    initDialog(context);
                    if(pvOptions != null){
                        pvOptions.show();
                    }
                }
            }
        });


        tv_enum_detail = (TextView) view.findViewById(R.id.tv_enum_detail);
        tv_enum_detail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pvOptions!=null)
                pvOptions.show();
                else {
                    initDialog(context);
                    if(pvOptions != null){
                        pvOptions.show();
                    }
                }
            }
        });

        try {

            String title = a.getString(R.styleable.EnumViewButtonGroup_enum_title);
            if (null != title) {
                tv_enum_title.setText(title);
            }

            String detail = a.getString(R.styleable.EnumViewButtonGroup_enum_detail);
            if (null != detail) {
                tv_enum_detail.setText(detail);
            }

            String detail_hint = a.getString(R.styleable.EnumViewButtonGroup_enum_detail_hint);
            if (null != detail_hint) {
                tv_enum_detail.setHint(detail_hint);
            }

            setEntries(a.getTextArray(R.styleable.EnumViewButtonGroup_entries));
            if (optionsItems != null) {
                initDialog(context);
            }
        } finally {
            a.recycle();
        }

    }

    public void initDialog(Context context) {
        if(optionsItems == null)return;
        //条件选择器
        pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = optionsItems.get(options1);
                currentIndex = options1;
                tv_enum_detail.setText(tx);
            }
        })
                .isDialog(true)
                .build();
        pvOptions.setPicker(optionsItems);

    }

    public void setEntries(List<String> entries) {
        this.optionsItems = entries;
    }

    public void setEntries(CharSequence[] entries) {
        if(entries == null || entries.length == 0) return;
        List<String> map = new ArrayList<>();
        for (CharSequence e : entries) {
            map.add(e.toString());
        }
        this.optionsItems = map;
        currentIndex = 0;
        tv_enum_detail.setText(optionsItems.get(currentIndex));
    }

    public void setEntries(HashMap<Integer, String> entries) {
        if(entries == null  || entries.size() == 0)return;
        List<String> map = new ArrayList<>();
        Set<Integer> keys = entries.keySet();
        for (Integer i : keys) {
            map.add(entries.get(i));
        }
        this.optionsItems = map;
        currentIndex = 0;
        tv_enum_detail.setText(optionsItems.get(currentIndex));
    }

    public void setEntries(LinkedHashMap<Integer, String> entries) {
        if(entries == null || entries.size() == 0) return;
        List<String> map = new ArrayList<>();
        Set<Integer> keys = entries.keySet();
        for (Integer i : keys) {
            map.add(entries.get(i));
        }
        this.optionsItems = map;
        currentIndex = 0;
        tv_enum_detail.setText(optionsItems.get(currentIndex));
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public String getCurrentDetailText() {
        if (null == tv_enum_detail) {
            return "";
        }
        return tv_enum_detail.getText().toString();
    }
    public void setCurrentDetailText(String detailText){
        if(detailText == null)return;
        tv_enum_detail.setText(detailText);
    }
    public void setCurrentIndex(int currentIndex){
        this.currentIndex = currentIndex;
    }
}
