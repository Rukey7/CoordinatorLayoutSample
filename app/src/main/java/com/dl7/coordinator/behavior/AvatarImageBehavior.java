package com.dl7.coordinator.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.dl7.coordinator.R;
import com.dl7.coordinator.utils.AnimHelper;
import com.dl7.coordinator.utils.MeasureUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Rukey7 on 2016/7/9.
 */
public class AvatarImageBehavior extends CoordinatorLayout.Behavior<CircleImageView> {

    // 缩放动画变化的支点
    private static final float ANIM_CHANGE_POINT = 0.3f;

    private Context mContext;
    // 整个滚动的范围
    private int mTotalScrollRange;
    // AppBarLayout高度
    private int mAppBarHeight;
    // AppBarLayout宽度
    private int mAppBarWidth;
    // 控件原始大小
    private int mOriginalSize;
    // 控件最终大小
    private int mFinalSize;
    // 控件最终缩放的大小
    private float mScaleSize;
    // 原始x坐标
    private float mOriginalX;
    // 最终x坐标
    private float mFinalX;
    // 起始y坐标
    private float mOriginalY;
    // 最终y坐标
    private float mFinalY;
    // 状态栏高度
    private int mStatusBarHeight;
    // 滚动执行百分比[0~1]
    private float mPercent;
    // Y轴移动插值器
    private DecelerateInterpolator mMoveYInterpolator;
    // X轴移动插值器
    private AccelerateInterpolator mMoveXInterpolator;


    public AvatarImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mMoveYInterpolator = new DecelerateInterpolator();
        mMoveXInterpolator = new AccelerateInterpolator();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        _initVariables(child, dependency);

        mPercent = (mStatusBarHeight - dependency.getY()) * 1.0f / mTotalScrollRange;
        Log.e("AvatarImageBehavior", "" + mFinalSize);
        Log.w("AvatarImageBehavior", "" + mFinalY);
        Log.w("AvatarImageBehavior", "" + child.getY());
        Log.d("AvatarImageBehavior", "" + MeasureUtil.getStatusBarHeight(mContext));

//        AnimHelper.scaleView(child, mOriginalSize, mFinalSize, mPercent);
//        AnimHelper.setViewX(child, mOriginalX, mFinalX - mScaleSize, mPercent);
//        AnimHelper.setViewY(child, mOriginalY, mFinalY - mScaleSize, mPercent);

        float percentY = mMoveYInterpolator.getInterpolation(mPercent);
        AnimHelper.setViewY(child, mOriginalY, mFinalY - mScaleSize, percentY);
        if (mPercent > ANIM_CHANGE_POINT) {
            float scalePercent = (mPercent - ANIM_CHANGE_POINT) / (1 - ANIM_CHANGE_POINT);
            float percentX = mMoveXInterpolator.getInterpolation(scalePercent);
            AnimHelper.scaleView(child, mOriginalSize, mFinalSize, scalePercent);
            AnimHelper.setViewX(child, mOriginalX, mFinalX - mScaleSize, percentX);
        }

        return true;
    }

    private void _initVariables(CircleImageView child, View dependency) {
        if (mAppBarHeight == 0) {
            mAppBarHeight = dependency.getHeight();
        }
        if (mTotalScrollRange == 0) {
            mTotalScrollRange = ((AppBarLayout) dependency).getTotalScrollRange();
        }
        if (mOriginalSize == 0) {
            mOriginalSize = child.getWidth();
        }
        if (mFinalSize == 0) {
            mFinalSize = mContext.getResources().getDimensionPixelSize(R.dimen.avatar_final_size);
        }
        if (mAppBarWidth == 0) {
            mAppBarWidth = dependency.getWidth();
        }
        if (mOriginalX == 0) {
            mOriginalX = child.getX();
        }
        if (mFinalX == 0) {
            mFinalX = mContext.getResources().getDimensionPixelSize(R.dimen.avatar_final_x);
        }
        if (mStatusBarHeight == 0) {
            mStatusBarHeight = MeasureUtil.getStatusBarHeight(mContext);
        }
        if (mOriginalY == 0) {
            mOriginalY = child.getY();
        }
        if (mFinalY == 0) {
            mStatusBarHeight = MeasureUtil.getStatusBarHeight(mContext);
            int toolBarHeight = mContext.getResources().getDimensionPixelSize(R.dimen.toolbar_height);
            mFinalY = (toolBarHeight - mFinalSize) / 2 + mStatusBarHeight;
        }
        if (mScaleSize == 0) {
            mScaleSize = (mOriginalSize - mFinalSize) * 1.0f / 2;
        }
    }

}
