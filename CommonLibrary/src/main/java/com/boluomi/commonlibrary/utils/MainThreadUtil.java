package com.boluomi.commonlibrary.utils;

import android.os.Handler;
import android.os.Looper;


/**
 * 主线程工具类
 * author：liu by 20200315
 */
public class MainThreadUtil {

    /**
     * 主线程handler
     */
    private final static Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    /**
     * 发送消息
     *
     * @param callback 回调
     */
    public static void post(Runnable callback) {
        postDelayed(callback, 0);
    }

    /**
     * 发送延迟消息
     *
     * @param callback      回调
     * @param delayedMillis 延迟时间
     */
    public static void postDelayed(Runnable callback, long delayedMillis) {
        MAIN_HANDLER.postDelayed(callback, delayedMillis);
    }

}
