package com.openx.recruitment.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
    private List<Post> posts = new ArrayList<>();

    public int getNumberOfPosts() {
        return posts.size();
    }

    private GeoLocation getGeoLocation(){
        return address.getGeo();
    }

    public BigDecimal calculateDistanceBetweenUser(User user){
        return user.getGeoLocation().calculateDistance(getGeoLocation());
    }

}
