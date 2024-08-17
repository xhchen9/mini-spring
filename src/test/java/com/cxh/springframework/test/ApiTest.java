package com.cxh.springframework.test;

import com.cxh.springframework.BeanDefinition;
import com.cxh.springframework.BeanFactory;
import com.cxh.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("UserService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("UserService");
        userService.queryUserInfo();
    }
}
