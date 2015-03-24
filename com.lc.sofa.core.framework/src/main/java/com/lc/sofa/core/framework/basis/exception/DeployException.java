package com.lc.sofa.core.framework.basis.exception;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;

/**
 * 组件部署异常类
 * 
 * @author YZ
 * @version 1.0, 2014-1-10
 * @since 1.0, 2014-1-10
 */
public class DeployException extends SofaException {

	public DeployException() {

		super();

	}

	public DeployException(String message) {

		super(message);
	}

	public DeployException(String message, Throwable cause) {

		super(message, cause);
	}

	public DeployException(Throwable cause) {

		super(cause);
	}

	public String getExceptionCode() {

		return ExceptionConstants.ARTIFACT_DEPLOY_EXCEPTION;
	}

}
