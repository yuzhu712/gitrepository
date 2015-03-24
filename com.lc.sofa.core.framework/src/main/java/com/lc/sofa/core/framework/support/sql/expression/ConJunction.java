package com.lc.sofa.core.framework.support.sql.expression;

/**
 * AND连接表达式
 * @author yz
 *
 */
public class ConJunction extends Junction{

       public ConJunction(){
	      
	      super(Expression.SQL_AND);
       }
    
       public ConJunction(Expression ...expression){
	      
	      super(Expression.SQL_AND,expression); 
	   
       }
       
       public ConJunction(String whereSql){
    	      super(Expression.SQL_AND,whereSql);
    	   
       }
}
