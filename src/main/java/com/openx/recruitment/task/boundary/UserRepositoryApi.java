package com.openx.recruitment.task.boundary;

import com.openx.recruitment.task.domain.User;
import feign.RequestLine;

import java.util.List;

public interface UserRepositoryApi {

    @RequestLine("GET /users")
    List<User> findAllUsers();

}
