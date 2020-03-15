package com.boluomi.commonlibrary.manager;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

/**
 * 适配设备屏幕尺寸
 * author：liu by 20200315
 */
public class ScreenManager {

    private static float sNoncompatDensity;
    private static float sNoncompatScaleDensity;

    public static void SCREEN(Activity activity) {
        if (activity == null) {
            return;
        }
        final Application application = activity.getApplication();
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaleDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    if (newConfig.fontScale > 0) {
                        sNoncompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
            float targetDensity = appDisplayMetrics.widthPixels / 360f;
            float targetScaledDensity = targetDensity * (sNoncompatScaleDensity / sNoncompatDensity);
            int targetDensityDpi = (int) (160 * targetDensity);

            appDisplayMetrics.density = targetDensity;
            appDisplayMetrics.scaledDensity = targetScaledDensity;
            appDisplayMetrics.densityDpi = targetDensityDpi;

            DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
            displayMetrics.density = targetDensity;
            displayMetrics.scaledDensity = targetScaledDensity;
            displayMetrics.densityDpi = targetDensityDpi;

        }
    }
}
