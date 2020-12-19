package com.entertaiment.demo.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseFragment;
import com.entertaiment.demo.viewmodel.MainViewModel;

public class OTPFragment extends BaseFragment<MainViewModel> {

    public OTPFragment() {
        // Required empty public constructor
    }

    public static OTPFragment newInstance() {
        OTPFragment fragment = new OTPFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_otp;
    }

    @Override
    public void observeViewModel() {

    }

    @Override
    public MainViewModel getViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onChanged(Object o) {

    }
}