package com.example.testmodule.testrxjavaretrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.testmodule.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by BZB on 2018/7/12.
 * Project: Syberos.
 * Package：com.example.testmodule.testrxjavaretrofit.
 */
public class HttpRequestResultActivity extends Activity {
    List<MyJoke> jokes = new ArrayList<>();
    MyAdapter adapter = new MyAdapter(this, new ArrayList<MyJoke>());
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        getData();
    }

    public void getData() {


        HttpMethods.getInstance().getJoke(new Observer<List<MyJoke>>() {
            Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
            }

            @Override
            public void onNext(List<MyJoke> myJokes) {
                jokes = myJokes;

                LinearLayoutManager layoutManager = new LinearLayoutManager(HttpRequestResultActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.update(jokes);
                Log.d("MAIN", "获取数据完成" + myJokes.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MAIN", "error" + e.toString());
                d.dispose();
            }

            @Override
            public void onComplete() {
                Log.d("MAIN", "onComplete");
                d.dispose();
            }
        });

    }
}
