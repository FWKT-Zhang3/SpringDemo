package com.spring.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestAnnotation {

    @Test
    public void testAnnotation() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/AnnotationTestConfig.xml");
        AnnotationTest annotationTest = context.getBean("annotationTest", AnnotationTest.class);
        System.out.println(annotationTest);
        annotationTest.a();
    }
}
