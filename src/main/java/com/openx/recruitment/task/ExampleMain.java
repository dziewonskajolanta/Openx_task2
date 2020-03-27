package com.openx.recruitment.task;

import com.openx.recruitment.task.boundary.ForumRestApiFactory;
import com.openx.recruitment.task.service.PostProviderService;
import com.openx.recruitment.task.service.UserProviderService;
import lombok.Data;

@Data
public class ExampleMain {

    private static final ForumRestApiFactory restApiFactory = new ForumRestApiFactory("https://jsonplaceholder.typicode.com");
    private static final PostProviderService postProviderService = new PostProviderService(restApiFactory.createPostRepositoryApi());
    private static final UserProviderService userProviderService = new UserProviderService(restApiFactory.createUserRepositoryApi(), restApiFactory.createPostRepositoryApi());

    public static void main(String[] args) {
        System.out.println("User stats");
        userProviderService.findAllUserStats().forEach(System.out::println);
        System.out.println("All not unique posts");
        postProviderService.findAllNotUniqueTitles().forEach(System.out::println);
        System.out.println("Neighbours");
        userProviderService.findNearestNeighbours().forEach(System.out::println);
    }
}
