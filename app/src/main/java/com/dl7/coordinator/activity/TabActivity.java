package com.dl7.coordinator.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dl7.coordinator.R;
import com.dl7.coordinator.adapter.ViewPagerAdapter;
import com.dl7.coordinator.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabActivity extends BaseActivity {

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        initToolBar(mToolBar, true, "Tab");
        _initTabLayout();
    }

    private void _initTabLayout() {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        titles.add("One");
        titles.add("Two");
        titles.add("Three");
        for (String title : titles) {
            fragments.add(TabFragment.newInstance(title));
        }

        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setFragments(fragments);
        mPagerAdapter.setTitles(titles);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Snackbar.make(mFab, "Floating Action Button", Snackbar.LENGTH_LONG)
                .setAction("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }
}
