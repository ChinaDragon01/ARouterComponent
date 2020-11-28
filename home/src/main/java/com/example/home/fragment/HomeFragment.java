package com.example.home.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselib.base.BaseFragment;
import com.example.baselib.cantact.BaseContacts;
import com.example.home.R;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/28 10:49 AM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */

@Route(path = BaseContacts.ROUTE_PATH_HOME_FRAGMENT)
public class HomeFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
