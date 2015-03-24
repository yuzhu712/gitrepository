package com.lc.sofa.core.framework.services.sso;

import com.lc.sofa.core.framework.basis.vo.UserInfo;
import com.lc.sofa.core.framework.services.Service;


/**
 * 操作用户获取用户信息并校验接口
 * @author lihuishen
 * @version 1.0, 2013-11-14
 * @since 1.0, 2013-11-14
 */
public interface UserService extends Service{
	
	
	/**
	 * 根据loginCode从获取用户完整信息
	 * @param loginCode
	 * @return
	 * @throws LoginException
	 */
	   public UserInfo getUserInfo(String loginCode)throws LoginException;
	   
	/**
	 * 验证用户密码
	 * @param UserInfo通过DB或者ldap获取的用户实体信息
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	   public UserInfo validatePassword(UserInfo userInfo,String password)throws LoginException;
	   
	   
	/**
	 * 根据用户密码是否错误，更新用户信息表中的数据
	 * @param 用户实体信息
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	   public void updateUserInfo(UserInfo userInfo)throws LoginException;
	
	 /**
	 * 根据用户密码是否错误，更新用户信息表中的数据
	 * @param 用户实体信息
	 * @param 第一次输入错误密码时间
	 * @param 输入错误密码次数
	 * @param 账户冻结标识
	 * @param 冻结账户时间
	 * @return
	 * @throws LoginException
	 */   
	   public void  updateUserInfo(UserInfo userInfo,String firWroPassTime,int wroPassTimes,String freezeFlag,String freezeTime);
}
