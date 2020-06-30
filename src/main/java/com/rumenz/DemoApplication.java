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
