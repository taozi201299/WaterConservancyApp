package com.syberos.shuili.activity.wins;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.inspect.InspectProblemInformation;
import com.syberos.shuili.utils.Arrays2;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建稽查问题
 */
public class InspectNewProblemActivity extends BaseActivity {

    public static final String RESULT_KEY = "InspectProblemInformation";
    private final String Title = "新建稽查问题";

    @BindView(R.id.tv_project)
    TextView tv_project;
    @BindView(R.id.ll_problems_type)
    EnumView ll_problems_type;
    @BindView(R.id.ll_severity_level)
    EnumView ll_severity_level;
    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;
    @BindView(R.id.ae_rect_audio)
    AudioEditView ae_rect_audio;
    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;


    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_new_problem;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        ll_problems_type.setEntries(GlobleConstants.winsProbMap);
        ll_problems_type.setCurrentDetailText(GlobleConstants.winsProbMap.get(1));
        ll_severity_level.setEntries(GlobleConstants.winsProbTypeMap);
        ll_severity_level.setCurrentDetailText(GlobleConstants.winsProbTypeMap.get(1));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.setData(null);
    }
}
