package org.smart.framework.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * action跳转到jsp处的参数和路径
 * @author sunkang
 *
 */
public class View {
	
	private String path;
	
	private Map<String, Object> model;
	
	

	public View(String path) {
		super();
		this.path = path;
		this.model =new   HashMap<String, Object>();
	}
	
	/**
	 * 在视图上面添加数据
	 * @param key
	 * @param value
	 * @return
	 */
	public View addModel(String key,Object value){
		model.put(key, value);
		return this;
	}

	/**
	 * 获得跳转的路径
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 获得数据模型
	 * @return
	 */
	public Map<String, Object> getModel() {
		return model;
	}

	
}
