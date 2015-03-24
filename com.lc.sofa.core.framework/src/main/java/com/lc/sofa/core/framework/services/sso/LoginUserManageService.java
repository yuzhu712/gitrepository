package com.lc.sofa.core.framework.services.sso;

import com.lc.sofa.core.framework.services.Service;
import com.lc.sofa.core.framework.basis.vo.UserInfo;
import java.util.List;

/**
 * 
 * 登录成功用户的信息管理服务接口
 * @author     YZ
 * @version 1.0, 2013-11-4
 * @since 1.0, 2013-11-4
 */
public interface LoginUserManageService extends Service{

	
	/**
	 * 注销登录用户
	 * @param ticketGrantingTicketId
	 * @return
	 * @throws SOFAException
	 */
    public boolean logout(String loginId);
    
    /**
     * 
     * 根据用户名称查询已登录用户信息
     * @param loginCode
     * @return
     */
    public UserInfo getLoginedUserInfo(String loginName);
    
    /**
     * 获取所有在线用户
     * @return
     */
    public List<UserInfo> getLoginedUsers();
    
    /**
     * 添加登录用户到缓存
     * @param userInfo
     */
   public void addLoginedUser(UserInfo userInfo);
    
    
   /**
    *  从缓存中移除登录的用户
    * @param loginId
    */
   public void removeLoginedUser(String[] loginId); 
    
    
}
