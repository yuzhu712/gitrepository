package com.lc.sofa.core.framework.basis.config.bean;


import com.lc.sofa.core.framework.basis.config.bean.Sofa_DataSourcesConfig;

/**
 * 
 * 平台数据源配置集合
 * @author     YZ
 * @version 1.0, 2013-11-5
 * @since 1.0, 2013-11-5
 */
public class Sofa_DataSourceConfigss {
    /**
     * 数据源类型(XA、NonXA)
     */
	private String dsType;
	
	/**
	 * 数据库类型(mysql、oracle、db2、sqlserver、sysbase)
	 */
	private String dbDialect; 
	
	private Sofa_DataSourcesConfig[] dataSourceConfigArray;

	public Sofa_DataSourceConfigss(String dsType,String dbDialect,Sofa_DataSourcesConfig[] dataSourceConfigArray){
		   this.dsType=dsType;
		   this.dbDialect=dbDialect;
		   this.dataSourceConfigArray=dataSourceConfigArray;
		
	}
	
	/**
	 * @return the dataSourceConfigArray
	 */
	public Sofa_DataSourcesConfig[] getDataSourceConfigArray() {
	
		return dataSourceConfigArray;
	}
	
	/**
	 * @return the dsType
	 */
	public String getDsType() {
	
		return dsType;
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
	 * 根据数据源ID获取该数据源的配置
	 * @param dataSourceId
	 * @return
	 */
	public Sofa_DataSourcesConfig getDataSourceConfigById(String dataSourceId){
		for(Sofa_DataSourcesConfig dsConfig : dataSourceConfigArray){
			if(dsConfig.getDataSourceId().equals(dataSourceId))
				return dsConfig;
		}
		return null;
		
	}
	
	
}
