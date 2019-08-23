package com.hxj.enjoyandroid;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class UIUtils {

    private static UIUtils instance;

    private final int mStatusBarHeight;

    private int mScreenWidth;

    private int mScreenHeight;

    private UIUtils(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;

        int in = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        mStatusBarHeight = context.getResources().getDimensionPixelSize(in);
    }

    public static UIUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (UIUtils.class) {
                if (instance == null) {
                    instance = new UIUtils(context.getApplicationContext());
                }
            }
        }

        return instance;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public int getStatusHeight() {
        return mStatusBarHeight;
    }
}
