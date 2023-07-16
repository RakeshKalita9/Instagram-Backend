package com.InstaGram.InstaGram.Repository;

import com.InstaGram.InstaGram.Model.Post;
import com.InstaGram.InstaGram.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepo extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
}
