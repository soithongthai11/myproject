package com.entertaiment.demo.viewmodel;

import android.app.Application;

import com.entertaiment.demo.commonbase.viewmodel.BaseViewModel;
import com.entertaiment.demo.model.Profile;
import com.entertaiment.demo.model.VerifyRegistered;
import com.entertaiment.demo.repository.ItemRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class AppMainViewModel extends BaseViewModel {

    private static ItemRepository sItemRepository;
    private MutableLiveData<VerifyRegistered> mProfileData;
    private MutableLiveData<Profile> mDetailProfileData;

    public AppMainViewModel(@NonNull Application application) {
        super(application);
        sItemRepository = ItemRepository.getInstance(application);
    }

    public MutableLiveData<VerifyRegistered> getProfileData() {
        return mProfileData = (MutableLiveData<VerifyRegistered>) Transformations.map(sItemRepository.getProfileData(), input -> input);
    }

    public MutableLiveData<Profile> getDetailProfileData() {
        return mDetailProfileData = (MutableLiveData<Profile>) Transformations.map(sItemRepository.getDetailProfileData(), input -> input);
    }

    public void getDetailProfile() {
        sItemRepository.getProfiles();
    }
}
