package com.starfall.test;

import com.starfall.config.sf_config;
import com.starfall.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
//        System.out.println(userService.getHead("1"));
    }

}
