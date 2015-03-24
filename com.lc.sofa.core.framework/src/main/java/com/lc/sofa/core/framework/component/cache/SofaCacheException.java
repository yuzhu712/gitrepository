package com.lc.sofa.core.framework.component.cache;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;
/**
 * 
 * SOFA缓存异常类
 * @author    YZ
 * @version 1.0, 2013-10-25
 * @since 1.0, 2013-10-25
 */
public class SofaCacheException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8209735817647202335L;

	public String getExceptionCode() {

	
		return ExceptionConstants.SOFA_CACHE_EXCEPTION;
	}
	
	public SofaCacheException(String message){
 	   super(message);
 }
 
 public SofaCacheException(String message,Throwable cause){
 	   super(message,cause);
 }
 
 public SofaCacheException(Throwable cause){
 	   super(cause);
 }
 

}
