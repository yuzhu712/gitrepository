package com.lc.sofa.core.framework.basis.dao.plugin;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import  com.lc.sofa.core.framework.constants.ExceptionConstants;

/**
 * 
 * 分页插件异常
 * @author       YZ
 * @version 1.0, 2013-12-23
 * @since 1.0, 2013-12-23
 */
public class PagePluginException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2469457268597638883L;

	public String getExceptionCode() {

		
		return ExceptionConstants.PAGE_PLUGIN_EXCEPTION;
	}
	
	 public PagePluginException(){
		    super();
		
	}
	
	public PagePluginException(String message){
		   super(message);
	}
	
	public PagePluginException(String message, Throwable cause){
		   super(message,cause);
	}

	public PagePluginException(Throwable cause){
		   super(cause);
	}

}
