package com.entertaiment.demo.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseFragment;
import com.entertaiment.demo.view.activity.MainActivity;
import com.entertaiment.demo.viewmodel.AppMainViewModel;

public class ProfileFragment extends BaseFragment<AppMainViewModel> implements View.OnClickListener {

    private TextView mTvEdit, mTvFullName, mTvEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void observeViewModel() {
        getViewModel().getProfileData().observe(this, verifyRegistered -> {
            mTvFullName.setText(verifyRegistered.getFirstname() + " " + verifyRegistered.getLastname());
            mTvEmail.setText(verifyRegistered.getEmail() != null ? verifyRegistered.getEmail() : "");
        });
    }

    @Override
    public AppMainViewModel getViewModel() {
        return ViewModelProviders.of(this).get(AppMainViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        getViewModel().getDetailProfile();
    }

    @Override
    public void onChanged(Object o) {

    }

    private void initView() {
        mTvEdit = getView().findViewById(R.id.tv_edit);
        mTvFullName = getView().findViewById(R.id.tv_full_name);
        mTvEmail = getView().findViewById(R.id.tv_email);
    }

    private void initListener() {
        mTvEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                ((MainActivity)getActivity()).changeFragment(MainActivity.DETAIL_PROFILE_FRAGMENT);
                break;
        }
    }
}