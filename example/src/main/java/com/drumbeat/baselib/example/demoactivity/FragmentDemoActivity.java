package com.drumbeat.baselib.example.demoactivity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.drumbeat.baselib.base.activity.BaseActivity;
import com.drumbeat.baselib.example.R;

public class FragmentDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);
    }

    @Override
    public void onEmptyPageClick() {

    }

    @Override
    public void initView() {
        customActionBar.setVisibility(View.GONE);
        FragmentDemo fragment = new FragmentDemo();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void initData() {

    }
}
