package com.lc.sofa.core.framework.internal;


import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.packageadmin.PackageAdmin;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import com.lc.sofa.container.deployer.BundleLifecycleListener;
import com.lc.sofa.core.framework.web.listener.internal.WebApplicationListener;

import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.support.BundleSupport;

/**
 * 
 * 核心框架接口层core framework组件启动器
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class FrameworkActivator implements BundleActivator,ServiceTrackerCustomizer{

	private SofaLogger logger=SofaLoggerFactory.getSofaLog(FrameworkActivator.class);
	
	/**
	 * 
	 * 服务追踪器
	 */
	private ServiceTracker packageAdmin_ServiceTracker;
	
	/**
	 * 
	 * 包管理服务
	 */
	private static PackageAdmin packageAdminService;
	
	/**
	 * 
	 * 注册的BundleLifeListener集合
	 */
	private ServiceRegistration<BundleLifecycleListener> bundleLifeListener;
	
	/**
	 * 添加服务的事件响应
	 * {@inheritDoc}
	 * @see org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi.framework.ServiceReference)
	 */
	public Object addingService(ServiceReference arg0) {
		synchronized (FrameworkActivator.class) {
			packageAdminService = (PackageAdmin) BundleSupport.getSystemBundleContext().getService(arg0);
		}
		return packageAdminService;
	
	}

	/**
	 * 修改服务的事件响应
	 * {@inheritDoc}
	 * @see org.osgi.util.tracker.ServiceTrackerCustomizer#modifiedService(org.osgi.framework.ServiceReference, java.lang.Object)
	 */
	public void modifiedService(ServiceReference arg0, Object arg1) {
		synchronized (FrameworkActivator.class) {
			BundleSupport.getSystemBundleContext().ungetService(arg0);
			packageAdminService = null;
			packageAdminService = null;
		}
		
		
	}
    
	/**
     * 
     *  移除服务的事件响应
     * {@inheritDoc}
     * @see org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
     */
	public void removedService(ServiceReference arg0, Object arg1) {

		
		
	}
    
	/**
	 * core framework启动执行方法
	 * {@inheritDoc}
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		
		   logger.info("核心框架接口组件:Core-Framework Starting...");
		   packageAdmin_ServiceTracker = new ServiceTracker(bundleContext, PackageAdmin.class.getName(), this);
		   packageAdmin_ServiceTracker.open();
		   
           WebApplicationListener webapplicationListener = new WebApplicationListener();
		   bundleLifeListener = bundleContext.registerService(BundleLifecycleListener.class, webapplicationListener, null);
           logger.info("核心框架接口组件:Core-Framework Started!");
		  
	}

	/**
	 * core framework停止执行方法
	 * {@inheritDoc}
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		   bundleLifeListener.unregister();
		   packageAdmin_ServiceTracker.close();
		   packageAdmin_ServiceTracker=null;
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static synchronized Bundle getBundle(Class clazz) {

		if (packageAdminService == null) 
			throw new IllegalStateException("Not started");

		return packageAdminService.getBundle(clazz);
	}

	
	
}
