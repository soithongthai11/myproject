package com.entertaiment.demo.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseFragment;
import com.entertaiment.demo.viewmodel.AppMainViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

public class DetailProfileFragment extends BaseFragment<AppMainViewModel> {
    private EditText mEdFirstName, mEdLastName, mEdNric, mEdEmail;
    public DetailProfileFragment() {
        // Required empty public constructor
    }

    public static DetailProfileFragment newInstance() {
        return new DetailProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail_profile;
    }

    @Override
    public void observeViewModel() {
        getViewModel().getDetailProfileData().observe(this, profile -> {
            if(profile != null) {
                mEdFirstName.setText(profile.getFirstname());
                mEdLastName.setText(profile.getLastname());
                mEdNric.setText(profile.getNRIC());
                mEdEmail.setText(profile.getEmail());
            }
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

    }

    @Override
    public void onChanged(Object o) {

    }

    private void initView() {
        mEdFirstName = getView().findViewById(R.id.ed_first_name);
        mEdLastName = getView().findViewById(R.id.ed_last_name);
        mEdNric = getView().findViewById(R.id.ed_nric);
        mEdEmail = getView().findViewById(R.id.ed_email);
    }
}