package com.hackerkernel.user.sqrfactor.Pojo;

import java.io.Serializable;

public class FollowerClass implements Serializable {
    private String name,place,post,portfolio,profileUrl;

    public FollowerClass(String name, String place, String post, String portfolio, String profileUrl) {
        this.name = name;
        this.place = place;
        this.post = post;
        this.portfolio = portfolio;
        this.profileUrl = profileUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
