package com.example.baselib.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselib.BuildConfig;
import com.example.baselib.utils.LogUitls;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/27 2:32 PM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
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

    /*

        Android Application中的onTerminate()方法不会被回调的问题

        https://blog.csdn.net/Sunxiaolin2016/article/details/97750696?utm_medium=distribute.pc_relevant_t0.none-task-blog-searchFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-searchFromBaidu-1.control

        即：此方法用于模拟过程环境。 永远不会在生产Android设备上调用它，只需杀死它们即可删除进程; 这样做时不会执行任何用户代码（包括此回调）。

        所以，该方法只在模拟器中回调，Android设备中永远不会调用该函数。

     */
    @Override
    public void onTerminate() {
        ARouter.getInstance().destroy();
        LogUitls.i(this," onTerminate");
        super.onTerminate();
    }

}
