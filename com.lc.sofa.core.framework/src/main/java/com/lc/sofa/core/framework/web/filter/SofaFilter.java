package com.lc.sofa.core.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 平台Filter总代理类,各组件统一配置的过滤器
 * @author     YZ
 * @version 1.0, 2013-10-24
 * @since 1.0, 2013-10-24
 */
public class SofaFilter implements Filter{

	
	private Filter  filterDelegate;
	
	private SofaFilterFactory filterFactory;
	
	private FilterConfig filterConfig;
	
	public void destroy() {
		filterDelegate.destroy();
	    filterDelegate=null;
		filterFactory=null;
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filteChain) throws IOException, ServletException {
         
		   this.filterDelegate.doFilter(request, response, filteChain);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
           this.filterConfig=filterConfig;
           if(this.filterFactory==null){
         	  this.filterFactory= SofaFilterFactory.getInstance();
            }
 		   if(this.filterDelegate==null){
 			  this.filterDelegate=this.filterFactory.obtainSofaFilter(filterConfig, filterConfig.getServletContext().getContextPath()); 
	   	
	       }
	}
	
	
	
}
