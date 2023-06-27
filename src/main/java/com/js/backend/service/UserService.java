package com.js.backend.service;

import com.js.backend.exceptions.ExceptionMessages;
import com.js.backend.repository.UserRepo;
import com.js.backend.user.AuthRequest;
import com.js.backend.user.User;
import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void saveUser(AuthRequest authRequest) throws Exception {

        Optional<User> userFromDb = userRepo.findByEmail(authRequest.getEmail());
        if(userFromDb.isPresent()){
            throw new Exception(ExceptionMessages.USER_ALREADY_EXIST.getCode());
        }
        else {
            User newUser = new User(authRequest.getEmail(),passwordEncoder.encode(authRequest.getPassword()),"ROLE_USER");
            userRepo.save(newUser);
        }
    }
}
