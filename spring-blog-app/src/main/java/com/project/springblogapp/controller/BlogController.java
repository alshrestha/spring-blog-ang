package com.project.springblogapp.controller;

import com.project.springblogapp.model.Post;
import com.project.springblogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
public class BlogController {

private PostService postService;

@Autowired
public BlogController(PostService postService) {
    this.postService = postService;

}
@GetMapping("/all")
public List<Post> getAllPost () {

    return postService.getAllPosts();
}
@GetMapping("/{title}")
public Post getPostByTitle(@PathVariable String title){
    return postService.getPostByTitle(title);
}

@PostMapping
    public void addPost(@RequestBody Post post){

    postService.addPost(post);

}
@PutMapping
    public void updatePost(@RequestBody Post post){
    postService.updatePost(post);
}

@DeleteMapping("/delete/{title}")
    public void deletePost(@PathVariable String title) {
    postService.deletePostByTitle(title);
}

@GetMapping("/comments/{name}")
    public List<Post> getByCommentedBy(@PathVariable String name){
    return postService.getPostByCommentedBy(name);
}

}
