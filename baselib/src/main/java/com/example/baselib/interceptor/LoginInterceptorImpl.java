package com.example.baselib.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.example.baselib.utils.LogUitls;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/27 5:49 PM
 * Name:拦截器的使用面向切面编程
 * Overview:
 * https://blog.csdn.net/liangxingfu0809/article/details/82964693
 * Usage:
 * *******************************************************
 */

/*
    比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查

    拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行

    使用Interceptor类注解的priority数值越小，越先执行，优先级越高。
    （四大组件中的广播，优先级的取值是 -1000到1000，数值越大优先级越高）

 */
@Interceptor(name = "login", priority = 10)
public class LoginInterceptorImpl implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        LogUitls.i("LoginInterceptorImpl process 函数获取的 path = " + path);

        boolean isLogin = true;
        if (isLogin) {// 如果已经登录不拦截
            callback.onContinue(postcard);
        } else {
            switch (path) {
                // 不需要登录的直接进入这个页面
                case "1":
                case "2":
                    callback.onContinue(postcard);
                    break;
                default:
                    callback.onInterrupt(null);
                    // 需要登录的直接拦截下来
                    break;
            }
        }

    }

    @Override
    public void init(Context context) {

        LogUitls.i("路由登录拦截器初始化成功");// //只会走一次

    }
}
