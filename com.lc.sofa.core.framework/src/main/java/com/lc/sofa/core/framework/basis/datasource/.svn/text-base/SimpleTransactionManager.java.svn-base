package com.lc.sofa.core.framework.basis.datasource;

import org.osgi.framework.Bundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.osgi.context.support.OsgiBundleXmlApplicationContext;

/**
 * 事务管理器工厂BEAN
 * 
 * @author YZ
 * @version 1.0, 2013-12-3
 * @since 1.0, 2013-12-3
 */
public class SimpleTransactionManager implements FactoryBean, ApplicationContextAware, InitializingBean, DisposableBean {

	/**
	 * 对应该数据源的事务管理器 {@inheritDoc}
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */

	private DataSourceTransactionManager dsTransactionManager;

	/**
	 * 组件bean容器上下文
	 */
	private OsgiBundleXmlApplicationContext osgiContext;

	/**
	 * 数据源工厂
	 */
	private DataSourceFactory dataSourceFactory;

	/**
	 * 组件对应的数据源ID
	 */
	private String dataSourceId;

	public void afterPropertiesSet() throws Exception {

		Bundle bundle = osgiContext.getBundle();
		String symbolicName = bundle.getSymbolicName();
		this.dsTransactionManager = this.dataSourceFactory.getTransactionManagers().get(this.dataSourceId);
		if (dsTransactionManager == null) {
			throw new DataSourceException("获取数据源事务管理器为空...组件数据源ID=" + this.dataSourceId + " 组件名称=" + symbolicName);
		}
	}

	public Object getObject() throws Exception {

		return this.dsTransactionManager;

	}

	public Class getObjectType() {

		return DataSourceTransactionManager.class;
	}

	public boolean isSingleton() {

		return true;
	}

	public void destroy() throws Exception {

		if (dsTransactionManager != null) {
			dsTransactionManager = null;
		}
		if (this.osgiContext != null) {
			osgiContext.close();
			this.osgiContext = null;
		}

	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		this.osgiContext = (OsgiBundleXmlApplicationContext) applicationContext;

	}

	/**
	 * @return the dataSourceId
	 */
	public String getDataSourceId() {

		return dataSourceId;
	}

	/**
	 * @param dataSourceId
	 *            the dataSourceId to set
	 */
	public void setDataSourceId(String dataSourceId) {

		this.dataSourceId = dataSourceId;
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

}
