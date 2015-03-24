package com.lc.sofa.core.framework.support.sql.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL联合表达式
 * 
 * @author YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class Junction extends AbstractExpression implements Expression {

	/**
	 * 存放所有的表达式
	 */
	protected List<Expression> allExpression = new ArrayList<Expression>();

	/**
	 * 表达式操作符
	 */
	protected String op;

	/**
	 * 自定义查询条件(不带where)
	 */
	protected String customerWhereSql;

	public Junction(String op, Expression... expressions) {

		this.op = op;
		for (Expression expression : expressions) {
			addExpression(expression);
		}
	}

	public Junction(String op) {

		this.op = op;
	}

	public Junction(String op, String customWhereSql, Expression... expressions) {

		this.op = op;
		for (Expression expression : expressions) {
			addExpression(expression);
		}
		this.customerWhereSql = customWhereSql;

	}

	public Junction(String op, String customWhereSql) {

		this.op = op;
		this.customerWhereSql = customWhereSql;
	}

	public void addExpression(Expression expression) {

		allExpression.add(expression);

	}

	/**
	 * @param customerWhereSql
	 *            the customerWhereSql to set
	 */
	public void setCustomerWhereSql(String customerWhereSql) {

		this.customerWhereSql = customerWhereSql;
	}

	public boolean isValid() {

		if (allExpression.size() != 0) {
			for (Expression expression : allExpression) {
				if (!expression.isValid()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	protected String createSql() {

		StringBuilder sqlBuilder = new StringBuilder(200);
		int expressionSize = allExpression.size();
		if (expressionSize != 0) {
			if (expressionSize == 1) {
				//sqlBuilder.append(getOp()).append(" ").append(allExpression.get(0).toSqlString());
				sqlBuilder.append(allExpression.get(0).toSqlString());

			} else {
				for (int i = 0; i < expressionSize; i++) {
					sqlBuilder.append(allExpression.get(i).toSqlString());
					if (i < expressionSize - 1) {
						sqlBuilder.append(" ").append(getOp()).append(" ");

					}
				}

			}

			if (customerWhereSql != null && !customerWhereSql.equals("")) {
				sqlBuilder.append(getOp()).append(" ").append(customerWhereSql);
			}

		} else {  //expressionSize==0

			if (customerWhereSql != null && !customerWhereSql.equals("")) {
				sqlBuilder.append(customerWhereSql);
			}
		}

		return " (" + sqlBuilder.toString() + ") ";
	}

	public String getOp() {

		return op;
	}

}
