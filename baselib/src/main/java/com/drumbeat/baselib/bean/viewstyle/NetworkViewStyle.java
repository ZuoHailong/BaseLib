package com.drumbeat.baselib.bean.viewstyle;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

/**
 * 无网络View的样式
 * Created by ZuoHailong on 2019/7/26.
 */
public class NetworkViewStyle {
    private int textSize;
    private int textColor;
    private int bgColor;
    private int bgImg;

    public int getTextSize() {
        return textSize;
    }

    public NetworkViewStyle setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public NetworkViewStyle setTextColor(@ColorRes int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getBgColor() {
        return bgColor;
    }

    public NetworkViewStyle setBgColor(@ColorRes int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public int getBgImg() {
        return bgImg;
    }

    public NetworkViewStyle setBgImg(@DrawableRes int bgImg) {
        this.bgImg = bgImg;
        return this;
    }
}
