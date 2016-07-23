package org.smart.framework.util;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Propertis操作类
 * @author sunkang
 *
 */
public class PropertisUtil {
	
	/**
	 * 获得Propertis
	 * @param propertisName Propertis的名字
	 * @return
	 */
	public static Properties getProperties(String propertisName){
		Properties p = new Properties();
		InputStream in=null;
		try {
			//获得项目根目录路径，应为该方法为静态的，所以得用object
			in = Object.class.getResourceAsStream(propertisName);
			p.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return p;
	}
	
	public static String getString(Properties properties,String key){
		String str=properties.get(key)+"";
		return str;
	}
	
	public static Integer getInt(Properties properties,String key){
		Integer value=CastUtil.castInt(properties.get(key)) ;
		return value;
	}
	
	public static void main(String[] args) {
		try {
			Properties p= PropertisUtil.getProperties("/smart.properties");
			String value=getString(p,"smart.framework.jdbc.driver");
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
