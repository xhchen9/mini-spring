package com.cxh.springframework.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建Bean时需要填充的属性集合
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名称获取属性
     * @param propertyName 属性名称
     * @return 属性值
     */
    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue propertyValue : propertyValueList){
            if(propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }
}
