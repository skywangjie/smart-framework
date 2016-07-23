package org.smart.framework.entity;

import java.lang.reflect.Method;

/**
 * 请求的处理对象
 * @author sunkang
 *
 */
public class Handler {

	/**
	 * controller类
	 */
	private Class<?> controllerClass;
	
	/**
	 * action注解对应的方法
	 */
	private Method actionMethod;

	public Class<?> getControllerClass() {
		return controllerClass;
	}
	
	
	
	public Handler(Class<?> controllerClass, Method actionMethod) {
		super();
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}



	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(Method actionMethod) {
		this.actionMethod = actionMethod;
	}
	
	
	
}
