package com.boluomi.commonlibrary;

import android.app.Application;
import android.content.Context;

/**
 * 统一Application
 */
public class CommonApp extends Application {

    private static Application mApplication;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //进程初始化请误做耗时操作
        mApplication = this;
        mContext = this;
    }

    public static Application getApplication() {
        return mApplication;
    }

    public static Context getContext() {
        return mContext;
    }
}
