package com.lc.sofa.core.framework.support.sql.expression;

/**
 * SQL中IS NOT NULL表达式
 * 
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class NotNullExpression extends AbstractExpression implements Expression{

    /**
     * NotNull表达式属性名称
     */
    private String fieldName;
    
    public NotNullExpression(String fieldName){
	   this.fieldName=fieldName;
    }
    
    
    /**
     * 
     * 对属性的验证是否正确。
     */
    public boolean isValid() {
	if(fieldName==null || fieldName.trim().equals("")){
	    throw new IllegalArgumentException("SQL IsNullExpression Parameter Validate Error!propertyName+"+fieldName);
	}
	
	return true;
     }

    /**
     * 
     * 该方法创建NotNull表达式语句。
     */
    @Override
    protected String createSql() {
	StringBuilder strBuilder=new StringBuilder();
	strBuilder.append(fieldName).append(" ").append(Expression.SQL_IS_NOT_NULL);
	return strBuilder.toString();
	
    }

}
