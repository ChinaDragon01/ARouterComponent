package com.example.chat.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselib.base.BaseActivity;
import com.example.baselib.cantact.BaseContacts;
import com.example.baselib.utils.LogUitls;
import com.example.chat.R;

@Route(path = BaseContacts.ROUTE_PATH_CHAT)
public class ChatMainActivity extends BaseActivity {
    private TextView mTvChat;
    private TextView mTvMsgContent;
    private TextView mTvChatBack;
    private TextView mTvHome;

    public static final String PARAM_RESULT = "param_result";
    public static final int REQUEST_CODE = 106;
    public static final int RESULT_CODE = 20020;


    // 自动注入参数
    @Autowired
    public String chatKey;


//    @Autowired
//    LoginInterceptorImpl mLoginInterceptor;


    @Override
    public void initView() {
        super.initView();

        mTvChat = findViewById(R.id.tv_chat);
        mTvMsgContent = findViewById(R.id.tv_msg_content);
        mTvChatBack = findViewById(R.id.tv_chat_back);
        mTvHome = findViewById(R.id.tv_home);

        LogUitls.i(ChatMainActivity.this, "接收到的 chatKey ： " + chatKey);
        if (!TextUtils.isEmpty(chatKey)) {
            mTvMsgContent.setText(chatKey);
        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initEvent() {
        super.initEvent();


        mTvChatBack.setOnClickListener(v -> onBackPressed());


        mTvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ARouter.getInstance().build("/home/HomeActivity")
                        .withString("homeKey", "聊天室传的数据")
//                        .navigation();
                        .navigation(ChatMainActivity.this, REQUEST_CODE, new NavCallback() {// 返回结果处理
                            @Override
                            public void onFound(Postcard postcard) {
                                super.onFound(postcard);
                                LogUitls.i(ChatMainActivity.this, "onFound 找到了 回调");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                super.onLost(postcard);
                                LogUitls.i(ChatMainActivity.this, "onLost 没有找到 回调");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                super.onInterrupt(postcard);
                                LogUitls.i(ChatMainActivity.this, "onInterrupt 被拦截了 回调");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {

                                LogUitls.i(ChatMainActivity.this, "onArrival 跳转完成 回调");

                            }
                        });
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_main;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {

            String stringExtra = data.getStringExtra(PARAM_RESULT);
            LogUitls.i(ChatMainActivity.this, " onActivityResult stringExtra = " + stringExtra);
        } else {

            LogUitls.i(ChatMainActivity.this, " onActivityResult data = null");

        }
        LogUitls.i(ChatMainActivity.this, "onActivityResult requestCode = " + requestCode + " resultCode = " + resultCode);
    }
}