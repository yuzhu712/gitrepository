package com.lc.sofa.core.framework.basis.config.bean;

import java.util.Map;
import java.util.HashMap;

import com.lc.sofa.core.framework.constants.SofaConstants;
import com.lc.sofa.core.framework.util.StringUtil;
/**
 * 平台自定义参数属性文件BEAN
 * 
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class Sofa_ParametersConfig {

	/**
	 * 
	 * 全局配置目录扫描间隔
	 */
	private static final String DIR_SCAN_INTERVAL = "dirScanInterval";
    
	/**
	 * 
	 * 目录扫描间隔
	 */
	private static final long DIRSCAN_INTERVAL_DEFAULT = 60000;
	
	/**
	 * 
	 * 是否开启单点登录
	 */
	private static final String IS_OPEN_SSO="openSSO";
    
	/**
	 * 
	 * 默认全局配置目录扫描时间间隔
	 * 
	 */
	private static final long DEFAULT_DIR_SCAN_INTERNAL=6000000;
	
	/**
	 * 默认采用的数据源类型
	 */
	private static final String DS_POOL_CATEGORY="c3p0";
	
	
	/**
	 * 
	 * 自定义参数存储
	 */
	private Map<String ,String> customerParams;
	
	public Sofa_ParametersConfig(Map<String ,String> customerParams){
		   this.customerParams=customerParams;
	}
	
	/**
	 * 添加自定义参数key-value
	 * @param key
	 * @param value
	 */
	public void addCustomParams(String key,String value){
		    if(customerParams==null){
		       customerParams=new HashMap<String ,String>(10);
		    }
		    customerParams.put(key, value);
		
	}
	
	public void setCustomParams(Map<String ,String> customerParams){
		   this.customerParams=customerParams;
	}
	
	/**
	 * 
	 * 
	 * 根据自定义参数KEY,获取自定义参数值
	 * @param key
	 * @return
	 */
	public String getCustomerParam(String key){
		   if(customerParams==null || customerParams.size()==0){
			  return null; 
		   }
		   return customerParams.get(key);
	}
	
	/**
	 * 获取全局配置目录扫描时间间隔
	 * 
	 * @return
	 */
	public long getDirScanTimeinterval() {
		if(customerParams.size()==0)
			return DEFAULT_DIR_SCAN_INTERNAL;
		
		String interval = customerParams.get(DIR_SCAN_INTERVAL);
		if(interval == null || interval.trim().equals(""))
			return DEFAULT_DIR_SCAN_INTERNAL;
		else
			return Long.valueOf(interval);
	}
	
	/**
	 * 
	 * 是否开启单点登录
	 * @return
	 */
	public boolean isOpenSSO(){
		
		String isOpenSSo = customerParams.get(IS_OPEN_SSO);
		if(isOpenSSo == null || isOpenSSo.trim().equals(""))
			return false;
		else
			return Boolean.valueOf(isOpenSSo);	
	}
	
	/**
	 * 获取配置的数据库连接池类型
	 * @return
	 */
	public String getDsPoolCategory(){
		if(customerParams.size()==0){
			return DS_POOL_CATEGORY;
		}
		 return customerParams.get("DS.POOL");
		
	}
	
	/**
	 * 获取应用系统自定义应用Title显示名称
	 * @return
	 */
	public String getCustomAppName(){
	       if(!customerParams.isEmpty()){
	    	   return customerParams.get("Application.Name");
	       }
	       return null;
	}
	
	/**
	 * 平台是否开发模式运行(开发模式运行不进行用户session验证)
	 * @return
	 */
	public String isDevelopMode(){
		 if(!customerParams.isEmpty()){
	    	   return customerParams.get("DEVELOP_MODE");
	       }
	       return "false";
		
	}
	
}
