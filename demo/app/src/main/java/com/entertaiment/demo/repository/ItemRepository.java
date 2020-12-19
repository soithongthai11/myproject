package com.entertaiment.demo.repository;

import android.app.Application;

import com.entertaiment.demo.commonbase.repository.Repository;

public class ItemRepository implements Repository {

    private static ItemRepository sInstance;

    private ItemRepository (Application application) {

    }

    public static ItemRepository getInstance(Application application) {
        synchronized (ItemRepository.class) {
            if (sInstance == null) {
                sInstance = new ItemRepository(application);
            }
        }
        return sInstance;
    }
}
