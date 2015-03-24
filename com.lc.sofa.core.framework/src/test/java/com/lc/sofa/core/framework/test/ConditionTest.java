package com.lc.sofa.core.framework.test;

import com.lc.sofa.core.framework.support.sql.*;
import com.lc.sofa.core.framework.support.sql.expression.Order;
import com.lc.sofa.core.framework.support.sql.expression.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL函数表达式的用法
 * 
 * @author yuzhu
 * @version 1.0, 2014-1-3
 * @since 1.0, 2014-1-3
 */
public class ConditionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//*****************one mode (only one condition)
		ConditionBuilder builder1=ConditionBuilder.getInstance();
		builder1.setTableName("userinfo");
		builder1.setFields("is","name","age","level");
		builder1.setExpression(Restrictions.isNull("name"));
		//builder1.setExpression(Restrictions.whereSql("nam is null and age is not null"));
		builder1.setOrder(Restrictions.order("name", Order.type.DESC));
	
		System.out.println(builder1.getSql());
		
		
		//*****************************two mode(multi and condition)
	
		ConditionBuilder builder=ConditionBuilder.getInstance();
		builder.setTableName("userinfo");
		//builder.setFields("is","name","age","level");
		List<String> fieldsList=new ArrayList<String>();
		fieldsList.add("is");
		fieldsList.add("name");
		fieldsList.add("age");
		fieldsList.add("level");
		builder.setFields(fieldsList);
		builder.setExpression(Restrictions.and(Restrictions.between("age", 25, 35),
		                                       Restrictions.isNotNull("level"),
		                                       Restrictions.gt("salary", 5000),
		                                       Restrictions.in("area", "001","002","003","004"),
		                                       Restrictions.whereSql("name is not null")
		                                       ));
		builder.setOrder(Restrictions.order("name", Order.type.DESC),Restrictions.order("age", Order.type.ASC));
		System.out.println(builder.getSql());
	
	
	   //*****************************three mode(multi or condition)
		ConditionBuilder builder2=ConditionBuilder.getInstance();
		builder2.setTableName("userinfo");
		String[] fields=new String[]{"id as pid","name as pname","age as page","level as plevel"};
		builder2.setFields(fields);
		builder2.setExpression(Restrictions.or(Restrictions.between("age", 25, 35),
		                                       Restrictions.between("age", 45, 60),
		                                       Restrictions.whereSql("name is  null")
		                                     
		                                       ));
		builder2.setOrder(Restrictions.order("name", Order.type.DESC),Restrictions.order("age", Order.type.ASC));
		System.out.println(builder2.getSql());
		
      //*****************************four mode(multi table query)
		ConditionBuilder builder3=ConditionBuilder.getInstance();
		builder3.setTableName("userinfo u","orginfo o");
		String[] fields3=new String[]{"u.id","u.name","o.id","o.name"};
		builder3.setFields(fields3);
		builder3.setExpression(Restrictions.and( Restrictions.whereSql("u.id=o.id"),
		                       Restrictions.in("u.name", "li","fen","ff","gg"), 
		                       Restrictions.isNotNull("o.name")
		                                       ));
		                      
		builder3.setOrder(Restrictions.order("u.name", Order.type.DESC));
		System.out.println(builder3.getSql());
		
		//********************************five mode group by 
		ConditionBuilder builder4=ConditionBuilder.getInstance();
		builder4.setTableName("t_sofa_userinfo");
		String[] fields4=new String[]{"id","logincode","loginname"};
		builder4.setFields(fields4);
		builder4.setExpression(Restrictions.and( Restrictions.between("wropasstimes", 0, 5),
		                                         Restrictions.rlike("logincode", "admin"),
		                                         Restrictions.like("loginname", "管理员")
		                                 ));
		                      
		builder4.setGroupBy(Restrictions.groupBy("name")).setHaving(Restrictions.having(Restrictions.ge("salary", 5000)));
		//builder4
		builder4.setOrder(Restrictions.order("logincode", Order.type.DESC));
		System.out.println(builder4.getSql());
		
		
	}

}
