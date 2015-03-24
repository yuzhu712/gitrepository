package com.lc.sofa.core.framework.basis.config.bean;

import java.util.Iterator;
import java.util.Properties;

import org.dom4j.Element;
/**
 * 
 * 平台数据库连接配置信息BEAN
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class Sofa_DataSourcesConfig {

	/**
	 * 
	 * 是否JNDI数据源
	 */
	private boolean jndiEnable;
	
	/**
	 * 
	 * JNDI数据源名称
	 */
	private String dsJndiName;
	
	/**
	 * 
	 * 数据源标识ID
	 */
	private String dataSourceId;
	
	/**
	 * 
	 * 数据库驱动类名
	 */
	private String driveClassName;
	
	/**
	 * 
	 * 数据库连接的URL
	 */
	private String connectionUrl;
	
	/**
	 * 
	 * 
	 * 数据库用户
	 */
	private String dbUser;
	
	/**
	 * 
	 * 数据库密码
	 */
	private String dbPassword;
	
    /**
     * 
     *  数据源类型(XA、NonXA)
     */
	private String dsType;
	
	/**
	 * 
	 * 数据源连接池配置属性
	 */
	
	
	private Properties dsPoolProperties;

	/**
	 * 
	 * 数据库类型(mysql、oracle、db2、sqlserver、sysbase)
	 */
	private String dbDialect;
	
	public Sofa_DataSourcesConfig(String dsJndiName){
	       this.dsJndiName=dsJndiName;
	  
	}
	
	public Sofa_DataSourcesConfig(String dataSourceId,String driveClassName,String connectionUrl,
	                              String dbUser,String dbPassword,String dsType){
		
		   this.dataSourceId=dataSourceId;
		   this.driveClassName=driveClassName;
		   this.connectionUrl=connectionUrl;
		   this.dbUser=dbUser;
		   this.dbPassword=dbPassword;
		   this.dsType=dsType;
		   this.dsPoolProperties=new Properties();
	}
	
	
	/**
	 * @return the dataSourceId
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

	
	/**
	 * @return the driveClassName
	 */
	public String getDriveClassName() {
	
		return driveClassName;
	}

	
	/**
	 * @param driveClassName the driveClassName to set
	 */
	public void setDriveClassName(String driveClassName) {
	
		this.driveClassName = driveClassName;
	}

	
	/**
	 * @return the connectionUrl
	 */
	public String getConnectionUrl() {
	
		return connectionUrl;
	}

	
	/**
	 * @param connectionUrl the connectionUrl to set
	 */
	public void setConnectionUrl(String connectionUrl) {
	
		this.connectionUrl = connectionUrl;
	}

	
	/**
	 * @return the dbUser
	 */
	public String getDbUser() {
	
		return dbUser;
	}

	
	/**
	 * @param dbUser the dbUser to set
	 */
	public void setDbUser(String dbUser) {
	
		this.dbUser = dbUser;
	}

	
	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
	
		return dbPassword;
	}

	
	/**
	 * @param dbPassword the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
	
		this.dbPassword = dbPassword;
	}

	
	/**
	 * @return the dsType
	 */
	public String getDsType() {
	
		return dsType;
	}

	
	/**
	 * @param dsType the dsType to set
	 */
	public void setDsType(String dsType) {
	
		this.dsType = dsType;
	}


	
	/**
	 * @return the dsPoolProperties
	 */
	public Properties getDsPoolProperties() {
	
		return dsPoolProperties;
	}


	
	/**
	 * @param dsPoolProperties the dsPoolProperties to set
	 */
	public void setDsPoolProperties(Properties dsPoolProperties) {
	
		this.dsPoolProperties = dsPoolProperties;
	}
	
	
    /**
	 * @return the dsJndiName
	 */
	public String getDsJndiName() {
	
		return dsJndiName;
	}

	
	/**
	 * @param dsJndiName the dsJndiName to set
	 */
	public void setDsJndiName(String dsJndiName) {
	
		this.dsJndiName = dsJndiName;
	}

	
	/**
	 * @return the jndiEnable
	 */
	public boolean isJndiEnable() {
	
		return jndiEnable;
	}

	
	/**
	 * @param jndiEnable the jndiEnable to set
	 */
	public void setJndiEnable(boolean jndiEnable) {
	
		this.jndiEnable = jndiEnable;
	}

	/**
	 * @return the dbDialect
	 */
	public String getDbDialect() {
	
		return dbDialect;
	}

	
	/**
	 * @param dbDialect the dbDialect to set
	 */
	public void setDbDialect(String dbDialect) {
	
		this.dbDialect = dbDialect;
	}

	/**
	 * 初始化连接池配置属性
	 * @param poolConfig
	 */
	public void initPoolProperties(Element poolConfig){
		if(poolConfig !=null){
			Iterator<Element> it=poolConfig.elementIterator();
			while(it.hasNext()){
				Element e = it.next();
				dsPoolProperties.put(e.getName(), e.getTextTrim());
			}
		}
	}
	
	
}
