package com.tencent.modebuilder.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import com.tencent.modebuilder.MyApplication;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/7
 * Describe:
 */
public class ScreenUtils {
    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dp2px(Context context, float dp) {
        if (context == null) return 0;
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

    public static int getStatusBarHeight() {
        Resources resources = MyApplication.application.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
