# Spring

-- by Ming Zhang



## IOC(控制反转)

### 概念和原理

1. 什么是IOC

   控制反转，把对象创建和对象之间的调用过程，交给Spring管理

2. 目的：降低耦合度

3. 底层原理

   xml解析、工厂模式、反射

```java
//第一步：xml配置文件，配置创建的对象
<bean id="helloworld" class="com.demo.HelloWorld"></bean>
//第二步：创建工厂类
class UserFactory {
    public static HelloWorld getHelloWorld() {
        String classValue = class属性值; // xml解析
        Class theClass = Class.forName(classValue); //通过反射创建对象
        return (HelloWorld)theClass.newInstance();
    }
}
```

### 接口

1. IOC思想基于IOC容器完成，IOC容器底层就是对象工厂

2. Spring提供IOC容器是想两种方式（两个接口）：

   * BeanFactory: IOC 容器基本实现，是Spring内部的使用接口，不提供开发人员使用
     * 加载配置文件的时候不会创建对象，在获取/使用对象时才会创建对象

   * ApplicationContext：BeanFactory接口的子接口，提供更多更强大的功能，一般由开发人员进行使用
     * 加载配置文件的时候就会创建配置文件内的类的对象

3. ApplicationContext实现类

   * FileSystemXmlApplicationContext：绝对路径
   * ClassPathXmlApplicationContext：相对路径（src）

### 操作 ：Bean管理

1. Spring创建对象
2. Spring注入属性（DI：依赖注入，注入属性）

Bean管理操作有两种方式

1. 基于xml配置文件方式实现

   **创建对象**：

   ```java 
   <bean id="helloworld" class="com.demo.HelloWorld"></bean>
   ```

   在Spring配置文件中，使用bean标签，标签里面添加对应属性，就可以实现对象创建

   bean标签的属性：

   * id：对象的唯一标识
   * class：类全路径（包类路径）

   创建对象时，默认执行无参数构造方法（没有无参方法时报错）

   

   **注入属性**：

   ​	set方法注入（setter）：

   ​	在class中写相应的set方法，在xml配置文件夹中，使用property标签注入属性。

   ```xml
   <!-- set 方法注入属性-->
   <bean id="book" class="com.spring.demo.Book">
       <!--使用property标签-->
       <property name="bookAuthor" value="Ming Zhang"></property>
       <property name="bookName" value="炼丹术"></property>
   </bean>
   ```

   ​	有参构造注入：

   ```xml
   <!-- 有参构造器注入属性-->    
   <bean id="order" class="com.spring.demo.Orders">
       <constructor-arg name="orderName" value="a Name"></constructor-arg>
       <constructor-arg name="address" value="UQ"></constructor-arg>
   </bean>
   ```

   xml注入其他类型属性：

   1、字面量

   * null值

   ```xml
   <!-- null值 -->
   <property name="address">
   	<null/>
   </property>
   ```

   * 特殊符号

   ```xml
   <!-- 特殊符号值 -->
   // 1. 转义 &lt; &gt;
   // 2. 把特殊符号内筒写到CDATA
   <property name="address">
   	<value>
           <![CDATA[<<南京>>]]>
       </value>
   </property>
   ```

   外部 bean

   ```xml
   <bean id="userService" class="com.atguigu.srping5.service.UserService">
   	<!-- 注入userDao对象 -->
       <property name="userDao" ref="userDaoImpl"></property>
   </bean>
   <bean id="userDaoImpl" class="com.atguigu.spring5.dao.UserDaoImpl"></bean>
   ```

   内部bean

   ```xml
   <!-- 内部bean -->
   <bean id="emp" class="com.spring.bean.Emp">
       <property name="ename" value="Max"></property>
       <property name="gender" value="male"></property>
       <property name="dept">
           <bean id="dept" class="com.spring.bean.Dept">
               <property name="dname" value="安保部"></property>
           </bean>
       </property>
   </bean>
   ```

   级联赋值

   ```xml
   <bean id="emp" class="com.spring.bean.Emp">
       <property name="ename" value="Max"/>
       <property name="gender" value="male"/>
       <property name="dept" ref="dept"/>
       <property name="dept.dname" value="安保部"/>
   </bean>
   <bean id="dept" class="com.spring.bean.Dept"/>
   ```

   

2. 基于注解方式实现

