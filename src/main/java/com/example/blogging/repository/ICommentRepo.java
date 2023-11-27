package com.example.blogging.repository;


import com.example.blogging.models.Comment;
import com.example.blogging.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByBlog(Post myPost);
}
