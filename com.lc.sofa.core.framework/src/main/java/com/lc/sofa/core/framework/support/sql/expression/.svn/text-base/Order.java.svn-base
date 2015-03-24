package com.lc.sofa.core.framework.support.sql.expression;

/**
 * 
 * 字段排序BEAN
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class Order extends AbstractExpression implements Expression{

	
    /**
     * 
     * 排序字段
     */
    private String  fieldName;
    
    /**
     * 排序类型(ACS,DESC)
     * 
     */
    private Order.type orderMode;
    

    Order(String fieldName,Order.type orderMode) {
    	this.fieldName = fieldName;
	    this.orderMode = orderMode;
    }

    /**
	 * 排序枚举类型
	 * 
	 */
  public static enum type{
         ASC,
         DESC
  }

	public boolean isValid() {
		if (fieldName == null || fieldName.trim().equals("")) {
			throw new IllegalArgumentException("order Expression Parameter Validate Error!fieldName+" + fieldName);
		}
		return true;
	}

	protected String createSql() {
           StringBuffer order=new StringBuffer(50);
           order.append(fieldName).append(" ").append(this.orderMode.name());
		   return order.toString();
	}	
    	
    
    
}
