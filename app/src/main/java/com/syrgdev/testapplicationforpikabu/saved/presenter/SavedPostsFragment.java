package com.syrgdev.testapplicationforpikabu.saved.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.ScreensManager;
import com.syrgdev.testapplicationforpikabu.common.presenter.base.BaseFragment;
import com.syrgdev.testapplicationforpikabu.common.view.ViewFactory;
import com.syrgdev.testapplicationforpikabu.posts.usecase.UpdatePostUseCase;
import com.syrgdev.testapplicationforpikabu.posts.view.PostsView;
import com.syrgdev.testapplicationforpikabu.saved.usecase.FetchSavedPostsUseCase;

import java.util.List;

import javax.inject.Inject;

public class SavedPostsFragment extends BaseFragment
        implements PostsView.Observer,
        FetchSavedPostsUseCase.Observer,
        UpdatePostUseCase.Observer {

    @Inject
    ViewFactory mViewFactory;

    @Inject
    FetchSavedPostsUseCase mFetchSavedPostsUseCase;

    @Inject
    UpdatePostUseCase mUpdatePostUseCase;

    private PostsView mSavedPostsView;

    @Inject
    ScreensManager mScreensManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSavedPostsView = mViewFactory.newViewInstance(PostsView.class, container);
        mSavedPostsView.setToolbarTitle("Сохраненные посты");
        return mSavedPostsView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mSavedPostsView.registerObserver(this);
        mFetchSavedPostsUseCase.registerObserver(this);
        mUpdatePostUseCase.registerObserver(this);
        fetchSavedPosts();

    }

    private void fetchSavedPosts() {

        if (!isInternetAvailable()) {
            mSavedPostsView.noInternetConnection();
        } else {
            mSavedPostsView.fetchPostsStarted();
            mFetchSavedPostsUseCase.fetchSavedPosts();
        }

    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onStop() {
        super.onStop();
        mSavedPostsView.unregisterObserver(this);
        mFetchSavedPostsUseCase.unregisterObserver(this);
        mUpdatePostUseCase.unregisterObserver(this);
    }


    @Override
    public void onPostUpdated(PostData postData) {
        fetchSavedPosts();
    }

    @Override
    public void onUpdateFailed() {
        Toast.makeText(getContext(), "Не удалось изменить пост.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(PostData postData) {
        mScreensManager.navigateFromSavedFragmentToPostFragment(requireActivity(), postData);
    }

    @Override
    public void onSaveClicked(PostData postData) {
        mUpdatePostUseCase.updatePost(
                postData.isSaved()
                        ? postData.toBuilder().isSaved(false).build()
                        : postData.toBuilder().isSaved(true).build()
        );
    }

    @Override
    public void onRetryButtonClicked() {
        fetchSavedPosts();
    }

    @Override
    public void onSavedPostsFetched(List<PostData> savedPosts) {
        mSavedPostsView.onSavedPostsFetched(savedPosts);
    }
}