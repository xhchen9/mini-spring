package com.cxh.springframework.beans.factory.support;

import com.cxh.springframework.beans.BeansException;
import com.cxh.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 通过 beanDefinition 获取 Class 信息，这个 Class 信息是在 Bean 定义的时候传递进去的

        Class clazz = beanDefinition.getBeanClass();

        try{
            if(ctor != null){  // 有参构造
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else{  // 无参构造
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
