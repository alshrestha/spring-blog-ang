package com.project.springblogapp.service;


import com.project.springblogapp.model.Customer;
import com.project.springblogapp.model.CustomerResponse;
import com.project.springblogapp.model.Post;
import com.project.springblogapp.model.PostDetail;
import com.project.springblogapp.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PostService {

    Logger logger = LoggerFactory.getLogger(PostService.class);
    @Value("${customer.service.uri}")
    String customerUri;

    private PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        logger.info("Service 001: To get all Data");
        List<Post> posts = postRepository.findAll();
        logger.info("Number of Records for Post: {}", posts.size() );
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

    public PostDetail getPostByCommentedBy(String name) {

        PostDetail postDetail = new PostDetail();
        postDetail.setPosts(postRepository.findPostByCommentedBy(name));
        postDetail.setUser(getCustomerInfo(name));
        return postDetail;
    }

    public List<Customer> getCustomerDetails(){
        WebClient customerWebClient = createWebClient(customerUri);
        Mono<CustomerResponse> response =  customerWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/customers").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(CustomerResponse.class);

        System.out.println(response.block());

        return response.block().getCustomers();

    }

    public Customer getCustomerInfo(String name){
        WebClient customerWebClient = createWebClient(customerUri);
        Mono<Customer> response = customerWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/customer/{userName}").build(name))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Customer.class);
        return response.block();
    }

    private WebClient createWebClient(String uri){
        return WebClient.builder()
                .baseUrl(uri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .build();

    }


}
