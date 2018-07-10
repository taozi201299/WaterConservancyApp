package com.example.testmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import javax.xml.transform.sax.TemplatesHandler;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GSON FORMATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnClick(View view) {
        ThematicMapEntry.ProjectInfo projectInfo=new ThematicMapEntry.ProjectInfo();
        projectInfo.setProCount("1");
        projectInfo.setProID("1");
        projectInfo.setProInfo("1");
        projectInfo.setProName("1");
        ArrayList<ThematicMapEntry.ProjectInfo> projectInfoArrayList=new ArrayList<>();
        projectInfoArrayList.add(projectInfo);

        ThematicMapEntry.ChartData chartData = new ThematicMapEntry.ChartData();
        chartData.setCharItemID("1");
        chartData.setCharItemName("1");
        chartData.setCharItemValue("1");
        ArrayList<ThematicMapEntry.ChartData> chartDataArrayList=new ArrayList<>();
        chartDataArrayList.add(chartData);

        ThematicMapEntry.OwnerAreaData ownerAreaData = new ThematicMapEntry.OwnerAreaData();
        ownerAreaData.setCharID("1");
        ownerAreaData.setCharName("1");
        ownerAreaData.setChartData(chartDataArrayList);
        ownerAreaData.setOwnerTypeName("1");
        ownerAreaData.setProjectInfos(projectInfoArrayList);
        ownerAreaData.setOwnerTypeID("1");
        ArrayList<ThematicMapEntry.OwnerAreaData> ownerAreaDataArrayList=new ArrayList<>();
        ownerAreaDataArrayList.add(ownerAreaData);

        ThematicMapEntry thematicMapEntry = new ThematicMapEntry();
        thematicMapEntry.setContent("1");
        thematicMapEntry.setDataId("1");
        thematicMapEntry.setDataList(ownerAreaDataArrayList);
        thematicMapEntry.setDataName("1");


        Gson gson = new Gson();
        String str = gson.toJson(thematicMapEntry);
        Log.e(TAG, "onCreate: " + str);
//        JSONObject json = JSONObject.fromObject(thematicMapEntry);//将java对象转换为json对象
//        String str = json.toString();
    }
}
