package com.lc.sofa.core.framework.support.sql.expression;


/**
 * OR连接表达式
 * @author YZ
 *
 */
public class DisJunction extends Junction{

    public DisJunction(){
	      
	      super(Expression.SQL_OR);
    }

    public DisJunction(Expression ...expression){
	      
	      super(Expression.SQL_OR,expression); 
	   
    }
 
    public DisJunction(String whereSql){
    	   super(Expression.SQL_OR,whereSql);
    	
    }
    
}
