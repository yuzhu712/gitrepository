package com.lc.sofa.core.framework.component.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import com.lc.sofa.core.framework.component.log.LogServiceTracker;

/**
 * 
 * 平台公共AOP统一拦截器链
 * @author        YZ
 * @version 1.0, 2014-1-10
 * @since 1.0, 2014-1-10
 */
@Aspect
@Order(1)
public class SofaUniformInterceptor {
	
	/**
	 * 日志服务跟踪器
	 */
	private static LogServiceTracker sofaLogExecuter = new LogServiceTracker();
	
	public SofaUniformInterceptor(){
		   sofaLogExecuter.init();
	}
	
	
	@Pointcut("@annotation(com.lc.sofa.core.framework.basis.log.RecordLog)")
	private void logMethod() {

	}

	@Around("logMethod()")
	public Object invokeLog(ProceedingJoinPoint pjp) throws Throwable {
           return sofaLogExecuter.invokeLog(pjp);
	}

}
