package com.InstaGram.InstaGram.Repository;

import com.InstaGram.InstaGram.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByEmail(String newEmail);
}
