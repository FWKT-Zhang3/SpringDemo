package com.spring.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestHelloWorld {
    @Test
    public void test1HW() {
        //1、加载spring配置文件
        ApplicationContext context = new FileSystemXmlApplicationContext("src/bean1.xml");
        //2、获取配置文件创建的对象
        HelloWorld helloWorld = context.getBean("helloworld", HelloWorld.class);

        System.out.println(helloWorld);
        helloWorld.helloWorld();
    }
}
