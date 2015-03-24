package com.lc.sofa.core.framework.services;

import java.util.Map;

import java.lang.NullPointerException;

import  com.lc.sofa.core.framework.services.Service;
import  com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
import  com.lc.sofa.core.framework.basis.log.SofaLogger;
import  com.lc.sofa.core.framework.services.ServiceException;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
/**
 * 
 * 平台服务工厂抽象类,通过接口类型获取服务(LocalService或WebService)
 * 使用该服务接口的条件：应用服务接口必须继承com.lc.sofa.core.framework.services.Service接口。
 * @author  YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public abstract class ServiceFactory {
       
	  /**
	   * 
	   * 获取服务工厂实例
	   * @return
	   */
	   public static ServiceFactory getInstance(){
		     ServiceFactory serviceFactory=OsgiServiceUtil.getOsgiService(ServiceFactory.class);
		     if(serviceFactory==null){
		    	 throw new NullPointerException("获取ServiceFactory为NULL");
		     }
		      return serviceFactory;
	   }
	   
	   /**
	    * 根据服务接口Class类型获取服务
	    * @param clazz
	    * @return
	    */
	   public abstract <T> T getService(Class<? extends Service> clazz);
	   
	   /**
	    * 
	    * 根据服务接口Class类型以及过滤条件获取服务
	    * @param clazz
	    * @param filter
	    * @return
	    */
	   public abstract <T> T getService(Class<? extends Service> clazz,Map<String,String> filter)throws ServiceException;
	   
	
}
