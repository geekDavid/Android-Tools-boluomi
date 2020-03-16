package com.boluomi.commonlibrary.lifecycle;

import androidx.lifecycle.MutableLiveData;
import com.boluomi.commonlibrary.bean.DialogBean;

/**
 * 用来显示加载中dialog的MutableLiveData
 *
 * @author Jin
 */
public final class DialogLiveData<T> extends MutableLiveData<T> {

  private DialogBean mDialogBean = new DialogBean();

  public void setValue(boolean isShow) {
    mDialogBean.setShow(isShow);
    mDialogBean.setMsg("");
    setValue((T) mDialogBean);
  }

  public void setValue(boolean isShow, String msg) {
    mDialogBean.setShow(isShow);
    mDialogBean.setMsg(msg);
    setValue((T) mDialogBean);
  }
}
