package com.lc.sofa.core.framework.support;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;


/**
 * 
 * 本地服务帮助支持类,用户获取服务(非实现Service接口的服务)
 * @author    YZ
 * @version 1.0, 2013-10-23
 * @since 1.0, 2013-10-23
 * 
 */
public class LocalServiceHelper {
    
	/**
	 * 获取系统组件上下文-BundleContext
	 * @return
	 */
	public static BundleContext getBundleContext() {

		return OsgiServiceUtil.getKernelBundleContext();
	}
	
	/**
	 * 根据类名获取服务
	 * @param clazz
	 * @return
	 */
	public static Object getOSGIService(String clazz){
		Object service = null;
        BundleContext context = getBundleContext();
		if (context != null) {
            ServiceReference ref = context.getServiceReference(clazz);
			if (ref != null) {
                service = context.getService(ref);
			}
		}
		return service;
	}
	
	/**
	 * 根据类Class获取服务
	 * @param t
	 * @return
	 */
	public static <T> T getOSGIService(Class<T> t) {
		   T obj = (T)getOSGIService(t.getName());   
	       return obj;
	}
	
	/**
	 * 通过服务跟踪器获取OSGI的服务
	 * @param clazz
	 * @param timeout
	 * @return
	 */
	public static Object getOSGIService(String clazz, long timeout) {
		Object service = null;
		ServiceTracker serviceTracker = null;
		try {
            serviceTracker = new ServiceTracker(getBundleContext(), clazz, null);
			serviceTracker.open();
			service = serviceTracker.waitForService(timeout);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (serviceTracker != null) serviceTracker.close();
		}
		return service;
		
		
	}
	
}
