package org.smart.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart.framework.util.ReflectionUtil;

/**
 * 用于创建bean的映射和，获取bean，单例
 * @author sunkang
 *
 */
public class BeanHelper {

	private static final Map<Class<?>, Object> BEAN_MAP=new HashMap<Class<?>, Object>();
	
	//初始化bean的映射
	static{
		Set<Class<?>> classSet=ClassHelper.getClassSet();
		for(Class<?> cls:classSet){
			BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls));
		}
	}
	
	/**
	 * 获得bean映射
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 
	 * 根据类名获取bean
	 * @param cls
	 * @return
	 */
	public static Object getBean(Class<?> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by "+cls);
		}
		return BEAN_MAP.get(cls);
	}
}
