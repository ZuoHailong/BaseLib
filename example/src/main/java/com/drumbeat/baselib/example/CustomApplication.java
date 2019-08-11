package com.drumbeat.baselib.example;

import android.app.Application;

import com.drumbeat.baselib.helper.BaseLibHelper;

/**
 * Created by ZuoHailong on 2019/7/25.
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseLibHelper.newInstance().init(this);
    }
}
