package com.lc.sofa.core.framework.component.uniformaccess.dbaccess;

import java.util.List;
import java.util.Map;

import com.lc.sofa.core.framework.basis.vo.Page;
/**
 * 
 * 统一数据访问接口
 * @author       YZ
 * @version 1.0, 2014-1-6
 * @since 1.0, 2014-1-6
 */
public interface DataAccess {

	/**
	 * 根据完整的SQL查询数据列表
	 * @param allsql
	 * @param resultClazz 返回结果集java类的class
	 * @return
	 */
	public List queryObjectList(String sql,Class resultClazz);
	
	/**
	 *  根据完整的基本SQL进行分页查询数据
	 * @param sql
	 * @param resultClazz 返回结果集java类的class
	 * @return
	 */
	public List queryListByPage(String sql,Page page,Class resultClazz);
	
	/**
	 * 根据完整的SQL查询单个数据
	 * @param allsql
	 * @param resultClazz 返回结果集java类的class
	 * @return
	 */
	public Object querySingleObject(String sql,Class resultClazz);
	
	/**
	 * 根据完整的Count语句统计结果
	 * @param countSql
	 * @param resultClazz 返回结果集java类的class
	 * @return
	 */
	public int querySingleInt(String sql);
	
	
	/**
	 * 根据完整的Count语句统计结果
	 * @param countSql
	 * @param resultClazz 返回结果集java类的class
	 * @return
	 */
	public String querySingleString(String sql);
	
	/**
	 * 根据prepare的SQL查询数据列表,可传入参数
	 * @param sql
	 * @param resultClazz 返回结果集java类的class
	 * @param params
	 * @return
	 */
	public List queryObjectList(String prepareSql,Class resultClazz,Object ...params);
	
	/**
	 *  根据prepare的SQL分页查询数据列表,可传入参数
	 * @param sql
	 * @param resultClazz 返回结果集java类的class
	 * @param params
	 * @return
	 */
	public List queryListByPage(String prepareSql,Page page,Class resultClazz,Object ...params);
	
	/**
	 * 根据prepare的SQL查询单个数据,可传入参数
	 * @param sql
	 * @param resultClazz 返回结果集java类的class
	 * @param params
	 * @return
	 */
	public Object querySingleObject(String prepareSql,Class resultClazz,Object ...params);
	
    /**
     * 根据Preapre的Count语句统计结果
     * @param countSql
     * @param params
     * @return
     */
	public int querySingleInt(String sql, Object... params);
	
	/**
     * 根据Preapre的查询单个String
     * @param countSql
     * @param params
     * @return
     */
	public String querySingleString(String sql, Object... params);
	
	
	/**
	 *  根据完整的SQL查询结果集为map(一条记录按column-value存储在map中)
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> queryResultMap(String sql);
	
	/**
	 *  根据preapreSQL以及参数查询结果集为map(一条记录按column-value存储在map中)
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryResultMap(String sql,Object... params);
	
	
}
