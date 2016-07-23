package org.smart.framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart.framework.annotation.Controller;
import org.smart.framework.annotation.Dao;
import org.smart.framework.annotation.Service;
import org.smart.framework.util.ClassUtil;

/**
 * 获得各种class集合的帮助类
 * @author sunkang
 *
 */
public final class ClassHelper {
	/**
	 * 存放要加载的class集合
	 */
	private static final Set<Class<?>> CLASS_SET;
	static{
		CLASS_SET=ClassUtil.getClassSet(ConfigHelper.getAppBasePackage());
	}
	
	/**
	 * 获取基础包名下的所有类
	 * @return
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * 获得所有的controller类
	 * @return
	 */
	public static Set<Class<?>> getControllerClass(){
		 Set<Class<?>> classSet=new HashSet<Class<?>>();
		 for(Class<?> cls:CLASS_SET){
			 //如果注解是Controller类型
			 if(cls.isAnnotationPresent(Controller.class)){
				 classSet.add(cls);
			 }
		 }
		 return classSet;
	}
	
	/**
	 * 获得所有的service注解类
	 * @return
	 */
	public static Set<Class<?>> getServiceClass(){
		Set<Class<?>> classSet=new HashSet<Class<?>>();
		for(Class<?> cls:CLASS_SET){
			if(cls.isAnnotationPresent(Service.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获得所有的dao的bean类
	 * @return
	 */
	public static Set<Class<?>> getDaoClass(){
		Set<Class<?>> classSet=new HashSet<Class<?>>();
		for(Class<?> cls:CLASS_SET){
			if(cls.isAnnotationPresent(Dao.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	/**
	 * 获得所有的bean类（包括controller，services,dao）
	 * @return
	 */
	public static Set<Class<?>> getBeanClass(){
		Set<Class<?>> classSet=new HashSet<Class<?>>();
		classSet.addAll(getControllerClass());
		classSet.addAll(getServiceClass());
		classSet.addAll(getDaoClass());
		return classSet;
	}
}

