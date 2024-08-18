package com.cxh.springframework.beans.factory.support;

import com.cxh.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefiniton(String beanName, BeanDefinition beanDefinition);
}
