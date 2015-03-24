package com.lc.sofa.core.framework.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
import com.lc.sofa.core.framework.web.filter.SofaFilterException;
/**
 * 
 * 平台SofaFilter过滤器工厂抽象服务
 * @author   YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public abstract class SofaFilterFactory {

	  /**
	   * 获取SofaFilterFactory实例
	   * @return
	   */
	   public static SofaFilterFactory getInstance(){
		      SofaFilterFactory filterFactory= OsgiServiceUtil.getOsgiService(SofaFilterFactory.class);
		      if(filterFactory==null){
		    	 throw  new NullPointerException("获取SofaFilterFactory服务为null...");
		      }
		      return filterFactory;
	   }
	  
	   /**
	    * 获取SofaFilter
	    * @param filterConfig
	    * @param contextPath
	    * @return
	    */
	  public abstract Filter obtainSofaFilter(FilterConfig filterConfig, String bundleContextPath) throws SofaFilterException;
	   
	   
}
