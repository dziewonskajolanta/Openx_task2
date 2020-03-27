package com.openx.recruitment.task.domain;

import lombok.Value;

@Value
public class Neighbours {

    User firstUser;
    User secondUser;

    public String toString() {
        return String.format("User %s ma najblizej do %s", firstUser.getName(), secondUser.getName());
    }

}
