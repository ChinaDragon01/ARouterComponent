package com.example.baselib.utils;

import android.content.Context;
import android.util.Log;

/**
 * *******************************************************
 * Author: chinadragon
 * Time: 2020/11/27 9:45 AM
 * Name:
 * Overview:
 * Usage:
 * *******************************************************
 */
public class LogUitls {

    public static void i(String text) {
        Log.i("==", text);
    }

    public static void i(Context context, String text) {
        if (null != context) {

            Log.i(context.getClass().getSimpleName() + " == ", text);
        } else {

            Log.i("==", text);
        }
    }
}
