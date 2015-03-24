package com.lc.sofa.core.framework.support.sql.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lc.sofa.core.framework.util.ArraysUtil;

/**
 * 
 * In or Not in表达式抽象类
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public  class InOrNotExpression extends AbstractExpression implements Expression {

	/**
	 * SQL-In/NotIn表达式中字段名称
	 */
	protected String fieldName;

	/**
	 * SQL-In/NotIn表达式中集合
	 */
	protected Object[] values;

	/**
	 * 具体操作符：In/NotIn
	 */
	protected String op;

	/**
	 * In/NotIn中每次限值900个数据值
	 */
	static final int SQL_IN_LIMIT = 900;

	public InOrNotExpression() {

	}

	/**
	 * 对属性的验证是否正确
	 */
	public boolean isValid() {

		//values为空不抛出异常
		if (fieldName == null || fieldName.trim().equals("")) {
			throw new IllegalArgumentException("SQL InOrNotExpression Parameter Validate Error!propertyName=" + fieldName
				+ "values=" + values);
		}
		return true;

	}

	/**
	 * 该方法创建In/NotIn表达式语句。
	 */
	@Override
	protected String createSql() {

		if (values.length <= SQL_IN_LIMIT) {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(fieldName).append(" ").append(this.getOp()).append("(");
			int valuesLength = values.length;
			for (int i = 0; i < valuesLength; i++) {
				strBuilder.append("'").append(values[i]).append("'");
				if (i < valuesLength - 1) {
					strBuilder.append(",");
				}
			}
			strBuilder.append(")");
			return strBuilder.toString();
		} else {

			return createExceedSql();
		}
	}

	/**
	 * 对超过IN/NotIn-LIMIT限制的表达式语句创建
	 * 
	 * @return String-返回In/NotIn表达式语句
	 */
	private String createExceedSql() {

		StringBuilder strBuilder = new StringBuilder();
		List<Object[]> list = new ArrayList<Object[]>();
		int n = ((values.length % SQL_IN_LIMIT) != 0) ? (values.length / SQL_IN_LIMIT + 1) : (values.length / SQL_IN_LIMIT);
		int afterPos = 0;
		for (int i = 0; i < n; i++) {
			if (i == n - 1) {
				afterPos = values.length;
			} else {
				afterPos = i * SQL_IN_LIMIT + SQL_IN_LIMIT;
			}

			list.add(ArraysUtil.copyOfRange(values, i * SQL_IN_LIMIT, afterPos));
		}

		for (int j = 0; j < list.size(); j++) {
			strBuilder.append(fieldName).append(" ").append(this.getOp()).append("(");
			Object[] partValues = list.get(j);
			for (int k = 0; k < partValues.length; k++) {
				strBuilder.append("'").append(partValues[k]).append("'");
				if (k < partValues.length - 1) {
					strBuilder.append(",");
				}
			}
			strBuilder.append(")");
			if (j < list.size() - 1) {
				strBuilder.append(this.SQL_OR);
			}

		}
		return "(" + strBuilder.toString() + ")";

	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
	
		this.fieldName = fieldName;
	}

	public void setValues(Object[] values) {

		this.values = values;
	}

	public void setOp(String op) {

		this.op = op;
	}

	public String getOp() {

		return op;
	}

}
