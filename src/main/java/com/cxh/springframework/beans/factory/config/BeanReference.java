package com.cxh.springframework.beans.factory.config;

/**
 * 类引用，存放beanName，在bean属性依旧为bean时进行递归创建和属性填充
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName(){
        return beanName;
    }
}
