package com.drumbeat.baselib.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.drumbeat.baselib.R;

/**
 * 自定义ActionBar，包含statusBar
 * Created by ZuoHailong on 2019/7/24.
 */
public class CustomActionBar extends RelativeLayout {
    /**
     * 类型标识：标题文本
     */
    public static final int ACTION_BAR_TYPE_TITLE = 100;
    /**
     * 类型标识：搜索输入框
     */
    public static final int ACTION_BAR_TYPE_INPUT = 101;

    /**
     * titleBar 高度常量值：50dp
     * titleBar = customActionBar - statusBar
     */
    private static final float TITLE_BAR_HEIGHT = 50F;

    private Context context;
    private View customActionBar;

    private View viewStatusBar;
    private RelativeLayout rlTitleBar;
    private LinearLayout llRootView;
    private ConstraintLayout clLeft;
    private AppCompatImageView ivLeft;
    private TextView tvLeft;
    private ConstraintLayout clRight;
    private AppCompatImageView ivRight;
    private TextView tvRight;
    // 标题文本
    private TextView tvCenter;
    // 标题输入框
    private EditText etCenter;

    /**************************************************** statusBar ****************************************************/

    /**
     * statusBar：是否开启 Light Mode
     *
     * @param isLightMode true开启Light Mode，状态栏中文字变为深色；false关闭Light Mode，状态栏中文字变为浅色。
     * @return CustomActionBar
     */
    public CustomActionBar setStatusBarLightMode(boolean isLightMode) {
        BarUtils.setStatusBarLightMode((Activity) context, isLightMode);
        return this;
    }

    /**************************************************** customActionBar ****************************************************/
    @Override
    public void setBackgroundColor(int color) {
        llRootView.setBackgroundColor(color);
    }

    @Override
    public void setBackgroundResource(int resid) {
        llRootView.setBackgroundResource(resid);
    }

    /**
     * 设置ActionBar中间显示标题文本/搜索输入框，默认显示标题文本
     *
     * @param type 类型：CustomActionBar.ACTION_BAR_TYPE_TITLE 显示标题文本；CustomActionBar.ACTION_BAR_TYPE_INPUT 显示搜索输入框。
     * @return CustomActionBar
     */
    public CustomActionBar setActionBarType(int type) {
        if (type == ACTION_BAR_TYPE_INPUT) {
            tvCenter.setVisibility(GONE);
            etCenter.setVisibility(VISIBLE);
        } else {
            tvCenter.setVisibility(VISIBLE);
            etCenter.setVisibility(GONE);
        }
        return this;
    }

    /**
     * customActionBar：背景颜色
     *
     * @param colorRes
     * @return CustomActionBar
     */
    public CustomActionBar setActionBarBgColor(@ColorRes int colorRes) {
        llRootView.setBackgroundColor(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * customActionBar：背景图片
     *
     * @param drawableRes
     * @return CustomActionBar
     */
    public CustomActionBar setActionBarBgResource(@DrawableRes int drawableRes) {
        llRootView.setBackgroundResource(drawableRes);
        return this;
    }

    /**
     * customActionBar：自定义ActionBar（整体替换）
     *
     * @param view
     * @return CustomActionBar
     */
    public CustomActionBar setActionBarView(View view) {
        llRootView.removeAllViews();
        llRootView.addView(view);
        return this;
    }

    /**************************************************** TitleBar ****************************************************/

    /**
     * TitleBar：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setTitleBarVisiable(boolean isVisiable) {
        rlTitleBar.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**************************************************** 左侧 ****************************************************/
    /**
     * 左侧layout：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setLeftVisiable(boolean isVisiable) {
        clLeft.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**
     * 自定义左侧View
     *
     * @param view 左侧View
     * @return
     */
    public CustomActionBar setLeftView(View view) {
        clLeft.removeAllViews();
        clLeft.addView(view);
        return this;
    }

    /**
     * 左侧layout：点击监听
     *
     * @param clickListener
     * @return CustomActionBar
     */
    public CustomActionBar setLeftClickListener(View.OnClickListener clickListener) {
        clLeft.setOnClickListener(clickListener);
        return this;
    }

    /**
     * 左侧文本：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setLeftTextVisiable(boolean isVisiable) {
        tvLeft.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**
     * 左侧文本：文字内容
     *
     * @param text
     * @return CustomActionBar
     */
    public CustomActionBar setLeftText(String text) {
        tvLeft.setText(text);
        return this;
    }

    /**
     * 左侧文本：文字颜色
     *
     * @param colorRes
     * @return CustomActionBar
     */
    public CustomActionBar setLeftTextColor(@ColorRes int colorRes) {
        tvLeft.setTextColor(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * 左侧文本：文字大小
     *
     * @param size 字体单位 sp
     * @return CustomActionBar
     */
    public CustomActionBar setLeftTextSize(float size) {
        tvLeft.setTextSize(size);
        return this;
    }

    /**
     * 左侧图标：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setLeftIconVisiable(boolean isVisiable) {
        ivLeft.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**
     * 左侧图标：设置颜色，此方法要求系统版本 >= Android L（Android 5.0）
     *
     * @param colorRes
     * @return CustomActionBar
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomActionBar setLeftIconColor(@ColorRes int colorRes) {
        Drawable drawable = ivLeft.getDrawable();
        drawable.setTint(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * 左侧图标：更换图标
     *
     * @param drawableRes
     * @return CustomActionBar
     */
    public CustomActionBar setLeftIconResource(@DrawableRes int drawableRes) {
        ivLeft.setImageResource(drawableRes);
        return this;
    }

    /**
     * 左侧图标：获取 ImageView
     *
     * @return
     */
    public ImageView getLeftIconView() {
        return ivLeft;
    }

    /**************************************************** 右侧 ****************************************************/
    /**
     * 右侧layout：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setRightVisiable(boolean isVisiable) {
        clRight.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**
     * 自定义右侧View
     *
     * @param view 右侧View
     * @return
     */
    public CustomActionBar setRightView(View view) {
        clRight.removeAllViews();
        clRight.addView(view);
        return this;
    }

    /**
     * 右侧点击监听
     *
     * @param clickListener
     * @return CustomActionBar
     */
    public CustomActionBar setRightClickListener(View.OnClickListener clickListener) {
        clRight.setOnClickListener(clickListener);
        return this;
    }

    /**
     * 右侧文本：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setRightTextVisiable(boolean isVisiable) {
        tvRight.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**
     * 右侧文本：文字内容
     *
     * @param text
     * @return CustomActionBar
     */
    public CustomActionBar setRightText(String text) {
        tvRight.setText(text);
        return this;
    }

    /**
     * 右侧文本：文字颜色
     *
     * @param colorRes
     * @return CustomActionBar
     */
    public CustomActionBar setRightTextColor(@ColorRes int colorRes) {
        tvRight.setTextColor(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * 右侧文本：文字大小
     *
     * @param size 单位 sp
     * @return CustomActionBar
     */
    public CustomActionBar setRightTextSize(float size) {
        tvRight.setTextSize(size);
        return this;
    }

    /**
     * 右侧图标：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setRightIconVisiable(boolean isVisiable) {
        ivRight.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**
     * 右侧图标：设置颜色，此方法要求系统版本 >= Android L（Android 5.0）
     *
     * @param colorRes
     * @return CustomActionBar
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomActionBar setRightIconColor(@ColorRes int colorRes) {
        Drawable drawable = ivRight.getDrawable();
        drawable.setTint(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * 右侧图标：更换图标
     *
     * @param drawableRes
     * @return CustomActionBar
     */
    public CustomActionBar setRightIconResource(@DrawableRes int drawableRes) {
        ivRight.setImageResource(drawableRes);
        return this;
    }

    /**
     * 右侧图标：获取 ImageView
     *
     * @return
     */
    public ImageView getRightIconView() {
        return ivRight;
    }


    /**************************************************** 中间文本 ****************************************************/

    /**
     * 中间文本：文字内容
     *
     * @param text
     * @return CustomActionBar
     */
    public CustomActionBar setCenterTitleText(String text) {
        tvCenter.setText(text);
        return this;
    }

    /**
     * 中间文本：文字颜色
     *
     * @param colorRes
     * @return CustomActionBar
     */
    public CustomActionBar setCenterTitleTextColor(@ColorRes int colorRes) {
        tvCenter.setTextColor(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * 中间文本：文字大小
     * 当 >= Android O（Android 8.0），支持autoSize，16sp < autoSize < 20sp
     *
     * @param size 单位 sp
     * @return CustomActionBar
     */
    public CustomActionBar setCenterTitleTextSize(float size) {
        tvCenter.setTextSize(size);
        return this;
    }

    /**
     * 中间文本：获取 TextView
     *
     * @return
     */
    public TextView getCenterTitleTextView() {
        return tvCenter;
    }

    /**************************************************** 中间输入框 ****************************************************/
    /**
     * 中间输入框：是否显示
     *
     * @param isVisiable
     * @return CustomActionBar
     */
    public CustomActionBar setCenterInputVisiable(boolean isVisiable) {
        etCenter.setVisibility(isVisiable ? VISIBLE : GONE);
        return this;
    }

    /**
     * 中间输入框：文字大小
     *
     * @param size 单位 sp
     * @return CustomActionBar
     */
    public CustomActionBar setCenterInputTextSize(float size) {
        etCenter.setTextSize(size);
        return this;
    }

    /**
     * 中间输入框：文字颜色
     *
     * @param colorRes
     * @return CustomActionBar
     */
    public CustomActionBar setCenterInputTextColor(@ColorRes int colorRes) {
        etCenter.setTextColor(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * 中间输入框：hint文字内容
     *
     * @param text
     * @return CustomActionBar
     */
    public CustomActionBar setCenterInputHintText(String text) {
        etCenter.setHint(text);
        return this;
    }

    /**
     * 中间输入框：hint文字颜色
     *
     * @param colorRes
     * @return CustomActionBar
     */
    public CustomActionBar setCenterInputHintTextColor(@ColorRes int colorRes) {
        etCenter.setHintTextColor(ContextCompat.getColor(context, colorRes));
        return this;
    }

    /**
     * 中间输入框：获取 EditText
     *
     * @return
     */
    public EditText getCenterEditTextView() {
        return etCenter;
    }

    /**
     * 中间输入框：获取输入内容
     *
     * @return CustomActionBar
     */
    public String getCenterInputText() {
        return etCenter.getEditableText().toString().trim();
    }

    /**
     * 中间输入框：内容变化监听
     *
     * @param textWatcher
     * @return CustomActionBar
     */
    public CustomActionBar setCenterInputTextChangedListener(TextWatcher textWatcher) {
        etCenter.addTextChangedListener(textWatcher);
        return this;
    }

    public CustomActionBar(Context context) {
        this(context, null);
    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        customActionBar = View.inflate(context, R.layout.baselib_view_custom_actionbar, this);

        rlTitleBar = customActionBar.findViewById(R.id.rlTitleBar);
        clLeft = customActionBar.findViewById(R.id.clLeft);
        ivLeft = customActionBar.findViewById(R.id.ivLeft);
        tvLeft = customActionBar.findViewById(R.id.tvLeft);
        clRight = customActionBar.findViewById(R.id.clRight);
        ivRight = customActionBar.findViewById(R.id.ivRight);
        tvRight = customActionBar.findViewById(R.id.tvRight);
        tvCenter = customActionBar.findViewById(R.id.tvCenter);
        etCenter = customActionBar.findViewById(R.id.etCenter);
        llRootView = customActionBar.findViewById(R.id.llRootView);
        //确定statusBar高度
        viewStatusBar = customActionBar.findViewById(R.id.viewStatusBar);
        LinearLayout.LayoutParams layoutParamsStatusBar = (LinearLayout.LayoutParams) viewStatusBar.getLayoutParams();
        layoutParamsStatusBar.height = BarUtils.getStatusBarHeight();
        viewStatusBar.setLayoutParams(layoutParamsStatusBar);

        //左侧默认的点击监听
        clLeft.setOnClickListener(view -> ((Activity) context).finish());
    }


}
