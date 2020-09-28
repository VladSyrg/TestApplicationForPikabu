package com.syrgdev.testapplicationforpikabu.common;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syrgdev.testapplicationforpikabu.R;
import com.syrgdev.testapplicationforpikabu.common.presenter.MainActivity;

import static com.syrgdev.testapplicationforpikabu.Constants.POST_DATA;

public class ScreensManager {

    private int mNavHostFragmentId;

    public ScreensManager() {
        mNavHostFragmentId = R.id.nav_host_fragment;
    }

    public void init(BottomNavigationView bottomNavigationView,
                     Activity activity,
                     @IdRes int navHostFragmentId) {
        setupBottomNavigationViewWithNavController(
                bottomNavigationView,
                findNavController(activity, navHostFragmentId));
    }

    public NavController findNavController(Activity activity,
                                           @IdRes int navHostFragmentId) {
        Fragment navHostFragment = ((MainActivity) activity).getSupportFragmentManager().findFragmentById(navHostFragmentId);
        assert navHostFragment != null;
        return NavHostFragment.findNavController(navHostFragment);
    }

    public void setupBottomNavigationViewWithNavController(BottomNavigationView bottomNavigationView,
                                                           NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public void navigateBack(FragmentActivity activity) {
        findNavController(activity, mNavHostFragmentId).navigateUp();
    }

    public void navigateToPostFragment(FragmentActivity activity, PostData postData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(POST_DATA, postData);
        findNavController(activity, mNavHostFragmentId)
                .navigate(R.id.action_posts_to_post, bundle);
    }

    public void navigateFromSavedFragmentToPostFragment(FragmentActivity activity, PostData postData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(POST_DATA, postData);
        findNavController(activity, mNavHostFragmentId)
                .navigate(R.id.action_saved_to_post, bundle);
    }
}
