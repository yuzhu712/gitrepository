package com.lc.sofa.core.framework.basis.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 日志记录注解
 * @author       YZ
 * @version 1.0, 2014-1-10
 * @since 1.0, 2014-1-10
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordLog {
    
	/**
	 * 业务功能编号
	 * @return
	 */
	String functionCode() default "";
	
	/**
	 * 业务操作编号
	 * @return
	 */
	String operationCode()  default "";
	
	/**
	 * 业务功能名称
	 * @return
	 */
	String functionName()  default "";
	
	
	/**
	 * 业务操作名称
	 * @return
	 */
	String operationName() default "";
	
	/**
	 * 操作URL
	 * @return
	 */
	String operationURL()  default "";

	/**
	 * 描述信息
	 * @return
	 */
	String description()  default "";
	
}
