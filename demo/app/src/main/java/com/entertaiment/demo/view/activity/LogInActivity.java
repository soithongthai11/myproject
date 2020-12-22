package com.entertaiment.demo.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseActivity;
import com.entertaiment.demo.utils.Constraints;
import com.entertaiment.demo.viewmodel.MainViewModel;

public class LogInActivity extends BaseActivity<MainViewModel> implements View.OnClickListener {
    private static final int SIGN_FORM = 0;
    private static final int OTP_FORM  = 1;

    private int mCurrentFormID = SIGN_FORM;

    private Toolbar mToolBar;
    private Button  mBtContinue;
    private EditText mEdPhoneNumber;
    private Spinner mListPhoneCode;
    private TextView mTvOtpTime;

    private LinearLayout mSignForm, mOtpForm;

    private CountDownTimer mOtpTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void observeViewModel() {
        getViewModel().getValidPhone().observe(this, aBoolean -> {
            if(aBoolean) {
                findViewById(R.id.view_valid).setBackgroundColor(ContextCompat.getColor(this, R.color.white_two));
                findViewById(R.id.tv_valid).setVisibility(View.GONE);
            } else {
                findViewById(R.id.view_valid).setBackgroundColor(ContextCompat.getColor(this, R.color.tomato));
                findViewById(R.id.tv_valid).setVisibility(View.VISIBLE);
            }
        });

        getViewModel().getRequestSuccess().observe(this, aBoolean -> {
            TextView validText = findViewById(R.id.tv_valid);
            if(aBoolean) {
                validText.setText(R.string.invalid_string);
                validText.setVisibility(View.GONE);
                changeFragment(OTP_FORM);
            } else {
                validText.setText(R.string.check_phone_string);
                validText.setVisibility(View.VISIBLE);
            }
        });
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
    }

    private void initView() {
        mToolBar       = (Toolbar) findViewById(R.id.toolbar);
        mBtContinue    = (Button) findViewById(R.id.bt_continue);
        mEdPhoneNumber = (EditText) findViewById(R.id.ed_phone_number);
        mListPhoneCode = (Spinner) findViewById(R.id.spinner_phone_code);
        mTvOtpTime     = (TextView) findViewById(R.id.tv_time_otp);
    }

    private void initListener() {
        mSignForm = findViewById(R.id.content_signin_form);
        mOtpForm = findViewById(R.id.content_otp_form);

        mBtContinue.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Constraints.getPhoneCode());
        mListPhoneCode.setAdapter(adapter);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onChanged(Object o) {

    }

    private void changeFragment(int formID) {
        this.mCurrentFormID = formID;
        switch (formID) {
            case SIGN_FORM:
                mSignForm.setVisibility(View.VISIBLE);
                mOtpForm.setVisibility(View.GONE);
                break;
            case OTP_FORM:
                mSignForm.setVisibility(View.GONE);
                mOtpForm.setVisibility(View.VISIBLE);
                countTimeOtp();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_continue:
                handleContinueBt();
                break;
            default:
                break;
        }
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_popup, null);
        final Button btRequestToken = (Button) alertLayout.findViewById(R.id.bt_request_token);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();

        btRequestToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().requestOTP(mListPhoneCode.getSelectedItem().toString(), mEdPhoneNumber.getText().toString(), "sms");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void handleContinueBt() {
        if(mCurrentFormID != OTP_FORM) {
            boolean isValid;
            isValid = getViewModel().checkValidPhoneNumer(mListPhoneCode.getSelectedItem().toString(), mEdPhoneNumber.getText().toString());
            if (isValid) {
                displayAlertDialog();
            }
        }
    }

    private void countTimeOtp() {
        mOtpTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvOtpTime.setText("(00:"+millisUntilFinished/1000+")");
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if(mCurrentFormID == OTP_FORM) {
            changeFragment(SIGN_FORM);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}