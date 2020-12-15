package com.stackroute.jwthelloworld.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JWTRequestLogin {

    private String email;
    private String password;

}
