package com.lc.sofa.core.framework.support.sql.expression;

import com.lc.sofa.core.framework.basis.exception.SofaException;
import com.lc.sofa.core.framework.constants.ExceptionConstants;
/**
 * 
 * SQL表达式异常类
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class ExpressionException extends SofaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4113699921532936863L;

	public String getExceptionCode() {
           return ExceptionConstants.SQL_EXPRESSION_EXCEOTION;
	}

	 public ExpressionException(){
		    super();
		
	}
	
	public ExpressionException(String message){
		   super(message);
	}
	
	public ExpressionException(String message, Throwable cause){
		   super(message,cause);
	}

	public ExpressionException(Throwable cause){
		   super(cause);
	}
	
	
}
