package com.dl7.coordinator.behavior;

import android.content.Context;
import android.content.res.TypedArray;
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
 * 头像行为
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
    // 控件最终缩放的尺寸,设置坐标值需要算上该值
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
    // ToolBar高度
    private int mToolBarHeight;
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
        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.AvatarImageBehavior);
            mFinalSize = (int) a.getDimension(R.styleable.AvatarImageBehavior_finalSize, 0);
            mFinalX = a.getDimension(R.styleable.AvatarImageBehavior_finalX, 0);
            mToolBarHeight = (int) a.getDimension(R.styleable.AvatarImageBehavior_toolBarHeight, 0);
            a.recycle();
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        _initVariables(child, dependency);

        mPercent = (mStatusBarHeight - dependency.getY()) * 1.0f / mTotalScrollRange;

        float percentY = mMoveYInterpolator.getInterpolation(mPercent);
        AnimHelper.setViewY(child, mOriginalY, mFinalY - mScaleSize, percentY);

        if (mPercent > ANIM_CHANGE_POINT) {
            float scalePercent = (mPercent - ANIM_CHANGE_POINT) / (1 - ANIM_CHANGE_POINT);
            float percentX = mMoveXInterpolator.getInterpolation(scalePercent);
            AnimHelper.scaleView(child, mOriginalSize, mFinalSize, scalePercent);
            AnimHelper.setViewX(child, mOriginalX, mFinalX - mScaleSize, percentX);
        } else {
            AnimHelper.scaleView(child, mOriginalSize, mFinalSize, 0);
            AnimHelper.setViewX(child, mOriginalX, mFinalX - mScaleSize, 0);
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
            if (mToolBarHeight == 0) {
                mToolBarHeight = mContext.getResources().getDimensionPixelSize(R.dimen.toolbar_height);
            }
            mFinalY = (mToolBarHeight - mFinalSize) / 2 + mStatusBarHeight;
        }
        if (mScaleSize == 0) {
            mScaleSize = (mOriginalSize - mFinalSize) * 1.0f / 2;
        }

        Log.e("AvatarImageBehavior", ""+dependency.getY());
        Log.e("AvatarImageBehavior", ""+mTotalScrollRange);
        Log.w("AvatarImageBehavior", ""+mOriginalSize);
        Log.w("AvatarImageBehavior", ""+mFinalSize);
        Log.i("AvatarImageBehavior", ""+child.getY());
        Log.i("AvatarImageBehavior", ""+mOriginalY);
        Log.d("AvatarImageBehavior", ""+child.getX());
        Log.d("AvatarImageBehavior", ""+mOriginalX);
    }

}
