package com.InstaGram.InstaGram.Service;

import com.InstaGram.InstaGram.Model.AuthenticationToken;
import com.InstaGram.InstaGram.Model.User;
import com.InstaGram.InstaGram.Repository.IAuthenticationRepo;
import com.InstaGram.InstaGram.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;
    @Autowired
    IUserRepo userRepo;
    public boolean authenticate(String currentEmail, String currentTokenValue) {
        User user = userRepo.findFirstByEmail(currentEmail);
        AuthenticationToken authToken = authenticationRepo.findFirstByTokenValue(currentTokenValue);
        return authToken.getUser().equals(user);
    }
}
