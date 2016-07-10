package com.dl7.coordinator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dl7.coordinator.activity.CustomBehaviorActivity;
import com.dl7.coordinator.activity.FabBehaviorActivity;
import com.dl7.coordinator.activity.ScrollActivity;
import com.dl7.coordinator.activity.TabActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_tab)
    Button mBtnTab;
    @BindView(R.id.btn_scroll)
    Button mBtnScroll;
    @BindView(R.id.btn_fab_behavior)
    Button mBtnFabBehavior;
    @BindView(R.id.btn_custom_behavior)
    Button mBtnCustomBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_tab, R.id.btn_scroll, R.id.btn_fab_behavior, R.id.btn_custom_behavior})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tab:
                startActivity(new Intent(this, TabActivity.class));
                break;
            case R.id.btn_scroll:
                startActivity(new Intent(this, ScrollActivity.class));
                break;
            case R.id.btn_fab_behavior:
                startActivity(new Intent(this, FabBehaviorActivity.class));
                break;
            case R.id.btn_custom_behavior:
                startActivity(new Intent(this, CustomBehaviorActivity.class));
                break;
        }
    }
}
