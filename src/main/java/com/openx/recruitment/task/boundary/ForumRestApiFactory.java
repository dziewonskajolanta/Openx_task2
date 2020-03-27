package com.openx.recruitment.task.boundary;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ForumRestApiFactory {

    private final String apiBaseUrl;

    public PostRepositoryApi createPostRepositoryApi() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(PostRepositoryApi.class, apiBaseUrl);
    }

    public UserRepositoryApi createUserRepositoryApi() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(UserRepositoryApi.class, apiBaseUrl);
    }

}
