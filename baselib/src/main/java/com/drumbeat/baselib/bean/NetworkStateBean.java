package com.drumbeat.baselib.bean;

/**
 * 网络状态实体，用于网络发生变化时传递消息
 * Created by ZuoHailong on 2019/7/25.
 */
public class NetworkStateBean {
    // 是否存在网络
    public boolean isConnected;

    public NetworkStateBean(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
