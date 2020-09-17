package com.spring.demo;

import com.spring.config.SpringConfigDemo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestAnnotation {

    @Test
    public void testAnnotation() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/AnnotationTestConfig.xml");
        AnnotationTest annotationTest = context.getBean("annotationTest", AnnotationTest.class);
        System.out.println(annotationTest);
        annotationTest.a();
    }

    @Test
    public void testAutowired() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/AnnotationTestConfig.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.a();
    }

    @Test
    public void testNoXML() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigDemo.class);
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.a();
    }
}
