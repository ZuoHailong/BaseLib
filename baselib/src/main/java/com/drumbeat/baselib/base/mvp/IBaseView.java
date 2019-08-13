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

    /**********************************************非抽象子类实现下述方法**********************************************/

    void initView();

    void initData();
}
