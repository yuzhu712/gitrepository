package com.lc.sofa.core.framework.test;

import java.util.List;

import com.lc.sofa.core.framework.component.uniformaccess.bo.GenericBO;
import com.lc.sofa.core.framework.basis.vo.UserInfo;
import com.lc.sofa.core.framework.support.sql.ConditionBuilder;
import com.lc.sofa.core.framework.support.sql.expression.Order;
import com.lc.sofa.core.framework.support.sql.expression.Restrictions;

import org.springframework.stereotype.Component;

/**
 * 查询对象只需扩展于GenericBO,并添加对象生成注解@Component,无需额外配置
 * 
 * @author yuzhu
 * @version 1.0, 2014-1-9
 * @since 1.0, 2014-1-9
 */

@Component(value = "loginBo")
public class LoginBo extends GenericBO{

	protected Class getResultVoClass() {

		
		return UserInfo.class;
	}

	public List<UserInfo> queruUserList(){
		ConditionBuilder builder=new ConditionBuilder();
		builder.setTableName("t_sofa_userinfo");
		builder.setFields("id","logincode","loginname");
		
		builder.setExpression(Restrictions.and(Restrictions.between("wropasstimes", 0, 5),
		             	                      Restrictions.rlike("logincode", "admin"))
				);
		builder.setOrder(Restrictions.order("logincode", Order.type.DESC));
		return this.queryDataList(builder);
	}
	
	
	
}
