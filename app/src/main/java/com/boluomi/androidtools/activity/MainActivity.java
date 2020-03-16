package com.boluomi.androidtools.activity;

import com.boluomi.androidtools.R;
import com.boluomi.commonlibrary.CommonActivity;

public class MainActivity extends CommonActivity {
  private static final String TAG = "MainActivity";

  @Override
  protected void initData() {
    showLoadingDialog();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void showError(Object obj) {}
}
