package com.example.testmodule.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.testmodule.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/6.
 */

public class MultiThreadActivity extends AppCompatActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.et_value)
    EditText etValue;
    @BindView(R.id.btn_start)
    Button btnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multy_thread);
        ButterKnife.bind(this);

    }
}
