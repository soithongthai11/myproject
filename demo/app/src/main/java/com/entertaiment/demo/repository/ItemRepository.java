package com.entertaiment.demo.repository;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.entertaiment.demo.api.ApiService;
import com.entertaiment.demo.client.RetrofitClient;
import com.entertaiment.demo.commonbase.repository.Repository;
import com.entertaiment.demo.model.LogInResult;
import com.entertaiment.demo.model.Profile;
import com.entertaiment.demo.model.ProfileModel;
import com.entertaiment.demo.model.VerifyRegistered;

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

    private MutableLiveData<Boolean> mRequestSuccess = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsRegister = new MutableLiveData<>();
    private MutableLiveData<Boolean> mOtpValid = new MutableLiveData<>();

    private MutableLiveData<Boolean> mCreateProfileSuccess = new MutableLiveData<>();

    private MutableLiveData<VerifyRegistered> mProfileData = new MutableLiveData<>();
    private MutableLiveData<Profile> mDetailProfileData = new MutableLiveData<>();

    private VerifyRegistered mVerifyRegistered;

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

    public void verifyRegistered(String phoneCode, String phoneNumber, String otp) {

        sRetrofitInstance.verifyRegistered(phoneCode, phoneNumber, otp).enqueue(new Callback<VerifyRegistered>() {
            @Override
            public void onResponse(Call<VerifyRegistered> call, Response<VerifyRegistered> response) {
                int code = response.code();
                if(code == 200) {
                    mVerifyRegistered = response.body();
                    mProfileData.setValue(mVerifyRegistered);
                    mIsRegister.setValue(mVerifyRegistered.getIsRegistered());
                }else {
                    mOtpValid.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<VerifyRegistered> call, Throwable t) {

            }
        });
    }

    public void createProfiles(String email, String firstName, String nric, String lastName) {
        Map<String,Object> data=new HashMap<>();
        data.put("email",email);
        data.put("firstname",firstName);
        data.put("id_number",nric);
        data.put("lastname",lastName);

        sRetrofitInstance.createProfile(mVerifyRegistered.getToken(), data).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                int code = response.code();
                if(code == 200) {
                    ProfileModel profileModel = response.body();
                    mVerifyRegistered.setFirstname(profileModel.getFistName());
                    mVerifyRegistered.setLastname(profileModel.getLastName());
                    mProfileData.setValue(mVerifyRegistered);
                    mCreateProfileSuccess.setValue(true);
                }else {

                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {

            }
        });
    }

    public void getProfiles() {
        sRetrofitInstance.getProfile(mVerifyRegistered.getToken()).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                int code = response.code();
                if(code == 200) {
                    mDetailProfileData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Boolean> getRequestSuccess() {
        return mRequestSuccess;
    }

    public MutableLiveData<Boolean> getIsRegister() {
        return mIsRegister;
    }

    public MutableLiveData<Boolean> getCreateProfileSuccess() {
        return mCreateProfileSuccess;
    }

    public MutableLiveData<Boolean> getOtpValid() {
        return mOtpValid;
    }

    public MutableLiveData<VerifyRegistered> getProfileData() {
        return mProfileData;
    }

    public MutableLiveData<Profile> getDetailProfileData() {
        return mDetailProfileData;
    }
}
