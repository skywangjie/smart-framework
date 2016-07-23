package org.smart.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 反射的帮助类，主要用于创建对象
 * @author sunkang
 *
 */
public class ReflectionUtil {
	private static final Logger logger=LoggerFactory.getLogger(ReflectionUtil.class);
	
	
	/**
	 * 创建实体类
	 * @param cls
	 * @return
	 */
	public static Object newInstance(Class<?> cls){
		
		
		Object instance=null;
		try {
			instance=cls.newInstance();
		} catch (Exception e) {
			logger.error("new instance failure", e);
		} 
		return instance;
	}
	
	/**
	 * 调用方法
	 * @param obj 要调用的对象
	 * @param method 要调用的方法
	 * @param args 要调用的方法的参数
	 * @return 调用的方法的返回值
	 */
	public static Object invokeMethod(Object obj,Method method,Object args){
		Object result=null;
		try {
			result=method.invoke(obj, args);
		} catch (Exception e) {
			logger.error("invoke method failure", e);
		}
		return result;
	}
	
	/**
	 * 设置实体类的属性
	 * @param obj 实体对象
	 * @param field 属性
	 * @param value 设置的值
	 */
	public static void  setField(Object obj,Field field,Object value){
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			logger.error("set field failure", e);
		} 
	}
	
	public  String test1="初始化";
	
	public String test(String pram){
		return pram;
	}
	
	public static void main(String[] args) {
		//创建对象
		ReflectionUtil reflectionUtil= (ReflectionUtil) newInstance(ReflectionUtil.class);
		Method method;
		try {
			//获得类的方法
			method = ReflectionUtil.class.getMethod("test", String.class);
			//调用该方法
			String result=invokeMethod(reflectionUtil, method, "hahah")+"";
			System.out.println(result);
			Field field=ReflectionUtil.class.getField("test1");
			System.out.println(reflectionUtil.test1);
			setField(reflectionUtil,field,"hahahaha ");
			System.out.println(reflectionUtil.test1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
