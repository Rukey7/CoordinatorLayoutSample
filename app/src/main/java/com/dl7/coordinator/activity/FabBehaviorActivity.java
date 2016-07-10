package com.dl7.coordinator.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dl7.coordinator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FabBehaviorActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_behavior);
        ButterKnife.bind(this);
        initToolBar(mToolBar, true, "FloatActionButton Behavior");
    }
}
