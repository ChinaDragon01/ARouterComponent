package com.example.baselib.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baselib.baseinterface.BaseFragmentInterface;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/28 10:51 AM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */
abstract public class BaseFragment extends Fragment implements BaseFragmentInterface {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        initView();
        initData();
        initEvent();
        return mView;
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
