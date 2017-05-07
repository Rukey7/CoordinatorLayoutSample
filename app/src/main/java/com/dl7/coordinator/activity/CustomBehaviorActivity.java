package com.dl7.coordinator.activity;

import android.os.Bundle;

import com.dl7.coordinator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CustomBehaviorActivity extends BaseActivity {


    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_avatar)
    public void onClick() {
        mIvAvatar.setImageResource(R.mipmap.avatar);
    }
}
