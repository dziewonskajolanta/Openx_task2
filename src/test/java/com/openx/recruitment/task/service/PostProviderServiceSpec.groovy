package com.openx.recruitment.task.service

import com.openx.recruitment.task.boundary.PostRepositoryApi
import com.openx.recruitment.task.utils.DataLoader
import spock.lang.Specification

class PostProviderServiceSpec extends Specification {
    def postRepoApi = Mock(PostRepositoryApi)
    def postProviderService = new PostProviderService(postRepoApi)

    def "should find not unique titles"(){
        given:
        postRepoApi.findAllPosts() >> DataLoader.readPosts("src/test/samples/posts1.json" as File)
        when:
        def notUniqueTitles = postProviderService.findAllNotUniqueTitles()
        then:
        notUniqueTitles == expectedTitles()
    }

    private static List<String> expectedTitles(){
        return ["sunt aut facere repellat provident occaecati excepturi optio reprehenderit"].toList()
    }
}
