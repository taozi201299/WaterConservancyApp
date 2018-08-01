package com.example.testmodule.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testmodule.R;

/**
 * Created by BZB on 2018/7/30.
 * Project: Syberos.
 * Package：com.example.testmodule.view.
 */
public class WaterViewActivity extends AppCompatActivity {
    EditText editText;
    private WaterView w;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        editText = (EditText) findViewById(R.id.et_value);
        w = findViewById(R.id.water_view);
        w.setProgress(80);
        Button btn = (Button) findViewById(R.id.btn_refresh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                w.setProgress(Integer.parseInt(editText.getText().toString()));
                w.startAnimation();
            }
        });
    }

}
