package com.lc.sofa.core.framework.component.i18n;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;
/**
 * 
 * 平台i18n国际化异常类
 * 
 * @author   YZ
 * @version 1.0, 2013-10-25
 * @since 1.0, 2013-10-25
 */
public class SofaI18nException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8854091107586208120L;

	public String getExceptionCode() {

		
		return ExceptionConstants.SOFA_I18N_EXCEPTION;
	}
	
	 public SofaI18nException(String message){
	 	   super(message);
	 }
	 
	 public SofaI18nException(String message,Throwable cause){
	 	   super(message,cause);
	 }
	 
	 public SofaI18nException(Throwable cause){
	 	   super(cause);
	 }

}
