package com.entertaiment.demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.entertaiment.demo.utils.Util;
import com.entertaiment.demo.viewmodel.MainViewModel;

public class LogInActivity extends BaseActivity<MainViewModel> implements View.OnClickListener, TextWatcher {
    private static final int SIGN_FORM = 0;
    private static final int OTP_FORM  = 1;

    private int mCurrentFormID = SIGN_FORM;

    private Toolbar mToolBar;
    private Button  mBtContinue;
    private EditText mEdPhoneNumber;
    private Spinner mListPhoneCode;
    private TextView mTvOtpTime;
    private TextView mEdOtp1, mEdOtp2, mEdOtp3, mEdOtp4, mEdOtp5, mEdOtp6;

    private LinearLayout mSignForm, mOtpForm;

    private CountDownTimer mOtpTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void observeViewModel() {
        getViewModel().getRequestSuccess().observe(this, aBoolean -> {
            setValidView(aBoolean);
            if (aBoolean) {
                changeFragment(OTP_FORM);
            }
        });

        getViewModel().getIsRegister().observe(this, aBoolean -> {
            Intent startActivity;
            stopCountTimeOtp();
            if(aBoolean) {
                startActivity = new Intent(this, MainActivity.class);
            } else {
                startActivity = new Intent(this, RegistationActivity.class);
            }
            startActivity(startActivity);
            finish();
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

        mSignForm = findViewById(R.id.content_signin_form);
        mOtpForm = findViewById(R.id.content_otp_form);

        mEdOtp1 = findViewById(R.id.et_otp1);
        mEdOtp2 = findViewById(R.id.et_otp2);
        mEdOtp3 = findViewById(R.id.et_otp3);
        mEdOtp4 = findViewById(R.id.et_otp4);
        mEdOtp5 = findViewById(R.id.et_otp5);
        mEdOtp6 = findViewById(R.id.et_otp6);

    }

    private void initListener() {
        mTvOtpTime.setOnClickListener(this);

        mBtContinue.setOnClickListener(this);
        mEdPhoneNumber.addTextChangedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Constraints.getPhoneCode());
        mListPhoneCode.setAdapter(adapter);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mEdOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 1) {
                    mEdOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 1) {
                    mEdOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 1) {
                    mEdOtp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 1) {
                    mEdOtp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 1) {
                    mEdOtp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
            case R.id.tv_time_otp:
                if(mTvOtpTime.getText().toString().equalsIgnoreCase("Resend")) {
                    getViewModel().requestOTP(mListPhoneCode.getSelectedItem().toString(), mEdPhoneNumber.getText().toString(), "sms");
                }
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
            boolean isValid = Util.checkValidPhoneNumber(mListPhoneCode.getSelectedItem().toString(), mEdPhoneNumber.getText().toString());
            if (isValid) {
                displayAlertDialog();
            }
            setValidView(isValid);
        } else {
            if(getOtpToken() != null) {
                getViewModel().verifyRegistered(mListPhoneCode.getSelectedItem().toString(), mEdPhoneNumber.getText().toString(), getOtpToken());
            }
        }
    }

    private void countTimeOtp() {
        mTvOtpTime.setTextColor(getResources().getColor(R.color.black));
        TextView notice = findViewById(R.id.tv_notice);
        notice.setText(getResources().getText(R.string.wait_string));
        mEdOtp1.requestFocus();
        mOtpTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvOtpTime.setText("(00:"+millisUntilFinished/1000+")");
            }

            @Override
            public void onFinish() {
                mTvOtpTime.setTextColor(getResources().getColor(R.color.dodger_blue));
                mTvOtpTime.setText(getResources().getText(R.string.resend_string));
                notice.setText(getResources().getText(R.string.notice_string));
            }
        }.start();
    }

    private void stopCountTimeOtp() {
        mOtpTimer.cancel();
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

    private void setValidView(boolean isValid) {
        if(isValid) {
            findViewById(R.id.view_valid).setBackgroundColor(ContextCompat.getColor(this, R.color.white_two));
            findViewById(R.id.tv_valid).setVisibility(View.GONE);
        } else {
            findViewById(R.id.view_valid).setBackgroundColor(ContextCompat.getColor(this, R.color.tomato));
            findViewById(R.id.tv_valid).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(mCurrentFormID != OTP_FORM) {
            boolean isValid = Util.checkValidPhoneNumber(mListPhoneCode.getSelectedItem().toString(), mEdPhoneNumber.getText().toString());
            setValidView(isValid);
        }
    }

    private String getOtpToken() {
        String otp = mEdOtp1.getText().toString() + mEdOtp2.getText().toString() + mEdOtp3.getText().toString()
                + mEdOtp4.getText().toString() + mEdOtp5.getText().toString() + mEdOtp6.getText().toString();
        if (otp.length() == 6) {
            return otp;
        }
        return null;
    }
}