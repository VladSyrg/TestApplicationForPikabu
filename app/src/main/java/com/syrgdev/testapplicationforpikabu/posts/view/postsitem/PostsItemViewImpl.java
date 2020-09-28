package com.syrgdev.testapplicationforpikabu.posts.view.postsitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.R;

public class PostsItemViewImpl extends PostsItemView {

    TextView mTitleTextView, mBodyTextView;
    RecyclerView mRecyclerView;
    ImagesAdapter mImagesAdapter;
    private PostData mPostData;
    Button mSaveButton;

    public PostsItemViewImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.view_post_item, container, false));

        mSaveButton = findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onSavedClicked(mPostData);
            }
        });

        mTitleTextView = findViewById(R.id.title_text_view);
        mBodyTextView = findViewById(R.id.body_text_view);
        mRecyclerView = findViewById(R.id.recycler_view_for_images);

        mTitleTextView.setOnClickListener(v -> {
            for (Observer observer : getObservers()) {
                observer.onItemClicked(mPostData);
            }
        });

        mImagesAdapter = new ImagesAdapter();
        mRecyclerView.setAdapter(mImagesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void bind(PostData postData) {
        mPostData = postData;
        mTitleTextView.setText(postData.getTitle());
        if (postData.getBody() != null) {
            mBodyTextView.setVisibility(View.VISIBLE);
            mBodyTextView.setText(postData.getBody());
        } else {
            mBodyTextView.setVisibility(View.GONE);
        }
        if (postData.getImages() != null && postData.getImages().length != 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mImagesAdapter.setImages(postData.getImages());
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
        mSaveButton.setText(
                postData.isSaved()
                        ? getString(R.string.remove)
                        : getString(R.string.save)
        );
    }

}