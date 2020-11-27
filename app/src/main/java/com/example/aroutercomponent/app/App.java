package com.example.aroutercomponent.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.aroutercomponent.BuildConfig;
import com.example.baselib.app.BaseApp;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/26 9:28 PM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();

//        initARouter();
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
    }
}
