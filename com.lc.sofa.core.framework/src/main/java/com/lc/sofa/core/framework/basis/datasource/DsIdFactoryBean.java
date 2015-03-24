package com.lc.sofa.core.framework.basis.datasource;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.osgi.framework.Bundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.osgi.context.support.OsgiBundleXmlApplicationContext;

import com.lc.sofa.core.framework.constants.SofaConstants;
import com.lc.sofa.core.framework.support.SofaEnvoronmentSupport;
import com.lc.sofa.core.framework.util.XmlUtil;

/**
 * 获取组件对应数据源ID的工厂BEAN
 * 
 * @author YZ
 * @version 1.0, 2013-12-3
 * @since 1.0, 2013-12-3
 */
public class DsIdFactoryBean implements FactoryBean, InitializingBean, ApplicationContextAware,DisposableBean {

	/**
	 * 组件bean容器上下文
	 */
	private OsgiBundleXmlApplicationContext osgiContext;

	/**
	 * 组件对应的数据源ID {@inheritDoc}
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	private String dsID;

	public void afterPropertiesSet() throws Exception {

		Bundle bundle = osgiContext.getBundle();
		String symbolicName = bundle.getSymbolicName();
		String version = bundle.getVersion().toString();

		String filePath =
			SofaEnvoronmentSupport.getSofaHomePath() + "/bundles/" + symbolicName + "-" + version + "/"
				+ SofaConstants.DATA_SOURCE_MAPPING_FILE;
		if(filePath==null || filePath.trim().equals("")){
			throw new DataSourceException("组件的数据源映射文件不存在,组件名称="+symbolicName);
		}
	
		this.dsID=this.obtainDsMappingId(filePath);
		
	    if (this.dsID==null || this.dsID.trim().equals("")) {
			throw new DataSourceException("组件的数据源映射文件中获取的数据源ID为空,组件名称=" + symbolicName);
		}
		
	}

	/**
	 * 获取组件的数据源映射ID
	 * 
	 * @param dsMappingFile
	 * @return
	 */
	private String obtainDsMappingId(String dsMappingFile) {
		List<String> list = new ArrayList<String>();
		try {
			Document document = XmlUtil.loadXmlFile(dsMappingFile);
			for (Object o : document.getRootElement().elements()) {
				Element element = (Element) o;
				String dsEnable=element.attributeValue("enabled");
				if(dsEnable!=null && !dsEnable.trim().equals("") && dsEnable.trim().equalsIgnoreCase("true")){
					list.add(element.attributeValue("bundle-ds-id"));
				}
				
			}
		} catch (Exception ex) {
			throw new DataSourceException("解析组件数据源映射文件发生异常,dsMappingFile="+dsMappingFile,ex);
		}
		return list.get(0); 
	}
	
	/**
	 * 获取工厂BEAN创建的数据源ID对象
	 * {@inheritDoc}
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	public Object getObject() throws Exception {

		return this.dsID;
	}

	public Class getObjectType() {

		return dsID.getClass();
	}

	public boolean isSingleton() {

		return true;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		this.osgiContext = (OsgiBundleXmlApplicationContext) applicationContext;

	}

	public void destroy() throws Exception {
           if(osgiContext!=null){
        	   osgiContext.close(); 
        	   osgiContext=null;
           }
		   if(dsID!=null){
			  dsID=null; 
		   }
		
	}
	
	

}
