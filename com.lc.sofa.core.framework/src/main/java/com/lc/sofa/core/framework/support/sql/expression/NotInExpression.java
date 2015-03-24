package com.lc.sofa.core.framework.support.sql.expression;

/**
 * 对oracle in(..)超过1000个单位分割内容的特殊处理(进行适当数量的分组后在or连接)
 * 
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class NotInExpression extends InOrNotExpression{

   
   public NotInExpression(String fieldName,Object[] values){
	   this.fieldName=fieldName;
	   this.values=values;
	   this.op=Expression.SQL_NOT_IN;
    }
    
   
   
}
