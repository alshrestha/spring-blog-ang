package com.project.springblogapp.repository;

import com.project.springblogapp.model.Post;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends MongoRepository<Post, String> {

    Post deleteByTitle(String title);
    Post getByTitle(String title);

    @Cacheable("CommentsBy")
    @Query(value = "{'comments.commentedBy':?0}")
    List<Post> findPostByCommentedBy(String name);

}
