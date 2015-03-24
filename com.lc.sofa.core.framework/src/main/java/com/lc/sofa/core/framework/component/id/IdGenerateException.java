package com.lc.sofa.core.framework.component.id;

import com.lc.sofa.core.framework.constants.ExceptionConstants;
import com.lc.sofa.core.framework.basis.exception.SofaException;;

/**
 * 
 * ID生成服务异常
 * @author    YZ
 * @version 1.0, 2013-10-25
 * @since 1.0, 2013-10-25
 */
public class IdGenerateException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308196998950571435L;
	
    public String getExceptionCode() {

		return ExceptionConstants.SOFA_ID_GENERATE_EXCEPTION;
	}

    public IdGenerateException(String message){
    	   super(message);
    }
    
    public IdGenerateException(String message,Throwable cause){
    	   super(message,cause);
    }
    
    public IdGenerateException(Throwable cause){
    	   super(cause);
    }
    
   
}
