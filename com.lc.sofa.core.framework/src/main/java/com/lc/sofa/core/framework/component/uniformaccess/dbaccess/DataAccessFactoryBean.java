package com.lc.sofa.core.framework.component.uniformaccess.dbaccess;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import com.lc.sofa.core.framework.basis.datasource.DataSourceFactory;
import com.lc.sofa.core.framework.basis.datasource.DataSourcePool;
import com.lc.sofa.core.framework.basis.datasource.dialect.Dialect;


/**
 * 
 * 数据访问工厂BEAN,用于创建数据访问工厂
 * @author       YZ
 * @version 1.0, 2014-1-6
 * @since 1.0, 2014-1-6
 */
public class DataAccessFactoryBean implements FactoryBean, InitializingBean,DisposableBean{
    
	/**
	 * 数据访问接口工厂类
	 */
	 DataAccessFactory dataAccessFactory=new DataAccessFactory();
	
	
	/**
	 * 数据库方言
	 */
	private Dialect dbDialect;
	
	/**
	 * 数据源工厂类
	 */
	private DataSourceFactory dataSourceFactory;
	
	
	public void afterPropertiesSet() throws Exception {
		Map<String, DataSourcePool> dataSourcess=this.dataSourceFactory.getDataSources();
		Set<String> keys=dataSourcess.keySet();
		for(String key:keys){
			DataAccess dataAccess=new UnityDataAccess(dbDialect,dataSourcess.get(key));
			dataAccessFactory.setDataAccess(key, dataAccess);
		}
		
		
	}

	public Object getObject() throws Exception {

	    return dataAccessFactory;
	}

	public Class getObjectType() {

		return DataAccessFactory.class;
	}

	public boolean isSingleton() {
           return true;
	}

	public void destroy() throws Exception {
        if(dataAccessFactory!=null){
        	dataAccessFactory.clearDataAccess();
        	dataAccessFactory=null;
        }
        this.dbDialect=null;
        this.dataSourceFactory=null;
		
	}

	/**
	 * @param dbDialect the dbDialect to set
	 */
	public void setDbDialect(Dialect dbDialect) {
	
		this.dbDialect = dbDialect;
	}

	
	/**
	 * @param dataSourceFactory the dataSourceFactory to set
	 */
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
	
		this.dataSourceFactory = dataSourceFactory;
	}
	
	
	

}
