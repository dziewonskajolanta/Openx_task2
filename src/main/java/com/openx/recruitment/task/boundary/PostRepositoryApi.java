package com.openx.recruitment.task.boundary;

import com.openx.recruitment.task.domain.Post;
import feign.RequestLine;

import java.util.List;

public interface PostRepositoryApi {

    @RequestLine("GET /posts")
    List<Post> findAllPosts();

}
