package com.InstaGram.InstaGram.Controller;

import com.InstaGram.InstaGram.Model.Post;
import com.InstaGram.InstaGram.Service.AuthenticationService;
import com.InstaGram.InstaGram.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PostService postService;
    @PostMapping("post")
    public String createInstaPost(@RequestBody Post post, @RequestParam String currentEmail,@RequestParam String currentTokenValue){
        if(authenticationService.authenticate(currentEmail,currentTokenValue)){
            postService.savePost(post);
            return "Insta Post Uploaded";
        }
        return "Authentication Failed";

    }
    @GetMapping("post")
    public List<Post> getPost (@RequestParam String currentEmail, @RequestParam String currentTokenValue){
        if(authenticationService.authenticate(currentEmail,currentTokenValue)){
            return postService.getAllPost(currentEmail);
        }
        return null;
    }
}
