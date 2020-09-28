package com.syrgdev.testapplicationforpikabu.posts.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.R;
import com.syrgdev.testapplicationforpikabu.common.view.ViewFactory;

import java.util.List;
import java.util.Objects;

public class PostsViewImpl extends PostsView
        implements PostsAdapter.Observer {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;
    TextView mEmptyListTextView, mErrorMessageTextView;
    PostsAdapter mPostsAdapter;
    View mNoInternetView;
    Button mRetry;

    public PostsViewImpl(LayoutInflater layoutInflater, ViewGroup container, ViewFactory viewFactory) {

        setRootView(layoutInflater.inflate(R.layout.view_posts, container, false));

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_posts);

        mProgressBar = findViewById(R.id.progress_bar);
        mEmptyListTextView = findViewById(R.id.empty_list_text_view);
        mErrorMessageTextView = findViewById(R.id.error_text_view);
        mNoInternetView = findViewById(R.id.no_internet_view);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRetry = findViewById(R.id.retry_button);
        mRetry.setOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onRetryButtonClicked();
            }
        });

        mPostsAdapter = new PostsAdapter(viewFactory, this);
        mRecyclerView.setAdapter(mPostsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Чтобы не мерцал экран при обновлении состояния виджета сохранненого поста
        ((SimpleItemAnimator) Objects.requireNonNull(mRecyclerView.getItemAnimator())).setSupportsChangeAnimations(false);
    }

    public void bind(List<PostData> postDataList) {
        if (postDataList != null && postDataList.size() != 0) {
            mPostsAdapter.setPosts(postDataList);
        }
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

    public void showEmptyListTextView() {
        mEmptyListTextView.setVisibility(View.VISIBLE);
    }

    public void hideEmptyListTextView() {
        mEmptyListTextView.setVisibility(View.GONE);
    }

    public void showEmptySavedPostsTextView(String emptySavedList) {
        showEmptyListTextView();
        mEmptyListTextView.setText(emptySavedList);
    }

    public void showNoInternetView() {
        mNoInternetView.setVisibility(View.VISIBLE);
    }

    public void hideNoInternetView() {
        mNoInternetView.setVisibility(View.GONE);
    }

    @Override
    public void fetchPostsStarted() {
        hideNoInternetView();
        showProgressBar();
    }

    @Override
    public void onPostsFetched(List<PostData> postDataList) {
        hideProgressBar();
        hideErrorMessage();
        if (!postDataList.isEmpty()) {
            showRecyclerView();
            bind(postDataList);
        } else {
            showEmptyListTextView();
            hideRecyclerView();
        }
    }

    @Override
    public void fetchFailed(Throwable throwable) {

        hideRecyclerView();
        hideProgressBar();
        showErrorMessage();

    }

    // 2 фрагмента используют этот view.
    // Вызывается когда SavedPostsFragment получает список сохраненных статей.
    @Override
    public void onSavedPostsFetched(List<PostData> savedPosts) {
        hideProgressBar();
        hideNoInternetView();
        if (savedPosts.isEmpty()) {
            mPostsAdapter.setPosts(savedPosts);
            showEmptySavedPostsTextView("У вас нет сохраненных статей.");
        } else {
            onPostsFetched(savedPosts);
        }
    }

    @Override
    public void noInternetConnection() {
        showNoInternetView();
    }

    @Override
    public void onItemClicked(PostData postData) {
        for (Observer observer : getObservers()) {
            observer.onItemClicked(postData);
        }
    }

    @Override
    public void onSaveClicked(PostData postData) {
        for (Observer observer : getObservers()) {
            observer.onSaveClicked(postData);
        }
    }

    @Override
    public void onPostUpdated(PostData postData) {
        mPostsAdapter.onPostUpdated(postData);
    }

    // 2 фрагмента используют этот view.
    // Если создается фрагмент сохраненных постов вызывается этот метод
    @Override
    public void setToolbarTitle(String toolbarTitle) {
        mToolbar.setTitle(toolbarTitle);
    }
}