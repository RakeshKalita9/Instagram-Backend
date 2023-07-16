package com.InstaGram.InstaGram.Controller;

import com.InstaGram.InstaGram.Model.User;
import com.InstaGram.InstaGram.Model.dto.SignInInput;
import com.InstaGram.InstaGram.Model.dto.SignUpOutput;
import com.InstaGram.InstaGram.Service.AuthenticationService;
import com.InstaGram.InstaGram.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("user/signUp")
    public SignUpOutput signUpUser(@RequestBody User user)  {
      return   userService.signUpUser(user);
    }
    @PostMapping("user/signIn")
    public String signIn(@RequestBody SignInInput signInInput ) throws NoSuchAlgorithmException {
      return   userService.signInUser(signInInput);
    }
    @PutMapping("user/update/Email/{email}")
    public String updateUserEmail(@PathVariable String email,@RequestParam String currentEmail,@RequestParam String currentTokenValue){
        if(authenticationService.authenticate(currentEmail,currentTokenValue)){
            userService.updateUserEmail(email,currentEmail);
            return "User Email is Updated";
        }
        return "Email updation failed";
    }
    @DeleteMapping("user/signOut")
    public String signOut(@RequestParam String currentEmail,@RequestParam String currentTokenValue){
       if(authenticationService.authenticate(currentEmail,currentTokenValue)){
           userService.signOut(currentEmail);
           return "SignOut SuccessFully";
       }
       return "Authentication Failed";
    }
}
