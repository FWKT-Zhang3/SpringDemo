package com.spring.aop;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {
    public static void main(String[] args) {
        Class[] interfaces = {UserDao.class};
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        UserDao userDao = (UserDao)Proxy.newProxyInstance(JDKProxy.class.getClassLoader(),
                interfaces, (proxy, method, args1) -> {
                    // 方法之前
                    System.out.println("方法之前执行..." + method.getName() + Arrays.toString(args1));

                    // 被增强的方法执行
                    Object o = method.invoke(userDaoImpl,args1);

                    //方法之后
                    System.out.println("方法之后执行...");

                    return o;
                });
        System.out.println(userDao.add(1, 2));
    }
}

