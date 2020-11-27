package com.example.aroutercomponent.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.aroutercomponent.R;
import com.example.aroutercomponent.base.BaseAppActivity;
import com.example.baselib.baseinterface.HomeExportService;
import com.example.baselib.cantact.BaseContacts;
import com.example.baselib.utils.LogUitls;
import com.example.home.service.HomeService;

import butterknife.BindView;

/*
    一篇文章搞懂Android组件化,
    https://www.jianshu.com/p/8b6e6a50e21e

    alibaba ARouter
    https://github.com/alibaba/Arouter


     ARouter的使用以及遇到的问题
    https://www.jianshu.com/p/3a0133cdb93f?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation

    使用ARouter进行Android模块化开发
    https://segmentfault.com/a/1190000019681919


    Android组件化之ARouter的使用
    https://blog.csdn.net/jzman/article/details/104785623

    探索Android路由框架-ARouter之基本使用（一）
    https://www.jianshu.com/p/6021f3f61fa6

    Android 组件化开发 各个Mode之间和App的通信
    https://blog.csdn.net/lixiaoshuai_91/article/details/86526848


   Android中gradle的依赖：implementation与api的使用
   https://blog.csdn.net/liyi1009365545/article/details/81477299

 */

//配置的path至少需要两级，如/xx/xxx; 注意：不同module 一级路径名字不能相同，否则编译不通过，会抱错；同意哥一级目录被多次定义
@Route(path = MainActivity.ROUTE_PATH)
public class MainActivity extends BaseAppActivity {

    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.tv_chat)
    TextView mTvChat;

    public static final String ROUTE_PATH = "/app/MainActivity";
    public static final String PARAM_RESULT = "param_result";
    public static final int REQUEST_CODE = 106;
    public static final int RESULT_CODE = 20020;

    // 使用依赖注入的方式完成服务的调用  HomeService 方式一
    @Autowired(name = HomeService.PATH_HomeService)
    HomeExportService mHomeExportService;
//    HomeService mHomeExportService;


    @Override
    public void initData() {
        super.initData();
        // 调用 HomeService 方式一
        String homeData = mHomeExportService.homeData("我是从主界面进来到");
        LogUitls.i(MainActivity.this, "homeData方法返回的数据 homeData = ： " + homeData);

        // 通过服务类class 调用 HomeService 方式二
//        String s = ARouter.getInstance().navigation(HomeService.class).homeData("调用方式二"); // 传入 HomeService 报错：空指针
        String s = ARouter.getInstance().navigation(HomeExportService.class).homeData("调用方式二");
        LogUitls.i(MainActivity.this, s);

        //通过服务类Path调用 HomeService  调用方式三 注意： 强制转换 HomeExportService 或者 HomeService
//        String s3 = ((HomeExportService) ARouter.getInstance().build(HomeService.PATH_HomeService).navigation()).homeData("调用方式三");
        String s3 = ((HomeService) ARouter.getInstance().build(HomeService.PATH_HomeService).navigation()).homeData("调用方式三");
        LogUitls.i(MainActivity.this, s3);
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void initEvent() {
        super.initEvent();


        mTvChat.setOnClickListener(v -> ARouter.getInstance().build(BaseContacts.ROUTE_PATH_CHAT)
                .withString(BaseContacts.CHAT_KEY, "来自主界面数据")
//                .navigation();
                .navigation(MainActivity.this, new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        super.onFound(postcard);
                        LogUitls.i(MainActivity.this, "mTvChat onFound 找到了 回调");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        super.onLost(postcard);
                        LogUitls.i(MainActivity.this, "mTvChat onLost 没有找到 回调");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);
                        LogUitls.i(MainActivity.this, "mTvChat onInterrupt 被拦截了 回调");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {

                        LogUitls.i(MainActivity.this, "mTvChat onArrival 跳转完成 回调");

                    }
                }));



        mTvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ARouter.getInstance().build(BaseContacts.ROUTE_PATH_HOME)
                        .withString(BaseContacts.HOME_KEY, "收到string数据")
//                        .navigation();
                        .navigation(MainActivity.this, REQUEST_CODE, new NavCallback() {// 返回结果处理
                            @Override
                            public void onFound(Postcard postcard) {
                                super.onFound(postcard);
                                LogUitls.i(MainActivity.this, "mTvHome onFound 找到了 回调");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                super.onLost(postcard);
                                LogUitls.i(MainActivity.this, "mTvHome onLost 没有找到 回调");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                super.onInterrupt(postcard);
                                LogUitls.i(MainActivity.this, "mTvHome onInterrupt 被拦截了 回调");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {

                                LogUitls.i(MainActivity.this, "mTvHome onArrival 跳转完成 回调");

                            }
                        });
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (data != null) {

            String stringExtra = data.getStringExtra(PARAM_RESULT);
            LogUitls.i(MainActivity.this, " onActivityResult stringExtra = " + stringExtra);
        } else {

            LogUitls.i(MainActivity.this, " onActivityResult data = null");

        }
        LogUitls.i(MainActivity.this, "onActivityResult requestCode = " + requestCode + " resultCode = " + resultCode);
    }
}