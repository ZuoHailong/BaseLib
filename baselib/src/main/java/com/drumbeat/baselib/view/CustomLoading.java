package com.drumbeat.baselib.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.drumbeat.baselib.R;

/**
 * 自定义圆形进度条
 * Created by ZuoHailong on 2019/7/30.
 */
public class CustomLoading {

    private Dialog mLoadingDialog;
    private Context context;
    private ConstraintLayout layoutContainer;
    private ImageView ivLoading;

    private RotateAnimation rotateAnimation;
    private boolean cancelable = false;
    private boolean isShow;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomLoading setIconColor(@ColorRes int colorRes) {
        Drawable drawable = ivLoading.getDrawable();
        drawable.setTint(ContextCompat.getColor(context, colorRes));
        return this;
    }

    public CustomLoading setIconResource(@DrawableRes int drawableRes) {
        ivLoading.setImageResource(drawableRes);
        return this;
    }

    public CustomLoading setBiasVertical(float biasVertical) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ivLoading.getLayoutParams();
        layoutParams.verticalBias = biasVertical;
        ivLoading.setLayoutParams(layoutParams);
        return this;
    }

    /**
     * 开始动画
     */
    public void startAnimation() {
        if (rotateAnimation != null) {
            ivLoading.clearAnimation();
            ivLoading.setAnimation(rotateAnimation);
        }
    }

    /**
     * 停止动画
     */
    public void stopAnimation() {
        if (rotateAnimation != null) {
            ivLoading.clearAnimation();
        }
    }

    /**
     * 设置动画速度，包含匀速动画、加速动画、减速动画等，默认加速动画
     *
     * @param interpolator android.view.animation.Interpolator接口的子类
     */
    public void setInterpolator(@NonNull Interpolator interpolator) {
        rotateAnimation.setInterpolator(interpolator);
    }

    /**
     * 设置动画每执行一次的时长，默认1000毫秒
     *
     * @param durationMillis 时长，毫秒
     */
    public void setDuration(long durationMillis) {
        if (durationMillis > 0) {
            rotateAnimation.setDuration(durationMillis);
        }
    }

    /**
     * 是否可取消
     */
    public CustomLoading setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public void show(){
        mLoadingDialog = new Dialog(context, R.style.baselib_LoadingDialog);
        // 设置返回键无效
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setContentView(layoutContainer, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
        isShow = true;
    }

    public void dismiss() {
        if (mLoadingDialog != null && isShow) {
            ivLoading.clearAnimation();
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
            isShow = false;
        }
    }

    public boolean isShowing() {
        return isShow;
    }

    public CustomLoading(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        View customLoading = View.inflate(context, R.layout.baselib_view_custom_loading, null);

        ivLoading = customLoading.findViewById(R.id.ivLoading);
        layoutContainer = customLoading.findViewById(R.id.layoutContainer);

        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(-1);
        //匀速动画LinearInterpolator，加速动画AccelerateInterpolator，减速动画DecelerateInterpolator
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setDuration(700);
        rotateAnimation.setStartOffset(10);

    }
}