package com.example.home.activity;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselib.base.BaseActivity;
import com.example.baselib.cantact.BaseContacts;
import com.example.baselib.utils.LogUitls;
import com.example.home.R;


@Route(path = BaseContacts.ROUTE_PATH_HOME)
public class HomeActivity extends BaseActivity {

    TextView mTvMsgContent;
    TextView mTvBack;

    // 自动注入参数
    @Autowired
    public String homeKey;


    @Override
    public void initView() {
        super.initView();

        mTvMsgContent = findViewById(R.id.tv_msg_content);
        mTvBack = findViewById(R.id.tv_back);

        LogUitls.i(HomeActivity.this, "接收到的 homeKey ： " + homeKey);
        if (!TextUtils.isEmpty(homeKey)) {
            mTvMsgContent.setText(homeKey);
        }
    }

    @Override
    public void initData() {
        super.initData();
        //如果没有使用 @Autowired 可以使用 Intent 获取传递过来的参数，
        // 如果上面使用 @Autowired  下面 通过getIntent().getStringExtra(HOME_KEY);获取到是空值 null
//        if (getIntent() != null){
//
//            String getStringExtra_homeKey = getIntent().getStringExtra(HOME_KEY);
//            LogUitls.i("使用 Intent 获取传递过来的参数 getStringExtra_homeKey ： "+getStringExtra_homeKey);
//        }


    }

    @Override
    public void initEvent() {
        super.initEvent();
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("param_result", "首月回传到数据");
//                setResult(20020);
                setResult(20020, intent);
                onBackPressed();
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

}