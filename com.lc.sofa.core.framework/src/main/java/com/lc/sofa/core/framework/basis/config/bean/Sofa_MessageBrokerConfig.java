package com.lc.sofa.core.framework.basis.config.bean;

/**
 * 平台消息中间件配置BEAN
 * 
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class Sofa_MessageBrokerConfig {
    
	/**
	 * 
	 * 消息BrokerId
	 */
	private String messBrokerId;
	
	/**
	 * 
	 * 消息队列地址
	 */
	private String address;
	
	/**
	 * 
	 * 连接用户
	 */
	private String connectUser;
	
	/**
	 * 
	 * 连接密码
	 */
	private String connectPassword;

	
	public Sofa_MessageBrokerConfig(String messBrokerId,String address){
		this.messBrokerId=messBrokerId;
		this.address=address;
		
	}
	
	public Sofa_MessageBrokerConfig(String messBrokerId,String address,String user,String password){
		this.messBrokerId=messBrokerId;
		this.address=address;
		this.connectUser=user;
		this.connectPassword=password;
		
	}
	

	/**
	 * @return the messBrokerId
	 */
	public String getMessBrokerId() {
	
		return messBrokerId;
	}

	
	/**
	 * @param messBrokerId the messBrokerId to set
	 */
	public void setMessBrokerId(String messBrokerId) {
	
		this.messBrokerId = messBrokerId;
	}

	
	/**
	 * @return the address
	 */
	public String getAddress() {
	
		return address;
	}

	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
	
		this.address = address;
	}

	
	/**
	 * @return the connectUser
	 */
	public String getConnectUser() {
	
		return connectUser;
	}

	
	/**
	 * @param connectUser the connectUser to set
	 */
	public void setConnectUser(String connectUser) {
	
		this.connectUser = connectUser;
	}

	
	/**
	 * @return the connectPassword
	 */
	public String getConnectPassword() {
	
		return connectPassword;
	}

	
	/**
	 * @param connectPassword the connectPassword to set
	 */
	public void setConnectPassword(String connectPassword) {
	
		this.connectPassword = connectPassword;
	}
	
	
	
	
}
