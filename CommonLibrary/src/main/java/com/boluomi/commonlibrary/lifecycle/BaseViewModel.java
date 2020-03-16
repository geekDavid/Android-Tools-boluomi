package com.boluomi.commonlibrary.lifecycle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.boluomi.commonlibrary.bean.DialogBean;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ViewModel基类，管理rxJava发出的请求，ViewModel销毁同时也取消请求
 *
 * @author Jin
 */
public abstract class BaseViewModel extends ViewModel {

  /** 管理RxJava请求 */
  private CompositeDisposable mCompositeDisposable;
  /** 用来通知 Activity／Fragment 是否显示等待Dialog */
  protected DialogLiveData<DialogBean> mShowDialog = new DialogLiveData<>();
  /** 当ViewModel层出现错误需要通知到Activity／Fragment */
  protected MutableLiveData<Object> mError = new MutableLiveData<>();

  /** 添加 rxJava 发出的请求 */
  protected void addDisposable(Disposable disposable) {
    if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
      mCompositeDisposable = new CompositeDisposable();
    }
    mCompositeDisposable.add(disposable);
  }

  public void getShowDialog(LifecycleOwner owner, Observer<DialogBean> observer) {
    mShowDialog.observe(owner, observer);
  }

  public void getError(LifecycleOwner owner, Observer<Object> observer) {
    mError.observe(owner, observer);
  }

  /** ViewModel销毁同时也取消请求 */
  @Override
  protected void onCleared() {
    super.onCleared();
    if (mCompositeDisposable != null) {
      mCompositeDisposable.dispose();
      mCompositeDisposable = null;
    }
    mShowDialog = null;
    mError = null;
  }
}
