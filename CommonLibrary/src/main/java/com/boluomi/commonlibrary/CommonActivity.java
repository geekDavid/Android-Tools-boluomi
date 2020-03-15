package com.boluomi.commonlibrary;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.boluomi.commonlibrary.manager.ActivityTaskManager;
import com.boluomi.commonlibrary.manager.ScreenManager;

/**
 * @name Android Tools
 * @class name：com.example.commonlibrary
 * @class describe
 * @anthor jin
 * @time 2020/3/8 22:06
 * @change
 * @chang time
 */
public abstract class CommonActivity<DBinding extends ViewDataBinding,VModel extends ViewModel> extends AppCompatActivity {

    private static final String TAG = "CommonActivity";
    protected DBinding mDatabinding;
    protected VModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTaskManager.getInstance().put(this);
        ScreenManager.SCREEN(this);
        mDatabinding = initDataBinding();
        mViewModel = initViewModel();
    }


    protected abstract @LayoutRes int getLayoutId();

    protected DBinding initDataBinding(){
        return DataBindingUtil.setContentView(this,getLayoutId());
    }

    protected abstract VModel initViewModel();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityTaskManager.getInstance().remove(this);
    }
}
