package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Auth.AuthenticationException;
import com.example.demo.Auth.JwtTokenUtil;
import com.example.demo.Auth.TokenRequest;
import com.example.demo.Auth.TokenResponse;
import com.example.demo.Dao.TodoUserDao;
import com.example.demo.Entity.TodoUser;


import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationRestController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TodoUserDao userRepo;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody TodoUser user) {

        userRepo.save(user);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest)
            throws Exception {
        System.out.println("getting token");
        System.out.println(authenticationRequest.getEmail()+" "+authenticationRequest.getPassword());
        try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
 } catch(BadCredentialsException e) {
    System.out.println("Incorrect username or password");
    throw new Exception("Incorrect username or password", e);
 }
        final UserDetails userDetails = new User(authenticationRequest.getEmail(), authenticationRequest.getPassword(), new ArrayList<>());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponse(token,userDetails.getUsername()));
    }


    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
