package com.boluomi.androidtools.activity;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import com.boluomi.commonlibrary.lifecycle.BaseViewModel;
import com.boluomi.commonlibrary.utils.MLog;

public class TestViewModel extends BaseViewModel {

  private static final String TAG = "NewsViewModel";

  private MutableLiveData<String> progress = new MutableLiveData<>();

  public MutableLiveData<String> getProgress() {
    return progress;
  }

  public void requestData() {
    showDialog.setValue(true);
    MLog.i(TAG, "开始请求，显示dialog");
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                showDialog.setValue(false);
                MLog.i(TAG, "结束请求，关闭dialog");
              }
            },
            1000);
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                MLog.i(TAG, "显示进度100");
                progress.setValue("100");
              }
            },
            3000);
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                MLog.i(TAG, "显示错误");
                error.setValue("报错了大哥");
              }
            },
            5000);
  }
}
