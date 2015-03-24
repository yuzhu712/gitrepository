package com.lc.sofa.core.framework.services;


import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;
/**
 * 
 * 平台服务异常类,扩展于平台异常基类
 * @author  YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class ServiceException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3360282797145849868L;

	public String getExceptionCode() {

		
		return ExceptionConstants.SOFA_SERVICE_EXCEPTION_CODE;
	}
	
	public ServiceException(){
		  super();
		
	}
	
    public ServiceException(String message){
    	   super(message);
    	
    }
	
    public ServiceException(String message, Throwable cause){
    	   super(message,cause);
    }
    
    public ServiceException(Throwable cause){
    	   super(cause);
    }
    
}
