package com.lc.sofa.core.framework.component.id;

/**
 * 
 * ID生成器接口
 * @author    YZ
 * @version 1.0, 2013-10-25
 * @since 1.0, 2013-10-25
 */
public interface IdGenerator {
	
	/**
	 * 
	 * 获取下个ID,生成多个ID时需要循环遍历该方法.
	 * @return
	 */
	public String nextId()throws IdGenerateException;
	
	/**
	 * 批量生成多个ID,一次性返回.
	 * @return
	 */
	public String[] nextIds()throws IdGenerateException;
	

}
