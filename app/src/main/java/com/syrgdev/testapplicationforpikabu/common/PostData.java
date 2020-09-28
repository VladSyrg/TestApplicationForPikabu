package com.syrgdev.testapplicationforpikabu.common;

import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforpikabu.network.PostSchema;

import java.io.Serializable;

public class PostData implements Serializable {
    private String mId;
    private String mTitle;
    @Nullable
    private String mBody;
    @Nullable
    private String[] mImages;

    private boolean mIsSaved;

    private PostData(String id,
                    String title,
                    @Nullable String body,
                    @Nullable String[] images,
                    boolean isSaved) {
        mId = id;
        mTitle = title;
        mBody = body;
        mImages = images;
        mIsSaved = isSaved;
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

    public boolean isSaved() {
        return mIsSaved;
    }

    /**
     * Clones existing PostData. Set fields and then call {@link PostSchema.Builder#build}
     * in order to create new PostData object.
     * <p>
     * Example:
     * <p>
     * PostData.toBuilder()
     * .id("5")
     * .title("Title")
     * .body("body")
     * .images(new ArrayList<String>())
     * .build();
     *
     * @return Builder object.
     */
    public PostData.Builder toBuilder() {
        return new PostData.Builder()
                .id(this.getId())
                .title(this.getTitle())
                .body(this.getBody())
                .images(this.getImages())
                .isSaved(this.isSaved());
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String mId;
        private String mTitle;
        @Nullable
        private String mBody;
        @Nullable
        private String[] mImages;

        private boolean mIsSaved;

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

        public Builder isSaved(boolean isSaved) {
            mIsSaved = isSaved;
            return this;
        }

        public PostData build() {
            return new PostData(
                    mId,
                    mTitle,
                    mBody,
                    mImages,
                    mIsSaved);
        }
    }
}