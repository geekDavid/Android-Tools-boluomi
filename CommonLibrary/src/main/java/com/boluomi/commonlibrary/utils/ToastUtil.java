package com.boluomi.commonlibrary.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.boluomi.commonlibrary.CommonApp;

/**
 * 解决Toast重复弹出
 */
public class ToastUtil {

    public static void toast(String message) {
        toast(message, Toast.LENGTH_SHORT);
    }

    public static void toast(@StringRes int resId) {
        toast(resId, Toast.LENGTH_SHORT);
    }

    public static void toast(@StringRes int resId, int duration) {
        String content = CommonApp.getContext().getString(resId);
        toast(content, duration);
    }

    public static void toast(final String content, final int duration) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        MainThreadUtil.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(CommonApp.getContext(), content, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
