package com.stackroute.jwthelloworld.controllers;


import com.stackroute.jwthelloworld.configs.JwtUtils;
import com.stackroute.jwthelloworld.models.JWTRequestLogin;
import com.stackroute.jwthelloworld.models.User;
import com.stackroute.jwthelloworld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    AuthController(UserService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JWTRequestLogin creds) throws  Exception {
        System.out.println(creds);
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword()));
//        authenticate(creds.getEmail(), creds.getPassword());
        final UserDetails userDetails = service.getUserByUsername(creds.getEmail());

        System.out.println(userDetails);


        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<String>(token, HttpStatus.OK);

    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JWTRequestLogin creds) {
        User newUser = new User();
        newUser.setEmail(creds.getEmail());
        newUser.setPassword(creds.getPassword());

        return new ResponseEntity<User>(this.service.saveNewUser(newUser), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
