package com.lc.sofa.core.framework.support.sql.expression;

import com.lc.sofa.core.framework.util.StringUtil;

/**
 * SQL GROUP-BY BEAN
 * 
 * @author YZ
 * @version 1.0, 2014-1-8
 * @since 1.0, 2014-1-8
 */
public class GroupBy extends AbstractExpression implements Expression {

	/**
	 * 分组的字段
	 */
	private String[] groupFields;

	private Having having;

	public GroupBy(String ...groupFields){
		   this.groupFields=groupFields;
		
	}
	
	
	
	/**
	 * @param having
	 *            the having to set
	 */
	public void setHaving(Having having) {

		this.having = having;
	}

	/**
	 * @param groupFields
	 *            the groupFields to set
	 */
	public void setGroupFields(String[] groupFields) {

		this.groupFields = groupFields;
	}

	public boolean isValid() {

		return (groupFields != null && groupFields.length != 0) ? true : false;
	}

	protected String createSql() {

		StringBuffer sql = new StringBuffer(100);
		if (groupFields != null && groupFields.length != 0) {
			sql.append(Expression.SQL_GROUP_BY).append(StringUtil.join(groupFields, ","));
			if (having != null) {
				sql.append(having.toSqlString());
			}
		}
		return sql.toString();

	}

}
