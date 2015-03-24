package com.lc.sofa.core.framework.basis.config;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;;

/**
 * 
 * 平台配置信息异常类,扩展于平台异常基类。
 * @author     YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class InvalidConfigException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5162271286923843903L;

	public String getExceptionCode() {

		
		return ExceptionConstants.SOFA_CONGFIG_EXCEPTION_CODE;
	}

	public InvalidConfigException(String message){
		   super(message);
		
	}
	
     public InvalidConfigException(String message, Throwable cause){
    	    super(message,cause);
		
	}
     
     public InvalidConfigException(Throwable cause){
    	     super(cause);
 		
 	}
	
}
