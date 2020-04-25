package com.project.springblogapp.controller;

import com.project.springblogapp.model.Customer;
import com.project.springblogapp.model.Post;
import com.project.springblogapp.model.PostDetail;
import com.project.springblogapp.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/posts")
public class BlogController {

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private CacheManager cacheManager;

private PostService postService;

@Autowired
public BlogController(PostService postService) {

    this.postService = postService;

}
@GetMapping("/all")
public ResponseEntity<List<Post>> getAllPost () {

    logger.info("Blog-service: 001 (Get Mapping All Post)");
    HttpHeaders headers = new HttpHeaders();
    //headers.set("Access-Control-Allow-Origin", "/**");

    return ResponseEntity.ok().headers(headers).body(postService.getAllPosts());
}
@GetMapping("/{title}")
public Post getPostByTitle(@PathVariable String title){

    logger.info("Blog-service: 002 (Get Mapping By Title)");
    return postService.getPostByTitle(title);
}

@PostMapping
    public void addPost(@RequestBody Post post){

    logger.info("Blog-service: 003 (Post Mapping add Post)");
    postService.addPost(post);

}
@PutMapping
    public void updatePost(@RequestBody Post post){
    logger.info("Blog-service: 004 (Put Mapping Update Post)");
    postService.updatePost(post);
}

@DeleteMapping("/delete/{title}")
    public void deletePost(@PathVariable String title) {
    logger.info("Blog-service: 006 (Delete Mapping Delete Post)");
    postService.deletePostByTitle(title);
}

@GetMapping("/comments/{name}")
    public PostDetail getByCommentedBy(@PathVariable String name){

    return postService.getPostByCommentedBy(name);
}

    @Scheduled(fixedRateString = "${cache.fixed.rate}", initialDelayString = "${cache.init.delayed}")
    public void clearCache(){
        cacheManager.getCacheNames().parallelStream().forEach(cacheName ->
                cacheManager.getCache(cacheName).clear());
    }

    @GetMapping("/customers")
    public List<Customer> getCustomerDetails(){
            return postService.getCustomerDetails();
    }
}
