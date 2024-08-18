package com.cxh.springframework.beans.factory.support;

import com.cxh.springframework.beans.BeansException;
import com.cxh.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;

        try{
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 将bean添加至父类map
        addSingleton(beanName, bean);
        return bean;
    }
}
