package com.lc.sofa.core.framework.services.security;

import com.lc.sofa.core.framework.services.Service;

/**
 * 
 * 加密服务接口
 * @author    YZ
 * @version 1.0, 2013-10-29
 * @since 1.0, 2013-10-29
 */
public interface CryptService extends Service {
	
	/** 进行MD5加密
	 * @param info 要加密的信息
	 * @return 加密后的信息
	 */
	public String encryptToMD5(String info);

	/**
	 * 对密码进行md5校验
	 * @param password	  数据库中用户加密后的密码
	 * @param inputString 用户输入的密码
	 * @return
	 */
	
	  public  boolean validatePassword(String password, String inputString);
}
