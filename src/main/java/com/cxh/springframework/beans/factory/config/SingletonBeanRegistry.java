package com.cxh.springframework.beans.factory.config;

/**
 * 负责单例bean的注册
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
