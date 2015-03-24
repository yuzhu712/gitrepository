package com.lc.sofa.core.framework.basis.config.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 平台应用服务器配置信息BEAN
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class Sofa_AppServersConfig {

    private String serverCode;
    
    private String address;
    
    private Set<String> internalAddresses;

    public Sofa_AppServersConfig(String serverCode,String address){
	  this.serverCode=serverCode;
	  this.address=address;
    }

	
	/**
	 * @return the serverCode
	 */
	public String getServerCode() {
	
		return serverCode;
	}

	
	/**
	 * @param serverCode the serverCode to set
	 */
	public void setServerCode(String serverCode) {
	
		this.serverCode = serverCode;
	}

	
	/**
	 * @return the internalAddress
	 */
	public Set<String> getAppServerInnerAddresses() {
	
		return internalAddresses;
	}

	
	/**
	 * @param internalAddress the internalAddress to set
	 */
	public void addAppServerInnerAddress(String internalAddress) {
		   if(internalAddresses==null){
			   internalAddresses=new HashSet<String>(3); 
		   }
		   internalAddresses.add(internalAddress);
	}
    
	/**
     * 
     * @return
     */
	public String getAppServerSingleInnerAddress(){
		  if(this.internalAddresses!=null && this.internalAddresses.size()!=0){
		     return (String)this.internalAddresses.toArray()[0];  
		  }
		  return "";
		  
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
    
    
    
}
