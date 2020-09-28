package com.syrgdev.testapplicationforpikabu.posts.usecase;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.observable.BaseObservable;
import com.syrgdev.testapplicationforpikabu.persistance.PersistenceManager;

import java.util.List;

public class FetchPostsUseCase
        extends BaseObservable<FetchPostsUseCase.Observer>
        implements PersistenceManager.Observer {

    public interface Observer {
        void onPostsFetched(List<PostData> posts);

        void onFetchFailed(Throwable throwable);

        void onPostsFetchedFromCash(List<PostData> postDataList);
    }

    @Override
    public void onPostsFetched(List<PostData> posts) {
        mPersistenceManager.unregisterObserver(this);
        for (Observer observer : getObservers()) {
            observer.onPostsFetched(posts);
        }
    }

    @Override
    public void onFetchFailed(Throwable e) {
        mPersistenceManager.unregisterObserver(this);

    }

    @Override
    public void onPostsFetchedFromCash(List<PostData> postDataList) {
        mPersistenceManager.unregisterObserver(this);
        for (Observer observer : getObservers()) {
            observer.onPostsFetchedFromCash(postDataList);
        }
    }

    @Override
    public void onSavedPostsFetched(List<PostData> savedPosts) {
        //noop
    }

    PersistenceManager mPersistenceManager;

    public FetchPostsUseCase(PersistenceManager persistenceManager) {
        mPersistenceManager = persistenceManager;
    }

    public void fetchPosts() {
        mPersistenceManager.registerObserver(this);
        mPersistenceManager.fetchPosts();
    }
}
