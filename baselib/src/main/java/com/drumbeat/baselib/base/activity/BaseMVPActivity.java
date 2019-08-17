package com.drumbeat.baselib.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.drumbeat.baselib.base.mvp.BasePresenter;

/**
 * 应用MVP模式的Activity基类，继承于BaseActivity
 * Created by ZuoHailong on 2019/7/31.
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建present
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
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
