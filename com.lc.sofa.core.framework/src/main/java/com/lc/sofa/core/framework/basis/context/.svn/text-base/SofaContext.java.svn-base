package com.lc.sofa.core.framework.basis.context;

import com.lc.sofa.core.framework.basis.config.SofaConfigFactory;
import com.lc.sofa.core.framework.basis.context.SofaContextFactory;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
import com.lc.sofa.core.framework.basis.log.SofaLogger;;
/**
 * 
 * 平台上下文环境服务API
 * @author   YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public abstract class SofaContext {
       
	
	private static SofaLogger sofaLogger=SofaLoggerFactory.getSofaLog(SofaContext.class);
	/**
	 * 
	 * 获取平台上下文环境
	 * @return
	 */
	   public static SofaContext getInstance(){
		      return SofaContextFactory.getInstance().ObtainSofaContext();
	   }
	   
	   /**
	    * 获取当前用户登录ID
	    * @return
	    */
	   public abstract String getCurrentLoginId();
	   
	   /**
	    * 获取当前用户登录名称
	    * @return
	    */
	   public abstract String getCurrentLoginName();
	   
	   /**
	    * 
	    * 获取当前用户的编号
	    * @return
	    */
	   public abstract String getCurrentLoginCode();
	   
	   /**
	    * 获取当前用户登录IP
	    * @return
	    */
	   public abstract String getCurrentLoginIP();
	   
	   /**
	    * 获取当前用户请求ID
	    * @return
	    */
	   public abstract String getCurrentRequestId();
	   
	   /**
	    * 根据KEY获取一个属性值
	    * @return
	    */
	   public abstract Object getThreadScopeAttribute(String key);
	   
	   /**
	    * 设置当前登录用户ID
	    * @return
	    */
	   public abstract void setCurrentLoginId(String userID);
	   
	   /**
	    * 获取当前用户登录名称
	    * @return
	    */
	   public abstract void setCurrentLoginName(String userName);
	   
	   /**
	    * 
	    * 设置当前登录用户编号
	    * @param userName
	    */
	   public abstract void setCurrentLoginCode(String userCode);
	   
	   /**
	    * 设置当前登录用户IP
	    * @param loginIp
	    */
	   public abstract void setCurrentLoginIp(String loginIp);
	   /**
	    * 获取当前请求的ID
	    * @return
	    */
	   public abstract void setCurrentRequestId(String requestId);
	   
	   /**
	    * 根据key-value设置线程范围内参数
	    * @return
	    */
	   public abstract void setThreadScopeAttribute(String key,Object value);
	   
	   
	   public abstract void setReuestFullContextPaht(String basePath);
	   
	   public abstract String getReuestFullContextPaht();
	
	   /**
	    * 获取门户URL
	    * @return
	    */
	   public static String getPortalUrl(){
		   return SofaConfigFactory.getInstance().getPortalConfig().getPortalAddress();
	   }
	   
	   /**
	    * 获取公共资源的URL
	    * @return
	    */
	   public static String getShareResourceUrl(){
		   
		   return null;
	   }
	   
	   /**
	    * 获取登录URL
	    * @return
	    */
	   public static String getSsoURL(){
		   return SofaConfigFactory.getInstance().getSofaSSOConfig().getAddress();
		   
	   }
	   
	   /**
	    * 获取WEB共享资源的URL(http://ip:port/share-resource)
	    * @return
	    */
	   public static String getShareResourceURL(){
		   
		      return SofaConfigFactory.getInstance().getShareResourceConfig().getShareResourceAddress();
	   }
	   
	  
	   /**
	    * 获取个性化定制WEB共享资源的URL
	    * 
	    * @return
	    */
	   public static String getCustomResourceURL(){
		   
		   return getShareResourceURL()+"/assembly";
	   }
	   
	   /**
	    * 判断当前用户是否为超级管理员
	    * @return
	    */
	   public boolean isSuperAdmin(){
              return (this.getCurrentLoginName().equalsIgnoreCase("admin") 
            		      && this.getCurrentLoginId().equals("0123456789"))?true:false;  
	   }
	   
	   
}
