package com.boluomi.commonlibrary.utils;

/**
 * 单例构建类
 * author:liu by 20200315
 */
public abstract class Singleton<T> {
    private T mInstance;

    protected abstract T create();

    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}
