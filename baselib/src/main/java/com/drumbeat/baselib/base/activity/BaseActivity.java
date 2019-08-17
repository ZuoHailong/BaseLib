package com.drumbeat.baselib.base.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.drumbeat.baselib.R;
import com.drumbeat.baselib.base.mvp.IBaseView;
import com.drumbeat.baselib.bean.viewstyle.ActionBarViewStyle;
import com.drumbeat.baselib.bean.viewstyle.EmptyViewStyle;
import com.drumbeat.baselib.bean.viewstyle.LoadingViewStyle;
import com.drumbeat.baselib.bean.viewstyle.NetworkViewStyle;
import com.drumbeat.baselib.bean.viewstyle.ToastViewStyle;
import com.drumbeat.baselib.helper.BaseLibHelper;
import com.drumbeat.baselib.view.CustomActionBar;
import com.drumbeat.baselib.view.CustomEmptyView;
import com.drumbeat.baselib.view.CustomLoading;

/**
 * Activity基类
 * Created by ZuoHailong on 2019/7/23.
 */
public abstract class BaseActivity extends FragmentActivity implements IBaseView {

    public CustomActionBar customActionBar;
    public CustomEmptyView customEmptyView;
    public CustomLoading customLoading;
    private TextView tvNoNetwork;
    private FrameLayout flContainer;
    private View childView;

    //是否开启网络状态检查，默认开启
    private boolean isCheckNetWork = true;
    //网络状态变化监听
    private NetworkUtils.OnNetworkStatusChangedListener networkStatusChangedListener;

    private boolean firstShowEmpty = true, firstShowLoading = true, firstShowNoNet = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.baselib_activity_base);
        //开启沉浸式，最后一个参数在此处没什么用处
        BarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0), false);
        customActionBar = findViewById(R.id.customActionBar);
        customEmptyView = findViewById(R.id.customEmptyView);
        //空页面点击监听，子Activity可选择重写onEmptyPageClick()方法，以实现诸如“点击空页面重新查询数据”的功能
        customEmptyView.setOnClickListener(v -> onEmptyPageClick());
        customLoading = findViewById(R.id.customLoading);
        flContainer = findViewById(R.id.flContainer);
        tvNoNetwork = findViewById(R.id.tvNoNetwork);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //一定要在initView()前面
        initViewActionBar();
        // 上面的initViewXxx()方法一定要在initView()前面
        initView();
        initData();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (flContainer == null) {
            //出现异常，重启应用
            ActivityUtils.finishAllActivities();
            ActivityUtils.startLauncherActivity();
            return;
        }
        childView = getLayoutInflater().inflate(layoutResID, null);
        flContainer.addView(childView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 注册网络监听
        networkStatusChangedListener = new NetworkUtils.OnNetworkStatusChangedListener() {
            @Override
            public void onDisconnected() {
                processNetworkChange(false);
            }

            @Override
            public void onConnected(NetworkUtils.NetworkType networkType) {
                tvNoNetwork.setVisibility(View.GONE);
            }
        };
        NetworkUtils.registerNetworkStatusChangedListener(networkStatusChangedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 启动页面获取当前网络状态
        processNetworkChange(NetworkUtils.isConnected());
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 注销网络监听
        NetworkUtils.unregisterNetworkStatusChangedListener(networkStatusChangedListener);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onError(String errorMsg) {
        hideLoading();
        ToastUtils.showShort(errorMsg);
    }

    /**
     * 显示圆形进度条，若要自定义属性，使用customLoading.xxx()
     */
    @Override
    public void showLoading() {
        if (firstShowLoading) {
            initLoading();
            firstShowLoading = false;
        }
        customLoading.setVisibility(View.VISIBLE);
        customLoading.startAnimation();
    }

    /**
     * 隐藏圆形进度条
     */
    @Override
    public void hideLoading() {
        customLoading.stopAnimation();
        customLoading.setVisibility(View.GONE);
    }

    /********************************************************* protected *********************************************************/

    /**
     * 启动其他Activity
     *
     * @param clz 其他Activity
     */
    protected void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 启动其他Activity，携带数据
     *
     * @param clz    其他Activity
     * @param bundle 携带的数据
     */
    protected void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 启动其他Activity，并在 onActivityResult() 监听结果
     *
     * @param cls         其他Activity
     * @param requestCode 请求码
     */
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 启动其他Activity，携带数据，并在 onActivityResult() 监听结果
     *
     * @param cls         其他Activity
     * @param bundle      携带的数据
     * @param requestCode 请求码
     */
    protected void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 是否开启网络状态检查及提醒，默认开启
     *
     * @param isCheckNetwork true开启，flase关闭，默认开启
     */
    protected void setCheckNetwork(boolean isCheckNetwork) {
        this.isCheckNetWork = isCheckNetwork;
        if (!isCheckNetwork) {
            //关闭网络监听，隐藏网络变化提示View
            tvNoNetwork.setVisibility(View.GONE);
        }
    }

    /**
     * 显示空页面，若要修改提示文字和图片，使用customEmptyView.xxx()
     */
    protected void showEmptyView() {
        if (firstShowEmpty) {
            initViewEmpty();
            firstShowEmpty = false;
        }
        customEmptyView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏空页面
     */
    protected void hideEmptyView() {
        customEmptyView.setVisibility(View.GONE);
    }

    protected void showToastShort(@StringRes int stringRes) {
        ToastUtils.showShort(stringRes);
    }

    protected void showToastShort(String msg) {
        ToastUtils.showShort(msg);
    }

    /**
     * 自定义当前弹出toast的样式，结束后恢复原样式
     *
     * @param toastViewStyle toast的样式
     * @param stringRes      toast的消息
     */
    protected void showToastShort(ToastViewStyle toastViewStyle, @StringRes int stringRes) {
        //当前页面自定义Toast样式
        updateToastViewStyle(toastViewStyle);
        showToastShort(stringRes);
        //恢复Toast默认样式
        updateToastViewStyle(BaseLibHelper.newInstance().getToastViewStyle());
    }

    protected void showToastLong(@StringRes int stringRes) {
        ToastUtils.showLong(stringRes);
    }

    protected void showToastLong(String msg) {
        ToastUtils.showLong(msg);
    }

    /**
     * 自定义当前弹出toast的样式，结束后恢复原样式
     *
     * @param toastViewStyle toast的样式
     * @param stringRes      toast的消息
     */
    protected void showToastLong(ToastViewStyle toastViewStyle, @StringRes int stringRes) {
        //当前页面自定义Toast样式
        updateToastViewStyle(toastViewStyle);
        showToastLong(stringRes);
        //恢复Toast默认样式
        updateToastViewStyle(BaseLibHelper.newInstance().getToastViewStyle());
    }

    protected View getChildView() {
        return childView;
    }

    /**
     * 空页面点击监听，子Activity可选择重写onEmptyPageClick()方法，以实现诸如“点击空页面重新查询数据”的功能
     */
    protected void onEmptyPageClick() {
    }

    /********************************************************* private *********************************************************/

    /**
     * 初始化无网络提示View的位置和样式
     */
    private void initViewNoNetwork() {
        //根据ActionBar的显示状态，确定无网络提示View的位置
        if (customActionBar.getVisibility() == View.GONE) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tvNoNetwork.getLayoutParams();
            lp.topMargin = BarUtils.getStatusBarHeight();
            tvNoNetwork.setLayoutParams(lp);
        }
        //初始化样式
        NetworkViewStyle networkViewStyle = BaseLibHelper.newInstance().getNetworkViewStyle();
        if (networkViewStyle != null) {
            if (networkViewStyle.getTextColor() != 0) {
                tvNoNetwork.setTextColor(networkViewStyle.getTextColor());
            }
            if (networkViewStyle.getTextSize() != 0) {
                tvNoNetwork.setTextSize(networkViewStyle.getTextSize());
            }
            if (networkViewStyle.getBgColor() != 0) {
                tvNoNetwork.setBackgroundColor(networkViewStyle.getBgColor());
            }
            if (networkViewStyle.getBgImg() != 0) {
                tvNoNetwork.setBackgroundResource(networkViewStyle.getBgImg());
            }
        }
    }

    /**
     * 初始化ActionBar
     */
    private void initViewActionBar() {
        //初始化样式
        ActionBarViewStyle actionBarViewStyle = BaseLibHelper.newInstance().getActionBarViewStyle();
        if (actionBarViewStyle != null) {
            //customActionBar
            if (actionBarViewStyle.getActionBarBgColor() != 0) {
                customActionBar.setActionBarBgColor(actionBarViewStyle.getActionBarBgColor());
            }
            if (actionBarViewStyle.getActionBarBgResource() != 0) {
                customActionBar.setActionBarBgResource(actionBarViewStyle.getActionBarBgResource());
            }
            //CenterTitle
            if (actionBarViewStyle.getCenterTitleTextSize() != 0) {
                customActionBar.setCenterTitleTextSize(actionBarViewStyle.getCenterTitleTextSize());
            }
            if (actionBarViewStyle.getCenterTitleTextColor() != 0) {
                customActionBar.setCenterTitleTextColor(actionBarViewStyle.getCenterTitleTextColor());
            }
            //Left
            customActionBar.setLeftVisiable(actionBarViewStyle.isLeftVisiable());
            //LeftText
            customActionBar.setLeftTextVisiable(actionBarViewStyle.isLeftTextVisiable());
            if (actionBarViewStyle.getLeftTextSize() != 0) {
                customActionBar.setLeftTextSize(actionBarViewStyle.getLeftTextSize());
            }
            if (actionBarViewStyle.getLeftTextColor() != 0) {
                customActionBar.setLeftTextColor(actionBarViewStyle.getLeftTextColor());
            }
            //LeftIcon
            customActionBar.setLeftIconVisiable(actionBarViewStyle.isLeftIconVisiable());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (actionBarViewStyle.getLeftIconColor() != 0) {
                    customActionBar.setLeftIconColor(actionBarViewStyle.getLeftIconColor());
                }
            }
            if (actionBarViewStyle.getLeftIconResource() != 0) {
                customActionBar.setLeftIconResource(actionBarViewStyle.getLeftIconResource());
            }
            //Right
            customActionBar.setRightVisiable(actionBarViewStyle.isRightVisiable());
            //RightText
            customActionBar.setRightTextVisiable(actionBarViewStyle.isRightTextVisiable());
            if (actionBarViewStyle.getRightTextSize() != 0) {
                customActionBar.setRightTextSize(actionBarViewStyle.getRightTextSize());
            }
            if (actionBarViewStyle.getRightTextColor() != 0) {
                customActionBar.setRightTextColor(actionBarViewStyle.getRightTextColor());
            }
            //RightIcon
            customActionBar.setRightIconVisiable(actionBarViewStyle.isRightIconVisiable());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (actionBarViewStyle.getRightIconColor() != 0) {
                    customActionBar.setRightIconColor(actionBarViewStyle.getRightIconColor());
                }
            }
            if (actionBarViewStyle.getRightIconResource() != 0) {
                customActionBar.setRightIconResource(actionBarViewStyle.getRightIconResource());
            }

        }
    }

    /**
     * 初始化EmptyView
     */
    private void initViewEmpty() {
        //初始化样式
        EmptyViewStyle emptyViewStyle = BaseLibHelper.newInstance().getEmptyViewStyle();
        if (emptyViewStyle != null) {
            //文字
            if (emptyViewStyle.getText() != 0) {
                customEmptyView.setText(emptyViewStyle.getText());
            }
            if (emptyViewStyle.getTextSize() != 0) {
                customEmptyView.setTextSize(emptyViewStyle.getTextSize());
            }
            if (emptyViewStyle.getTextColor() != 0) {
                customEmptyView.setTextColor(emptyViewStyle.getTextColor());
            }
            //图标
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (emptyViewStyle.getIconColor() != 0) {
                    customEmptyView.setIconColor(emptyViewStyle.getIconColor());
                }
            }
            if (emptyViewStyle.getIconResource() != 0) {
                customEmptyView.setIconResource(emptyViewStyle.getIconResource());
            }
        }
    }

    /**
     * 初始化LoadingView
     */
    private void initLoading() {
        //初始化样式
        LoadingViewStyle loadingViewStyle = BaseLibHelper.newInstance().getLoadingViewStyle();
        if (loadingViewStyle != null) {
            //图标
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (loadingViewStyle.getIconColor() != 0) {
                    customLoading.setIconColor(loadingViewStyle.getIconColor());
                }
            }
            if (loadingViewStyle.getIconResource() != 0) {
                customLoading.setIconResource(loadingViewStyle.getIconResource());
            }
        }
    }

    public void updateToastViewStyle(ToastViewStyle toastViewStyle) {
        if (toastViewStyle != null) {
            //文字
            if (toastViewStyle.getMsgTextSize() != 0) {
                ToastUtils.setMsgTextSize(toastViewStyle.getMsgTextSize());
            }
            if (toastViewStyle.getMsgTextColor() != 0) {
                ToastUtils.setMsgColor(toastViewStyle.getMsgTextColor());
            }
            //背景
            if (toastViewStyle.getBgColor() != 0) {
                ToastUtils.setBgColor(toastViewStyle.getBgColor());
            }
            if (toastViewStyle.getBgResource() != 0) {
                ToastUtils.setBgResource(toastViewStyle.getBgResource());
            }
            //位置
            ToastViewStyle.CustomGravity customGravity = toastViewStyle.getCustomGravity();
            if (customGravity != null) {
                customGravity.setGravity(customGravity.getGravity() == 0 ? -1 : customGravity.getGravity());
                customGravity.setxOffset(customGravity.getxOffset() == 0 ? -1 : customGravity.getxOffset());
                customGravity.setyOffset(customGravity.getyOffset() == 0 ? -1 : customGravity.getyOffset());
                ToastUtils.setGravity(customGravity.getGravity(), customGravity.getxOffset(), customGravity.getyOffset());
            }
        }
    }

    /**
     * 处理网络状态变化
     *
     * @param isConnected 是否在连接状态
     */
    private void processNetworkChange(boolean isConnected) {
        if (isCheckNetWork && !isConnected) {
            if (firstShowNoNet) {
                initViewNoNetwork();
                firstShowNoNet = false;
            }
            tvNoNetwork.setVisibility(View.VISIBLE);
        }
    }

}
