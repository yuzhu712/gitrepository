package com.lc.sofa.core.framework.web.listener;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;

/**
 * 平台sessionListener服务异常,扩展于服务异常基类ServiceException
 * 
 * @author YZ
 * @version 1.0, 2013-10-24
 * @since 1.0, 2013-10-24
 */
public class SofaListenerException extends SofaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 255901810917525686L;

	@Override
	public String getExceptionCode() {

		return ExceptionConstants.SOFA_LINTENER_EXCEPTION;
	}

	public SofaListenerException(String message) {
           super(message);
	}

	public SofaListenerException(String message, Throwable cause) {
		 super(message,cause);
	}

	public SofaListenerException(Throwable cause) {
		super(cause);
	}
}
