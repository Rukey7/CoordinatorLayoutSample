package com.dl7.coordinator.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by Rukey7 on 2016/7/9.
 */
public class AnimHelper {

    private AnimHelper() {
        throw new RuntimeException("AnimHelper cannot be initialized!");
    }


    public static void zoomOut(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alpha, scaleX, scaleY);
        set.setDuration(300);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
    }

    public static void zoomIn(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alpha, scaleX, scaleY);
        set.setDuration(300);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
    }

    public static void setViewX(View view, float originalX, float finalX, float percent) {
        float calcX = (finalX - originalX) * percent + originalX;
        view.setX(calcX);
    }

    public static void setViewY(View view, float originalY, float finalY, float percent) {
        float calcY = (finalY - originalY) * percent + originalY;
        view.setY(calcY);
    }

    public static void scaleView(View view, float originalSize, float finalSize, float percent) {
        float calcSize = (finalSize - originalSize) * percent + originalSize;
        float caleScale = calcSize / originalSize;
        view.setScaleX(caleScale);
        view.setScaleY(caleScale);
    }
}
