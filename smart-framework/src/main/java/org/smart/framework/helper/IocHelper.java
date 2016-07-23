package org.smart.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.smart.framework.annotation.Inject;
import org.smart.framework.util.ReflectionUtil;

/**
 * 实现依赖注入的帮助类
 * @author sunkang
 *
 */
public class IocHelper {
	

	static{
		//获取bean映射
		Map<Class<?>, Object> beanMap= BeanHelper.getBeanMap();
		if(beanMap!=null){
			//便利bean的映射
			for(Map.Entry<Class<?>, Object> beanEntry:beanMap.entrySet()){
				Class<?> beanClass=beanEntry.getKey();
				Object beanInstance=beanEntry.getValue();
				//获得bean的所有成员变量
				Field[] beanFields=beanClass.getDeclaredFields();
				for(Field field:beanFields){
					//获取要注入的对象，并且赋值给该变量
					Class<?> beanFieldClass=field.getType();
					Object beanFieldInstance=beanMap.get(beanFieldClass);
					if(beanFieldInstance!=null){
						if(field.isAnnotationPresent(Inject.class)){
							ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
						}
					}
				}
			}
		}
		
	}

}
