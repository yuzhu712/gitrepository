package com.lc.sofa.core.framework.basis.datasource.dialect;

import com.lc.sofa.core.framework.basis.vo.Page;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
import com.lc.sofa.core.framework.component.cache.SofaCacheException;
/**
 * 
 * 数据库类型方言抽象服务类
 * @author       YZ
 * @version 1.0, 2013-12-20
 * @since 1.0, 2013-12-20
 */
public abstract class Dialect {

	
	 public static Dialect getInstance(){
		Dialect dialect = (Dialect) OsgiServiceUtil.getOsgiService(Dialect.class);
		if (dialect == null) {
			throw new SofaCacheException("未获取到数据库方言 Dilalect Osgi服务....");
		}
		return dialect;
		
	}
	
	protected static final String SQL_END_DELIMITER = ";";
	
	/**
	 * 
	 * 数据库类型
	 * @author       YZ
	 * @version 1.0, 2013-12-20
	 * @since 1.0, 2013-12-20
	 */
	public static enum Type{
		MYSQL,
		ORACLE,
		DB2,
		SQLSERVER,
		SYSBASE
	}
	
	/**
	 * 获取分页后的SQL
	 * @param sql
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	public String getPagedSql(String sql,Page page){
		   String handledsql=HandlePageSql(sql);
		   return createDialectPagedSql(handledsql,page);
	}

	/**
	 * 获取数量统计的SQL
	 * @param sql
	 * @return
	 */
	public String createCountSql(String sql){
		String handledSql=getCountSql(sql);
		String countSql = "select count(0) from " +"("+ handledSql+") total";
		//return createDialectCountSql(countSql);
		return countSql;
	}
	
	
	/**
	 * 对原始SQL进行分页处理(去掉末尾分号)
	 * @param sql
	 * @return
	 */
	private String HandlePageSql(String sql){
		      String handledSql=sql;
		       int index=handledSql.lastIndexOf(SQL_END_DELIMITER);
		       if(index!=-1){
		       	  return  handledSql.substring(0, index);
		      }
		      return handledSql;
	}
	
	/**
	 * 对原始SQL进行数据统计处理(去掉orderBy语句,返回from后的语句)
	 * @param sql
	 * @return
	 */
	private String getCountSql(String sql){
		    String handledSql=getLineSql(sql);
		    int orderIndex = handledSql.lastIndexOf("order by");
		    if(orderIndex!=-1){
		    	return handledSql.substring(0, orderIndex);
		    }
		    return handledSql;
	}	
	
	/**
	 * 格式化SQL语句(每个字符串之间只留一个空格,并全部转换为小写)
	 * @param sql
	 * @return
	 */
	private String getLineSql(String sql) {
		String result= sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
		
		return result.toLowerCase();
	}
	
	
	/**
	 * 分页策略,具体的Dialect实现(不同数据库不同处理)
	 * @param sql
	 * @param page
	 * @return
	 */
	protected abstract String createDialectPagedSql(String sql,Page page);
	
	/**
	 * 统计策略,具体的Dialect实现(不同数据库不同处理)
	 * @param sql
	 * @return
	 */
	protected abstract String createDialectCountSql(String sql);
	
	
	
}
