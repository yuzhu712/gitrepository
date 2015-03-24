package com.lc.sofa.core.framework.basis.context;

import javax.servlet.http.HttpServletRequest;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;

/**
 * 
 * 平台上下文环境工厂抽象服务
 * @author   YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public abstract class SofaContextFactory {
       
	   
	 private static SofaLogger sofaLogger=SofaLoggerFactory.getSofaLog(SofaContextFactory.class);
	   /**
	    * 
	    * 获取SofaContextFactory实例
	    * @return
	    */
	   public static SofaContextFactory getInstance(){
		      SofaContextFactory sofaContextFactory= OsgiServiceUtil.getOsgiService(SofaContextFactory.class); 
		      if(sofaContextFactory==null){
		    	  sofaLogger.error("获取SofaContextFactory服务为Null.");
		      }
		   
		      return sofaContextFactory; 
	   }
	   
	   /**
	    * 
	    * 创建SofaContext上下文环境
	    * 
	    */
	   public abstract void createSofaContext(HttpServletRequest request);
	   
	   
	   /**
	    * 
	    * 获取SofaContext上下文环境
	    */
	   public abstract SofaContext ObtainSofaContext();
	   
	   
	   /**
	    * 
	    * 移除当前的sofaContext
	    */
	   public abstract void removeSofaContexts();
	   
	   
	   
}
