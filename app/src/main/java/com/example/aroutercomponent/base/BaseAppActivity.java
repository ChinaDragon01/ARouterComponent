package com.example.aroutercomponent.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselib.baseinterface.BaseActivityInterface;

import butterknife.ButterKnife;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/27 5:24 PM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */
abstract public class BaseAppActivity extends AppCompatActivity implements BaseActivityInterface {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        initData();
        initView();
        initEvent();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    abstract public int getLayoutId();
}
