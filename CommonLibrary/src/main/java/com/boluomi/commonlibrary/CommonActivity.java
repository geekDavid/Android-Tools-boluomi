package com.boluomi.commonlibrary;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.boluomi.commonlibrary.bean.DialogBean;
import com.boluomi.commonlibrary.lifecycle.BaseViewModel;
import com.boluomi.commonlibrary.manager.ActivityTaskManager;
import com.boluomi.commonlibrary.manager.ScreenManager;
import com.boluomi.commonlibrary.utils.DialogLoadingUtils;
import java.lang.reflect.ParameterizedType;

/**
 * @name Android Tools
 * @class name：com.example.commonlibrary
 * @class describe
 * @anthor jin
 * @time 2020/3/8 22:06
 * @change
 * @chang time
 */
public abstract class CommonActivity<DBinding extends ViewDataBinding, VModel extends BaseViewModel>
    extends AppCompatActivity {

  private static final String TAG = "CommonActivity";
  protected DBinding mDatabinding;
  protected VModel mViewModel;
  private Dialog mLoadingDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityTaskManager.getInstance().put(this);
    ScreenManager.SCREEN(this);
    mDatabinding = initDataBinding();
    initData();
  }

  protected abstract void initData();

  public VModel initViewModel() {
    try {
      // 通过反射获取model的真实类型
      ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
      Class<VModel> clazz = (Class<VModel>) pt.getActualTypeArguments()[1];
      mViewModel = ViewModelProviders.of(this).get(clazz);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return mViewModel;
  }

  protected abstract @LayoutRes int getLayoutId();

  protected DBinding initDataBinding() {
    mDatabinding = DataBindingUtil.setContentView(this, getLayoutId());
    mViewModel = initViewModel();
    initObserve();
    return mDatabinding;
  }

  /** 监听当前ViewModel中 showDialog和error的值 */
  private void initObserve() {
    if (mViewModel == null) {
      return;
    }
    mViewModel.getShowDialog(
        this,
        new Observer<DialogBean>() {
          @Override
          public void onChanged(DialogBean bean) {
            if (bean.isShow()) {
              showDialog(bean.getMsg());
            } else {
              dismissDialog();
            }
          }
        });
    mViewModel.getError(
        this,
        new Observer<Object>() {
          @Override
          public void onChanged(Object obj) {
            showError(obj);
          }
        });
  }

  /**
   * 显示用户等待框
   *
   * @param msg 提示信息
   */
  protected void showDialog(String msg) {
    mLoadingDialog = DialogLoadingUtils.createLoadingDialog(this, msg);
    mLoadingDialog.show();
  }

  /** 隐藏等待框 */
  protected void dismissDialog() {
    if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
      mLoadingDialog.dismiss();
      mLoadingDialog = null;
    }
  }

  /** ViewModel层发生了错误 */
  protected abstract void showError(Object obj);

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ActivityTaskManager.getInstance().remove(this);
  }
}
