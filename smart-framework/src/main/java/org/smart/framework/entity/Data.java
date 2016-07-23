package org.smart.framework.entity;

/**
 * action返回个页面的数据，如ajax调用的时候
 * @author sunkang
 *
 */
public class Data {
	
	private Object model;
	
	

	public Data(Object model) {
		super();
		this.model = model;
	}



	public Object getModel() {
		return model;
	}

	
}
