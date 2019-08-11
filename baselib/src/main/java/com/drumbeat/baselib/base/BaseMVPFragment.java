package com.drumbeat.baselib.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.drumbeat.baselib.base.mvp.BasePresenter;

/**
 * 应用MVP模式的Fragment基类，继承于BaseFragment
 * Created by ZuoHailong on 2019/8/1.
 */
public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除绑定，避免内存泄露
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();
}
