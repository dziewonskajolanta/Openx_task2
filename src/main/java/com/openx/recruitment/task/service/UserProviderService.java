package com.openx.recruitment.task.service;

import com.openx.recruitment.task.boundary.PostRepositoryApi;
import com.openx.recruitment.task.boundary.UserRepositoryApi;
import com.openx.recruitment.task.domain.Neighbours;
import com.openx.recruitment.task.domain.Post;
import com.openx.recruitment.task.domain.User;
import com.openx.recruitment.task.domain.UserStats;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class UserProviderService {
    private final UserRepositoryApi userRepositoryApi;
    private final PostRepositoryApi postRepositoryApi;

    public List<User> findAllUsersWithPosts() {
        var users = userRepositoryApi.findAllUsers();
        var posts = postRepositoryApi.findAllPosts();

        return users.stream()
                .map(user -> addPostsToUser(posts, user))
                .collect(toList());
    }

    public List<String> findAllUserStats() {
        return findAllUsersWithPosts().stream()
                .map((user) -> new UserStats(user.getUsername(), user.getNumberOfPosts()).toString())
                .collect(Collectors.toList());
    }

    public List<Neighbours> findNearestNeighbours() {
        var users = userRepositoryApi.findAllUsers();
        return users.stream()
                .map(user -> new Neighbours(user, findNearestNeighbour(user, users)))
                .collect(Collectors.toList());
    }

    private User findNearestNeighbour(User user, List<User> users) {
        var nearestNeighbour = users.get(0);
        var nearestDistance = nearestNeighbour.calculateDistanceBetweenUser(user);

        for (User potentialNeighbour : users) {
            var currentDistance = potentialNeighbour.calculateDistanceBetweenUser(user);
            if ((currentDistance.compareTo(nearestDistance) <= 0 && !potentialNeighbour.equals(user)) || nearestNeighbour.equals(user)) {
                nearestNeighbour = potentialNeighbour;
                nearestDistance = currentDistance;
            }
        }
        return nearestNeighbour;
    }

    private User addPostsToUser(List<Post> posts, User user) {
        var userPosts = posts.stream()
                .filter((post) -> post.getUserId() == user.getId())
                .collect(toList());
        user.setPosts(userPosts);
        return user;
    }

}
