package com.boluomi.androidtools.activity;

import androidx.lifecycle.ViewModel;

import com.boluomi.androidtools.R;
import com.boluomi.commonlibrary.CommonActivity;

public class MainActivity extends CommonActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected ViewModel initViewModel() {
        return null;
    }
}
