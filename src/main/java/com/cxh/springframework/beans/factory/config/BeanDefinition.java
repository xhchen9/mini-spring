package com.cxh.springframework.beans.factory.config;

public class BeanDefinition {

    private Class beanClass;   // 因为ioc容器不保留实例对象信息，只保留类信息，因此使用Class而非Object

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass(){
        return beanClass;
    }
}
