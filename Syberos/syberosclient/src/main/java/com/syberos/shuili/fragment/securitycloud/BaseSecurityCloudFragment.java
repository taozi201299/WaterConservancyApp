package com.syberos.shuili.fragment.securitycloud;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.view.DialPlateView;

import butterknife.BindView;

/**
 * Created by BZB on 2018/7/20.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.securitycloud.
 */
@SuppressLint("ValidFragment")
public class BaseSecurityCloudFragment extends BaseFragment {
    public static final String TAG = "BaseSecurityCloudFragment";
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_grade_time)
    TextView tvGradeTime;
    @BindView(R.id.view_dial_plate)
//    ImageView imageView;
    DialPlateView viewDialPlate;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_score_title)
    TextView tvScoreTitle;
    String title;
    String titleDetail;
    String strJsonData;

    public void initViewData() {
        titleDetail = new String(title);
        if(strJsonData!=null){
            StringBuilder sb = new StringBuilder(title);
            sb.append("·安全评分·");
            sb.append(strJsonData);
            sb.append("分");
            titleDetail = new String(sb);
            tvScore.setText(strJsonData);
        }else {
            titleDetail=title;
        }
        tvTitle.setText(title);
        collapsingToolbarLayout.setTitle(titleDetail);


    }
    public void updataData(String title,String strJsonData){
        this.title=title;
        this.strJsonData=strJsonData;
        initData();
    }

    @SuppressLint("ValidFragment")
    public BaseSecurityCloudFragment(String title) {
        this.title = title;
    }

    @SuppressLint("ValidFragment")
    public BaseSecurityCloudFragment(String title, @Nullable String strJsonData) {
        this.title = title;
        this.strJsonData = strJsonData;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud_river_area;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initViewData();
        viewDialPlate.upData(Integer.parseInt(strJsonData));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                scoreRate= (tvScore.getTop()-(toolbar.getHeight()-tvTitle.getHeight())/2)/(appBarLayout.getHeight()-toolbar.getHeight())*1.0f;
//                graduationRate=(collapsingToolbarLayout.getTop()-(toolbar.getHeight()-tvTitle.getHeight())/2)/(appBarLayout.getHeight()-toolbar.getHeight())*1.0f;
//                collapsingToolbarLayout.setTranslationY(graduationRate*verticalOffset);
//                //得到文本框、头像缩放值 不透明 ->透明  文本框x跟y缩放
//                float scale = 1.0f -  (-verticalOffset*4)/5  / (float)(appBarLayout.getHeight() - toolbar.getHeight());
                float scale = 1.0f - (-verticalOffset) / (float) (appBarLayout.getHeight() - toolbar.getHeight());
//                Log.e(TAG, "onOffsetChanged: ");
//                Log.e(TAG, "onOffsetChanged: scale" + scale);
//                Log.e(TAG, "onOffsetChanged:verticalOffset " + verticalOffset);
//                Log.e(TAG, "onOffsetChanged:appBarLayout.getHeight() " + appBarLayout.getHeight());
//                Log.e(TAG, "onOffsetChanged:toolbar.getHeight() " + toolbar.getHeight());
//                Log.e(TAG, "onOffsetChanged: (appBarLayout.getHeight() - toolbar.getHeight())" + (appBarLayout.getHeight() - toolbar.getHeight()));
//                Log.e(TAG, "onOffsetChanged:(-verticalOffset) / (appBarLayout.getHeight() - toolbar.getHeight())" + (-verticalOffset) / (appBarLayout.getHeight() - toolbar.getHeight()));
                tvScore.setScaleX(scale);
                tvScore.setScaleY(scale);
                tvScore.setAlpha(scale);

                viewDialPlate.setScaleX(scale);
                viewDialPlate.setScaleY(scale);
                viewDialPlate.setAlpha(scale);

                tvScoreTitle.setScaleX(scale);
                tvScoreTitle.setScaleY(scale);
                viewDialPlate.setAlpha(scale);

                tvGradeTime.setScaleX(scale);
                tvGradeTime.setScaleY(scale);
                viewDialPlate.setAlpha(scale);



            }
        });
    }

    @Override
    protected void initView() {

    }

}
