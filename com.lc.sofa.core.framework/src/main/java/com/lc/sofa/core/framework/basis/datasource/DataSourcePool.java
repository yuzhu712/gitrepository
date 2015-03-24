package com.lc.sofa.core.framework.basis.datasource;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import com.lc.sofa.core.framework.basis.config.bean.Sofa_DataSourcesConfig;
import com.lc.sofa.core.framework.basis.datasource.DataSourceException;
/**
 * 
 * 数据源连接池接口
 * @author       YZ
 * @version 1.0, 2013-11-11
 * @since 1.0, 2013-11-11
 */
public interface DataSourcePool {


	/**
	 * 
	 * 默认数据库连接池初始化数
	 */
	public static final int DEFAULT_DB_MIN_POOL_SIZE=50; 
	
	/**
	 * 默认数据库连接最大数字
	 * 
	 */
	public static final int DEFAULT_DB_MAX_POOL_SIZE=200;
	
	
	public static final int DEFAULT_ACQUIREMENT=5;
	
	public static final String DB_QUERY_TEST="SELECT 1 FROM T_SOFA_DBTEST";
	
	
	/**
	 * 数据源配置信息初始化
	 * @param dataSourceConfig
	 * @throws DataSourceException
	 */
	public void init(Sofa_DataSourcesConfig dataSourceConfig)throws DataSourceException;
	
	/**
	 * 设置事务管理器
	 * @param transactionManager
	 */
	public void setTransactionManager(TransactionManager transactionManager);
	
	/**
	 * 启动创建数据源连接池
	 * @throws DataSourceException
	 */
	public void start()throws DataSourceException;
	
	/**
	 * 获取连接池中的数据源
	 * @return
	 * @throws DataSourceException
	 */
	public DataSource getDataSource()throws DataSourceException;
	
	/**
	 * 对连接池的销毁
	 * @throws DataSourceException
	 */
	public void destory()throws DataSourceException;
	
	
}
