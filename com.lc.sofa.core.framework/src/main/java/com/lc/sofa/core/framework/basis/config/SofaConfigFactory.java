package com.lc.sofa.core.framework.basis.config;

import java.lang.NullPointerException;

import com.lc.sofa.core.framework.basis.config.bean.Sofa_AppServersConfig;
import com.lc.sofa.core.framework.basis.config.bean.Sofa_DataSourceConfigss;
import com.lc.sofa.core.framework.basis.config.bean.Sofa_MessageBrokerConfig;
import com.lc.sofa.core.framework.basis.config.bean.Sofa_ParametersConfig;
import com.lc.sofa.core.framework.basis.config.bean.Sofa_PortalConfig;
import com.lc.sofa.core.framework.basis.config.bean.Sofa_SSOConfig;
import com.lc.sofa.core.framework.basis.config.bean.Sofa_ShareResourceConfig;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;

/**
 * 
 * 平台全局配置抽象服务类
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public abstract class SofaConfigFactory {
    
	/**
	 * 
	 * 获取SofaConfigFactory实例
	 * @return
	 */
	public static SofaConfigFactory  getInstance(){
		    SofaConfigFactory configFactory=OsgiServiceUtil.getOsgiService(SofaConfigFactory.class);
		    if(configFactory==null){
		       throw new NullPointerException("获取SofaConfigFactory服务为NULL");
		    }
		
		   return configFactory;
	}
	
	/**
	 * 
	 * 获取平台服务器配置信息
	 * @return
	 */
	public abstract Sofa_AppServersConfig getAppServersConfig();
	
	/**
	 * 
	 * 获取平台数据源配置信息集合
	 * @return
	 */
	public abstract Sofa_DataSourceConfigss getDataSourcesConfigss();
	
	/**
	 * 
	 * 获取消息队列Broker配置信息
	 * @return
	 */
	public abstract Sofa_MessageBrokerConfig getMessageBrokerConfig();
	
	/**
	 * 
	 * 获取门户配置信息
	 * @return
	 */
	public abstract Sofa_PortalConfig getPortalConfig();
	
	/**
	 * 
	 * 获取平台自定义参数配置信息
	 * @return
	 */
	public abstract Sofa_ParametersConfig getCustomerParamConfig();
	
	/**
	 * 
	 * 获取平台登录配置信息
	 * @return
	 */
	public abstract Sofa_SSOConfig getSofaSSOConfig();
	
	/**
	 * 获取平台共享资源配置信息
	 * @return
	 */
	public abstract Sofa_ShareResourceConfig getShareResourceConfig();
	
	
	
}
