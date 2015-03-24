package com.lc.sofa.core.framework.component.uniformaccess.dbaccess;

import com.lc.sofa.core.framework.basis.exception.SofaException;

/**
 * 
 * 数据访问异常
 * @author       YZ
 * @version 1.0, 2014-1-6
 * @since 1.0, 2014-1-6
 */
public class DataAccessException extends SofaException{

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5096255958222922174L;

	public DataAccessException(){
		    super();
		
	}
	
	public DataAccessException(String message){
		   super(message);
	}
	
	public DataAccessException(String message, Throwable cause){
		   super(message,cause);
	}

	public DataAccessException(Throwable cause){
		   super(cause);
	}
	
	/**
	 * 获取异常的编号,每个异常有唯一的固定编号
	 * @return String
	 */
	public String getExceptionCode(){
		   return "data_access_exception_001";
	}

}
