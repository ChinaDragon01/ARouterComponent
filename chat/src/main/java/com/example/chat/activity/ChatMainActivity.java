package com.example.chat.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselib.base.BaseActivity;
import com.example.baselib.cantact.BaseContacts;
import com.example.baselib.utils.HomeExportServiceUtils;
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


    /*
        通过依赖 aar 方式 完成组件之间单独调试以及通信

        单独调试依然可以获取到 HomeExportService  因为gradle里添加了 implementation(name: 'home-debug', ext: 'aar')

     */


    // 使用依赖注入的方式完成服务的调用  HomeService 方式一
//    @Autowired(name = BaseContacts.PATH_HOME_SERVICE)
//    HomeExportService mHomeExportService;


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
        // 调用 HomeService 方式一
//        String homeData = mHomeExportService.homeData("我是从聊天室界面进来到");
//        LogUitls.i(ChatMainActivity.this, "homeData方法返回的数据 homeData = ： " + homeData);

        //获取 Fragment 实例
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(BaseContacts.ROUTE_PATH_HOME_FRAGMENT).navigation();
        fragmentTransaction.add(R.id.frame_chat, homeFragment, "homeFragment").commit();
    }

    @Override
    public void initEvent() {
        super.initEvent();


        mTvChatBack.setOnClickListener(v -> onBackPressed());


        mTvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 /*
                    “终于懂了” 系列：Android组件化，全面掌握！ | 掘金技术征文-双节特别篇
                     https://juejin.cn/post/6881116198889586701

                    通过依赖 aar 方式 完成组件之间单独调试以及通信

                    jar包与arr包区别
                    https://www.jianshu.com/p/a2eddf5a9b88

                    单独调试依然可以获取到 HomeExportService  因为gradle里添加了 implementation(name: 'home-debug', ext: 'aar')

                 */

//               HomeExportServiceUtils.openHome("聊天室传的数据");
                HomeExportServiceUtils.openHome(ChatMainActivity.this, "聊天室传的数据", new NavCallback() {
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




               /* ARouter.getInstance().build("/home/HomeActivity")
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
                        });*/
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