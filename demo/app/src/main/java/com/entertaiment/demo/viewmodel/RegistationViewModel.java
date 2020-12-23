package com.entertaiment.demo.viewmodel;

import android.app.Application;

import com.entertaiment.demo.commonbase.viewmodel.BaseViewModel;
import com.entertaiment.demo.repository.ItemRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class RegistationViewModel extends BaseViewModel {

    private static ItemRepository sItemRepository;
    private MutableLiveData<Boolean> mRequestSuccess;

    public RegistationViewModel(@NonNull Application application) {
        super(application);
        sItemRepository = ItemRepository.getInstance(application);
    }

    public void createProfiles(String email, String firstName, String nric, String lastName) {
        sItemRepository.createProfiles(email, firstName, nric, lastName);
    }

    public MutableLiveData<Boolean> getRequestSuccess() {
        return mRequestSuccess = (MutableLiveData<Boolean>) Transformations.map(sItemRepository.getCreateProfileSuccess(), input -> input);
    }
}
