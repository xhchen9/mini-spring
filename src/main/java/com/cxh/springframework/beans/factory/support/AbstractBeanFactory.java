package com.cxh.springframework.beans.factory.support;

import com.cxh.springframework.beans.BeansException;
import com.cxh.springframework.beans.factory.BeanFactory;
import com.cxh.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象beanfactory
 * 具备获取bean、bean注册两大功能
 * 其可以继承多种bean注册器，实现不同类型的bean注册、获取
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException{
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args){
        // bean register由具体子类实现，子类添加至子类自己的map
        // get bean 会先判断父类的map是否存在，存在返回；不存在从子类自己的map获取并添加至父类map
        // 目前默认单例bean，故直接调用 DefaultSingletonBeanRegistry#getSingleton
        Object bean = getSingleton(beanName);

        if(bean != null){  // DefaultSingletonBeanRegistry中存在直接返回，否则从子类DefaultListableBeanFactory取
            return (T) bean;
        }

        // 具体实现由具体子类重写，便于扩展

        // 如果bean为null，则调用具体子类的getBeanDefiniton，从其map中获取BeanDefiniton  （不同实现类管理自己的bean，便于管理
        BeanDefinition beanDefinition = getBeanDefiniton(beanName);

        return (T) createBean(beanName, beanDefinition, args); // 在父类map中添加bean（从子类获取，添加至父类
    }

    /**
     * 因为不止有单例bean，故下面两个方法为抽象方法，具体的实现由具体类实现
     */
    protected abstract BeanDefinition getBeanDefiniton(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
