package com.lc.sofa.core.framework.web.controller;

import com.lc.sofa.core.framework.basis.vo.Page;

import com.lc.sofa.core.framework.basis.context.PageThreadContext;
import com.lc.sofa.core.framework.basis.context.SofaContext;

/**
 * WEB控制器基类,提供了获取用户基本信息的公共方法.
 * 
 * @author YZ
 * @version 1.0, 2013-11-11
 * @since 1.0, 2013-11-11
 */
public class BaseController {

	/**
	 * 获取当前用户ID信息
	 * 
	 * @return
	 */
	protected String getCurrentUserId() {

		return SofaContext.getInstance().getCurrentLoginId();
	}

	/**
	 * 获取当前用户CODE信息
	 * 
	 * @return
	 */
	protected String getCurrentUserCode() {

		return SofaContext.getInstance().getCurrentLoginCode();
	}

	/**
	 * 获取当前用户CODE信息
	 * 
	 * @return
	 */
	protected String getCurrentUserName() {

		return SofaContext.getInstance().getCurrentLoginName();
	}

	
	/**
	 * 获取分页信息,此方法不对外开放,如果controller将Page作为属性会引发内存泄露问题
	 * @return
	 */
	private Page getPageInfo(){
		
		      return PageThreadContext.getCurrentPage();
	}
	
	/**
	 * 返回当前页码
	 * @return
	 */
	protected int getPageNo(){
		return getPageInfo().getCurrentPageNo();
	}
	
	/**
	 * 返回每页显示记录数
	 * @return
	 */
	protected int getPageSize(){
		return getPageInfo().getPerPageSize();
	}
	
	/**
	 * 获取分页查询的全部记录数
	 * @return
	 */
	protected int getTotalCount(){
		return getPageInfo().getTotalCount();
		
	}
	
	/**
	 * 返回分页查询的总页数
	 * @return
	 */
	protected int getTotalPage(){
		return getPageInfo().getTotalPageNo();
	}
	

}
