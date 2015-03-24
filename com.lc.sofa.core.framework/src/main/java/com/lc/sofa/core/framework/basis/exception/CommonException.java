package com.lc.sofa.core.framework.basis.exception;

import com.lc.sofa.core.framework.constants.ExceptionConstants;;
/**
 * 
 * 
 * 平台通用运行时异常
 * @author   YZ
 * @version 1.0, 2013-10-24
 * @since 1.0, 2013-10-24
 */
public class CommonException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3714671784732019855L;

	public String getExceptionCode() {

		
		return ExceptionConstants.SOFA_COMMON_EXCEOTION_CODE;
	}

	public CommonException(){
		   super();
	}
	
	public CommonException(String message){
		   super(message);
	}
	
	public CommonException(String message, Throwable cause){
		   super(message,cause);
	}
	
	public CommonException(Throwable cause){
		   super(cause);
	}
	
}
