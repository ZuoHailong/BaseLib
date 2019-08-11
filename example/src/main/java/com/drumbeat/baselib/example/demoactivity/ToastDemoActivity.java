package com.drumbeat.baselib.example.demoactivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.drumbeat.baselib.base.BaseActivity;
import com.drumbeat.baselib.bean.viewstyle.ToastViewStyle;
import com.drumbeat.baselib.example.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZuoHailong on 2019/7/30
 */
public class ToastDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_toast);
        ButterKnife.bind(this);
    }

    @Override
    public void onEmptyPageClick() {

    }

    @Override
    public void initView() {
        customActionBar.setCenterTitleText("Toast Demo")
                .setRightTextVisiable(false)
                .setRightIconVisiable(false)
                .setLeftTextVisiable(false);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btnDefaultToast, R.id.btnChangeText, R.id.btnChangeBgColor, R.id.btnChangeBgRes, R.id.btnChangeGravity})
    public void onViewClicked(View view) {
        ToastViewStyle toastViewStyle;
        switch (view.getId()) {
            case R.id.btnDefaultToast:
                showToastShort(R.string.toast_default);
                break;
            case R.id.btnChangeText:
                toastViewStyle = new ToastViewStyle();
                toastViewStyle.setMsgTextColor(ContextCompat.getColor(getContext(), R.color.color_ff0000))
                        .setMsgTextSize(20);
                showToastShort(toastViewStyle, R.string.toast_text);
                break;
            case R.id.btnChangeBgColor:
                toastViewStyle = new ToastViewStyle();
                toastViewStyle.setBgColor(ContextCompat.getColor(getContext(), R.color.color_ff0000));
                showToastShort(toastViewStyle, R.string.toast_bgcolor);
                break;
            case R.id.btnChangeBgRes:
                toastViewStyle = new ToastViewStyle();
                toastViewStyle.setBgResource(R.mipmap.bg_actionbar);
                showToastShort(toastViewStyle, R.string.toast_bgres);
                break;
            case R.id.btnChangeGravity:
                toastViewStyle = new ToastViewStyle();
                toastViewStyle.setCustomGravity(Gravity.TOP, (int) (Math.random() * 200), (int) (Math.random() * 400));
                showToastShort(toastViewStyle, R.string.toast_gravity);
                break;
        }
    }
}
