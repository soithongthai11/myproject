package com.entertaiment.demo.commonbase.view;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.entertaiment.demo.commonbase.viewmodel.BaseViewModel;

public abstract class BaseActivity<V extends BaseViewModel> extends
        AppCompatActivity implements Observer {

    private V mViewModel;

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * register listener to update view when data changed
     */
    public abstract void observeViewModel();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * start activity state block
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(android.R.id.content).requestFocus();
        findViewById(android.R.id.content).setFocusable(true);
        setContentView(getLayoutId());
        overridePendingTransition(0, 0);
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        observeViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * end activity state block
     */
}
