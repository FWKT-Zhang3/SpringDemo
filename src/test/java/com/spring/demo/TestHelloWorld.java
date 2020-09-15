package com.spring.demo;

import com.spring.bean.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestHelloWorld {
    @Test
    public void test1HW() {
        //1、加载spring配置文件
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/bean1.xml");
        //2、获取配置文件创建的对象
        HelloWorld helloWorld = context.getBean("helloworld", HelloWorld.class);

        System.out.println(helloWorld);
        helloWorld.helloWorld();
    }

    @Test
    public void testBook() {
        //1、加载spring配置文件
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/bean1.xml");
        //2、获取配置文件创建的对象
        Book book = context.getBean("book", Book.class);

        System.out.println(book.getBookAuthor() + ": " + book.getBookName());
    }

    @Test
    public void testOrders() {
        //1、加载spring配置文件
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/bean1.xml");
        //2、获取配置文件创建的对象
        Orders order = context.getBean("order", Orders.class);

        System.out.println(order.getAddress() + ": " + order.getOrderName());
    }

    @Test
    public void testEmp() {
        //1、加载spring配置文件
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/bean3.xml");
        //2、获取配置文件创建的对象
        Emp emp = context.getBean("emp", Emp.class);

        emp.testEmp();
    }
}
