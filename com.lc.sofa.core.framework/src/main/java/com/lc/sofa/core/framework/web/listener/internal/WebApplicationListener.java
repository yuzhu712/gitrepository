package com.lc.sofa.core.framework.web.listener.internal;

import com.lc.sofa.container.deployer.BundleLifecycleListener;
import com.lc.sofa.container.deployer.event.BundleEvent;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;

/**
 * 
 * 组件监听器,当组件状态发生改变时间，响应相应的事件。
 * @author  YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class WebApplicationListener implements BundleLifecycleListener{

	
	 private SofaLogger logger=SofaLoggerFactory.getSofaLog(WebApplicationListener.class);
	
	/**
	 * 响应事件
	 * {@inheritDoc}
	 * @see com.lc.sofa.container.deployer.BundleLifecycleListener#onChange(com.lc.sofa.container.deployer.event.BundleEvent)
	 */
	public void onChange(BundleEvent event) throws Exception {
           if(event.getEvent()==BundleEvent.BundleEventType.STARTED){
        	   logger.info("bundle starrted! bundle's symbolic name="+event.getBundle().getSymbolicName());
        	   
           }
           else if(event.getEvent()==BundleEvent.BundleEventType.STOPPED){
        	   logger.info("bundle stoped!clear bundle's applicationContext,bundle's symbolic name="+event.getBundle().getSymbolicName());
        	   
        	   
           }
		
		
	}

}
