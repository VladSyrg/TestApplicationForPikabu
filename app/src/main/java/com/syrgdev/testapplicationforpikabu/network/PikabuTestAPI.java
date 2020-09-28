package com.syrgdev.testapplicationforpikabu.network;

import com.syrgdev.testapplicationforpikabu.common.PostData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PikabuTestAPI {

    @GET("feed.php")
    public Single<List<PostSchema>> getPosts();

    @GET("story.php?")
    public Single<PostSchema> getPostById(@Query("id") PostData id);

}
