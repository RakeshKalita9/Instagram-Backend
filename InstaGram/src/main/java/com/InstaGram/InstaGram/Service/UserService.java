package com.InstaGram.InstaGram.Service;

import com.InstaGram.InstaGram.Model.AuthenticationToken;
import com.InstaGram.InstaGram.Model.User;
import com.InstaGram.InstaGram.Model.dto.SignInInput;
import com.InstaGram.InstaGram.Model.dto.SignUpOutput;
import com.InstaGram.InstaGram.Model.utilities.Encrypt;
import com.InstaGram.InstaGram.Repository.IAuthenticationRepo;
import com.InstaGram.InstaGram.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IAuthenticationRepo authenticationRepo;
    public SignUpOutput signUpUser(User user) {
        Boolean signUpStatus;
        String singUpMessage;

        String newEmail = user.getEmail();

        User existingUser = userRepo.findFirstByEmail(newEmail);
        if(newEmail==null){
            return new SignUpOutput(false,"Invalid SingIn credential.....");
        }

        if(existingUser!=null){
            return new SignUpOutput(false,"Email Already registered....");
        }

        try {
            String encryptPassword = Encrypt.encryptPassword(user.getPassword());
            user.setPassword(encryptPassword);
            userRepo.save(user);
            return new SignUpOutput(true,"signed Up Done successfully");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }


    public String signInUser(SignInInput signInInput) throws NoSuchAlgorithmException {
        String existingMail = signInInput.getEmail();
        User user = userRepo.findFirstByEmail(signInInput.getEmail());
        if(authenticationRepo.findFirstByUser(user)!=null) return "Email Is  Already Signed In....";

        if(existingMail==null){
            return "Mail Is required during SignIn";
        }
        if(user==null) return "Email is not registered Yet....";



        String encryptPassword = Encrypt.encryptPassword(signInInput.getPassword());
        if(encryptPassword.equals(user.getPassword())){
            AuthenticationToken authToken = new AuthenticationToken(user);
            authenticationRepo.save(authToken);
            return user.getFirstName()+" has Token Value ---> "+authToken.getTokenValue();
        }
      return "Invalid SignIn Credentials";
    }

    public void updateUserEmail(String email, String currentEmail) {
        User user = userRepo.findFirstByEmail(currentEmail);
        user.setEmail(email);
        userRepo.save(user);
    }

    public void signOut(String currentEmail) {
        User user = userRepo.findFirstByEmail(currentEmail);
        Long id = authenticationRepo.findFirstByUser(user).getTokenId();
        authenticationRepo.deleteById(id);
    }
}
