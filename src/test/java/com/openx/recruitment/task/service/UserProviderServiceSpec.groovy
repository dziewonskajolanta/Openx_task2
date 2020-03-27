package com.openx.recruitment.task.service

import com.openx.recruitment.task.boundary.PostRepositoryApi
import com.openx.recruitment.task.boundary.UserRepositoryApi
import com.openx.recruitment.task.domain.Neighbours
import com.openx.recruitment.task.utils.DataLoader
import spock.lang.Specification

class UserProviderServiceSpec extends Specification {
    def postRepoApi = Mock(PostRepositoryApi)
    def userRepoApi = Mock(UserRepositoryApi)
    def userProviderService = new UserProviderService(userRepoApi, postRepoApi)

    def "should add posts to user"() {
        given:
        postRepoApi.findAllPosts() >> DataLoader.readPosts("src/test/samples/posts1.json" as File)
        userRepoApi.findAllUsers() >> DataLoader.readUsers("src/test/samples/users1.json" as File)
        when:
        def users = userProviderService.findAllUsersWithPosts()
        then:
        users == DataLoader.readUsers("src/test/samples/users_with_posts.json" as File)
    }

    def "should count user posts"() {
        given:
        postRepoApi.findAllPosts() >> DataLoader.readPosts("src/test/samples/posts1.json" as File)
        userRepoApi.findAllUsers() >> DataLoader.readUsers("src/test/samples/users1.json" as File)
        when:
        def usersStats = userProviderService.findAllUserStats()
        then:
        usersStats == expectedUsersStats()
    }

    def "should find nearest neighbours"() {
        given:
        userRepoApi.findAllUsers() >> DataLoader.readUsers("src/test/samples/users2.json" as File)
        when:
        def nearestNeighbours = userProviderService.findNearestNeighbours()
        then:
        nearestNeighbours == expectedNeighboursList()

    }

    private static List<String> expectedUsersStats() {
        return  ["Bret napisał 3 postów",
                             "Antonette napisał 0 postów",
                             "Samantha napisał 0 postów",
                             "Karianne napisał 0 postów",
                             "Kamren napisał 0 postów",
                             "Leopoldo_Corkery napisał 0 postów",
                             "Elwyn.Skiles napisał 0 postów",
                             "Maxime_Nienow napisał 0 postów",
                             "Delphine napisał 0 postów",
                             "Moriah.Stanton napisał 0 postów"].toList()
    }

    private static List<Neighbours> expectedNeighboursList() {
        def users = DataLoader.readUsers("src/test/samples/users2.json" as File)
        return [new Neighbours(users[0], users[2]),
                                 new Neighbours(users[1], users[2]),
                                 new Neighbours(users[2], users[1])].toList()

    }
}
