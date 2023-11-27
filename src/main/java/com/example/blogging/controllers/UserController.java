package com.example.blogging.controllers;


import com.example.blogging.models.Comment;
import com.example.blogging.models.Follow;
import com.example.blogging.models.Post;
import com.example.blogging.models.User;
import com.example.blogging.models.dto.SignInInput;
import com.example.blogging.models.dto.SignUpOutput;
import com.example.blogging.service.AuthenticationService;
import com.example.blogging.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;


    //sign up, sign in , sign out a particular user
    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody @Valid User user) {
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String signOutUser(@RequestParam @Valid String email,@RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.signOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }

    //creation of post

    @PostMapping("post/postBlog")
    public String createBlogPost(@RequestBody @Valid Post post, @RequestParam @Valid String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.createBlogPost(post,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @PutMapping("post/updateBlog")
    public String updateBlogPost(@RequestBody @Valid Post updatePost, @RequestParam @Valid String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.updateBlogPost(updatePost,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("post/deleteBlog")
    public String removeBlogPost(@RequestParam Long postId, @RequestParam @Valid String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.removeBlogPost(postId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    //commenting functionalities

    @PostMapping("blog/addComment")
    public String addComment(@RequestBody @Valid Comment comment, @RequestParam @Valid String commenterEmail, @RequestParam String commenterToken)
    {
        if(authenticationService.authenticate(commenterEmail,commenterToken)) {
            return userService.addComment(comment,commenterEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("blog/deleteComment")
    public String removeBlogComment(@RequestParam Long commentId, @RequestParam @Valid String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.removeBlogComment(commentId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    //get blogs and comments
    @GetMapping("/getAllBlogs")
    public Object getAll(@RequestParam @Valid String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.getAllBlogs();
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @GetMapping("/getBlog/{postId}")
    public Object getBlogById(@RequestParam @Valid String email, @RequestParam String token , @PathVariable Long postId)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.getBlogById(postId);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }




    //follow functionality

    @PostMapping("follow/user")
    public String followUser(@RequestBody @Valid Follow follow, @RequestParam @Valid String followerEmail, @RequestParam String followerToken)
    {
        if(authenticationService.authenticate(followerEmail,followerToken)) {
            return userService.followUser(follow,followerEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("unfollow/user/{followId}")
    public String unFollowUser(@PathVariable Long followId, @RequestParam @Valid String followerEmail, @RequestParam String followerToken)
    {
        if(authenticationService.authenticate(followerEmail,followerToken)) {
            return userService.unFollowUser(followId,followerEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
}
