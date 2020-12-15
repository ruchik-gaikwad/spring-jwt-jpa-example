package com.stackroute.jwthelloworld.services;

import com.stackroute.jwthelloworld.dao.UserDaoImpl;
import com.stackroute.jwthelloworld.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService  {


    private UserDaoImpl userDao;


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    public User getUserByEmail(String email) {
        return this.userDao.findByEmail(email);
    }


    public User saveNewUser(User newUser) {
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userDao.save(newUser);
    }


    public UserDetails getUserByUsername(String email) {
        Iterable users = this.userDao.findAll();
        System.out.println(users);

        User user = this.userDao.findByEmail(email);
        System.out.println(user);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
