package com.hxj.enjoyandroid;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class BaseUiActivity extends AppCompatActivity {

    public static final int WIDTH = 480;
    private float mAppScaleDensity;

    private float mAppDensity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Application app = getApplication();
        DisplayMetrics appDisplayMetrics = app.getResources().getDisplayMetrics();
        if (mAppDensity == 0) {
            mAppDensity = appDisplayMetrics.density;
            mAppScaleDensity = appDisplayMetrics.scaledDensity;
            app.registerComponentCallbacks(new ComponentCallbacks() {

                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        mAppScaleDensity = app.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        // 设计稿的宽的到适配目标的 density, densityDpi
        float targetDensity = (float) appDisplayMetrics.widthPixels / WIDTH;
        int targetDensityDpi = (int) (targetDensity * 160);
        float targetScaleDensity = targetDensity * (mAppScaleDensity / mAppDensity);

        DisplayMetrics activityDisplayMetrics = this.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
    }
}
