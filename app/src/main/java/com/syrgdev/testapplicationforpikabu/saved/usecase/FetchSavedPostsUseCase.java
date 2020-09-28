package com.syrgdev.testapplicationforpikabu.saved.usecase;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.observable.BaseObservable;
import com.syrgdev.testapplicationforpikabu.persistance.PersistenceManager;

import java.util.List;

public class FetchSavedPostsUseCase extends BaseObservable<FetchSavedPostsUseCase.Observer>
        implements PersistenceManager.Observer {

    public interface Observer {
        void onSavedPostsFetched(List<PostData> savedPosts);
    }

    PersistenceManager mPersistenceManager;

    public FetchSavedPostsUseCase(PersistenceManager persistenceManager) {
        mPersistenceManager = persistenceManager;
    }

    public void fetchSavedPosts() {
        mPersistenceManager.registerObserver(this);
        mPersistenceManager.fetchSavedPosts();
    }

    @Override
    public void onSavedPostsFetched(List<PostData> savedPosts) {
        mPersistenceManager.unregisterObserver(this);

        for (Observer observer : getObservers()) {
            observer.onSavedPostsFetched(savedPosts);
        }
    }

    @Override
    public void onPostsFetched(List<PostData> posts) {
        //noop
    }

    @Override
    public void onFetchFailed(Throwable e) {
        //noop
    }

    @Override
    public void onPostsFetchedFromCash(List<PostData> postDataList) {
        //noop
    }

}
