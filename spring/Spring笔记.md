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

   字面量

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

   Spring有两种bean类型，一种普通bean，一种工厂bean：

   * 普通bean：

     在配置文件中，定义的bean类型就是返回类型

   * 工厂bean：

     在配置文件中，定义的bean类型可以和返回类型不一样

     （实现FactoryBean接口）



​	bean作用域：

​	a. 在Spring里面，设置创建bean实例是单实例还是多实例（默认是单实例）

> 单实例：只有一个实例
>
> 多实例：每次获取不同实例

​	b. Spring配置bean标签中有属性**scope**用于设置单实例还是多实例（默认值：singleton（单	实例），prototype（多实例），requset，session）

​	设置为singleton的时候，加载spring配置文件的时候就会创建单实例对象

​	设置为prototype的时候，在调用getBean方法时创建对象

​	

​	bean生命周期

​	（1）通过构造器创建bean实例（无参数构造）

​	（2）为bean的属性设置值和对其他bean引用（调用set方法）

​	（3）调用bean的初始化的方法（需要进行配置）

​	（4）获取bean对象

​	（5）容器关闭时，调用bean的销毁的方法（需要进行配置销毁的方法）

​	XML自动装配

* 根据指定装配规则（属性名称或者属性类型），Spring自动将匹配的属性值进行注入

```xml
<!-- 自动装配 
	bean标签属性：autowire，配置自动装配
	autowire属性常用值：
		byName：根据属性名称注入，注入值bean的id和类属性名称一样
		byType：根据属性类型注入
-->
```

​	外部属性文件

​	（1）直接配置数据库信息

​			配置德鲁伊连接池

​			引入德鲁伊连接池依赖jar包

​	（2）引入外部属性文件配置数据库连接池

2. 基于注解方式实现

   注解是代码特殊标记——@注解名称（属性名称=属性值，属性名称=属性值...）

   注解作用在类上面，方法上面，属性上面

   相比于xml方式，简洁方便

   

   **创建对象**：

   Spring针对Bean管理中的创建对象提供以下注解：

   * @Component
   * @Service
   * @Controller
   * @Repository

   > 四个注解功能是一样的，都可以用来创建bean实例。每个注解可以用在不同的层上，在开发时，为了方便开发人员，最好将相应的注解写在相应的层上。

   基于注解方式创建对象：

   * 引入依赖

   * 开启组件扫描

     * 在配置文件中引入名称空间

     ```xml
     <!-- 开启组件扫描 -->
     <context:component-scan base-package="com.spring.demo"/>
     
     ############################################################################################################
     
     // 注解里面value属性值可以省略不写
     // 默认值是类名称，首字母小写
     @Component(value = "annotationTest") //<bean id="annotationTest" class".."/>
     public class AnnotationTest {
         public void a() {
             System.out.println("A method");
         }
     }
     
     ```

   * 创建类，在类上面添加创建对象注解

   ```xml
   <!--
   如果不设置filter，系统使用默认filter
   可以通过修改use-default-filters="false" 来自己配置filter
   context:include-filter, 设置扫描哪些内容
   contest:exclude-filter, 设置不扫描哪些内容
   -->
   <context:component-scan base-package="com.spring.demo" use-default-filters="false">
       <context:include-filter type="annotation"
                               expression="org.springframework.stereotype.Controller"/>
   </context:component-scan>
   ```

   

   **属性注入**：

   * @Autowired：根据属性类型进行自动装配
   * @Qualifier：根据属性名称注入（需要和Autowired一起使用）
   * @Resource：可以根据类型注入，可以根据名称注入
   * @Value：注入普通类型属性

```java
@Service
public class UserService {

    // 不需要添加set方法
    // 添加注入属性注解
    @Autowired // 根据类型注入
    @Qualifier(value = "userDaoImpl2")
    private UserDao userDao;

    public void a() {
        System.out.println("User Service");
        userDao.a();
    }
}
```

```java
//    @Resource // 根据类型注入
    @Resource(name = "userDaoImpl2") // 根据名称注入
    private UserDao userDao;
// @Resource不是Spring包中的，是javax（java拓展包）包中的
```

```java
@Value(value = "A String")
private String s;
```



**完全注解开发**（不使用xml配置文件）

创建配置类替代配置文件：

```java
//使用@Configuration将类作为配置类，替代xml配置文件
//使用@ComponentScan替代组件扫描
@Confuguration
@ComponentScan(basePackages = {"com.spring.demo"})
public class SpringConfigDemo {
}

// 一般使用SpringBoot做
```



## AOP（面向切面编程）

利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

（不修改源代码，在主干功能里面添加新功能）

AOP底层使用动态代理

* 有两种情况的动态代理

  * 有接口，使用**JDK动态代理**

    -> 创建接口实现类**代理对象**，增强类的方法

  * 无接口，使用**CGLIB动态代理**

    -> 创建当前类的子类的**代理对象**，增强类的方法

> 术语：
>
> 1. 链接点：类里面的哪些方法可以被增强，这些方法称为连接点
> 2. 切入点：实际真正增强的方法称为切入点
> 3. 通知（增强）：实际增强的逻辑部分称为通知
>    * 前置通知
>    * 后置通知
>    * 环绕通知
>    * 异常通知
>    * 最终通知：类似try...catch...finally中的finally
> 4. 切面（是动作）：把通知应用到切入点的过程

1. 使用JDK动态代理 —— 使用Proxy类里面的方法创建代理对象

   调用newProxyInstance

   第一个参数：类加载器

   第二个参数：增强方法所在的类，这个类实现的接口，支持多个接口

   第三个参数：实现InvocationHandler，创建代理对象，写增强的方法

   

   Spring框架一般基于AspectJ实现AOP操作

   > AspectJ不是Spring组成部分，是独立AOP框架，一般把AspectJ和Spring框架一起使用，进行AOP操作

   

   基于AspectJ实现AOP操作

   * 基于xml配置文件实现
   * **基于注解方式实现**

   

   切入点表达式

   * 表明对哪个类的哪个方法进行增强
   * 语法：execution([权限修饰符] [返回类型] [类全路径] ([参数列表]))

   > execution(* com.atguigu.dao.BookDao.add()) *\*号表示所有*

