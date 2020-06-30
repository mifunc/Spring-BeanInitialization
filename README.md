- @PostConstruct 构造后置执行
- @Bean(initMethod="xxx") 初始化功能
- @InitializingBean#afterPropertiesSet 属性填充后执行

**DefaultRumenzFactory.java**

```java
package com.rumenz;
import org.springframework.beans.factory.InitializingBean;
import javax.annotation.PostConstruct;

public class DefaultRumenzFactory implements  InitializingBean {

    public DefaultRumenzFactory() {
        System.out.println("无参构造方法执行....");
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct init.......");
    }
    public void initMethod(){
        System.out.println("init method.......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet.....");
    }
}

```
**DemoApplication.java调用**

```java
package com.rumenz;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class DemoApplication {
    public static  void main(String[] args) {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        ac.register(DemoApplication.class);
        ac.refresh();
        DefaultRumenzFactory bean = ac.getBean(DefaultRumenzFactory.class);
        ac.close();
    }
    @Bean(initMethod = "initMethod")
    public static  DefaultRumenzFactory defaultRumenzFactory(){
        return new DefaultRumenzFactory();
    }

}

```
**输出**

```
xxx.DefaultListableBeanFactory - Creating shared instance of singleton bean 'defaultRumenzFactory'
无参构造方法执行....
PostConstruct init.......
afterPropertiesSet.....
init method.......
```

>执行顺序  
1.@PostConstruct
2.@InitializingBean#afterPropertiesSet
3.@Bean(initMethod="xxx")

源码:https://github.com/mifunc/Spring-BeanInitialization


原文: [https://rumenz.com/rumenbiji/.html](https://rumenz.com/rumenbiji/.html)
