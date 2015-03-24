package com.lc.sofa.core.framework.support.sql;

import java.util.List;

import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
import com.lc.sofa.core.framework.support.sql.expression.Expression;
import com.lc.sofa.core.framework.support.sql.expression.GroupBy;
import com.lc.sofa.core.framework.support.sql.expression.Order;

/**
 * 查询CONDITION构造器
 * 
 * @author YZ
 * @version 1.0, 2014-1-3
 * @since 1.0, 2014-1-3
 */
public class ConditionBuilder {
    
	private SofaLogger logger=SofaLoggerFactory.getDefaultSofaLogger(Condition.class);
	
	/**
	 * 数据查询条件(表、字段、where表达式)
	 */
	private Condition condition;

	public ConditionBuilder() {

		condition = new Condition();
	}

	/**
	 * 获取sql-condition构造实例
	 * 
	 * @return
	 */
	public static ConditionBuilder getInstance() {

		return new ConditionBuilder();

	}

	/**
	 * 设置要查询的表名(支持多表)
	 * 
	 * @param tableName
	 */
	public void setTableName(String ...tableName) {

		condition.addTableName(tableName);

	}

	/**
	 * 设置要查询的字段
	 * 
	 * @param fields
	 */
	public void setFields(String... fields) {

		condition.addQueryFields(fields);
	}

	public void setFields(List<String> fields) {

		condition.addQueryFields(fields);

	}

	/**
	 * 设置要查询的where表达式
	 * 
	 * @param expression
	 */
	public void setExpression(Expression... expression) {

		for (Expression expre : expression) {
			condition.addExpression(expre);
		}
	}

	/**
	 * 设置要查询的排序表达式
	 * 
	 * @param orders
	 */
	public void setOrder(Order... orders) {

		for (Order order : orders) {
			condition.addOrders(order);
		}

	}

	/**
	 * 设置要查询的groupby字段
	 * 
	 * @param orders
	 */
	public GroupBy setGroupBy(GroupBy groupBy) {
		   return condition.addGroupBy(groupBy);
		
	}
	
	
	public String getSql() {
        String sql=condition.toSqlString();
        logger.debug(sql);
		return sql;

	}

}
