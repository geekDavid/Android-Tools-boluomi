package com.example.commonlibrary.utils;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;
import androidx.annotation.StringRes;

/** 解决Toast重复弹出 */
public class ToastUtil {

  /** 全局Toast对象 */
  private static Toast mToast;

  /** 创建可以处理main线程的Handler对象 */
  private static Handler handler =
      new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
          // 先取消正在显示的Toast
          if (mToast != null) {
            mToast.cancel();
          }
          if (msg.arg1 == 0) {
            String message = (String) msg.obj;
            mToast = Toast.makeText(sApplication, message, msg.arg2);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
            return;
          }
          mToast = (Toast) msg.obj;
          mToast.show();
        }
      };

  private static Application sApplication;

  public static void toast(Application application) {
    sApplication = application;
  }

  public static void toast(String message, int duration) {
    // 将Toast需要的参数发送到消息队列
    handler.sendMessage(handler.obtainMessage(0, 0, duration, message));
  }


  public static void toast(@StringRes int resId, int duration) {
    if (!TextUtils.isEmpty(sApplication.getString(resId))) {
      toast(sApplication.getString(resId), duration);
    }
  }

  /**
   * 自定义Toast,需要将一个toast传进来
   *
   * @param toast 自定义的toast
   */
  public static void toast(Toast toast) {
    // 将Toast需要的参数发送到消息队列
    handler.sendMessage(handler.obtainMessage(0, 1, Toast.LENGTH_SHORT, toast));
  }

  public static void toast(String message) {
    if (!TextUtils.isEmpty(message)) {
      toast(message, Toast.LENGTH_SHORT);
    }
  }

  public static void toast(@StringRes int resId) {
    if (!TextUtils.isEmpty(sApplication.getString(resId))) {
      toast(sApplication.getString(resId), Toast.LENGTH_SHORT);
    }
  }
}
