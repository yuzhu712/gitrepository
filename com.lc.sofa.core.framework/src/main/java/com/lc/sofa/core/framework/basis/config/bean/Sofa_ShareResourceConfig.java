package com.lc.sofa.core.framework.basis.config.bean;


public class Sofa_ShareResourceConfig {
    
	/**
	 * 
	 * 共享WEB资源组件的URL
	 */
	private String shareResourceAddress;
	
	
	public Sofa_ShareResourceConfig(String shareResourceAddress){
		   this.shareResourceAddress=shareResourceAddress;
		
	}


	
	/**
	 * @return the shareResourceAddress
	 */
	public String getShareResourceAddress() {
	
		return shareResourceAddress;
	}


	
}
