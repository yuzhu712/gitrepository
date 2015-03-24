package com.lc.sofa.core.framework.support.sql.expression;

/**
 * 
 * SQL表达式抽象类,实现了Expression接口toSqlString()方法,
 * 扩展于该类的子类需要去实现createSql方法和isValid()方法。
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public abstract class AbstractExpression implements Expression {

    //@Override
    public String toSqlString() {

	try {
	    if (isValid()) {
		return createSql();
	    }
	} catch (Exception e) {
	    throw new ExpressionException("CREATE SQL EXPRESSION OCCUR EXCEPTION",e);
	}
	return null;

    }

    
    /**
     * 创建具体的SQL-expression,子类实现
     * 
     * @return
     */
    protected abstract String createSql();

}
