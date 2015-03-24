package com.lc.sofa.core.framework.support.sql.expression;


public  class BetweenOrNotExpression extends AbstractExpression implements Expression{

	 /**
	    * 
	    * BETWEEN表达式中的字段名称
	    */
	    protected String fieldName;
	    
	    /**
	     * 
	     * BETWEEN表达式中的左边值
	     */
	    protected Object loValue;
	    
	    /**
	     * 
	     * BETWEEN表达式中的右边值
	     * 
	     */
	    protected Object hiValue;
	    
	    /**
	     * 表达式操作符
	     */
	    protected String op;
	    
	    public BetweenOrNotExpression(){
	    	
	    }
	    
	    
	   /**
	    * 对属性的验证是否正确
	    * 
	    * 
	    */
	    public boolean isValid() {
		 if(fieldName==null || fieldName.trim().equals("") || loValue==null || hiValue==null){
		            throw new IllegalArgumentException("SQL BetweenExpression Parameter Validate Error!propertyName+"+fieldName+"loValue="+loValue+"hiValue="+hiValue);
		 }
		
		return true;
	    }

	    /**
	     * 
	     * 该方法创建BETWWEEN表达式语句。 
	     * 
	     */
	    @Override
	    protected String createSql() {
		 StringBuilder strBuilder=new StringBuilder(60);
		 strBuilder.append(fieldName).append(" ").append(this.op).append(" ").append(loValue).append(" ").append(Expression.SQL_AND).append(" ").append(hiValue);
		 return "("+strBuilder.toString()+")";
		
	    }
}
