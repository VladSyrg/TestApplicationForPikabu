package com.syrgdev.testapplicationforpikabu.posts.view.postsitem;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.view.base.BaseObservableView;

public abstract class PostsItemView
        extends BaseObservableView<PostsItemView.Observer> {

    public interface Observer {

        void onItemClicked(PostData postData);

        void onSavedClicked(PostData postData);

    }

    public abstract void bind(PostData postData);
}
