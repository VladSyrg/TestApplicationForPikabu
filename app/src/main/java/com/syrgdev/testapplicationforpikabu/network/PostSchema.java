package com.syrgdev.testapplicationforpikabu.network;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class PostSchema {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("body")
    @Nullable
    private String mBody;

    @SerializedName("images")
    @Nullable
    private String[] mImages;

    private PostSchema(
            String id,
            String title,
            @Nullable String body,
            @Nullable String[] images) {
        this.mId = id;
        this.mTitle = title;
        this.mBody = body;
        this.mImages = images;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getBody() {
        return mBody;
    }

    @Nullable
    public String[] getImages() {
        return mImages;
    }

    public static class Builder {

        private String mId;
        private String mTitle;
        @Nullable
        private String mBody;
        @Nullable
        private String[] mImages;

        public Builder() {
        }

        public Builder id(String id) {
            mId = id;
            return this;
        }

        public Builder title(String title) {
            mTitle = title;
            return this;
        }

        public Builder body(String body) {
            mBody = body;
            return this;
        }

        public Builder images(String[] images) {
            mImages = images;
            return this;
        }

        public PostSchema build() {
            return new PostSchema(
                    mId,
                    mTitle,
                    mBody,
                    mImages);
        }
    }
}
