package com.lc.sofa.core.framework.basis.context;

import com.lc.sofa.core.framework.basis.vo.Page;

import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;

/**
 * 分页线程上下文类
 * 
 * @author       YZ
 * @version 1.0, 2013-12-30
 * @since 1.0, 2013-12-30
 */
public class PageThreadContext {
	
	   static  ThreadLocal<Page> pagecontexts = new ThreadLocal<Page>();
	   
	   private static SofaLogger logger=SofaLoggerFactory.getSofaLog(PageThreadContext.class);
	   
	   /**
	    * 获取当前分页信息
	    * @return
	    */
	   public static Page getCurrentPage(){
		   
		       Page page=pagecontexts.get();
		       if(page==null){
		    	   logger.warn("get request page from ThreadLocal is null...."); 
		    	   page=new Page();
		    	   setCurrentPage( page);
		       }
		       return page;
		       
	   }
	   
	   /**
	    * 设置当前分页信息
	    * @param page
	    */
	   public static void setCurrentPage(Page page){
		      pagecontexts.set(page);
		   
		   
	   }
	   
	   /**
	    * 清出线程环境中的分页信息
	    */
	   public static void clear(){
		   pagecontexts.remove();
	   }

}
