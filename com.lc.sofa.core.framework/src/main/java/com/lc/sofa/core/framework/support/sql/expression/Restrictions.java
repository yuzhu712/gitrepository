
package com.lc.sofa.core.framework.support.sql.expression;

import java.util.Collection;
import com.lc.sofa.core.framework.support.sql.expression.Expression;

/**
 * 
 * SQL表达式定制获取类
 * @author       YZ 
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class Restrictions {

	private Restrictions() {
		
	}
	
	/**
	 * SQL简单表达式,无需传入操作符,指定列的别名
	 * @param  filedName
	 * @param  value
	 * @return Expression
	 * @deprecated
	 */
	public static SimpleExpression fieldAs(String filedName, String aliasName) {
		return new SimpleExpression(null,filedName, aliasName);
	}

	/**
	 * SQL简单表达式,等于操作
	 * name=value
	 * @param filedName
	 * @param value
	 * @return Expression
	 */
	public static SimpleExpression eq(String filedName, Object value) {
		return new SimpleExpression(Expression.SQL_EQUAL,filedName, value);
	}
	/**
	 * SQL简单表达式,不等于操作
	 * age <>30
	 * @param filedName
	 * @param value
	 * @return Expression
	 */
	public static SimpleExpression noEq(String filedName, Object value) {
		return new SimpleExpression(Expression.SQL_NE,filedName, value);
	}
	/**
	 * sql like操作(左右like)
	 * like '%yz%'
	 * @param filedName
	 * @param value
	 * @return Expression
	 */
	public static SimpleExpression like(String filedName, Object value) {
		return new SimpleExpression(" like ",filedName, "%"+value+"%");
	}

    /**
     * SQL 左like操作
     * @param filedName
     * @param value
     * @return
     */
    public static SimpleExpression llike(String filedName, Object value) {
		return new SimpleExpression(" like ",filedName, "%"+value);
	}

    /**
     * SQL 右like类
     * @param filedName
     * @param value
     * @return
     */
    public static SimpleExpression rlike(String filedName, Object value) {
		return new SimpleExpression(" like ",filedName, value+"%");
	}

	/**
	 * SQL简单表达式,大于操作
	 * age>30
	 * @param filedName
	 * @param value
	 * @return Expression
	 */
	public static SimpleExpression gt(String filedName, Object value) {
		return new SimpleExpression(Expression.SQL_GT,filedName, value);
	}
	/**
	 * SQL简单表达式,小于操作
	 * @param filedName
	 * @param value
	 * @return Expression
	 */
	public static SimpleExpression lt(String filedName, Object value) {
		return new SimpleExpression(Expression.SQL_LT,filedName, value);
	}
	/**
	 * SQL简单表达式,小于等于操作
	 * age<=30
	 * @param filedName
	 * @param value
	 * @return Expression
	 */
	public static SimpleExpression le(String filedName, Object value) {
		return new SimpleExpression(Expression.SQL_LE,filedName, value);
	}
	/**
	 * SQL简单表达式,大于等于操作
	 * @param filedName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression ge(String filedName, Object value) {
		return new SimpleExpression(Expression.SQL_GE,filedName, value);
	}
	/**
	 * SQL between操作
	 * @param filedName
	 * @param lo value
	 * @param hi value
	 * @return Criterion
	 */
	public static Expression between(String filedName, Object lo, Object hi) {
		return new BetweenExpression(filedName, lo, hi);
	}
	
	/**
	 * SQL NOT between操作
	 * @param filedName
	 * @param lo value
	 * @param hi value
	 * @return Criterion
	 */
	public static Expression notBetween(String filedName, Object lo, Object hi) {
		return new NotBetweenExpression(filedName, lo, hi);
	}
	
	
	/**
	 * SQL IN操作
	 * @param filedName
	 * @param values-数组
	 * @return Criterion
	 */
	public static Expression in(String filedName, Object ...values) {
		return new InExpression(filedName, values);
	}
	
	
	/**
	  * 
	  *  SQL not IN操作
	  * @param filedName
	  * @param values
	  * @return
	  */
	 public static Expression notIn(String filedName,Object ...values){
		 return new NotInExpression(filedName, values);
		
	 }
	
	/**
	 * SQL IN操作
	 * @param filedName
	 * @param values-collection集合
	 * @return Expression
	 */
	public static Expression in(String filedName, Collection values) {
		return new InExpression( filedName, values.toArray() );
	}
	
	/**
	 * SQL not IN操作
	 * @param filedName
	 * @param values-数组
	 * @return Criterion
	 */
	public static Expression notIn(String filedName, Collection values) {
		return new NotInExpression(filedName, values.toArray());
	}
	
	/**
	 * SQL is null操作
	 * @return Expression
	 */
	public static Expression isNull(String filedName) {
		return new NullExpression(filedName);
	}
	
	/**
	 * SQL is not null操作
	 * @return Criterion
	 */
	public static Expression isNotNull(String filedName) {
		return new NotNullExpression(filedName);
	}
	
	
	/**
	 * AND连接n个SQL表达式
	 *
	 * @param lhs
	 * @param rhs
	 * @return Expression
	 */
	public static Expression and(Expression ...express) {
		return new ConJunction(express);
	}
	
	
	/**
	 * OR连接N个SQL表达式
	 *
	 * @param lhs
	 * @param rhs
	 * @return Expression
	 */
	public static Expression or(Expression ...express) {
		return new DisJunction(express);
	}
	
	/**
	 * 排序的order by操作
	 * @param fieldName
	 * @param orderMode(Order.)
	 * @return
	 */
	public static Order order(String fieldName,Order.type orderMode){
		return new Order(fieldName,orderMode);	
	}
	
	/**
	 * 自定义and连接的SQL
	 * @param whereSql-传入的条件SQL(age is null)
	 * @return
	 * @deprecated
	 */
	public static Expression andSql(String whereSql){
	    return new ConJunction(whereSql);
	}
	
	/**
	 * 自定义OR连接的SQL
	 * @param whereSql-传入的条件SQL(name like 'yz%')
	 * @return
	 *@deprecated
	 */
	public static Expression orSql(String whereSql){
		 return new DisJunction(whereSql);
	}
	
	/**
	 * 自定义where条件查询SQL
	 * @param whereSql
	 * @return
	 */
	public static Expression whereSql(String whereSql){
		 return new Junction(null,whereSql);
	}
   	
	/**
	 * 自定义where条件查询SQL
	 * @param whereSql
	 * @return
	 */
	public static Having having(Expression...havingExpressions){
		  return new Having(havingExpressions);
	}
	
	/**
	 * 自定义where条件查询SQL
	 * @param whereSql
	 * @return
	 */
	public static GroupBy groupBy(String ...groupFields){
		 return new GroupBy(groupFields);
	}
	
}