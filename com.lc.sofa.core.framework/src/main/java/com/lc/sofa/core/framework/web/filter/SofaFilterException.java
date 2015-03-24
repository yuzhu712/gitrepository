package com.lc.sofa.core.framework.web.filter;

import com.lc.sofa.core.framework.constants.ExceptionConstants;
import com.lc.sofa.core.framework.basis.exception.SofaException;
/**
 * 
 * 平台filter服务异常,扩展于平台异常基类SofaException
 * @author   YZ
 * @version 1.0, 2013-10-24
 * @since 1.0, 2013-10-24
 */
public class SofaFilterException extends SofaException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7986604497056301032L;

	@Override
	public String getExceptionCode() {
           return ExceptionConstants.SOFA_FILTER_EXCEOTION;
	}
    
    public SofaFilterException(){
    	   super();
    	
    }
    
    public SofaFilterException(String message){
 	      super(message);
 	
    }
    
    public SofaFilterException(String message,Throwable cause){
    	   super(message,cause);
    }
    
    public SofaFilterException(Throwable cause){
 	       super(cause);
    }
    
    
}
