package com.cxh.springframework.beans.factory.support;

import com.cxh.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例bean默认注册的实现类
 * 功能：获取单例bean、注册单例bean
 * 这里
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    // 和DefaultListableBeanFactory中的map不同之处在于:
    // 这里的map存储了所有子类的bean，每次子类创建bean时，都会调用addSingleton
    // 目的: 为了方便判断bean是否创建，以此实现单例
    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    // protected: 同一包或子孙类可以访问
    protected void addSingleton(String beanName, Object singletonObject){
         singletonObjects.put(beanName, singletonObject);
    }
}
