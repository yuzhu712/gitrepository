package com.lc.sofa.core.framework.basis.config.bean;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
/**
 * 
 * 平台缓存配置信息BEAN
 * @author       YZ
 * @version 1.0, 2013-11-4
 * @since 1.0, 2013-11-4
 */
public class Sofa_CacheConfig{
   
   
	private String enable="false";
	
	/**
    * 
    * 数据传输协议	
    */
   private String portocol;    	
   
   /**
    * 
    * 服务器连接信息
    */
   private String clusterAddress;
	
	public Sofa_CacheConfig(){
		   
	}
	
	public void addAddressList(List<String> addresses){
	       if(addresses==null || addresses.size()==0){
	    	  return; 
	       }
	       StringBuffer result=new StringBuffer(30); 
		   for(int i=0;i<addresses.size();i++){
			   result.append(addresses.get(i));
			   if(i!=addresses.size()-1){
				   result.append(",");  
			   }
		   }
		   this.clusterAddress=result.toString();
	}	
	
	/**
	 * 获取缓存集群串地址(ex:localhost:7001,localhost:7002)
	 * @return
	 */
	public String getClusterAddress(){
		   return clusterAddress;
	}
	
	/**
	 * @return the portocol
	 */
	public String getPortocol() {
	
		return portocol;
	}

	
	/**
	 * @param portocol the portocol to set
	 */
	public void setPortocol(String portocol) {
	
		this.portocol = portocol;
	}

	
    /**
	 * @return the enable
	 */
	public String getEnable() {
	
		return enable;
	}

	
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(String enable) {
	
		this.enable = enable;
	}

	public static void main(String []args){
		Sofa_CacheConfig config=new Sofa_CacheConfig();
		List<String> list=new ArrayList<String>();
		list.add("localhots:9900");
		//list.add("localhots:9901");
		config.addAddressList(list);
		System.out.println(config.getClusterAddress());
	}
	
	
}
