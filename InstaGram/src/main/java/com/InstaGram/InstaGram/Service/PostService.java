package com.InstaGram.InstaGram.Service;

import com.InstaGram.InstaGram.Model.Post;
import com.InstaGram.InstaGram.Model.User;
import com.InstaGram.InstaGram.Repository.IPostRepo;
import com.InstaGram.InstaGram.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;
    @Autowired
    IUserRepo userRepo;
    public void savePost(Post post) {
        post.setCreatedDate(LocalDateTime.now());
        postRepo.save(post);
    }

    public List<Post> getAllPost(String currentEmail) {
      User user = userRepo.findFirstByEmail(currentEmail);
      return postRepo.findByUser(user);
    }
}
