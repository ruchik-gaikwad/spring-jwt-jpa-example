package com.stackroute.jwthelloworld.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Column
    @Id
    long id;

    @Column
    String email;

    @Column
    String password;
}
