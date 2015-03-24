package com.lc.sofa.core.framework.basis.log;

import java.lang.reflect.Method;

/**
 * 
 * 平台日志服务接口
 * @author   YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public interface SofaLogger {


	
	/**
	 * 记录未指定日志类型的debug级别的日志
	 * 
	 * @param msg
	 * 			日志摘要信息
	 */
	public void debug(String msg);
	
	
   /**
	 * 记录未指定日志类型的debug级别的日志
	 * 
	 * @param format
	 * 				带动态参数的日志摘要信息
	 * @param arguments
	 * 				摘要信息中对应的动态参数
	 */
	public void debug(String format, Object...arguments);
	
	/**
	 * 记录未指定日志类型的info级别的日志
	 * 
	 * @param msg
	 * 			日志摘要信息
	 */
	public void info(String msg);
	
	/**
	 * 记录未指定日志类型的info级别的日志
	 * 
	 * @param format
	 * 				带动态参数的日志摘要信息
	 * @param arguments
	 * 				摘要信息中对应的动态参数
	 */
	public void info(String format, Object...arguments);
	
	
	/**
	 * 记录未指定日志类型的warn级别的日志
	 * 
	 * @param msg
	 * 			日志摘要信息
	 */
	public void warn(String msg);
	
	
	/**
	 * 记录未指定日志类型的warn级别的日志
	 * 
	 * @param format
	 * 				带动态参数的日志摘要信息
	 * @param arguments
	 * 				摘要信息中对应的动态参数
	 */
	public void warn(String format, Object...arguments);
	
	
	/**
	 * 记录未指定日志类型的error级别的日志
	 * 
	 * @param msg
	 * 			日志摘要信息
	 */
	public void error(String msg);
	
	
	/**
	 * 记录未指定日志类型的error级别的日志
	 * 
	 * @param format
	 * 				带动态参数的日志摘要信息
	 * @param arguments
	 * 				摘要信息中对应的动态参数
	 */
	public void error(String format, Object...arguments);
	
	
	/**
	 * 记录未指定日志类型的debug级别的日志
	 * 
	 * @param format
	 * 				带动态参数的日志摘要信息
	 * @param throwable
	 * 				异常对象
	 */
	public void error(String msg, Throwable throwable);
	
	
	/**
	 * 记录用户行为日志(日志组件实现)
	 * @param targetClass-目标类
	 * @param targetMethod-目标方法
	 * @param arguments-目标方法参数
	 */
	public  void info(Class targetClass,Method targetMethod,Object[] arguments);
	
	/**
	 * 记录用户行为异常(日志组件实现)
	 * @param targetClass-目标类
	 * @param targetMethod-目标方法
	 * @param arguments-目标方法参数
	 * @param throwable-目标方法执行跑出的异常
	 */
	public  void error(Class targetClass,Method targetMethod,Object[] arguments,Throwable throwable);
	
	
}
