package com.InstaGram.InstaGram.Repository;

import com.InstaGram.InstaGram.Model.AuthenticationToken;
import com.InstaGram.InstaGram.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findFirstByTokenValue(String currentTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
