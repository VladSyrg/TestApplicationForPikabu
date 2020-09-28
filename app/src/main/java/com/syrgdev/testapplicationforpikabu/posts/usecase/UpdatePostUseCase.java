package com.syrgdev.testapplicationforpikabu.posts.usecase;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.observable.BaseObservable;
import com.syrgdev.testapplicationforpikabu.persistance.PersistenceManager;

public class UpdatePostUseCase extends BaseObservable<UpdatePostUseCase.Observer> {

    public interface Observer {

        public void onPostUpdated(PostData postData);

        public void onUpdateFailed();

    }

    PersistenceManager mPersistenceManager;

    public UpdatePostUseCase(PersistenceManager persistenceManager) {

        mPersistenceManager = persistenceManager;
    }

    public void updatePost(PostData postData) {
        mPersistenceManager.updatePost(postData);
        for (Observer observer : getObservers()) {
            observer.onPostUpdated(postData);
        }
    }

}
