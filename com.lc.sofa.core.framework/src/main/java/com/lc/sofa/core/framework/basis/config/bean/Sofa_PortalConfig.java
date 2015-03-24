package com.lc.sofa.core.framework.basis.config.bean;

/**
 * 
 * 平台门户配置信息BEAN
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class Sofa_PortalConfig {

    private String address;
	
	public Sofa_PortalConfig(String address){
		this.address = address;
	}
	
	public String getPortalAddress(){
		return address;
	}
}
