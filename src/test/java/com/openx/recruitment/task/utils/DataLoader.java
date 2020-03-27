package com.openx.recruitment.task.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openx.recruitment.task.domain.Post;
import com.openx.recruitment.task.domain.User;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public class DataLoader {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> readUsers(File file) {
        try {
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static List<Post> readPosts(File file) {
        try {
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
