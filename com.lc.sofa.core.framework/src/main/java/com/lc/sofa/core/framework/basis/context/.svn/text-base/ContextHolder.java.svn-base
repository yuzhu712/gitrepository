package com.lc.sofa.core.framework.basis.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.IllegalArgumentException;
import java.util.HashMap;

/**
 * 
 * 平台上下文环境支持类,当前用户数据存储的容器
 * @author   YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class ContextHolder {
	
	/**
	 * 
	 * 
	 * 当前用户自定义的线程级别生命周期内的数据存储,只在当前线程生命周期内有效
	 */
	private static ThreadLocal<Map<String,Object>>  customerData_ThreadLocal_Parameters = new ThreadLocal<Map<String,Object>>();
    
	
	/**
	 * 
	 * 设置用户自定义线程级别参数集合
	 * @param map
	 */
	public static void setCustomerThreadScopeParams(Map<String,Object> map){
		   if(map==null || map.size()==0){
			 throw new IllegalArgumentException("ContextHolder.setCustomerThreadLevelParams参数map为null数据"); 
		   }
		   customerData_ThreadLocal_Parameters.set(map);
	}
	
	
	/**
	 * 设置用户自定义线程级别参数(key-value)
	 * @param key
	 * @param value
	 * 
	 */
	public static void setCustomerThreadScopeParam(String key,Object value){
		    getCustomerThreadScopeParams().put(key, value);
		
	}
	
	
	/**
	 * 
	 * 获取用户自定义线程级别参数集合
	 * @return
	 */
	public static Map<String,Object> getCustomerThreadScopeParams(){
		   Map<String,Object> map=customerData_ThreadLocal_Parameters.get();
		   if(map==null){
			   customerData_ThreadLocal_Parameters.set(new HashMap<String,Object>());
		   }
		   return customerData_ThreadLocal_Parameters.get();
	}
	
	/**
	 * 
	 * 根据KEY获取用户自定义线程级别参数
	 * @param key
	 * @return
	 */
	public static Object getCustoThreadScopeParamsByKey(String key){
		
		return getCustomerThreadScopeParams().get(key);
		
	}
	
	/**
	 * 
	 * 移除用户自定义线程级别参数
	 */
	public static void removeCustoThreadScopeParams(){
		   customerData_ThreadLocal_Parameters.remove();
		  
	}
	
	
}
