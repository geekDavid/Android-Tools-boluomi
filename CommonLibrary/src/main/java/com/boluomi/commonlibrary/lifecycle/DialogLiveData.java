package com.boluomi.commonlibrary.lifecycle;

import androidx.lifecycle.MutableLiveData;
import com.boluomi.commonlibrary.bean.DialogBean;

/**
 * 用来显示加载中dialog的MutableLiveData
 *
 * @author Jin
 */
public final class DialogLiveData<T> extends MutableLiveData<T> {

  private DialogBean bean = new DialogBean();

  public void setValue(boolean isShow) {
    bean.setShow(isShow);
    bean.setMsg("");
    setValue((T) bean);
  }

  public void setValue(boolean isShow, String msg) {
    bean.setShow(isShow);
    bean.setMsg(msg);
    setValue((T) bean);
  }
}
