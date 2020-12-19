package com.entertaiment.demo.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseActivity;
import com.entertaiment.demo.view.fragment.OTPFragment;
import com.entertaiment.demo.view.fragment.SignInFragment;
import com.entertaiment.demo.viewmodel.MainViewModel;

public class LogInActivity extends BaseActivity<MainViewModel> implements View.OnClickListener{
    private static final int NONE_FORM = -1;
    private static final int SIGN_FORM = 0;
    private static final int OTP_FORM  = 1;

    private static final String OTP_FORM_TAG = "OTP_FORM_TAG";

    private int mCurrentFormID = NONE_FORM;

    private Toolbar mToolBar;
    private Button  mBtContinue;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void observeViewModel() {

    }

    @Override
    public MainViewModel getViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init View Object
        initView();
        initListener();

        changeFragment(SIGN_FORM);
    }

    private void initView() {
        mToolBar    = (Toolbar) findViewById(R.id.toolbar);
        mBtContinue = (Button) findViewById(R.id.bt_continue);
    }

    private void initListener() {
        mBtContinue.setOnClickListener(this);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onChanged(Object o) {

    }

    private void changeFragment(int formID) {
        if (mCurrentFormID == formID)
            return;

        this.mCurrentFormID = formID;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (formID) {
            case SIGN_FORM:
                SignInFragment signInFragment = SignInFragment.newInstance();
                transaction.replace(R.id.content_form, signInFragment);
                transaction.commit();
                break;
            case OTP_FORM:
                OTPFragment otpFragment = OTPFragment.newInstance();
                transaction.replace(R.id.content_form, otpFragment);
                transaction.addToBackStack(OTP_FORM_TAG);
                transaction.commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_continue:
                changeFragment(OTP_FORM);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(mCurrentFormID == OTP_FORM) {
            mCurrentFormID = SIGN_FORM;
        } else {
            mCurrentFormID = NONE_FORM;
        }
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}