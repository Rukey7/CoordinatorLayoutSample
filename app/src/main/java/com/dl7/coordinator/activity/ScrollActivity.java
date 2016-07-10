package com.dl7.coordinator.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import com.dl7.coordinator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.collapsing_tool_bar)
    CollapsingToolbarLayout mCollapsingToolBar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private boolean mIsChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);

        initToolBar(mToolBar, true, "Scroll");
    }

    @OnClick(R.id.fab)
    public void onClick() {
        mIsChoose = !mIsChoose;
        if (mIsChoose) {
            mFab.setImageResource(R.mipmap.ic_start_choose);
        } else {
            mFab.setImageResource(R.mipmap.ic_start);
        }
    }
}
