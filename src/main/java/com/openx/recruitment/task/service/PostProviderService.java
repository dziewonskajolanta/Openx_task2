package com.openx.recruitment.task.service;

import com.openx.recruitment.task.boundary.PostRepositoryApi;
import com.openx.recruitment.task.domain.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PostProviderService {

    private final PostRepositoryApi postRepositoryApi;

    public List<String> findAllNotUniqueTitles() {
        Map<String, Long> titleToCountMap = postRepositoryApi.findAllPosts().stream()
                .map(Post::getTitle)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return titleToCountMap
                .entrySet().stream()
                .filter(postLongEntry -> postLongEntry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
