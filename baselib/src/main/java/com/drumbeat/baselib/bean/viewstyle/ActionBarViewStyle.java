package com.drumbeat.baselib.bean.viewstyle;

import android.os.Build;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;

/**
 * ActionBar的样式，用于ActionBar初始化
 * Created by ZuoHailong on 2019/7/26.
 */
public class ActionBarViewStyle {

    //customActionBar：背景色
    private int actionBarBgColor;
    //customActionBar：背景图资源ID
    private int actionBarBgResource;

    // 标题：文字大小，单位 sp
    private int centerTitleTextSize;
    // 标题：文字颜色
    private int centerTitleTextColor;

    // 左侧文字：是否显示
    private boolean leftTextVisiable = true;
    // 左侧文字：文字颜色
    private int leftTextColor;
    // 左侧文字：文字大小，单位 sp
    private int leftTextSize;

    // 左侧图标：是否显示
    private boolean leftIconVisiable = true;
    // 左侧图标：图标颜色
    private int leftIconColor;
    // 左侧图标：图标资源ID
    private int leftIconResource;

    // 右侧文字：是否显示
    private boolean rightTextVisiable = true;
    // 右侧文字：文字颜色
    private int rightTextColor;
    // 右侧文字：文字大小，单位 sp
    private int rightTextSize;

    // 右侧图标：是否显示
    private boolean rightIconVisiable = true;
    // 右侧图标：图标颜色
    private int rightIconColor;
    // 右侧图标：图标资源ID
    private int rightIconResource;

    /**
     * customActionBar：背景色
     *
     * @return colorId
     */
    public int getActionBarBgColor() {
        return actionBarBgColor;
    }

    /**
     * customActionBar：背景色
     *
     * @param actionBarBgColor colorId
     * @return ActionBarViewStyle.this
     */
    public ActionBarViewStyle setActionBarBgColor(@ColorRes int actionBarBgColor) {
        this.actionBarBgColor = actionBarBgColor;
        return this;
    }

    /**
     * customActionBar：背景图资源ID
     *
     * @return 图片资源ID
     */
    public int getActionBarBgResource() {
        return actionBarBgResource;
    }

    /**
     * customActionBar：背景图资源ID
     *
     * @param actionBarBgResource 图片资源ID
     * @return ActionBarViewStyle.this
     */
    public ActionBarViewStyle setActionBarBgResource(@DrawableRes int actionBarBgResource) {
        this.actionBarBgResource = actionBarBgResource;
        return this;
    }

    /**
     * 标题：文字大小，单位 sp
     *
     * @return 文字大小，单位 sp
     */
    public int getCenterTitleTextSize() {
        return centerTitleTextSize;
    }

    /**
     * 标题：文字大小，单位 sp
     *
     * @param centerTitleTextSize 文字大小，单位 sp
     * @return ActionBarViewStyle.this
     */
    public ActionBarViewStyle setCenterTitleTextSize(int centerTitleTextSize) {
        this.centerTitleTextSize = centerTitleTextSize;
        return this;
    }

    /**
     * 标题：文字颜色
     *
     * @return colorId
     */
    public int getCenterTitleTextColor() {
        return centerTitleTextColor;
    }

    /**
     * 标题：文字颜色
     *
     * @param centerTitleTextColor colorId
     * @return ActionBarViewStyle.this
     */
    public ActionBarViewStyle setCenterTitleTextColor(@ColorRes int centerTitleTextColor) {
        this.centerTitleTextColor = centerTitleTextColor;
        return this;
    }

    /**
     * 左侧文字：是否显示
     *
     * @return 是否显示
     */
    public boolean isLeftTextVisiable() {
        return leftTextVisiable;
    }

    /**
     * 左侧文字：是否显示
     *
     * @param leftTextVisiable
     * @return ActionBarViewStyle.this
     */
    public ActionBarViewStyle setLeftTextVisiable(boolean leftTextVisiable) {
        this.leftTextVisiable = leftTextVisiable;
        return this;
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public ActionBarViewStyle setLeftTextColor(@ColorRes int leftTextColor) {
        this.leftTextColor = leftTextColor;
        return this;
    }

    public int getLeftTextSize() {
        return leftTextSize;
    }

    public ActionBarViewStyle setLeftTextSize(int leftTextSize) {
        this.leftTextSize = leftTextSize;
        return this;
    }

    public boolean isLeftIconVisiable() {
        return leftIconVisiable;
    }

    public ActionBarViewStyle setLeftIconVisiable(boolean leftIconVisiable) {
        this.leftIconVisiable = leftIconVisiable;
        return this;
    }

    public int getLeftIconColor() {
        return leftIconColor;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ActionBarViewStyle setLeftIconColor(@ColorRes int leftIconColor) {
        this.leftIconColor = leftIconColor;
        return this;
    }

    public int getLeftIconResource() {
        return leftIconResource;
    }

    public ActionBarViewStyle setLeftIconResource(@DrawableRes int leftIconResource) {
        this.leftIconResource = leftIconResource;
        return this;
    }

    public boolean isRightTextVisiable() {
        return rightTextVisiable;
    }

    public ActionBarViewStyle setRightTextVisiable(boolean rightTextVisiable) {
        this.rightTextVisiable = rightTextVisiable;
        return this;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public ActionBarViewStyle setRightTextColor(@ColorRes int rightTextColor) {
        this.rightTextColor = rightTextColor;
        return this;
    }

    public int getRightTextSize() {
        return rightTextSize;
    }

    public ActionBarViewStyle setRightTextSize(int rightTextSize) {
        this.rightTextSize = rightTextSize;
        return this;
    }

    public boolean isRightIconVisiable() {
        return rightIconVisiable;
    }

    public ActionBarViewStyle setRightIconVisiable(boolean rightIconVisiable) {
        this.rightIconVisiable = rightIconVisiable;
        return this;
    }

    public int getRightIconColor() {
        return rightIconColor;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ActionBarViewStyle setRightIconColor(@ColorRes int rightIconColor) {
        this.rightIconColor = rightIconColor;
        return this;
    }

    public int getRightIconResource() {
        return rightIconResource;
    }

    public ActionBarViewStyle setRightIconResource(@DrawableRes int rightIconResource) {
        this.rightIconResource = rightIconResource;
        return this;
    }
}
