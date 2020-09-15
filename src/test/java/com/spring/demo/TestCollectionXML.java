package com.spring.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestCollectionXML {
    @Test
    public void testCollectionXML() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/bean4.xml");

        CollectionTest collectionTest = context.getBean("collectionXML", CollectionTest.class);

        System.out.println(collectionTest);
    }
}
