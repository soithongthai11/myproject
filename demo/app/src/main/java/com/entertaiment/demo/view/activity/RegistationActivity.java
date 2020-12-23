package com.entertaiment.demo.view.activity;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.view.BaseActivity;
import com.entertaiment.demo.utils.Util;
import com.entertaiment.demo.viewmodel.RegistationViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class RegistationActivity extends BaseActivity<RegistationViewModel> implements View.OnClickListener {

    private TextInputLayout mEdFirstName, mEdLastName, mEdNric, mEdEmail;
    private Button mCreateAcc;


    @Override
    public int getLayoutId() {
        return R.layout.activity_registation;
    }

    @Override
    public void observeViewModel() {
        getViewModel().getRequestSuccess().observe(this, aBoolean -> {
            if(aBoolean) {
                Intent startIntentActivity = new Intent(this, MainActivity.class);
                startActivity(startIntentActivity);
                finish();
            }
        });
    }

    @Override
    public RegistationViewModel getViewModel() {
        return ViewModelProviders.of(this).get(RegistationViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    @Override
    public void onChanged(Object o) {

    }

    private void initView() {
        mEdFirstName = findViewById(R.id.ed_first_name);
        mEdLastName  = findViewById(R.id.ed_last_name);
        mEdNric      = findViewById(R.id.ed_nric);
        mEdEmail     = findViewById(R.id.ed_email);

        mCreateAcc = findViewById(R.id.bt_create_acc);
    }

    private void initListener() {
        mCreateAcc.setOnClickListener(this);

        mEdFirstName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Util.isEmpty(mEdFirstName.getEditText().getText())) {
                    mEdFirstName.setError(null);
                }
            }
        });

        mEdLastName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Util.isEmpty(mEdLastName.getEditText().getText())) {
                    mEdLastName.setError(null);
                }
            }
        });

        mEdNric.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Util.isEmpty(mEdNric.getEditText().getText())) {
                    mEdNric.setError(null);
                }
            }
        });

        mEdEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Util.isValidEmail(mEdEmail.getEditText().getText())) {
                    mEdEmail.setError(null);
                }
            }
        });
    }

    private boolean checkValidEditText() {
        boolean valid = true;
        if(Util.isEmpty(mEdFirstName.getEditText().getText())) {
            mEdFirstName.setError(getResources().getString(R.string.first_name_valid_string));
            valid = false;
        }

        if(Util.isEmpty(mEdLastName.getEditText().getText())) {
            mEdLastName.setError(getResources().getString(R.string.last_name_valid_string));
            valid = false;
        }

        if(Util.isEmpty(mEdNric.getEditText().getText())) {
            mEdNric.setError(getResources().getString(R.string.nric_valid_string));
            valid = false;
        }

        if(!Util.isValidEmail(mEdEmail.getEditText().getText())) {
            mEdEmail.setError(getResources().getString(R.string.email_valid_string));
            valid = false;
        }
        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_create_acc:
                if(checkValidEditText()) {
                    String firstName = mEdFirstName.getEditText().getText().toString();
                    String lastName  = mEdLastName.getEditText().getText().toString();
                    String nric      = mEdNric.getEditText().getText().toString();
                    String email     = mEdEmail.getEditText().getText().toString();
                    getViewModel().createProfiles(email, firstName, nric, lastName);
                }
                break;
            default:
                break;
        }
    }
}