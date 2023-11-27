package com.example.blogging.repository;

import com.example.blogging.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User , Long> {
    User findFirstByUserEmail(String newEmail);
}
