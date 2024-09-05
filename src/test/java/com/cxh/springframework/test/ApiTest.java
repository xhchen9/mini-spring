package com.cxh.springframework.test;

import com.cxh.springframework.beans.factory.PropertyValue;
import com.cxh.springframework.beans.factory.PropertyValues;
import com.cxh.springframework.beans.factory.config.BeanDefinition;
import com.cxh.springframework.beans.factory.BeanFactory;
import com.cxh.springframework.beans.factory.config.BeanReference;
import com.cxh.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cxh.springframework.test.bean.UserDAO;
import com.cxh.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        /**
         * 流程：
         *  1. 创建单例bean工厂
         *  2. 将变量bean UserDAO作为BeanDefinition先注册至单例bean工厂
         *  3. 设置BeanDefinition的属性名及对应的至
         *  4. 将UserService作为BeanDefinition注册至单例bean工厂
         *  5. 利用beanName从单例bean工厂获取bean实例（已填充属性
         * 总结：
         *  1. BeanDefinition中存储了bean的字节码以及属性名-属性值的kv结构
         *  2. Bean实例化时，利用BeanDefinition中信息，可实例化对象并进行属性填充
         */
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDAO注册
        beanFactory.registerBeanDefiniton("userDAO", new BeanDefinition(UserDAO.class));

        // 3. UserService设置属性: uid, userDAO 格式: [name, value]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "00001"));
        propertyValues.addPropertyValue(new PropertyValue("userDAO", new BeanReference("userDAO")));

        // 4. UserService注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefiniton("userService", beanDefinition);

        // 5. UserService获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
