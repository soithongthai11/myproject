package com.entertaiment.demo.repository;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.entertaiment.demo.api.ApiService;
import com.entertaiment.demo.client.RetrofitClient;
import com.entertaiment.demo.commonbase.repository.Repository;
import com.entertaiment.demo.model.LogInResult;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ItemRepository implements Repository {

    private static ItemRepository sInstance;
    private static ApiService sRetrofitInstance;
    private String mPhoneNumber;

    private MutableLiveData<Boolean> mValidPhone = new MutableLiveData<>();
    private MutableLiveData<Boolean> mRequestSuccess = new MutableLiveData<>();

    private ItemRepository (Application application) {
        sRetrofitInstance = RetrofitClient.getClient();
    }

    public static ItemRepository getInstance(Application application) {
        synchronized (ItemRepository.class) {
            if (sInstance == null) {
                sInstance = new ItemRepository(application);
            }
        }
        return sInstance;
    }

    public boolean checkValidPhoneNumer(String phoneCode, String phoneNumber) {
        boolean isValid = false;
        if(!TextUtils.isEmpty(phoneNumber) && android.util.Patterns.PHONE.matcher(phoneNumber).matches()) {
            mValidPhone.setValue(true);
            mPhoneNumber = phoneCode + phoneNumber;
            isValid = true;
        } else {
            mValidPhone.setValue(false);
        }
        return isValid;
    }

    public void requestOTP(String phoneCode, String phoneNumber, String via) {

        sRetrofitInstance.requestOTP(phoneCode, phoneNumber, via).enqueue(new Callback<LogInResult>() {
            @Override
            public void onResponse(Call<LogInResult> call, Response<LogInResult> response) {
                int code = response.code();
                if (code == 200) {
                    LogInResult logInResult = response.body();
                    mRequestSuccess.setValue(true);
                } else {
                    mRequestSuccess.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<LogInResult> call, Throwable t) {
                Log.d("TAG", "AAA");
            }
        });
    }

    public MutableLiveData<Boolean> getValidPhone() {
        return mValidPhone;
    }

    public MutableLiveData<Boolean> getRequestSuccess() {
        return mRequestSuccess;
    }
}
