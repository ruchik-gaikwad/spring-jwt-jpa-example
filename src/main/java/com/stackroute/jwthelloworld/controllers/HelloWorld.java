package com.stackroute.jwthelloworld.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class HelloWorld {


    @GetMapping("/sayHello/{user}")
    public ResponseEntity<?> sayHello(@PathVariable String user) {
        return new ResponseEntity<>("Hi!, " +  user, HttpStatus.OK);
    }
}
