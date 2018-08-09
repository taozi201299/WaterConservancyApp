package com.syberos.shuili.activity.thematic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by BZB on 2018/8/4.
 * Project: Syberos.
 * Package：com.syberos.shuili.activity.thematic.
 */
public class ThematicDetailBaseActivity extends AppCompatActivity {
    private int layoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    public int getLayoutId() {
        return layoutId;
    }
}
