package com.syrgdev.testapplicationforpikabu.common.view.main;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syrgdev.testapplicationforpikabu.common.view.base.BaseObservableView;

public abstract class MainActivityView extends BaseObservableView<MainActivityView.Observer> {

    public abstract BottomNavigationView getBottomNavigationView();

    public abstract int getFragmentContainerId();

    public interface Observer {
    }
}
