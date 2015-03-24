package com.lc.sofa.core.framework.basis.exception;

/**
 * 
 * 平台文件操作异常
 * @author     YZ
 * @version 1.0, 2013-11-4
 * @since 1.0, 2013-11-4
 */
public class FileOperationException extends SofaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4081952045907957945L;

	public String getExceptionCode() {

		return null;
	}

	public FileOperationException() {

		super();

	}

	public FileOperationException(String message) {

		super(message);
	}

	public FileOperationException(String message, Throwable cause) {

		super(message, cause);
	}

	public FileOperationException(Throwable cause) {

		super(cause);
	}

}
