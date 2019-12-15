package com.example.tikihometest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.tikihometest.ui.base.BaseActivity;
import com.example.tikihometest.ui.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createMainFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
}
