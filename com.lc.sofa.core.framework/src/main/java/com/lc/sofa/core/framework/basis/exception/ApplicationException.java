package com.lc.sofa.core.framework.basis.exception;

import com.lc.sofa.core.framework.constants.ExceptionConstants;
public class ApplicationException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8917932517734291451L;

	public String getExceptionCode() {
           return ExceptionConstants.APP_EXCEPTION;
	}
	
	 public ApplicationException(){
		    super();
		
	 }
	
	public ApplicationException(String message){
		   super(message);
	}
	
	public ApplicationException(String message, Throwable cause){
		   super(message,cause);
	}

	public ApplicationException(Throwable cause){
		   super(cause);
	}

}
