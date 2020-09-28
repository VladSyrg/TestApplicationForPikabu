package com.syrgdev.testapplicationforpikabu.persistance;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.network.PikabuTestAPI;
import com.syrgdev.testapplicationforpikabu.network.PostSchema;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersistenceManagerImpl extends PersistenceManager {

    private List<PostData> mPosts = new ArrayList<>();

    PikabuTestAPI mPikabuTestAPI;

    public PersistenceManagerImpl(PikabuTestAPI pikabuTestAPI) {
        mPikabuTestAPI = pikabuTestAPI;
    }

    @Override
    public void updatePost(PostData postData) {

        //в списке постов
        for (int i = 0; i < mPosts.size(); i++) {
            if (mPosts.get(i).getId().equals(postData.getId())) {
                mPosts.remove(i);
                mPosts.add(i, postData);
                break;
            }
        }

    }

    @Override
    public void fetchPosts() {
        if (mPosts == null || mPosts.size() == 0) {
            mPikabuTestAPI.getPosts()
                    .map(postSchemas -> {
                        List<PostData> postDataList = new ArrayList<>();
                        for (PostSchema postSchema : postSchemas) {
                            postDataList.add(Converters.toPostData(postSchema));
                        }
                        return postDataList;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<PostData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<PostData> posts) {

                            for (Observer observer : getObservers()) {
                                mPosts.addAll(posts);
                                observer.onPostsFetched(posts);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            for (Observer observer : getObservers()) {
                                observer.onFetchFailed(e);
                            }
                        }
                    });
        } else {
            for (Observer observer : getObservers()) {
                observer.onPostsFetchedFromCash(mPosts);
            }
        }
    }

    @Override
    public void fetchSavedPosts() {
        List<PostData> savedPosts = new ArrayList<>();
        for (PostData postData : mPosts) {
            if (postData.isSaved()) {
                savedPosts.add(postData);
            }
        }
        for (Observer observer : getObservers()) {
            observer.onSavedPostsFetched(savedPosts);
        }
    }


}