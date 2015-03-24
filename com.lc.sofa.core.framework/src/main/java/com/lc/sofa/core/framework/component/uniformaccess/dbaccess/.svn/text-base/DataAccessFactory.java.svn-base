package com.lc.sofa.core.framework.component.uniformaccess.dbaccess;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一数据访问工厂,根据数据库映射ID获取到对应的数据访问
 * 
 * @author YZ
 * @version 1.0, 2014-1-6
 * @since 1.0, 2014-1-6
 */
public class DataAccessFactory {

	/**
	 * 数据访问集合<datasourceId-DataAccess>
	 */
   private Map<String, DataAccess> dataAccesses;

	 DataAccessFactory() {
		   dataAccesses=new HashMap<String, DataAccess>(5);
		   
	}

	/**
	 * 获取数据访问
	 * 
	 * @param dataSourceId
	 * @return
	 */
	public  DataAccess getDataAccess(String dataSourceId) {

		if (dataSourceId == null || dataSourceId.trim().equals("")) {
			throw new DataAccessException("获取通用数据访问传入的dataSourceId为空");
		}
		return dataAccesses.get(dataSourceId);
	}

	/**
	 * 设置数据访问
	 * 
	 * @param dataSourceId
	 * @param dataAccess
	 */
	  void setDataAccess(String dataSourceId, DataAccess dataAccess) {

		if (dataSourceId == null || dataSourceId.trim().equals("")) {
			throw new DataAccessException("设置通用数据访问，传入的dataSourceId为空");
		}
		dataAccesses.put(dataSourceId, dataAccess);

	}
	
	public void clearDataAccess(){
		   if(dataAccesses!=null){
			   dataAccesses.clear();
			   dataAccesses=null;
		   }
		
	}
	
	

}
