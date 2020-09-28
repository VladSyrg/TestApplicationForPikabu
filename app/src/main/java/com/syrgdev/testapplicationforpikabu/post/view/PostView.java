package com.syrgdev.testapplicationforpikabu.post.view;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.view.base.BaseObservableView;

public abstract class PostView extends BaseObservableView<PostView.Observer> {

    public interface Observer {

        void onNavigateBackPressed();

        void onSaveClicked(PostData postData);

    }

    public abstract void bind(PostData postData);

    public abstract void onPostUpdated(PostData postData);

}
