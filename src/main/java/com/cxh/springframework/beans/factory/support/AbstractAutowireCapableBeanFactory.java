package com.cxh.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.cxh.springframework.beans.BeansException;
import com.cxh.springframework.beans.factory.PropertyValue;
import com.cxh.springframework.beans.factory.PropertyValues;
import com.cxh.springframework.beans.factory.config.BeanDefinition;
import com.cxh.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    // 创建对象的实例化策略属性类
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;

        try{
            // 利用beanDefinition的class以及cglib实例化
            bean = createBeanInstance(beanDefinition, beanName, args);
            // bean属性填充
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 将bean添加至父类map
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructor = null;

        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor ctor : declaredConstructors){
            // 根据构造函数参数数目选择构造函数（简单化了）
            if(args != null && ctor.getParameterTypes().length == args.length){
                constructor = ctor;
                break;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
    }

    /**
     * Bean 属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference){  // 表示value依旧为bean
                    BeanReference beanReference = (BeanReference) value;
                    // 递归获取最终的value: AbstractBeanFactory.getBean(beanName) ---> this.createBean()
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充，通过反射设置字段值，将bean的name变量值设置为value
                BeanUtil.setFieldValue(bean, name, value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
