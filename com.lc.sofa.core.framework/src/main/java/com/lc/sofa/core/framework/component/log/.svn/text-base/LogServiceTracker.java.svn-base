package com.lc.sofa.core.framework.component.log;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.lc.sofa.container.util.log.LogConfig;
import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;;

/**
 * 
 * 日志服务跟踪器
 * @author       YZ
 * @version 1.0, 2014-1-13
 * @since 1.0, 2014-1-13
 */
public class LogServiceTracker {
    
	private SofaLogger defaultLogger =SofaLoggerFactory.getDefaultSofaLogger(LogServiceTracker.class);
	
	/**
	 * 日志参数配置(容器配置:logback_config.properties)
	 */
	private LogConfig logConfig;
	
	
	/**
	 * 日志服务的跟踪器
	 */
	private static ServiceTracker logServiceTracker;
	
	/**
	 * 日志接口服务(由日志组件实现)
	 */
	private SofaLogger sofalLogger;

	/**
	 * 跟踪器初始化工作
	 */
	public void init() {
		logConfig = LogConfig.getInstance();
		BundleContext kernelBundleContext = OsgiServiceUtil.getKernelBundleContext();
		String name = SofaLogger.class.getName();
		if (kernelBundleContext != null && logServiceTracker == null) {
			   logServiceTracker = new ServiceTracker(kernelBundleContext, name, null) {

				public Object addingService(ServiceReference reference) {

					sofalLogger = (SofaLogger) super.addingService(reference); 
					return sofalLogger;
				}

				public void modifiedService(ServiceReference reference, Object service) {

					super.modifiedService(reference, service);
					sofalLogger = (SofaLogger) super.getService(reference);
				}

				public void remove(ServiceReference reference) {

					super.remove(reference);
					sofalLogger = null;
				}

				public void removedService(ServiceReference reference, Object service) {

					super.removedService(reference, service);
					sofalLogger = null;
				}
			};
			logServiceTracker.open();
		}
		
		
	}
	
	/**
	 * 关闭日志注解执行器服务的跟踪器
	 * 
	 */
	public void closeLogServiceTracker() {

		try {
			logServiceTracker.close();
		} catch (Throwable e) {
			defaultLogger.error("close logServiceTracker occur exception!");
		}
		logServiceTracker = null;
	}
	
	
	/**
	 * 操作注解日志拦截器
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object invokeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		if (!logConfig.isEnabled()) {
			return pjp.proceed();
		}

		if (null == sofalLogger) {
			defaultLogger.warn("未获取到日志组件OSGI服务");
			return pjp.proceed();
		} else {
			return aroundProcess(pjp);
		}
	}
	
	/**
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	private Object aroundProcess(ProceedingJoinPoint pjp) throws Throwable{
		Class<?> targetClass = null;
		Method targetMethod = null;
		Object[] arguments = null;
		boolean logFlag = true;
		
		targetClass = pjp.getTarget().getClass();
		try{
			MethodSignature msig = (MethodSignature) pjp.getSignature();  
        	targetMethod = targetClass.getMethod(msig.getName(), msig.getParameterTypes());
			
		}catch(NoSuchMethodException e){
			defaultLogger.error("获取日志注解切入点Method对象失败!", e);
			logFlag = false;
		}
        arguments = pjp.getArgs();
		Object o = null;
		try{
			o = pjp.proceed();
			if(logFlag)
				sofalLogger.info(targetClass, targetMethod, arguments);
		} catch( Throwable e ) {
			if(logFlag)
				sofalLogger.error(targetClass, targetMethod, arguments, e);
			    throw e;
		}
		
		    return o;
		
	}
	
	
	
}
