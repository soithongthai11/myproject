package com.entertaiment.demo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.entertaiment.demo.R;
import com.entertaiment.demo.commonbase.viewmodel.BaseViewModel;
import com.entertaiment.demo.repository.ItemRepository;

public class MainViewModel extends BaseViewModel {

    private static ItemRepository sItemRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        sItemRepository = ItemRepository.getInstance(application);
    }
}