package com.lc.sofa.core.framework.services.sso;

import com.lc.sofa.core.framework.basis.vo.UserInfo;
import com.lc.sofa.core.framework.services.Service;

/**
 * 
 * 登录服务接口
 * @author     YZ
 * @version 1.0, 2013-10-31
 * @since 1.0, 2013-10-31
 */
public interface LoginService extends Service{
       
	/**
	 * 用户登录验证服务接口
	 * @param loginCode
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	   public UserInfo login(String loginCode,String password)throws LoginException;
	   
		/**
		 * 更新用户注销时间
		 * @param userInfo
		 * @return
		 * @throws LoginException
		 */
		public  void updateLogTime(UserInfo userInfo)throws LoginException;
		      
	   
	
}
