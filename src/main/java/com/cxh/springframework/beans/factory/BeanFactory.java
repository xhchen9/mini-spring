package com.cxh.springframework.beans.factory;

import com.cxh.springframework.beans.BeansException;

/**
 * 负责bean的获取
 */
public interface BeanFactory {

   Object getBean(String beanName) throws BeansException;

   // 实例化对象时，可传入参数args，调用有参构造
   Object getBean(String beanName, Object... args) throws BeansException;
}
