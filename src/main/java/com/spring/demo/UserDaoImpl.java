package com.spring.demo;

import org.springframework.stereotype.Repository;

@Repository(value = "userDaoImpl2")
public class UserDaoImpl implements UserDao {

    @Override
    public void a() {
        System.out.println("A method...");
    }
}
