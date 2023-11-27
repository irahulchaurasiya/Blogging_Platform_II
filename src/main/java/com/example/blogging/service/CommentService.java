package com.example.blogging.service;


import com.example.blogging.models.Comment;
import com.example.blogging.models.Post;
import com.example.blogging.models.User;
import com.example.blogging.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;


    public String addComment(Comment comment, User user) {
        comment.setCommenter(user);
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        commentRepo.save(comment);
        return "Comment Done!!!";
    }

    public String removeBlogComment(Long commentId, User user) {
        Comment comment  = commentRepo.findById(commentId).orElse(null);
        if(comment != null && comment.getCommenter().equals(user))
        {
            commentRepo.deleteById(commentId);
            return "Comment Removed successfully";
        }
        else if (comment == null)
        {
            return "Comment to be deleted does not exist";
        }
        else{
            return "Un-Authorized delete detected....Not allowed";
        }
    }


    public void clearCommentsByPost(Post myPost) {

        List<Comment> commentsOfPost = commentRepo.findByBlog(myPost);
        commentRepo.deleteAll(commentsOfPost);
    }
}
