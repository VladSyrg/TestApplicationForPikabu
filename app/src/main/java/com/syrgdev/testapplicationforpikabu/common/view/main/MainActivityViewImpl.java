package com.syrgdev.testapplicationforpikabu.common.view.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syrgdev.testapplicationforpikabu.R;

public class MainActivityViewImpl extends MainActivityView {

    BottomNavigationView mBottomNavigationView;
    private View mFragmentContainer;

    public MainActivityViewImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.view_main_activity, container));
        mBottomNavigationView = findViewById(R.id.nav_view);
        mFragmentContainer = findViewById(R.id.nav_host_fragment);
    }

    @Override
    public BottomNavigationView getBottomNavigationView() {
        return mBottomNavigationView;
    }

    @Override
    public int getFragmentContainerId() {
        return mFragmentContainer.getId();
    }
}
