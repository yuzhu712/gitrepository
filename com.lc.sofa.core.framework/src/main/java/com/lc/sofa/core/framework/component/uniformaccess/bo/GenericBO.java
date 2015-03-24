package com.lc.sofa.core.framework.component.uniformaccess.bo;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.lc.sofa.core.framework.basis.vo.Page;

import com.lc.sofa.core.framework.basis.context.PageThreadContext;
import com.lc.sofa.core.framework.component.uniformaccess.dbaccess.DataAccess;
import com.lc.sofa.core.framework.component.uniformaccess.dbaccess.DataAccessException;
import com.lc.sofa.core.framework.support.ApplicationContextHolder;
import com.lc.sofa.core.framework.support.sql.ConditionBuilder;

/**
 * 领域分析对象访问抽象类
 * 
 * @author       YZ
 * @version 1.0, 2014-1-7
 * @since 1.0, 2014-1-7
 */
public abstract class GenericBO implements InitializingBean{

	@Resource(name="dataSourceId")
	protected String dataSourceId;
	
	/**
	 * 通用数据访问接口
	 */
	private DataAccess dataAccess;
	
    /**
	 * 该领域对象所操作数据值VO,由子类实现
	 * @return
	 */
	protected abstract Class getResultVoClass();

   
	 public void afterPropertiesSet(){
		  this.dataAccess=ApplicationContextHolder.getDataAccessFactory().getDataAccess(dataSourceId);
		
	}
	
	 /**
	  * 根据查询构造器查询数据集
	  * @param conditionBuilder
	  * @return
	  */
	protected List queryDataList(ConditionBuilder conditionBuilder){
		   if(conditionBuilder==null){
			   throw new DataAccessException("数据查询传入的构造器为空");
		   }
		   List list=dataAccess.queryObjectList(conditionBuilder.getSql(), getResultVoClass());
		   return list;
	}
	
	
	/**
	 * 根据完整的SQL查询数据列表
	 * @param allsql
	 * @return
	 */
	public List queryDataList(String sql){
		   if(sql==null || sql.trim().equals("")){
			   throw new DataAccessException("数据查询传入的SQL为空"); 
		   }
		   return dataAccess.queryObjectList(sql, getResultVoClass());
	}
	
	/**
	 * 根据prepare的SQL查询数据列表,可传入参数
	 * @param sql
	 * @param params
	 * @return
	 */
	public List queryDataList(String prepareSql,Object ...params){
		   if(prepareSql==null || prepareSql.trim().equals("")){
			   throw new DataAccessException("数据查询传入的SQL为空"); 
		   }
		   if(params==null){
			   throw new DataAccessException("数据查询传入的prepareSql的参数为空"); 
		   }
		   return dataAccess.queryObjectList(prepareSql, getResultVoClass(), params);
	}
	
	/**
	 * 根据prepare的SQL查询单个数据,可传入参数
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object querySingleObject(String prepareSql,Object ...params){
		   if(prepareSql==null || prepareSql.trim().equals("")){
			   throw new DataAccessException("数据查询传入的SQL为空"); 
		   }
		   if(params==null){
			   throw new DataAccessException("数据查询传入的prepareSql的参数为空"); 
		   }
		   return dataAccess.querySingleObject(prepareSql, getResultVoClass(), params);
		
	}
	
	/**
	 * 根据prepare的SQL查询单个数据,可传入参数
	 * @param sql
	 * @param params
	 * @return Object-自定义java bean
	 */
	public Object querySingleObject(ConditionBuilder conditionBuilder){
		  if(conditionBuilder==null){
			   throw new DataAccessException("数据查询传入的构造器为空");
		   }
		   
		   return dataAccess.querySingleObject(conditionBuilder.getSql(), getResultVoClass());
		
	}
	
	
	/**
	 *  根据完整的SQL查询结果集为map(一条记录按column-value存储在map中)
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> queryResultMap(String sql){
		 if(sql==null || sql.trim().equals("")){
			   throw new DataAccessException("数据查询传入的SQL为空"); 
		   }
		return dataAccess.queryResultMap(sql);
	}
	
	/**
	 *  根据preapreSQL以及参数查询结果集为map(一条记录按column-value存储在map中)
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryResultMap(String sql,Object... params){
		   if(sql==null || sql.trim().equals("")){
			   throw new DataAccessException("数据查询传入的SQL为空"); 
		   }
		   if(params==null){
			   throw new DataAccessException("数据查询传入的prepareSql的参数为空"); 
		   }
		return dataAccess.queryResultMap(sql, params);
		
	}
	
	/**
	 * 根据条件构造器查询结果集为map(一条记录按column-value存储在map中)
	 * @param conditionBuilder
	 * @return
	 */
	public List<Map<String,Object>> queryResultMap(ConditionBuilder conditionBuilder){
		if(conditionBuilder==null){
			throw new DataAccessException("数据查询传入的构造器为空");
		}
		return dataAccess.queryResultMap(conditionBuilder.getSql());
		
	}
	
	/**
	 * 根据构造好的ConditionBuilder进行page查询
	 * 
	 * @param conditionBuilder
	 * @param resultClazz
	 *            返回结果集java类的class
	 * @return
	 */
	public Page queryListByPage(ConditionBuilder conditionBuilder) {
		  if(conditionBuilder==null){
			  throw new DataAccessException("数据查询传入的构造器为空");
		  }
		   Page page=PageThreadContext.getCurrentPage();
		   List list=dataAccess.queryListByPage(conditionBuilder.getSql(), page, getResultVoClass());
		   page.setRecords(list);
		   return page;
	}
	
	/**
	 *  根据构造好的ConditionBuilder进行page查询
	 * @param conditionBuilder
	 * @param resultClazz 返回结果集java类的class
	 * @return
	 */
	public Page queryListByPage(String sql){
		    if(sql==null || sql.trim().equals("")){
			   throw new DataAccessException("数据查询传入的SQL为空");
		  }
		   Page page=PageThreadContext.getCurrentPage();
		   List list=dataAccess.queryListByPage(sql, page, getResultVoClass());
		   page.setRecords(list);
		   return page;
		 
	}
	
   /**
    * 根据conditionBuilder的查询单个int	
    * @param conditionBuilder
    * @return
    */
   public int querySingleInt(ConditionBuilder conditionBuilder){
	   return dataAccess.querySingleInt(conditionBuilder.getSql());
	   
   }
	
	/**
     * 根据conditionBuilder的查询单个String
     * @param countSql
     * @param params
     * @return
     */
	public String querySingleString(ConditionBuilder conditionBuilder){
		  return dataAccess.querySingleString(conditionBuilder.getSql());
	}
	
	/**
	 * 根据查询ConditionBuilder以及自定义class查询单个结果集
	 * @param conditionBuilder
	 * @param clazz-主要为基本数据类型(Float.class,Double.class,Date.class)
	 * @return
	 */
	public Object querySingleObject(ConditionBuilder conditionBuilder,Class clazz){
		  return  dataAccess.querySingleObject(conditionBuilder.getSql(), clazz);
		
	}
	
}
