package com.securebank.securenank.Service;

import com.securebank.securenank.Auth.AuthToken;
import com.securebank.securenank.DTO.appUserLoginDTO;
import com.securebank.securenank.ExceptionHandler.ResourceNotFoundException;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Repository.RepositoryAppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    private final RepositoryAppUser repositoryAppUser;
    private final PasswordEncoder passwordEncoder;
    private final AuthToken authToken;

    public LoginService(RepositoryAppUser repositoryAppUser,
                        AuthToken authToken){
        this.repositoryAppUser = repositoryAppUser;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.authToken = authToken;
    }

    public String login(appUserLoginDTO loginUser){
        app_user findUser = repositoryAppUser.findByUsername(loginUser.getUsername());
        if(findUser == null){
            throw new ResourceNotFoundException("The username or password is incorrect");
        }
        if(!passwordEncoder.matches(loginUser.getPassword(), findUser.getPassword())){
            throw new ResourceNotFoundException("The username or password is incorrect");
        }
        return authToken.generateToken(findUser);
    }
}
