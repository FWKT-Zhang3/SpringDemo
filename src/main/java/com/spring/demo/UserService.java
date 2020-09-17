package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    // 不需要添加set方法
    // 添加注入属性注解
    @Autowired // 根据类型注入
    @Qualifier(value = "userDaoImpl2")
    private UserDao userDao;

//    @Resource // 根据类型注入
//    @Resource(name = "userDaoImpl2") // 根据名称注入
//    private UserDao userDao;

    @Value(value = "A String")
    private String s;

    public void a() {
        System.out.println("User Service");
        userDao.a();
        System.out.println(s);
    }
}
