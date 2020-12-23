package com.entertaiment.demo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.viewmodel.BaseViewModel;
import com.entertaiment.demo.repository.ItemRepository;

public class MainViewModel extends BaseViewModel {

    private static ItemRepository sItemRepository;

    private MutableLiveData<Boolean> mRequestSuccess;
    private MutableLiveData<Boolean> mIsRegister;

    public MainViewModel(@NonNull Application application) {
        super(application);
        sItemRepository = ItemRepository.getInstance(application);
    }

    public void requestOTP(String phoneCode, String phoneNumber, String via) {
        sItemRepository.requestOTP(phoneCode, phoneNumber, via);
    }

    public void verifyRegistered(String phoneCode, String phoneNumber, String otp) {
        sItemRepository.verifyRegistered(phoneCode, phoneNumber, otp);
    }

    public MutableLiveData<Boolean> getRequestSuccess() {
        return mRequestSuccess = (MutableLiveData<Boolean>) Transformations.map(sItemRepository.getRequestSuccess(), input -> input);
    }

    public MutableLiveData<Boolean> getIsRegister() {
        return mIsRegister = (MutableLiveData<Boolean>) Transformations.map(sItemRepository.getIsRegister(), input -> input);
    }
}