package com.syrgdev.testapplicationforpikabu.persistance;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.network.PostSchema;

public class Converters {

    public static PostData toPostData(PostSchema postSchema) {
        return PostData.builder()
                .id(postSchema.getId())
                .title(postSchema.getTitle())
                .body(postSchema.getBody())
                .images(postSchema.getImages())
                .build();
    }
}
