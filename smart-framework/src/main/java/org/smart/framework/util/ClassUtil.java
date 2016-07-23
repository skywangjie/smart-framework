package org.smart.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil {
	private static final Logger logger=LoggerFactory.getLogger(ClassUtil.class);
	
	/**
	 * 获得类加载器
	 * @return
	 */
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 加载类
	 * @param className
	 * @param isInitialized
	 * @return
	 */
	public static Class<?> loadClass(String className,Boolean isInitialized){
		Class<?> cls=null;
		try {
			cls=Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cls;
		
	}
	/**
	 * 获得包下面所有的类
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet=new HashSet<Class<?>>();
		try {
			//获得当前包所在的文件路径
			Enumeration<URL> uels=getClassLoader().getResources(packageName.replace(".", "/"));
			while (uels.hasMoreElements()) {
				URL url = (URL) uels.nextElement();
				//获得文件属性
				String protocol=url.getProtocol();
				System.out.println(packageName);
				System.out.println(protocol);
				if("file".equals(protocol)){
					//当前包的文件路径
					String packagePath=url.getPath().replace("%20", "/");
					addClass(classSet,packagePath,packageName);
				}else if("jar".equals(protocol)){
					JarURLConnection jarURLConnection=(JarURLConnection) url.openConnection();
					if(jarURLConnection !=null){
						JarFile jarFile= jarURLConnection.getJarFile();
						if(jarFile!=null){
							Enumeration<JarEntry> enumeration= jarFile.entries();
							while (enumeration.hasMoreElements()) {
								JarEntry jarEntry = (JarEntry) enumeration.nextElement();
								String jarEntryName=jarEntry.getName();
								if(jarEntryName.endsWith(".class")){
									String className=jarEntryName.substring(0, jarEntryName.lastIndexOf("."));
									doAddClass(classSet,className);
								}
								
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return classSet;
	}
	/**
	 * 
	 * @param classSet
	 * @param packagePath 包的文件路径
	 * @param packageName 包名
	 */
	private static void addClass(Set<Class<?>> classSet ,String packagePath,String packageName){
		File file=new File(packagePath);
		//获得该文件下的所有文件,过滤出class文件或者文件夹
		File[] listFile=file.listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				//为true的过滤出来
				return ((file.isFile()&& file.getName().endsWith(".class"))||file.isDirectory());
			}
		});
		for(File f:listFile){
			String fileName=f.getName();
			//如果是文件夹的话将该名字拼接到包的路径上面，再往下层找
			if(f.isDirectory()){
				String subPackageName=fileName;
				if(StringUtil.isNotEmpty(packageName)){
					subPackageName=packageName+"."+subPackageName;
				}
				String subPackagePath=fileName;
				if(StringUtil.isNotEmpty(packagePath)){
					subPackagePath=packagePath+"/"+subPackagePath;
				}
				addClass(classSet,subPackagePath,subPackageName);
			//如果是文件的话，将该class加载到set中
			}
//			else if(file.getName().endsWith(".jar")){
//				System.out.println(file.getName());
////				JarURLConnection jarURLConnection=(JarURLConnection) url.openConnection();
////				if(jarURLConnection !=null){
////					JarFile jarFile= jarURLConnection.getJarFile();
////					if(jarFile!=null){
////						Enumeration<JarEntry> enumeration= jarFile.entries();
////						while (enumeration.hasMoreElements()) {
////							JarEntry jarEntry = (JarEntry) enumeration.nextElement();
////							String jarEntryName=jarEntry.getName();
////							if(jarEntryName.endsWith(".class")){
////								String className=jarEntryName.substring(0, jarEntryName.lastIndexOf("."));
////								doAddClass(classSet,className);
////							}
////							
////						}
////					}
////				}
//			}
			else{
				String className=fileName.substring(0, fileName.lastIndexOf("."));
				if(StringUtil.isNotEmpty(packageName)){
					className=packageName+"."+className;
				}
				doAddClass(classSet,className);
			}
		}
	}
	
	/**
	 * 获得class并且放到set中
	 * @param classSet
	 * @param className
	 */
	private static void doAddClass(Set<Class<?>> classSet,String className){
		Class cls=loadClass(className, false);
		classSet.add(cls);
	}
	
	public static void main(String[] args) {
		try {
			Set<Class<?>> classSet=new HashSet<Class<?>>();
			classSet=getClassSet("org.smart.framework.helper.charsets");
			System.out.println(classSet.size());
			Enumeration<URL> uels=getClassLoader().getResources("org/smart/framework");
			
			while (uels.hasMoreElements()) {
				URL url = (URL) uels.nextElement();
				String str=url.getProtocol();
//				System.out.println(url.getPath());
//				System.out.println(url.getPath().replaceAll("%20", "/"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
