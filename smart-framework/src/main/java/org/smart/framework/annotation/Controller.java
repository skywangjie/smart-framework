package org.smart.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解
 * @Target(ElementType.TYPE)
 *  这是一个enum类型,共有三个值，分别是SOURCE,CLASS 和 RUNTIME. 
 SOURCE代表的是这个Annotation类型的信息只会保留在程序源码里，源码如果经过了编译之后，Annotation的数据就会消失,并不会保留在编译好的.class文件里面。 
 ClASS的意思是这个Annotation类型的信息保留在程序源码里,同时也会保留在编译好的.class文件里面,在执行的时候，并不会把这一些信息加载到虚拟机(JVM)中去.注意一下，当你没有设定一个Annotation类型的Retention值时，系统默认值是CLASS. 
 RUNTIME,表示在源码、编译好的.class文件中保留信息，在执行的时候会把这一些信息加载到JVM中去的． 
 
 * @Retention(RetentionPolicy.RUNTIME)
 * TYPE(类型), FIELD(属性), METHOD(方法), PARAMETER(参数), CONSTRUCTOR(构造函数),LOCAL_VARIABLE(局部变量), ANNOTATION_TYPE,PACKAGE(包),其中的TYPE(类型)是指可以用在Class,Interface,Enum和Annotation类型上. 
 * @author sunkang
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
	
}
