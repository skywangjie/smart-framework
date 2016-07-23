package org.smart.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smart.framework.entity.Data;
import org.smart.framework.entity.Handler;
import org.smart.framework.entity.Param;
import org.smart.framework.entity.View;
import org.smart.framework.helper.ConfigHelper;
import org.smart.framework.helper.ControllerHelper;
import org.smart.framework.util.ReflectionUtil;
/**
 * 控制转发器
 * @author sunkang
 *
 */
@WebServlet(urlPatterns="/",loadOnStartup=0)
public class DispatcherServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//获得请求的类型
		String requestMethod=req.getMethod().toLowerCase();
		//获得请求的路径
		String requestPath=req.getPathInfo();
		//获得处理请求的action
		Handler handler= ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler!=null){
			//创建并获取请求参数
			Map<String, Object> paramMap=new HashMap<String, Object>();
			//获得请求的所有参数的名字
			Enumeration<String> paramNames=req.getParameterNames();
			while(paramNames.hasMoreElements()){
				String paramName=paramNames.nextElement();
				//获得请求参数
				String paramValue=req.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			//获得的请求的流
			
			//请求参数
			Param param=new Param(paramMap);
			//获得controller
			Class<?> controllerClass=handler.getControllerClass();
			//获得请求的controller的实体
			Object controllerObj=ReflectionUtil.newInstance(controllerClass);
			//获得处理请求的action方法
			Method actionMethod=handler.getActionMethod();
			//调用action方法并且获得返回值
			Object result=(String) ReflectionUtil.invokeMethod(controllerObj, actionMethod, param); 
			//如果返回的是视图
			if(result instanceof View){
				View view=(View) result;
				String path=view.getPath();
				//如果以/开头，则为重定向
				if(path.startsWith("/")){
					//跳转到新的action
					res.sendRedirect(req.getContextPath()+path);
				}else{//否则为转发
					Map<String, Object > model=view.getModel();
					for(Map.Entry<String, Object> entry:model.entrySet()){
						req.setAttribute(entry.getKey(), entry.getValue());
					}
					req.getRequestDispatcher(ConfigHelper.getAppAssetPath()+path).forward(req, res);
				}
			}
			//如果返回的是数据类型
			if(result instanceof Data){
				Data data=(Data) result;
				Object model=data.getModel();
				if(model!=null){
					//响应为json类型
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					PrintWriter printWriter=res.getWriter();
					String json=
					printWriter.write(json);
					printWriter.flush();
					printWriter.close();
				}
			}
			
		}
	}

	

	
}
