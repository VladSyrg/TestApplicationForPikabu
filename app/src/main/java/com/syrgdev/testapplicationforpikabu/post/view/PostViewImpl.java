package com.syrgdev.testapplicationforpikabu.post.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.R;
import com.syrgdev.testapplicationforpikabu.common.view.ViewFactory;
import com.syrgdev.testapplicationforpikabu.posts.view.postsitem.ImagesAdapter;

public class PostViewImpl extends PostView {

    Toolbar mToolbar;
    TextView mTitleTextView, mBodyTextView, mErrorMessageTextView;
    Button mSaveButton;
    RecyclerView mRecyclerView;
    private ImagesAdapter mImagesAdapter;
    ProgressBar mProgressBar;
    private PostData mPostData;

    public PostViewImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.view_post, container, false));

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Статья");
        mToolbar.setNavigationOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onNavigateBackPressed();
            }
        });

        mTitleTextView = findViewById(R.id.title_text_view);
        mBodyTextView = findViewById(R.id.body_text_view);
        mErrorMessageTextView = findViewById(R.id.error_text_view);
        mSaveButton = findViewById(R.id.save_button);
        mRecyclerView = findViewById(R.id.recycler_view_for_images);
        mProgressBar = findViewById(R.id.progress_bar);

        mSaveButton.setOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onSaveClicked(mPostData);
            }
        });

        mImagesAdapter = new ImagesAdapter();
        mRecyclerView.setAdapter(mImagesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void bind(PostData postData) {
        mPostData = postData;
        hideProgressBar();
        mTitleTextView.setText(postData.getTitle());
        if (postData.getBody() != null) {
            showBodyTextView();
            mBodyTextView.setText(postData.getBody());
        } else {
            hideBodyTextView();
        }
        if (postData.getImages() != null && postData.getImages().length != 0) {
            showRecyclerView();
            mImagesAdapter.setImages(postData.getImages());
        } else {
            hideRecyclerView();
        }
        mSaveButton.setText(
                postData.isSaved()
                        ? getString(R.string.remove)
                        : getString(R.string.save)
        );
    }

    @Override
    public void onPostUpdated(PostData postData) {
        bind(postData);
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    public void showErrorMessage() {
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }

    public void hideErrorMessage() {
        mErrorMessageTextView.setVisibility(View.GONE);
    }

    public void showRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void hideRecyclerView() {
        mRecyclerView.setVisibility(View.GONE);
    }

    public void showBodyTextView() {
        mBodyTextView.setVisibility(View.VISIBLE);
    }

    public void hideBodyTextView() {
        mBodyTextView.setVisibility(View.GONE);
    }

}
