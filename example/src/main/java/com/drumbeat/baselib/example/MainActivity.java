package com.drumbeat.baselib.example;

import android.os.Bundle;
import android.view.View;

import com.drumbeat.baselib.base.activity.BaseActivity;
import com.drumbeat.baselib.example.demoactivity.ActionBarDemoActivity;
import com.drumbeat.baselib.example.demoactivity.FragmentDemoActivity;
import com.drumbeat.baselib.example.demoactivity.OtherDemoSetsActivity;
import com.drumbeat.baselib.example.demoactivity.ToastDemoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onEmptyPageClick() {

    }

    @Override
    public void initView() {
        customActionBar.setLeftVisiable(false)
                .setRightVisiable(false)
                .setCenterTitleText(getString(R.string.app_name));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btnActionBarDemo, R.id.btnOtherDemoSets, R.id.btnFragmentDemo, R.id.btnToastDemoSets})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btnActionBarDemo) {
            startActivity(ActionBarDemoActivity.class);
        } else if (viewId == R.id.btnOtherDemoSets) {
            startActivity(OtherDemoSetsActivity.class);
        } else if (viewId == R.id.btnToastDemoSets) {
            startActivity(ToastDemoActivity.class);
        } else if (viewId == R.id.btnFragmentDemo) {
            startActivity(FragmentDemoActivity.class);
        }
    }
}
