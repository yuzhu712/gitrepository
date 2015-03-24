package com.lc.sofa.core.framework.support;

import java.io.File;

import org.osgi.framework.Bundle;
import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
import com.lc.sofa.container.util.config.ContainerConfig;
import com.lc.sofa.core.framework.basis.exception.CommonException;
import com.lc.sofa.core.framework.services.ServiceException;
import com.lc.sofa.core.framework.support.BundleSupport;
import com.lc.sofa.core.framework.util.StringUtil;
import com.lc.sofa.core.framework.util.PropertiesUtil;
import com.lc.sofa.core.framework.basis.exception.FileOperationException;
import com.lc.sofa.core.framework.basis.config.SofaConfigFactory;
import com.lc.sofa.core.framework.constants.SofaConstants;

/**
 * 
 *平台环境信息支持类 
 * @author YZ
 * @version 1.0, 2013-10-24
 * @since 1.0, 2013-10-24
 */
public class SofaEnvoronmentSupport {
    
	
	
	/**
	 * 
	 * 获取平台上下文部署所在路径(目前该方法不可用)
	 * @deprecated
	 * @return
	 */
	public static String getSofaContainerContextPath(){
		   ContainerConfig containerConfig=OsgiServiceUtil.getOsgiService(ContainerConfig.class);
		   if(containerConfig==null){
			  throw new ServiceException("获取containerConfig服务为null in SofaEnvoronmentSupport.getSofaContainerContextPath"); 
		   }
		   return containerConfig.getSofaContainerContextPath();
	}
	
	/**
	 * 
	 * 获取sofa_home的路径
	 * X:/sofa_home
	 * @return
	 */
	public static String getSofaHomePath(){
		   ContainerConfig containerConfig=OsgiServiceUtil.getOsgiService(ContainerConfig.class);
		   if(containerConfig==null){
			  throw new ServiceException("获取containerConfig服务为null in SofaEnvoronmentSupport.getSofaHomePath"); 
		   }
		   return containerConfig.getSOFACONFIG_HOME();
	}
	
	
	
	/**
	 * 获取指定组件的配置文件目录(解压后的)
	 * X:/SOFA_HOME/bundles/"bundleName"-"bundleVersion"
	 * @param clazz
	 * @return
	 */
	public static String getSofaHomeBundlePath(Class clazz){
		   if(clazz==null){
			   throw new CommonException("clazz is null in SofaEnvoronmentSupport.getSofaHomeBundlePath");
		   }
		   Bundle bundle=BundleSupport.getBundle(clazz);
		   return getSofaHomePath()+"/bundles/"+bundle.getSymbolicName()+"-"+bundle.getVersion();
		 
	}
	
	/**
     * 
     * 获取 平台配置环境的路径
     * 例如:X:/sofa_home/global
     * @return
     */
	public static File getSofaHomeGlobalPath() {
		   ContainerConfig containerConfig=OsgiServiceUtil.getOsgiService(ContainerConfig.class);
		   if(containerConfig==null){
				  throw new ServiceException("获取containerConfig服务为null in SofaEnvoronmentSupport.getSofaHomeGlobalPath"); 
		   }
		   return new File(getSofaHomePath(),"global");
		   
	}
	
	/**
	 * 
	 * 获取sofa_home/global/parameters.ini文件中定义的Appliction-Name。
	 * 获取不到Appliction-Name则采用平台默认的(SofaConstants.LC_PLATFORM_DEFAULT_APPNAME=龙信数据分析平台)
	 * 
	 * @return 应用系统名
	 */
	public static String getApplictionName() {

		    String appName = SofaConfigFactory.getInstance().getCustomerParamConfig().getCustomAppName();
			
			if (appName!=null && !appName.trim().equals("")) {
				return appName;
			}
		    return SofaConstants.LC_PLATFORM_DEFAULT_APPNAME;
	}
	
	/**
	 * 获取上传文件的目录路径
	 * @return
	 */
	public synchronized static String getUploadDir(){
		  
		  // File file=new File(getSofaHomePath(),"upload");
		  // if(!file.exists()){
			//   file.mkdir();
		  // }
		   //return file.getAbsolutePath();
		return getSofaHomePath()+"/upload";
	}
	
}
