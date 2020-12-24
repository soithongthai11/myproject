package com.entertaiment.demo.view.activity;

import android.os.Bundle;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseActivity;
import com.entertaiment.demo.view.fragment.DetailProfileFragment;
import com.entertaiment.demo.view.fragment.ProfileFragment;
import com.entertaiment.demo.viewmodel.AppMainViewModel;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends BaseActivity<AppMainViewModel> {
    public static final int PROFILE_FRAGMENT = 1;
    public static final int DETAIL_PROFILE_FRAGMENT = 2;

    private static final String DETAIL_PROFILE_TAG = "DETAIL_PROFILE";

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
        changeFragment(PROFILE_FRAGMENT);
    }

    @Override
    public void onChanged(Object o) {

    }

    public void changeFragment(int fragement) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (fragement) {
            case PROFILE_FRAGMENT:
                ProfileFragment profileFragment = ProfileFragment.newInstance();
                ft.replace(R.id.content_main, profileFragment);
                break;
            case DETAIL_PROFILE_FRAGMENT:
                DetailProfileFragment detailProfileFragment = DetailProfileFragment.newInstance();
                ft.replace(R.id.content_main, detailProfileFragment);
                ft.addToBackStack(DETAIL_PROFILE_TAG);
                break;
            default:
                break;
        }
        ft.commit();
    }
}