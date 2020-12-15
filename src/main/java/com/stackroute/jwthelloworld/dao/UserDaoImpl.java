package com.stackroute.jwthelloworld.dao;

import com.stackroute.jwthelloworld.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoImpl extends CrudRepository<User, Integer> {

    User findByEmail(String email);
}
