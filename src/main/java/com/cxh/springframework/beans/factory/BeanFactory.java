package com.cxh.springframework.beans.factory;

/**
 * 负责bean的获取
 */
public interface BeanFactory {

   Object getBean(String beanName);
}
