package com.cxh.springframework.beans.factory.support;

import com.cxh.springframework.beans.BeansException;
import com.cxh.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    /**
     * 实例化策略接口
     * @param beanDefinition
     * @param beanName
     * @param ctor 包含类信息，可以拿到符合入参信息的构造函数
     * @param args 入参信息
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
