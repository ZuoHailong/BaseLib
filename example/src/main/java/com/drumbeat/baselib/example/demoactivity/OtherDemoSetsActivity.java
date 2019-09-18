package com.drumbeat.baselib.example.demoactivity;

import android.os.Build;
import android.os.Bundle;

import com.drumbeat.baselib.base.activity.BaseActivity;
import com.drumbeat.baselib.example.R;

/**
 * Created by ZuoHailong on 2019/7/30
 */
public class OtherDemoSetsActivity extends BaseActivity {

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_other_sets);
        findViewById(R.id.btnChangeColor).setOnClickListener(v -> {
            int color;
            if (flag) {
                color = R.color.color_00ff00;
            } else {
                color = R.color.color_ff0000;
            }
            flag = !flag;
            customEmptyView.setTextColor(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                customLoading.setIconColor(color);
                customEmptyView.setIconColor(color);
            }
        });
        findViewById(R.id.btnChangeImg).setOnClickListener(v -> {
            customLoading.setIconResource(R.mipmap.ic_launcher);
            customEmptyView.setIconResource(R.mipmap.ic_launcher);
        });
        findViewById(R.id.btnShowLoading).setOnClickListener(v -> {
            showLoading();
        });
    }

    @Override
    public void onEmptyPageClick() {

    }

    @Override
    public void initView() {
        customActionBar.setCenterTitleText("Other Demo Sets")
                .setRightTextVisiable(false)
                .setRightIconVisiable(false)
                .setLeftTextVisiable(false);
//        showEmptyView();
    }

    @Override
    public void initData() {

    }

}
