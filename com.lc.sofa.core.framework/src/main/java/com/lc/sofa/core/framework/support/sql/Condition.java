package com.lc.sofa.core.framework.support.sql;


import java.util.List;
import java.util.ArrayList;

import com.lc.sofa.core.framework.support.sql.expression.AbstractExpression;
import com.lc.sofa.core.framework.support.sql.expression.Expression;
import com.lc.sofa.core.framework.support.sql.expression.GroupBy;
import com.lc.sofa.core.framework.support.sql.expression.Order;
import com.lc.sofa.core.framework.support.sql.expression.ExpressionException;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;

import com.lc.sofa.core.framework.util.StringUtil;

/**
 * SQL自定义查询condition
 * 
 * @author YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class Condition extends AbstractExpression implements Expression {

	/**
	 * 要查询的字段
	 */
	private String[] fields;

	/**
	 * 要查询的表(支持多表的串)
	 */
	private String table;

	/**
	 * 查询的条件表达式
	 */
	private List<Expression> expressions;

	/**
	 * 字段排序
	 */
	private List<Order> orders;
	
	/**
	 * 分组的字段(可以多个)
	 */
	private GroupBy groupBy;
	
	private SofaLogger logger=SofaLoggerFactory.getDefaultSofaLogger(Condition.class);

	 Condition() {
           this.expressions=new ArrayList<Expression>(); 
           orders=new ArrayList<Order>();
		  
	}

	 Condition(String[] fields, String ...tables) {

		this.fields = fields;
		this.table=StringUtil.join(tables,",");
		this.expressions=new ArrayList<Expression>(); 
		orders=new ArrayList<Order>();
	}
	
	 /**
	  * 添加groupBy
	  * @param groupBy
	  * @return
	  */
	 GroupBy addGroupBy(GroupBy groupBy){
		 this.groupBy=groupBy;
		 return this.groupBy;
	}
	 
	 
	 /**
	 * 添加条件表达式
	 * @param whereExpression
	 */
	 void addExpression(Expression whereExpression){
		   if(whereExpression!=null){
			   this.expressions.add(whereExpression);
		   }
	}
	
	/**
	 * 添加查询表名
	 * @param tableName
	 */
	 void addTableName(String ...tables){
		   if(tables==null || tables.equals("")){
			   throw new ExpressionException("构造查询Condition传入的表名为空,tablename="+tables);
		   }
		   this.table=StringUtil.join(tables,",");
		
	}
	
	/**
	 * 添加排序order
	 * @param order
	 */
	 void  addOrders(Order order){
		   if(order==null){
			  logger.warn("查询condition添加的排序order为空");
			   return;
		   }
		   orders.add(order);
	}
	
	
	
	/**
	 * 添加要查询的字段集合
	 * @param fields
	 */
	 void addQueryFields(List<String> fieldsList){
		   if(fieldsList!=null){
			   fields=fieldsList.toArray(new String[fieldsList.size()]);
		   }
	}
	
	 void addQueryFields(String []fields){
		   this.fields=fields;
		
	}
	
	
	public boolean isValid() {

		return true;
	}

	protected String createSql() {

		StringBuffer sql = new StringBuffer(200);
		sql.append(Expression.SQL_SELECT);
		if(fields!=null && fields.length!=0){
			for (int i = 0; i < fields.length; i++) {
				sql.append(fields[i]);
				if (i < fields.length - 1) {
					sql.append(",");
				}
			}
			
		}else{
			sql.append("*");
		}
		
	    sql.append(Expression.SQL_FROM).append(table).append(" ");
		if (expressions != null && expressions.size() != 0) {
			sql.append(Expression.SQL_WHERE);
			for (Expression express : expressions) {
				sql.append(express.toSqlString());
			}

		}
		
		if(this.groupBy!=null){
			sql.append(this.groupBy.toSqlString());
		}
		
		
		if (orders != null && orders.size() != 0) {
			sql.append(Expression.SQL_ORDER_BY);
			for (int i = 0; i < orders.size(); i++) {
				sql.append(orders.get(i).toSqlString());
				if (i < orders.size() - 1) {
					sql.append(",");
				}
			}
		}
		return sql.toString();
	}

}
