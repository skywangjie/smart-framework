package org.smart.framework.helper;

import java.util.Properties;

import org.smart.framework.conf.ConfigConstont;
import org.smart.framework.util.PropertisUtil;
/**
 * 获得配置的帮助类
 * @author sunkang
 *
 */
public final class ConfigHelper {
	
	private static final Properties config_props=PropertisUtil.getProperties(ConfigConstont.conf_file);
	
	/**
	 * 获取驱动
	 * @return
	 */
	public static String getJdbcDriver(){
		return PropertisUtil.getString(config_props, ConfigConstont.jdbc_dirver);
	}
	
	/**
	 * 获取URL、
	 * @return
	 */
	public static String getJdbcUrl(){
		return PropertisUtil.getString(config_props, ConfigConstont.jdbc_url);
	}
	
	/**
	 * 获取数据库 用户名
	 * @return
	 */
	public static String getJdbcUsername(){
		return PropertisUtil.getString(config_props, ConfigConstont.jdbc_username);
	}
	
	/**
	 * 获取数据库密码
	 * @return
	 */
	public static String getJdbcPassword(){
		return PropertisUtil.getString(config_props, ConfigConstont.jdbc_password);
	}
	
	/**
	 * 获取基本包的路径
	 * @return
	 */
	public static String getAppBasePackage(){
		return PropertisUtil.getString(config_props, ConfigConstont.app_base_package);
	}
	
	/**
	 * 获取jsp路径
	 * @return
	 */
	public static String getAppJspPath(){
		return PropertisUtil.getString(config_props, ConfigConstont.app_jsp_path);
	}
	
	/**
	 * 获取静态资源的路径
	 * @return
	 */
	public static String getAppAssetPath(){
		return PropertisUtil.getString(config_props, ConfigConstont.app_asset_path);
	}
	
	
	public static void main(String[] args) {
		System.out.println(getJdbcDriver()); 
	}
}
