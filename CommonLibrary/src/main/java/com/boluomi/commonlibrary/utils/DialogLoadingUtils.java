package com.boluomi.commonlibrary.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boluomi.commonlibrary.R;

/**
 * 加载中的dialog
 *
 * @author Jin
 */
public class DialogLoadingUtils {

  public static Dialog createLoadingDialog(Context context, String msg) {
    LayoutInflater inflater = LayoutInflater.from(context);
    // 得到加载view
    View v = inflater.inflate(R.layout.dialog_loading, null);
    // 加载布局
    LinearLayout layout = v.findViewById(R.id.dialog_loading_view);
    // 提示文字
    TextView tipTextView = v.findViewById(R.id.tipTextView);
    if (!TextUtils.isEmpty(msg)) {
      // 设置加载信息
      tipTextView.setText(msg);
    }
    // 创建自定义样式dialog
    Dialog loadingDialog = new Dialog(context, R.style.LoadingDialog);
    // 是否可以按“返回键”消失
    loadingDialog.setCancelable(true);
    // 点击加载框以外的区域
    loadingDialog.setCanceledOnTouchOutside(false);
    // 设置布局
    loadingDialog.setContentView(
        layout,
        new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    // 将显示Dialog的方法封装在这里面
    Window window = loadingDialog.getWindow();
    WindowManager.LayoutParams lp = window.getAttributes();
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    window.setGravity(Gravity.CENTER);
    window.setAttributes(lp);
    loadingDialog.show();
    return loadingDialog;
  }

  public static Dialog createLoadingDialog(Context context) {
    return createLoadingDialog(context, "");
  }

  /** 关闭dialog */
  public static void closeDialog(Dialog dialog) {
    if (dialog != null && dialog.isShowing()) {
      dialog.dismiss();
    }
  }
}
