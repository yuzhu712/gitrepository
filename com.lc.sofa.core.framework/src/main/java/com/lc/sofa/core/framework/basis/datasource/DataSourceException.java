package com.lc.sofa.core.framework.basis.datasource;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;

/**
 * 平台数据源异常
 * 
 * @author YZ
 * @version 1.0, 2013-11-11
 * @since 1.0, 2013-11-11
 */
public class DataSourceException extends SofaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1961970033929252651L;

	public String getExceptionCode() {

		return ExceptionConstants.SOFA_DATA_SOURCE_EXCEPTION;
	}

	public DataSourceException() {

		super();

	}

	public DataSourceException(String message) {

		super(message);
	}

	public DataSourceException(String message, Throwable cause) {

		super(message, cause);
	}

	public DataSourceException(Throwable cause) {

		super(cause);
	}

}
