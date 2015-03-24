package com.lc.sofa.core.framework.basis.exception;

/**
 * 
 * 平台运行时异常抽象基类
 * @author   YZ
 * @version 1.0, 2013-10-22
 * @since 1.0, 2013-10-22
 */
public abstract class SofaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -120892432178610433L;
	
	
    public SofaException(){
		    super();
		
	}
	
	public SofaException(String message){
		   super(message);
	}
	
	public SofaException(String message, Throwable cause){
		   super(message,cause);
	}

	public SofaException(Throwable cause){
		   super(cause);
	}
	
	/**
	 * 获取异常的编号,每个异常有唯一的固定编号
	 * @return String
	 */
	public  abstract String getExceptionCode();
	
	
}
