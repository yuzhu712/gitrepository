package com.lc.sofa.core.framework.component.uniformaccess.dbaccess;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.lc.sofa.core.framework.basis.vo.Page;

import com.lc.sofa.core.framework.basis.datasource.DataSourcePool;
import com.lc.sofa.core.framework.basis.datasource.dialect.Dialect;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
import com.lc.sofa.core.framework.util.ClassUtil;
/**
 * 统一数据访问实现
 * 
 * @author YZ
 * @version 1.0, 2014-1-6
 * @since 1.0, 2014-1-6
 */
public class UnityDataAccess extends JdbcDaoSupport implements DataAccess {

	private Dialect dbDialect;

	private SofaLogger logger = SofaLoggerFactory.getSofaLog(UnityDataAccess.class);

	public UnityDataAccess() {

	}

	public UnityDataAccess(Dialect dbDialect, DataSourcePool dataSourcePool) {

		this.dbDialect = dbDialect;
		this.setDataSource(dataSourcePool.getDataSource());

	}

	/**
	 * 根据完整的SQL查询数据列表
	 * 
	 * @param allsql
	 * @param resultClazz
	 *            返回结果集java类的class
	 * @return
	 */
	public List queryObjectList(String sql, Class resultClazz) {

		List list = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(resultClazz));

		return list;
	}

	/**
	 * 根据完整的基本SQL进行分页查询数据
	 * 
	 * @param sql
	 * @param resultClazz
	 *            返回结果集java类的class
	 * @return
	 */
	public List queryListByPage(String sql,Page page, Class resultClazz) {
        String pageSql=this.dbDialect.getPagedSql(sql, page);
        String countSql=this.dbDialect.createCountSql(sql);
        List result=queryObjectList(pageSql,resultClazz);
        int count=this.querySingleInt(countSql);
        page.setTotalCount(count);
		return result;
	}

	/**
	 * 根据完整的SQL查询单个数据
	 * 
	 * @param allsql
	 * @param resultClazz
	 * @param Object  返回结果集java类的class
	 * @return
	 * 
	 */
	public Object querySingleObject(String sql, Class resultClazz) {

		Object obj = null;
		if(ClassUtil.isPrimitiveType(resultClazz)){
			obj=this.getJdbcTemplate().queryForObject(sql, resultClazz);
		}
		else {
			obj = this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper(resultClazz));
		}
		return obj;
	}

	
	/**
	 * 根据prepare的SQL查询数据列表,可传入参数
	 * 
	 * @param sql
	 * @param resultClazz
	 *            返回结果集java类的class
	 * @param params
	 * @return
	 */
	public List queryObjectList(String prepareSql, Class resultClazz, Object... params) {

		return this.getJdbcTemplate().query(prepareSql, new BeanPropertyRowMapper(resultClazz), params);

	}

	/**
	 * 根据prepare的SQL分页查询数据列表,可传入参数
	 * 
	 * @param sql
	 * @param resultClazz
	 *            返回结果集java类的class
	 * @param params
	 * @return
	 */
	public List queryListByPage(String prepareSql,Page page, Class resultClazz, Object... params) {
		    
		    String pageSql=this.dbDialect.getPagedSql(prepareSql, page);
	        String countSql=this.dbDialect.createCountSql(prepareSql);
	        List result=queryObjectList(pageSql,resultClazz,params);
	        int count=this.querySingleInt(countSql,params);
	        page.setTotalCount(count);
			return result;
		
	}

	/**
	 * 根据prepare的SQL查询单个数据,可传入参数
	 * 
	 * @param sql
	 * @param resultClazz
	 *            返回结果集java类的class
	 * @param params
	 * @return
	 */
	public Object querySingleObject(String prepareSql, Class resultClazz, Object... params) {
		Object obj=null;
		if(ClassUtil.isPrimitiveType(resultClazz)){
			obj=this.getJdbcTemplate().queryForObject(prepareSql, resultClazz,params);
		}
		else {
			obj = this.getJdbcTemplate().queryForObject(prepareSql, new BeanPropertyRowMapper(resultClazz), params);
		}
		return obj;
	}

	
	/**
	 * @param dbDialect
	 *            the dbDialect to set
	 */
	public void setDbDialect(Dialect dbDialect) {

		this.dbDialect = dbDialect;
	}

	/**
	 * 根据完整的SQL查询结果集为map
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryResultMap(String sql) {

		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 根据preapreSQL以及参数查询结果集为map
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryResultMap(String sql, Object... params) {

		return this.getJdbcTemplate().queryForList(sql, params);
	}

	/**
	 * 根据完整SQL查询单一结果INT类型
	 * {@inheritDoc}
	 * @see com.lc.sofa.core.framework.component.uniformaccess.dbaccess.DataAccess#querySingleInt(java.lang.String)
	 */
	public int querySingleInt(String sql) {

		return this.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 根据完整SQL查询单一结果String类型
	 * {@inheritDoc}
	 * @see com.lc.sofa.core.framework.component.uniformaccess.dbaccess.DataAccess#querySingleString(java.lang.String)
	 */
	public String querySingleString(String sql) {

		
		return this.getJdbcTemplate().queryForObject(sql, String.class);
	}

	/**
	 * 根据prepareSQL和参数查询单一结果INT类型
	 * {@inheritDoc}
	 * @see com.lc.sofa.core.framework.component.uniformaccess.dbaccess.DataAccess#querySingleInt(java.lang.String, java.lang.Object[])
	 */
	public int querySingleInt(String sql, Object... params) {

		
		return this.getJdbcTemplate().queryForInt(sql, params);
	}

	/**
	 * 根据prepareSQL和参数查询单一结果String类型
	 * {@inheritDoc}
	 * @see com.lc.sofa.core.framework.component.uniformaccess.dbaccess.DataAccess#querySingleString(java.lang.String, java.lang.Object[])
	 */
	public String querySingleString(String sql, Object... params) {

		
		return this.getJdbcTemplate().queryForObject(sql, String.class, params);
	}

	
}
