package com.example.commonlibrary;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @name Android Tools
 * @class name：com.example.commonlibrary
 * @class describe
 * @anthor jin
 * @time 2020/3/8 22:06
 * @change
 * @chang time
 */
public class BaseActivity extends AppCompatActivity {

  private static final String TAG = "BaseActivity";

  @Override
  public void onCreate(
      @Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    Log.i(TAG, "测试一下: ");
  }
}
