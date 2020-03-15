package com.boluomi.androidtools.activity;

import com.boluomi.androidtools.R;
import com.boluomi.androidtools.databinding.ActivityMainBinding;
import com.boluomi.commonlibrary.CommonActivity;
import com.boluomi.commonlibrary.utils.MLog;

public class MainActivity extends CommonActivity<ActivityMainBinding, TestViewModel> {
  private static final String TAG = "MainActivity";

  @Override
  protected void initData() {
    mDatabinding.setModel(mViewModel);
    mDatabinding.setLifecycleOwner(this);
    mViewModel.requestData();
    mViewModel.getProgress().setValue("10");
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void showError(Object obj) {
    MLog.i(TAG, "obj" + obj.toString());
  }
}
