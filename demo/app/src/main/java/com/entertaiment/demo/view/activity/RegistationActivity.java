package com.entertaiment.demo.view.activity;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseActivity;
import com.entertaiment.demo.viewmodel.RegistationViewModel;

public class RegistationActivity extends BaseActivity<RegistationViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_registation;
    }

    @Override
    public void observeViewModel() {

    }

    @Override
    public RegistationViewModel getViewModel() {
        return ViewModelProviders.of(this).get(RegistationViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onChanged(Object o) {

    }
}