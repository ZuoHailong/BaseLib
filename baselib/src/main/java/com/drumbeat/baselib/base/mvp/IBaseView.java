package com.drumbeat.baselib.base.mvp;

import android.content.Context;

/**
 * Created by ZuoHailong on 2019/7/24.
 */
public interface IBaseView {

    /**********************************************抽象子基类实现下述方法**********************************************/

    /**
     * 上下文
     *
     * @return context
     */
    Context getContext();

    void onError(String errorMsg);

    void showLoading();

    void hideLoading();

    /**
     * 空页面点击监听，子Activity可选择重写onEmptyPageClick()方法，以实现诸如“点击空页面重新查询数据”的功能
     */
    void onEmptyPageClick();

    /**********************************************非抽象子类实现下述方法**********************************************/

    void initView();

    void initData();
}
