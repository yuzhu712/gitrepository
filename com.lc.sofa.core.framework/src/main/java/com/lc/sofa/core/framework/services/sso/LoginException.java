package com.lc.sofa.core.framework.services.sso;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;

public class LoginException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7842925068294806137L;

	public String getExceptionCode() {

		   return ExceptionConstants.SOFA_LOGIN_EXCEPTION;
	}

	public LoginException(){
		   super();
	}
	
	public LoginException(String message){
		   super(message);
	}
	
	public LoginException(String message, Throwable cause){
		   super(message,cause);
	}

	public LoginException(Throwable cause){
		   super(cause);
	}
}
