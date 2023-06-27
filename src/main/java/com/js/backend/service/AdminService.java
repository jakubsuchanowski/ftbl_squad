package com.js.backend.service;

import com.js.backend.repository.UserRepo;
import com.js.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepo userRepo;

    public List<User> showAllUsers(){
        return (List<User>) userRepo.findAll();
    }
}
