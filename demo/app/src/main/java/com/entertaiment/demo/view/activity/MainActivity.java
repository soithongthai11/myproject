package com.entertaiment.demo.view.activity;

import android.os.Bundle;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseActivity;
import com.entertaiment.demo.viewmodel.AppMainViewModel;

import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends BaseActivity<AppMainViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void observeViewModel() {

    }

    @Override
    public AppMainViewModel getViewModel() {
        return ViewModelProviders.of(this).get(AppMainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onChanged(Object o) {

    }
}