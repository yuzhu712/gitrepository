package com.lc.sofa.core.framework.component.url;


import java.util.List;
import java.util.Map;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;


/**
 * 
 * WEB组件URL转换类
 * @author     YZ
 * @version 1.0, 2013-10-25
 * @since 1.0, 2013-10-25
 */
public abstract class ComponentUrlService {

	 /**
	  * 
	  * 获取ComponentUrlService服务实例
	  * @return
	  */
	   public static ComponentUrlService getInstance(){
		       ComponentUrlService componentUrlService=OsgiServiceUtil.getOsgiService(ComponentUrlService.class);
		       if(componentUrlService==null){
		    	   throw new ComponentUrlException("获取ComponentUrlService为NULL");
		       }
		       return componentUrlService;
	   }
	   
	    /**将指定组件标识的URL转换为实际的部署URL
		 * 转换前："sofa-message/news.ctrl?method=toCenter"不能”/“开头
		 * 转换后："http://ip:port/sofa-message/news.ctrl?method=toCenter"
		 * @param url 要转换的URL
		 * @return 转换后的URL
		 * @throws ComponentUrlServiceException 
		 */
		public abstract String converteURL(String url) throws ComponentUrlException;
	   
	   
		/**获取所有组件的URL
		 * @return
		 * @throws ComponentUrlServiceException
		 */
		public abstract Map<String, String> converteURLs(List<String> urls) throws ComponentUrlException;
	
}
