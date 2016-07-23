package org.smart.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart.framework.annotation.Action;
import org.smart.framework.entity.Handler;
import org.smart.framework.entity.Request;

/**
 * 控制器的注解类
 * @author sunkang
 *
 */
public class ControllerHelper {
	
	private static final Map<Request, Handler> 	ACTION_MAP=new HashMap<Request, Handler>();
	
	//初始化请求和处理方法的映射
	static{
		//获得所有的controller
		Set<Class<?>> controllerSet=ClassHelper.getControllerClass();
		if(controllerSet!=null){
			//遍历这些controller
			for(Class<?> controllerClass:controllerSet){
				//获得controller中所有的方法
				Method[] methods=controllerClass.getDeclaredMethods();
				if(methods!=null){
					//遍历所有的方法
					for(Method method:methods){
						//检验是否带action注解
						if(method.isAnnotationPresent(Action.class)){
							//获得该方法的action注解对象
							Action action=method.getAnnotation(Action.class);
							//获得action注解里面的value值
							String mapping=action.value();
							//验证映射规则是否合法
							if(mapping!=null&&!"".equals(mapping)&&mapping.matches("\\w+:/\\w*")){
								String[] array=mapping.split(":");
								//获得请求的类型和路径
								if(array!=null&&array.length==2){
									String requestType=array[0];
									String requestPath=array[1];
									Request request=new Request(requestType, requestPath);
									Handler handler=new Handler(controllerClass, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * 获取处理的方法
	 * @param requestType 请求的类型（get，post）
	 * @param requestPath 请求的路径
	 * @return
	 */
	public static Handler getHandler(String requestType,String requestPath){
		Request request=new Request(requestType, requestPath);
		return ACTION_MAP.get(request);
	}
}
