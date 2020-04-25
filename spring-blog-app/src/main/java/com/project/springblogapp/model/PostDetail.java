package com.project.springblogapp.model;

import java.util.List;

public class PostDetail {

    private List<Post> posts;
    private Customer user;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}
