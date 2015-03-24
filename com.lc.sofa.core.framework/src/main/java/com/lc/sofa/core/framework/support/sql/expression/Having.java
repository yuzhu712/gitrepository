package com.lc.sofa.core.framework.support.sql.expression;

import java.util.List;
import java.util.ArrayList;

/**
 * SQL HAVING BEAN
 * 
 * @author YZ
 * @version 1.0, 2014-1-8
 * @since 1.0, 2014-1-8
 */
public class Having extends AbstractExpression implements Expression {

	private List<Expression> havingExpression;

	public Having(Expression... expressions) {

		havingExpression = new ArrayList<Expression>();
		for (Expression expre : expressions) {
			havingExpression.add(expre);
		}

	}

	/**
	 * 添加having条件表达式
	 * 
	 * @param whereExpression
	 */
	void addHavingExpression(Expression havingExpression) {

		if (havingExpression != null) {
			this.havingExpression.add(havingExpression);
		}
	}

	public boolean isValid() {

		return (havingExpression.size() != 0) ? true : false;
	}

	protected String createSql() {

		StringBuffer sql = new StringBuffer(50);
		sql.append(Expression.SQL_HAVING);
		for (Expression express : havingExpression) {
			sql.append(express.toSqlString());
		}
		return sql.toString();
	}

}
