package com.spring.demo;

import org.springframework.stereotype.Component;

// 注解里面value属性值可以省略不写
// 默认值是类名称，首字母小写
@Component(value = "annotationTest") //<bean id="annotationTest" class".."/>
public class AnnotationTest {
    public void a() {
        System.out.println("A method");
    }
}
