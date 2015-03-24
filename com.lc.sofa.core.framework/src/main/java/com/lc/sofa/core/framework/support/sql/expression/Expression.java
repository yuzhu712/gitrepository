package com.lc.sofa.core.framework.support.sql.expression;

/**
 * 
 * SQL表达式接口,子类需要实现toSqlString()和isValid()方法
 * toSqlString()将表达式转换为SQL
 * isValid()对表达式参数的验证
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public interface Expression {
    
    public static final String SQL_IN=" in ";
    
    public static final String SQL_NOT_IN=" not in ";
    
    public static final String SQL_TRUE=" 1=1 ";
    
    public static final String SQL_AND=" and ";
    
    public static final String SQL_OR=" or ";
    
    public static final String SQL_EQUAL="=";
    
    public static final String SQL_IS_NULL=" is null ";
    
    public static final String SQL_IS_NOT_NULL=" is not null ";
    
    public static final String SQL_BETWEEN=" between ";
    
    public static final String SQL_NOT_BETWEEEN=" not between";
    
    public static final String SQL_LIKE=" like ";
    
    public static final String SQL_GT=" >";
    
    public static final String SQL_GE=" >=";
    
    public static final String SQL_LT=" <";
    
    public static final String SQL_LE=" <=";
    
    public static final String SQL_NE=" <>";
    
    public static final String SQL_ORDER_BY=" order by ";
    
    public static final String SQL_SELECT="select ";
    
    public static final String SQL_FROM=" from ";
    
    public static final String SQL_WHERE=" where ";
    
    public static final String SQL_GROUP_BY=" group by ";
    
    public static final String SQL_HAVING=" having ";
    
    /**
     * 转换为SQL字符串
     * @return
     */
    public String toSqlString();
    
    /**
     * 对表达式参数的验证
     * @return
     */
    public boolean isValid();
    
    
}
