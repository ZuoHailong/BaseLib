package com.drumbeat.baselib.bean.viewstyle;

import android.os.Build;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;

/**
 * loadingView的样式
 * Created by ZuoHailong on 2019/7/29.
 */
public class LoadingViewStyle {
    // 图标颜色
    private int iconColor;
    // 图标资源ID
    private int iconResource;

    public int getIconColor() {
        return iconColor;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingViewStyle setIconColor(@ColorRes int colorRes) {
        this.iconColor = colorRes;
        return this;
    }

    public int getIconResource() {
        return iconResource;
    }

    public LoadingViewStyle setIconResource(@DrawableRes int drawableRes) {
        this.iconResource = drawableRes;
        return this;
    }
}
