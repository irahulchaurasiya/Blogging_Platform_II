package com.example.blogging.service;


import com.example.blogging.models.Follow;
import com.example.blogging.models.User;
import com.example.blogging.repository.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    IFollowRepo followRepo;

    public void startFollowing(Follow follow, User follower) {

        follow.setCurrentUserFollower(follower);
        followRepo.save(follow);
    }

    public boolean isFollowAllowed(User targetUser, User follower) {

        List<Follow> followList =  followRepo.findByCurrentUserAndCurrentUserFollower(targetUser,follower);

        return followList!=null && followList.isEmpty() && !targetUser.equals(follower);
    }

    public Follow findFollow(Long followId) {
        return followRepo.findById(followId).orElse(null);
    }

    public void unfollow(Follow follow) {
        followRepo.delete(follow);
    }

}
