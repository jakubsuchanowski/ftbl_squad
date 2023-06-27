package com.js.backend.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.js.backend.service.UserService;
import com.js.backend.user.AuthRequest;
import com.js.backend.user.AuthResponse;
import com.js.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/auth/login")
    public ResponseEntity<?> getJwt(@RequestBody AuthRequest authRequest){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            User user = (User) authenticate.getPrincipal();
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("kubynovv")
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
            AuthResponse authResponse = new AuthResponse(user.getUsername(), token);
            return ResponseEntity.ok().body(authResponse);

        }
        catch (UsernameNotFoundException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/registration")
    public ResponseEntity<?> saveUser(@RequestBody AuthRequest authRequest){
        try {
            userService.saveUser(authRequest);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
