package com.cxh.springframework.beans.factory.config;

import com.cxh.springframework.beans.factory.PropertyValues;

/**
 * Bean的具体信息，包含类信息和属性信息
 * 通过BeanDefinition创建具体Bean实例
 */
public class BeanDefinition {

    private Class beanClass;   // 因为ioc容器不保留实例对象信息，只保留类信息，因此使用Class而非Object

    private PropertyValues propertyValues;   // bean对应的属性信息

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null? propertyValues : new PropertyValues();
    }

    public Class getBeanClass(){
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
