package com.syberos.shuili.amap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.syberos.shuili.R;

public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void onSecurityCheckClicked(View view) {
        Intent i = new Intent(this, SecurityCheckMapTrailsActivity.class);
        startActivity(i);
    }

    public void onSeeNearbyClicked(View view) {
        Intent i = new Intent(this, ShowNearlyInfoActivity.class);
        startActivity(i);
    }
}
