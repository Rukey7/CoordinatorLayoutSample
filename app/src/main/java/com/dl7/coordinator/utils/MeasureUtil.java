package com.dl7.coordinator.utils;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by Rukey7 on 2016/7/9.
 */
public class MeasureUtil {

    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
            Log.e("AvatarImageBehavior", "heightPixels " + heightPixels);
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
