package com.entertaiment.demo.commonbase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull final Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
