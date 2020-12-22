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

    private MutableLiveData<Boolean> mValidPhone;
    private MutableLiveData<Boolean> mRequestSuccess;

    public MainViewModel(@NonNull Application application) {
        super(application);
        sItemRepository = ItemRepository.getInstance(application);
    }

    public void requestOTP(String phoneCode, String phoneNumber, String via) {
        sItemRepository.requestOTP(phoneCode, phoneNumber, via);
    }

    public boolean checkValidPhoneNumer(String phoneCode, String phoneNumber) {
        return sItemRepository.checkValidPhoneNumer(phoneCode, phoneNumber);
    }

    public MutableLiveData<Boolean> getValidPhone() {
        return mValidPhone = (MutableLiveData<Boolean>) Transformations.map(sItemRepository.getValidPhone(), input -> input);
    }

    public MutableLiveData<Boolean> getRequestSuccess() {
        return mRequestSuccess = (MutableLiveData<Boolean>) Transformations.map(sItemRepository.getRequestSuccess(), input -> input);
    }
}