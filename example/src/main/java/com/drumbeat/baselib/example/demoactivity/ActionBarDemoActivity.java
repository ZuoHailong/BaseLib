package com.drumbeat.baselib.example.demoactivity;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.drumbeat.baselib.base.activity.BaseActivity;
import com.drumbeat.baselib.example.R;
import com.drumbeat.baselib.view.CustomActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZuoHailong on 2019/7/25.
 */
public class ActionBarDemoActivity extends BaseActivity {

    @BindView(R.id.rgLightMode)
    RadioGroup rgLightMode;
    @BindView(R.id.rgActionBarBg)
    RadioGroup rgActionBarBg;
    @BindView(R.id.rgActionBarTitle)
    RadioGroup rgActionBarTitle;
    @BindView(R.id.rgTitleBarVisiable)
    RadioGroup rgTitleBarVisiable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_actionbar);
        ButterKnife.bind(this);
    }

    @Override
    public void onEmptyPageClick() {

    }

    @Override
    public void initView() {
        customActionBar.setCenterTitleText("ActionBar Demo")
                .setLeftTextVisiable(true)
                .setRightIconVisiable(true);
        rgLightMode.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rbLightModeTrue:
                    customActionBar.setStatusBarLightMode(true);
                    break;
                case R.id.rbLightModeFalse:
                    customActionBar.setStatusBarLightMode(false);
                    break;
            }
        });
        rgActionBarBg.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rbActionBarBgColor:
                    customActionBar.setActionBarBgColor(R.color.red);
                    break;
                case R.id.rbActionBarBgImg:
                    customActionBar.setActionBarBgResource(R.mipmap.bg_actionbar);
                    break;
            }
        });
        rgActionBarTitle.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rbActionBarTitle:
                    customActionBar.setActionBarType(CustomActionBar.ACTION_BAR_TYPE_TITLE);
                    break;
                case R.id.rbActionBarInput:
                    customActionBar.setActionBarType(CustomActionBar.ACTION_BAR_TYPE_INPUT);
                    break;
            }
        });
        rgTitleBarVisiable.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rbTitleBarVisiable:
                    customActionBar.setTitleBarVisiable(true);
                    break;
                case R.id.rbTitleBarGone:
                    customActionBar.setTitleBarVisiable(false);
                    break;
            }
        });
    }

    @Override
    public void initData() {

    }


}
