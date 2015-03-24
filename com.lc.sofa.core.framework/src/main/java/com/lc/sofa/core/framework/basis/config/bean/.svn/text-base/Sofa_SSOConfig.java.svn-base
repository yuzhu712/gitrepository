package com.lc.sofa.core.framework.basis.config.bean;

import java.io.Serializable;

/**
 * 平台登录配置信息BEAN
 * 
 * @author YZ
 * @version 1.0, 2013-11-4
 * @since 1.0, 2013-11-4
 */
public class Sofa_SSOConfig{

	
	/**
	 * 
	 * 登录ip:port
	 */
	private String address;

	/**
	 * 内网地址,解决网段间限制访问控制
	 */
	private String internalUrl;

	/**
	 * 登录时是否校验客户端IP，如果为true则不允许同一用户在不同IP登录
	 */
	private boolean checkLoginIP = true;

	/**
	 * 使用https时的ajax跨域问题
	 */
	private String ajaxUrl;

	public Sofa_SSOConfig(String address){
		   this.address=address;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {

		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {

		this.address = address;
	}

	/**
	 * @return the internalUrl
	 */
	public String getInternalUrl() {

		if (this.internalUrl == null || this.internalUrl.trim().equals("")) {
			return this.address;
		}
		return internalUrl;
	}

	/**
	 * @param internalUrl
	 *            the internalUrl to set
	 */
	public void setInternalUrl(String internalUrl) {

		this.internalUrl = internalUrl;
	}

	/**
	 * @return the checkLoginIP
	 */
	public boolean isCheckLoginIP() {

		return checkLoginIP;
	}

	/**
	 * @param checkLoginIP
	 *            the checkLoginIP to set
	 */
	public void setCheckLoginIP(boolean checkLoginIP) {

		this.checkLoginIP = checkLoginIP;
	}

	/**
	 * @return the ajaxUrl
	 */
	public String getAjaxUrl() {

		return ajaxUrl;
	}

	/**
	 * @param ajaxUrl
	 *            the ajaxUrl to set
	 */
	public void setAjaxUrl(String ajaxUrl) {

		this.ajaxUrl = ajaxUrl;
	}

}
