package com.boluomi.commonlibrary.utils;

import android.text.TextUtils;
import android.util.Log;
import com.boluomi.commonlibrary.BuildConfig;

/** 日志工具类 author:liu by 20200315 */
public final class MLog {

  public static void v(String tag, String... logs) {
    print(tag, LogType.VERBOSE, logs);
  }

  public static void i(String tag, String... logs) {
    print(tag, LogType.INFO, logs);
  }

  public static void d(String tag, String... logs) {
    if (BuildConfig.DEBUG) {
      print(tag, LogType.DEBUG, logs);
    }
  }

  public static void w(String tag, String... logs) {
    print(tag, LogType.WARNING, logs);
  }

  public static void e(String tag, Throwable throwable) {
    e(tag, throwable.getMessage());
  }
  public static void e(String tag, String log, Throwable throwable) {
    e(tag, log, throwable.getMessage());
  }

  public static void e(String tag, String... logs) {
    print(tag, LogType.ERROR, logs);
  }

  private static void print(String tag, LogType logType, String... logs) {
    if (logs == null || logs.length <= 0 || TextUtils.isEmpty(tag)) {
      return;
    }
    StringBuilder builder = new StringBuilder();
    for (String log : logs) {
      builder.append(log);
    }
    switch (logType) {
      case VERBOSE:
        Log.v(tag, builder.toString());
        break;
      case INFO:
        Log.i(tag, builder.toString());
        break;
      case DEBUG:
        Log.d(tag, builder.toString());
        break;
      case WARNING:
        Log.w(tag, builder.toString());
        break;
      case ERROR:
        Log.e(tag, builder.toString());
        break;
      default:
        Log.v(tag, builder.toString());
        break;
    }
  }

  private enum LogType {
    VERBOSE,
    INFO,
    DEBUG,
    WARNING,
    ERROR
  }
}
