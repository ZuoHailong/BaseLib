package com.drumbeat.baselib.example;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.drumbeat.baselib.base.activity.BaseActivity;
import com.drumbeat.baselib.bean.viewstyle.ActionBarViewStyle;
import com.drumbeat.baselib.bean.viewstyle.EmptyViewStyle;
import com.drumbeat.baselib.bean.viewstyle.LoadingViewStyle;
import com.drumbeat.baselib.bean.viewstyle.NetworkViewStyle;
import com.drumbeat.baselib.bean.viewstyle.ToastViewStyle;
import com.drumbeat.baselib.helper.BaseLibHelper;

/**
 * Created by ZuoHailong on 2019/7/26.
 */
public class SplashActivity extends BaseActivity {

    private TextView tvCountDown;

    private CountDownTimer countDownTimer;
    //初始化是否完成
    private boolean isInitFinish;
    // 倒计时是否结束
    private boolean isCountDownFinish;
    //正在跳转
    private boolean isSkippingActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvCountDown = findViewById(R.id.tvCountDown);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //初始化无网络View的样式
        NetworkViewStyle networkViewStyle = new NetworkViewStyle();
        /*networkViewStyle.setTextSize(15)
                .setTextColor(R.color.color_ff0000);*/
        BaseLibHelper.newInstance().initViewStyle(networkViewStyle);

        //初始化ActionBar的样式
        ActionBarViewStyle actionBarViewStyle = new ActionBarViewStyle();
        /*actionBarViewStyle.setLeftTextVisiable(false)
                .setRightIconVisiable(false);*/

        BaseLibHelper.newInstance().initViewStyle(actionBarViewStyle);

        //初始化EmptyView
        EmptyViewStyle emptyViewStyle = new EmptyViewStyle();
        /*emptyViewStyle.setText(R.string.nodata);*/
        BaseLibHelper.newInstance().initViewStyle(emptyViewStyle);

        //初始化LoadingView
        LoadingViewStyle loadingViewStyle = new LoadingViewStyle();
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            loadingViewStyle.setIconColor(R.color.color_ff0000);
        }*/
        loadingViewStyle.setBiasVertical(0.5f);
        BaseLibHelper.newInstance().initViewStyle(loadingViewStyle);

        //初始化 kjToast
        ToastViewStyle toastViewStyle = new ToastViewStyle();
        /*toastViewStyle.setBgColor(R.color.baselib_color_ffffff)
                .setMsgTextColor(R.color.baselib_color_333333);*/
        BaseLibHelper.newInstance().initViewStyle(toastViewStyle);

        //此页面关闭无网络检查
        setCheckNetwork(false);

        //初始化完成
        isInitFinish = true;
        toActivity();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 100);
    }

    @Override
    public void onEmptyPageClick() {

    }

    @Override
    public void initView() {
        customActionBar.setVisibility(View.GONE);
        customActionBar.setStatusBarLightMode(true);
        //计时3秒后才可进入主页面
        startTimer();
    }

    @Override
    public void initData() {

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(1300, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long count = millisUntilFinished / 1000;
                if (count > 0) {
                    tvCountDown.setText(millisUntilFinished / 1000 + "s");
                }
            }

            @Override
            public void onFinish() {
                isCountDownFinish = true;
                toActivity();
            }
        };
        countDownTimer.start();
    }

    private void toActivity() {
        if (isInitFinish && isCountDownFinish && !isSkippingActivity) {
            isSkippingActivity = true;
            isInitFinish = false;
            isCountDownFinish = false;
            startActivity(MainActivity.class);
            finish();
        }
    }

}
