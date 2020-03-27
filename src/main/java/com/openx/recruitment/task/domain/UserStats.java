package com.openx.recruitment.task.domain;

import lombok.Value;

@Value
public class UserStats {

    String username;
    int postsCount;

    public String toString() {
        return String.format("%s napisał %d postów", username, postsCount);
    }
}
