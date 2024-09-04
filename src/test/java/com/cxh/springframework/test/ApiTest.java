package com.cxh.springframework.test;

import com.cxh.springframework.beans.factory.config.BeanDefinition;
import com.cxh.springframework.beans.factory.BeanFactory;
import com.cxh.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cxh.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefiniton("userService", beanDefinition);

        // 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "张三");  // 可满足带构造函数的对象创建
        userService.queryUserInfo();

        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();
    }
}
