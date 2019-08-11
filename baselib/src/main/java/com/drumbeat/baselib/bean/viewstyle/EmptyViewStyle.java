package com.drumbeat.baselib.bean.viewstyle;

import android.os.Build;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;

/**
 * 无数据View的样式
 * Created by ZuoHailong on 2019/7/29.
 */
public class EmptyViewStyle {
    // 无数据提示内容
    private int stringRes;
    // 文字大小，单位 sp
    private int textSize;
    // 文字颜色
    private int textColor;
    // 图标颜色
    private int iconColor;
    // 图标资源ID
    private int iconResource;
    // 背景颜色
    private int bgColor;
    // 背景资源ID
    private int bgResource;

    public int getText() {
        return stringRes;
    }

    public EmptyViewStyle setText(@StringRes int stringRes) {
        this.stringRes = stringRes;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public EmptyViewStyle setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public EmptyViewStyle setTextColor(@ColorRes int colorRes) {
        this.textColor = colorRes;
        return this;
    }

    public int getIconColor() {
        return iconColor;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EmptyViewStyle setIconColor(@ColorRes int colorRes) {
        this.iconColor = colorRes;
        return this;
    }

    public int getIconResource() {
        return iconResource;
    }

    public EmptyViewStyle setIconResource(@DrawableRes int drawableRes) {
        this.iconResource = drawableRes;
        return this;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getBgResource() {
        return bgResource;
    }

    public void setBgResource(int bgResource) {
        this.bgResource = bgResource;
    }
}
