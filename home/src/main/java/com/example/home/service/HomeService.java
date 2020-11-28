package com.example.home.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselib.baseinterface.HomeExportService;
import com.example.baselib.cantact.BaseContacts;
import com.example.baselib.utils.LogUitls;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/27 9:55 AM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */

@Route(path = BaseContacts.PATH_HOME_SERVICE,name = "测试首页服务接口")
public class HomeService implements HomeExportService {
    private String mText;
    private Context mContext;


    @Override
    public String homeData(String text) {
        LogUitls.i(this.getClass().getSimpleName()+" homeData 方法 text = "+text);

        mText = text;
        return "成功调用 首页服务 homeData 方法 ： "+text;
    }

    @Override
    public void init(Context context) {
        mContext = context;
        LogUitls.i(this.getClass().getSimpleName()+" init 方法");

    }


    private void initData() {
        mText = "首页服务接口";

        LogUitls.i(this.getClass().getSimpleName()+" initData 方法");

    }
}
