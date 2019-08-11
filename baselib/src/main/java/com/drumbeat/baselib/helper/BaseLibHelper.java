package com.drumbeat.baselib.helper;

import android.app.Application;

import androidx.annotation.IntDef;

import com.blankj.utilcode.util.ToastUtils;
import com.drumbeat.baselib.bean.viewstyle.ActionBarViewStyle;
import com.drumbeat.baselib.bean.viewstyle.EmptyViewStyle;
import com.drumbeat.baselib.bean.viewstyle.LoadingViewStyle;
import com.drumbeat.baselib.bean.viewstyle.NetworkViewStyle;
import com.drumbeat.baselib.bean.viewstyle.ToastViewStyle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * BaseLib帮助类
 * Created by ZuoHailong on 2019/7/25.
 */
public class BaseLibHelper {

    private static BaseLibHelper baseLibHelper;

    /*
     * 各View样式
     * */
    private NetworkViewStyle networkViewStyle;
    private ActionBarViewStyle actionBarViewStyle;
    private EmptyViewStyle emptyViewStyle;
    private LoadingViewStyle loadingViewStyle;
    private ToastViewStyle toastViewStyle;

    /********************************************************* public *********************************************************/

    /**
     * 实例化BaseLibHelper，单例模式
     *
     * @return BaseLibHelper实例
     */
    public static BaseLibHelper newInstance() {
        if (baseLibHelper == null) {
            synchronized (BaseLibHelper.class) {
                if (baseLibHelper == null) {
                    baseLibHelper = new BaseLibHelper();
                }
            }
        }
        return baseLibHelper;
    }

    /**
     * 初始化BaseLib
     *
     * @param application Application
     */
    public void init(Application application) {

    }

    /**
     * 对NetworkState变化时在页面顶部显示的View的样式进行初始化
     *
     * @param networkViewStyle
     */
    public void initViewStyle(NetworkViewStyle networkViewStyle) {
        this.networkViewStyle = networkViewStyle;
    }

    public NetworkViewStyle getNetworkViewStyle() {
        return networkViewStyle;
    }

    /**
     * 对ActionBar的样式进行初始化
     *
     * @param actionBarViewStyle
     */
    public void initViewStyle(ActionBarViewStyle actionBarViewStyle) {
        this.actionBarViewStyle = actionBarViewStyle;
    }

    public ActionBarViewStyle getActionBarViewStyle() {
        return actionBarViewStyle;
    }

    /**
     * 对无数据View的样式进行初始化
     *
     * @param emptyViewStyle
     */
    public void initViewStyle(EmptyViewStyle emptyViewStyle) {
        this.emptyViewStyle = emptyViewStyle;
    }

    public EmptyViewStyle getEmptyViewStyle() {
        return emptyViewStyle;
    }

    /**
     * 对LoadingView的样式进行初始化
     *
     * @param loadingViewStyle
     */
    public void initViewStyle(LoadingViewStyle loadingViewStyle) {
        this.loadingViewStyle = loadingViewStyle;
    }

    public LoadingViewStyle getLoadingViewStyle() {
        return loadingViewStyle;
    }

    /**
     * 对 kjToast 的样式进行初始化，这个是全局统一设置的，默认样式是黑底白字，位于页面中下部
     *
     * @param toastViewStyle
     */
    public void initViewStyle(ToastViewStyle toastViewStyle) {
        /*
         * 如果开发者未初始化，设定Toast缺省样式
         * */
        final int COLOR_DEFAULT = 0xFEFFFFFF;
        if (toastViewStyle == null) {
            toastViewStyle = new ToastViewStyle();
        }
        ToastViewStyle.CustomGravity customGravity = toastViewStyle.getCustomGravity();
        customGravity.setGravity(customGravity.getGravity() == 0 ? -1 : customGravity.getGravity());
        customGravity.setxOffset(customGravity.getxOffset() == 0 ? -1 : customGravity.getxOffset());
        customGravity.setyOffset(customGravity.getyOffset() == 0 ? -1 : customGravity.getyOffset());
        toastViewStyle.setCustomGravity(customGravity.getGravity(), customGravity.getxOffset(), customGravity.getyOffset());
        toastViewStyle.setBgColor(toastViewStyle.getBgColor() == 0 ? COLOR_DEFAULT : toastViewStyle.getBgColor());
        toastViewStyle.setBgResource(toastViewStyle.getBgResource() == 0 ? -1 : toastViewStyle.getBgResource());
        toastViewStyle.setMsgTextColor(toastViewStyle.getMsgTextColor() == 0 ? COLOR_DEFAULT : toastViewStyle.getMsgTextColor());
        toastViewStyle.setMsgTextSize(toastViewStyle.getMsgTextSize() == 0 ? -1 : toastViewStyle.getMsgTextSize());
        /*
         * 初始化Toast样式
         * */
        //文字
        ToastUtils.setMsgTextSize(toastViewStyle.getMsgTextSize());
        ToastUtils.setMsgColor(toastViewStyle.getMsgTextColor());
        //背景
        ToastUtils.setBgColor(toastViewStyle.getBgColor());
        ToastUtils.setBgResource(toastViewStyle.getBgResource());
        //位置
        customGravity = toastViewStyle.getCustomGravity();
        ToastUtils.setGravity(customGravity.getGravity(), customGravity.getxOffset(), customGravity.getyOffset());
        //全局初始化的Toast样式
        this.toastViewStyle = toastViewStyle;
    }

    /**
     * 获得全局初始化的Toast样式
     *
     * @return 全局初始化的Toast样式
     */
    public ToastViewStyle getToastViewStyle() {
        return toastViewStyle;
    }

    /********************************************************* private *********************************************************/
    /**
     * 外部class不允许通过此构造实例化BaseLibHelper，可通过BaseLibHelper.newInstance()方法实例化
     */
    private BaseLibHelper() {
    }

}
