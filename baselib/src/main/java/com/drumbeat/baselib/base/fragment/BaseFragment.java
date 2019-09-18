package com.drumbeat.baselib.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

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

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * Created by ZuoHailong on 2019/7/31.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    private Unbinder unBinder;
    private Context context;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.baselib_activity_base, container, false);
        customActionBar = rootView.findViewById(R.id.customActionBar);
        customEmptyView = rootView.findViewById(R.id.customEmptyView);
        //空页面点击监听，子Fragment可选择重写onEmptyPageClick()方法，以实现诸如“点击空页面重新查询数据”的功能
        customEmptyView.setOnClickListener(v -> onEmptyPageClick());
        flContainer = rootView.findViewById(R.id.flContainer);
        //将子Fragment加入布局中
        childView = inflater.inflate(getLayoutId(), null);
        flContainer.addView(childView);
        tvNoNetwork = rootView.findViewById(R.id.tvNoNetwork);
        customLoading = new CustomLoading(context);
        unBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //一定要在initView()前面
        initViewActionBar();
        // 上面的initViewXxx()方法一定要在initView()前面
        initView();
        initData();
    }


    @Override
    public void onStart() {
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
    public void onResume() {
        super.onResume();
        // 启动页面获取当前网络状态
        processNetworkChange(NetworkUtils.isConnected());
    }

    @Override
    public void onStop() {
        super.onStop();
        // 注销网络监听
        NetworkUtils.unregisterNetworkStatusChangedListener(networkStatusChangedListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
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
        customLoading.show();
    }

    /**
     * 隐藏圆形进度条
     */
    @Override
    public void hideLoading() {
        customLoading.dismiss();
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
        intent.setClass(getContext(), clz);
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
        intent.setClass(getContext(), cls);
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

    protected abstract int getLayoutId();

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
            if (loadingViewStyle.getBiasVertical() > 0) {
                customLoading.setBiasVertical(loadingViewStyle.getBiasVertical());
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
