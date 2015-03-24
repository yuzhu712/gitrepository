package com.lc.sofa.core.framework.support;

import java.lang.NullPointerException;
import java.util.Iterator;

import java.util.Map;
import java.util.Hashtable;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.springframework.context.ApplicationContext;
import com.lc.sofa.core.framework.component.uniformaccess.dbaccess.DataAccessFactory;

/**
 * 组件bean容器环境
 * 
 * @author YZ
 * @version 1.0, 2013-10-29
 * @since 1.0, 2013-10-29
 */
public class ApplicationContextHolder {
    
	/**
	 * 核心框架实现组件的SymbocalName
	 */
    private static final String CORE_FRAMEWORK_EXTENDER_SYMBOCAL_NAME="com.lc.sofa.core.framework.extender";
    
	private static final Map<String, ApplicationContextHolder> APPLICATION_CONTEXT_HOLBERS =
		new Hashtable<String, ApplicationContextHolder>(20);

	private ApplicationContext applicationContext;

	private ApplicationContextHolder() {

	}

	/**
	 * 根据组件名称获取该组件的applicationContextHolder 实例
	 * @param symbocalName
	 * @param version
	 * @return
	 */
	public static ApplicationContextHolder getInstance(String symbocalName) {

		if (symbocalName == null || symbocalName.trim().equals("")) {
			throw new NullPointerException("");

		}
		ApplicationContextHolder holder = (ApplicationContextHolder) APPLICATION_CONTEXT_HOLBERS.get(symbocalName);
		if (holder == null) {
			holder = new ApplicationContextHolder();
			if (symbocalName != null && !symbocalName.equals("")) {
				APPLICATION_CONTEXT_HOLBERS.put(symbocalName, holder);
			}
		}

		return holder;

	}
	
	/**
	 * 获取当前线程组件的applicationContext实例
	 * @return
	 */
	public static ApplicationContextHolder getInstance() {

		String symbocalName=BundleSupport.getThreadBundle().getSymbolicName();
		return getInstance(symbocalName);
		
	}

	/**
	 * 根据对象实例获取相应bundle的applicationContext,若对象实例为空则返回当前线程bundle的applicationContext
	 * @param obj
	 * @return
	 */
	public static ApplicationContextHolder getInstance(Object obj){
		  if(obj==null){ 
			 return  getInstance();
		  }
		  Bundle bundle = FrameworkUtil.getBundle(obj.getClass());
		  return getInstance(bundle.getSymbolicName());
	}
	
	
	/**
	 * 获取平台核心框架层的ApplicationContext,无需传入该组件的symbocalname
	 * @return
	 */
	public static ApplicationContextHolder getCoreFrameworkAppContext(){
		   return getInstance(CORE_FRAMEWORK_EXTENDER_SYMBOCAL_NAME);
		
	}
	
	/**
	 * 获取核心框架中统一数据访问工厂
	 * @return
	 */
	public static DataAccessFactory getDataAccessFactory(){
		
		   return (DataAccessFactory)getCoreFrameworkAppContext().getBean("dataAccessFactory");
		
		
	}
	
	/**
	 * @param symbolicName
	 * @param version
	 */
	public static void removeApplicationContext(String symbolicName) {

		APPLICATION_CONTEXT_HOLBERS.remove(symbolicName);
	}

	
	/**
	 * 获取spring容器管理中的bean对象.
	 * 
	 * @param beanId
	 *            id
	 * @return bena对象
	 */
	public Object getBean(String beanId) {

		return applicationContext.getBean(beanId);
	}

	/**
	 * 获取spring容器管理中的bean对象并转换为Class指定的类型.
	 * 
	 * @param clazz
	 *            bean类型
	 * @return 对象实例
	 */
	public Object getBean(Class<?> clazz) {

		return applicationContext.getBean(clazz);
	}

	/**
	 * 清除系统中的ApplicationContextHolder集合
	 */
	public static void clear() {

		try {
			Iterator it = APPLICATION_CONTEXT_HOLBERS.values().iterator();
			while (it.hasNext()) {
				ApplicationContextHolder ach = (ApplicationContextHolder) it.next();
				ach.setApplicationContext(null);
			}
			APPLICATION_CONTEXT_HOLBERS.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {

		return applicationContext;
	}

	/**
	 * @param applicationContext
	 *            the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {

		this.applicationContext = applicationContext;
	}

	
}
