package com.syrgdev.testapplicationforpikabu.posts.view;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.view.base.BaseObservableView;

import java.util.List;

public abstract class PostsView extends BaseObservableView<PostsView.Observer> {

    public interface Observer {

        void onItemClicked(PostData postData);

        void onSaveClicked(PostData postData);

        void onRetryButtonClicked();
    }

    public abstract void fetchPostsStarted();

    public abstract void onPostUpdated(PostData postData);

    public abstract void setToolbarTitle(String toolbarTitle);

    public abstract void onPostsFetched(List<PostData> postDataList);

    public abstract void fetchFailed(Throwable throwable);

    public abstract void onSavedPostsFetched(List<PostData> savedPosts);

    public abstract void noInternetConnection();

}
