package com.project.springblogapp.service;


import com.project.springblogapp.model.Post;
import com.project.springblogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;

    }

    public Post getPostByTitle (String title){
        return postRepository.getByTitle(title);
    }
    public void addPost (Post post) {
        postRepository.insert(post);
    }

    public void updatePost (Post post) {
        postRepository.save(post);
    }

    public void deletePostByTitle(String title){
        postRepository.deleteByTitle(title);
    }

    public List<Post> getPostByCommentedBy(String name) {
        return postRepository.findPostByCommentedBy(name);
    }


}
