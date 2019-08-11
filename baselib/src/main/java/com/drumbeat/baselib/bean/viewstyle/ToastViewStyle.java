package com.drumbeat.baselib.bean.viewstyle;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * kjToast 的样式
 * Created by ZuoHailong on 2019/7/30.
 */
public class ToastViewStyle {

    /**
     * 自定义注解，限定取值范围，默认值0（LENGTH_SHORT）
     */
    @IntDef({LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    private int bgColor;
    private int bgResource;
    private int msgTextSize;
    private int msgTextColor;
    private CustomGravity customGravity;

    public int getBgColor() {
        return bgColor;
    }

    public ToastViewStyle setBgColor(@ColorInt int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public int getBgResource() {
        return bgResource;
    }

    public ToastViewStyle setBgResource(int bgResource) {
        this.bgResource = bgResource;
        return this;
    }

    public int getMsgTextSize() {
        return msgTextSize;
    }

    public ToastViewStyle setMsgTextSize(int msgTextSize) {
        this.msgTextSize = msgTextSize;
        return this;
    }

    public int getMsgTextColor() {
        return msgTextColor;
    }

    public ToastViewStyle setMsgTextColor(@ColorInt int msgTextColor) {
        this.msgTextColor = msgTextColor;
        return this;
    }

    public CustomGravity getCustomGravity() {
        if (customGravity == null) {
            customGravity = new CustomGravity();
        }
        return customGravity;
    }

    public ToastViewStyle setCustomGravity(int gravity, int xOffset, int yOffset) {
        if (this.customGravity == null) {
            this.customGravity = new CustomGravity();
        }
        this.customGravity.setGravity(gravity).setxOffset(xOffset).setyOffset(yOffset);
        return this;
    }

    public class CustomGravity {
        private int gravity;
        private int xOffset;
        private int yOffset;

        public int getGravity() {
            return gravity;
        }

        /**
         * 设置Toast出现的位置
         *
         * @param gravity 取值于 android.view.CustomGravity
         * @return
         */
        public CustomGravity setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public int getxOffset() {
            return xOffset;
        }

        public CustomGravity setxOffset(int xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public int getyOffset() {
            return yOffset;
        }

        public CustomGravity setyOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }
    }
}
