package com.syrgdev.testapplicationforpikabu.persistance;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.observable.BaseObservable;

import java.util.List;

public abstract class PersistenceManager
        extends BaseObservable<PersistenceManager.Observer> {

    public interface Observer {

        void onPostsFetched(List<PostData> posts);

        void onFetchFailed(Throwable e);

        void onPostsFetchedFromCash(List<PostData> postDataList);

        void onSavedPostsFetched(List<PostData> savedPosts);

    }

    public abstract void updatePost(PostData postData);

    public abstract void fetchPosts();

    public abstract void fetchSavedPosts();

}
