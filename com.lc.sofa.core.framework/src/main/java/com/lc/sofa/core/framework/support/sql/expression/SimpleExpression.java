package com.lc.sofa.core.framework.support.sql.expression;

/**
 * 简单表达式(默认构造在用等号表达式)
 * 
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class SimpleExpression extends AbstractExpression implements Expression {

	/**
	 * SQL表达式属性名称
	 */
	private String fieldName;

	/**
	 * SQL表达式属性值
	 */
	private Object value;

	/**
	 * SQL表达式中操作符
	 */
	private String op;

	public SimpleExpression(String op, String fieldName, Object value) {

		this.op = op;
		this.fieldName = fieldName;
		this.value = value;
	}
    
	/**
	 * 默认构造采用=表达式
	 * @param propertyName
	 * @param value
	 */
	public SimpleExpression(String propertyName, Object value) {

		this.fieldName = propertyName;
		this.value = value;
		this.op = Expression.SQL_EQUAL;
	}

	/**
	 * 对属性的验证是否正确。
	 */
	public boolean isValid() {

		if (fieldName == null || fieldName.trim().equals("") || value == null) {
			throw new IllegalArgumentException("SQL SimpleExpression Parameter Validate Error!propertyName+" + fieldName + "value=" + value);
		}
		return true;
	}

	/**
	 * 该方法创建BETWWEEN表达式语句。
	 */
	@Override
	protected String createSql() {

		StringBuilder strBuilder = new StringBuilder();
		if (op != null && !op.trim().equals("")) {
			strBuilder.append(fieldName).append(" ").append(getOp()).append(" ");
		} else {
			strBuilder.append(fieldName).append(" ");  //采用别名形式
		}

		if (value instanceof String) {
			strBuilder.append("'");
			strBuilder.append(value);
			strBuilder.append("'");
		} else {
			strBuilder.append(value);
		}
		return strBuilder.toString();
	}

	public String getOp() {

		return op;
	}

}
