package com.example.baselib.utils;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselib.baseinterface.HomeExportService;
import com.example.baselib.cantact.BaseContacts;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/28 9:12 AM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */
public class HomeExportServiceUtils {

    /**
     * 打开首页
     *
     * @param text
     */
    public static void openHome(String text) {
        if (TextUtils.isEmpty(text)) {
            text = "从公共组件进入的";
        }

        ARouter.getInstance().build(BaseContacts.ROUTE_PATH_HOME).withString(BaseContacts.HOME_KEY, text).navigation();
    }


    /**
     * 打开首页
     *
     * @param text
     */
    public static void openHome(Context context, String text, NavigationCallback navigationCallback) {
        if (TextUtils.isEmpty(text)) {
            text = "从公共组件进入的";
        }

        ARouter.getInstance().build(BaseContacts.ROUTE_PATH_HOME).withString(BaseContacts.HOME_KEY, text).navigation(context, navigationCallback);
    }

    /**
     * 获取首页服务
     *
     * @return
     */
    public HomeExportService getHomeExportService() {
        return (HomeExportService) ARouter.getInstance().build(BaseContacts.PATH_HOME_SERVICE).navigation();
    }


    /**
     * 获取首页数据
     *
     * @param txt
     * @return
     */
    public String getHomeData(String txt) {

        return getHomeExportService().homeData(txt);
    }
}
