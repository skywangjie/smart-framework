package org.smart.framework.entity;
/**
 * 请求的对象
 * @author sunkang
 *
 */
public class Request {
	
	//请求对象的类型，如get，post
	private String requestType;
	
	//请求的路径
	private String requestPath;

	
	
	public Request(String requestType, String requestPath) {
		super();
		this.requestType = requestType;
		this.requestPath = requestPath;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	
	
	
	
	
	
}
