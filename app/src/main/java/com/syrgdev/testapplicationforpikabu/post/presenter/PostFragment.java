package com.syrgdev.testapplicationforpikabu.post.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.ScreensManager;
import com.syrgdev.testapplicationforpikabu.common.presenter.base.BaseFragment;
import com.syrgdev.testapplicationforpikabu.common.view.ViewFactory;
import com.syrgdev.testapplicationforpikabu.post.view.PostView;
import com.syrgdev.testapplicationforpikabu.posts.usecase.UpdatePostUseCase;

import javax.inject.Inject;

import static com.syrgdev.testapplicationforpikabu.Constants.POST_DATA;

public class PostFragment extends BaseFragment
        implements PostView.Observer,
        UpdatePostUseCase.Observer {

    @Inject
    ScreensManager mScreensManager;

    @Inject
    ViewFactory mViewFactory;

    @Inject
    UpdatePostUseCase mUpdatePostUseCase;

    private PostView mPostView;
    private PostData mPostData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPostView = mViewFactory.newViewInstance(PostView.class, container);
        assert getArguments() != null;
        mPostData = (PostData) getArguments().getSerializable(POST_DATA);
        return mPostView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPostView.registerObserver(this);
        mUpdatePostUseCase.registerObserver(this);
        fetchPost(mPostData);
    }

    private void fetchPost(PostData postData) {
        mPostView.bind(postData);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPostView.unregisterObserver(this);
        mUpdatePostUseCase.unregisterObserver(this);
    }

    @Override
    public void onNavigateBackPressed() {
        mScreensManager.navigateBack(requireActivity());
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
    public void onPostUpdated(PostData postData) {
        mPostView.onPostUpdated(postData);
    }

    @Override
    public void onUpdateFailed() {

    }
}