package com.syrgdev.testapplicationforpikabu.common.presenter;

import android.os.Bundle;

import com.syrgdev.testapplicationforpikabu.common.ScreensManager;
import com.syrgdev.testapplicationforpikabu.common.presenter.base.BaseActivity;
import com.syrgdev.testapplicationforpikabu.common.view.main.MainActivityView;
import com.syrgdev.testapplicationforpikabu.common.view.ViewFactory;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    MainActivityView mMainActivityView;

    @Inject
    ViewFactory mViewFactory;

    @Inject
    ScreensManager mScreensManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
        mMainActivityView = mViewFactory.newViewInstance(MainActivityView.class, null);
        setContentView(mMainActivityView.getRootView());
        mScreensManager.init(
                mMainActivityView.getBottomNavigationView(),
                this,
                mMainActivityView.getFragmentContainerId());

    }

}