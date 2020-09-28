package com.syrgdev.testapplicationforpikabu.posts.presenter;

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
import com.syrgdev.testapplicationforpikabu.posts.usecase.FetchPostsUseCase;
import com.syrgdev.testapplicationforpikabu.posts.usecase.UpdatePostUseCase;
import com.syrgdev.testapplicationforpikabu.posts.view.PostsView;

import java.util.List;

import javax.inject.Inject;

public class PostsFragment extends BaseFragment
        implements PostsView.Observer,
        FetchPostsUseCase.Observer,
        UpdatePostUseCase.Observer {

    @Inject
    ViewFactory mViewFactory;

    @Inject
    FetchPostsUseCase mFetchPostsUseCase;

    @Inject
    UpdatePostUseCase mUpdatePostUseCase;

    private PostsView mPostsView;

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
        mPostsView = mViewFactory.newViewInstance(PostsView.class, container);
        return mPostsView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPostsView.registerObserver(this);
        mFetchPostsUseCase.registerObserver(this);
        mUpdatePostUseCase.registerObserver(this);
        fetchPosts();
    }

    private void fetchPosts() {
        if (!isInternetAvailable()) {
            mPostsView.noInternetConnection();
        } else {
            mPostsView.fetchPostsStarted();
            mFetchPostsUseCase.fetchPosts();
        }
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPostsView.unregisterObserver(this);
        mFetchPostsUseCase.unregisterObserver(this);
        mUpdatePostUseCase.unregisterObserver(this);
    }

    @Override
    public void onPostsFetched(List<PostData> posts) {
        mPostsView.onPostsFetched(posts);
    }

    @Override
    public void onFetchFailed(Throwable throwable) {
        mPostsView.fetchFailed(throwable);
    }

    @Override
    public void onPostsFetchedFromCash(List<PostData> posts) {
        mPostsView.onPostsFetched(posts);
    }

    @Override
    public void onItemClicked(PostData postData) {
        mScreensManager.navigateToPostFragment(requireActivity(), postData);
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
        fetchPosts();
    }

    @Override
    public void onPostUpdated(PostData postData) {
        mPostsView.onPostUpdated(postData);
    }

    @Override
    public void onUpdateFailed() {
        Toast.makeText(getContext(), "Не удалось изменить пост.", Toast.LENGTH_SHORT).show();
    }
}