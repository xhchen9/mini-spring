package com.cxh.springframework.beans.factory;

/**
 * 创建Bean时需要填充的属性
 */
public class PropertyValue {
    private final String name;  // 属性名称

    private final Object value;  // 如果value依旧为bean，则只存放BeanName

    public PropertyValue(String name, Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
