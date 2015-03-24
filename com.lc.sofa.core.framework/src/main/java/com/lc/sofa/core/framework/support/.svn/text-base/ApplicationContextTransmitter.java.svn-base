package com.lc.sofa.core.framework.support;

import org.osgi.framework.Bundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.osgi.context.support.OsgiBundleXmlApplicationContext;

import com.lc.sofa.core.framework.support.ApplicationContextHolder;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
import com.lc.sofa.core.framework.basis.log.SofaLogger;

/**
 * 
 * 组件BEAN容器-applicationContext监听传送器
 * @author    YZ
 * @version 1.0, 2013-10-29
 * @since 1.0, 2013-10-29
 */
public class ApplicationContextTransmitter implements ApplicationContextAware {

	private String bundleSymbocalName;
	
	//private String bundleVersion;
	
	private SofaLogger logger=SofaLoggerFactory.getSofaLog(ApplicationContextTransmitter.class);
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Bundle bundle = null;
		try {

			bundle = ((OsgiBundleXmlApplicationContext) applicationContext).getBundle();
		} catch (Exception e) {
			logger.error("组件BEAN容器监听器ApplicationContextTransmitter发生异常");
		}
		if (bundle == null) {
			logger.info("组件BEAN容器监听器,获取当前组件为NULL");
		}
		this.bundleSymbocalName = bundle.getSymbolicName().trim();
		//this.bundleVersion = bundle.getVersion().toString();
		ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance(bundleSymbocalName);
		contextHolder.setApplicationContext(applicationContext);
		
		
	}

}
