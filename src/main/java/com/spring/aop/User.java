package com.spring.aop;

import org.springframework.stereotype.Component;

/**
 * 被增强的类
 */
@Component
public class User {
    public void a() {
        System.out.println("A method...");
    }
}
