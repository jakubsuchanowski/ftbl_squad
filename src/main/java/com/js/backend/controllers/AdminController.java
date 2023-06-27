package com.js.backend.controllers;

import com.js.backend.service.AdminService;
import com.js.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/user/list")
    public ResponseEntity<List<User>>  getAll(){
        return ResponseEntity.ok().body(adminService.showAllUsers());
    }
}
