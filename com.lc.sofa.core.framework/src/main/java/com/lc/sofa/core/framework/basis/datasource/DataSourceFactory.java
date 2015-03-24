package com.lc.sofa.core.framework.basis.datasource;

import java.util.Map;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
/**
 * 
 * 数据源工厂接口,用户获取平台配置的数据源集合。
 * @author YZ
 * @version 1.0, 2013-11-11
 * @since 1.0, 2013-11-11
 */
public interface DataSourceFactory {
       
	/**
	 * 获取平台的数据源连接池集合
	 * <一个数据源对应一个连接池>
	 * @return
	 */
	   public Map<String,DataSourcePool> getDataSources();
	   
	   /**
	    * 获取平台的数据源事务管理器集合 
	    * @return
	    */
	   public Map<String,DataSourceTransactionManager> getTransactionManagers();
	   
	   /**
	    * 销毁所有的数据库连接池
	    * @throws DataSourceException
	    */
	   public void releaseDataSource() throws DataSourceException;
	   
}
