package org.smart.framework.entity;

import java.util.Map;

/**
 * action中的请求的参数（即前台传过来的参数）
 * @author sunkang
 *
 */
public class Param {
	
	/**
	 * 参数
	 */
	private Map<String, Object> paramMap;

	
	
	public Param(Map<String, Object> paramMap) {
		super();
		this.paramMap = paramMap;
	}


	/**
	 * 获得所有参数
	 * @return
	 */
	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	
}
