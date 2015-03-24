package com.lc.sofa.core.framework.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.lc.sofa.container.util.osgi.OsgiServiceUtil;

import com.lc.sofa.core.framework.services.ServiceException;;
/**
 * 平台Session监听器总代理类
 * 
 * @author  YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class SofaListener implements HttpSessionListener{
   
	
    private HttpSessionListener httpSessListenerProxy;
	
	public void sessionCreated(HttpSessionEvent sessionEvent) {

		   this.httpSessListenerProxy.sessionCreated(sessionEvent);
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		  this.httpSessListenerProxy.sessionDestroyed(sessionEvent);
		
	}
	
	public SofaListener(){
		this.httpSessListenerProxy=OsgiServiceUtil.getOsgiService(HttpSessionListener.class);
		if(httpSessListenerProxy==null){
			throw new ServiceException("获取Extender.HttpSessionListener服务为null");
		}
	}
	

}
