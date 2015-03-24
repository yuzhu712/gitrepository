package com.lc.sofa.core.framework.basis.datasource;



import javax.sql.DataSource;

import org.osgi.framework.Bundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.osgi.context.support.OsgiBundleXmlApplicationContext;

import com.lc.sofa.core.framework.basis.datasource.DataSourceException;
import com.lc.sofa.core.framework.basis.datasource.DataSourcePool;

/**
 * 平台数据源工厂BEAN,获取指定的数据源
 * 
 * @author YZ
 * @version 1.0, 2013-11-11
 * @since 1.0, 2013-11-11
 */
public class SimpleDriveDataSource implements FactoryBean, InitializingBean,ApplicationContextAware,DisposableBean{

	/**
	 * 组件的数据源
	 */
	private DataSource dataSource;

	/**
	 * 数据源工厂
	 */
	private DataSourceFactory dataSourceFactory;
    
	/**
	 * 组件对应的数据源ID
	 */
	private String dataSourceId;
	
	/**
	 * 组件bean容器上下文
	 */
	private OsgiBundleXmlApplicationContext osgiContext;

	public void afterPropertiesSet() throws DataSourceException {
		Bundle bundle = osgiContext.getBundle();
		String symbolicName = bundle.getSymbolicName();
		DataSourcePool dsPool = this.dataSourceFactory.getDataSources().get(this.dataSourceId);
	    if (dsPool == null) {
			throw new DataSourceException("获取数据源连接池发生异常...组件映射的数据源ID=" + this.dataSourceId + " 组件名称=" + symbolicName);
		}
		this.dataSource = dsPool.getDataSource();
        if (this.dataSource == null) {
			throw new DataSourceException("获取数据源为空,组件映射的数据源ID=" + this.dataSourceId + " 组件名称=" + symbolicName);
		}

	}

	
	/**
	 * 获取数据源 {@inheritDoc}
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	public Object getObject() throws Exception {

		return this.dataSource;
	}

	public Class getObjectType() {

		return this.dataSource.getClass();
	}

	public boolean isSingleton() {

		return true;
	}

	
	/**
	 * @return the dataSourceFactory
	 */
	public DataSourceFactory getDataSourceFactory() {

		return dataSourceFactory;
	}

	/**
	 * @param dataSourceFactory
	 *            the dataSourceFactory to set
	 */
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {

		this.dataSourceFactory = dataSourceFactory;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		this.osgiContext = (OsgiBundleXmlApplicationContext) applicationContext;

	}

    /**
	 * @return the dataSourceId
	 * 
	 */
	public String getDataSourceId() {
	
		return dataSourceId;
	}

    /**
	 * @param dataSourceId the dataSourceId to set
	 */
	public void setDataSourceId(String dataSourceId) {
	
		this.dataSourceId = dataSourceId;
	}


	public void destroy() throws Exception {
           if(this.osgiContext!=null){
        	  osgiContext.close();
        	  this.osgiContext=null;
           }
		   if(this.dataSource!=null){
			  this.dataSource=null;  
		   }
	}
	
	
	
}
