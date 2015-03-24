package com.lc.sofa.core.framework.basis.vo;

import java.util.List;

/**
 * 
 * 分页数据对象，用于转换为JSON的中间对象
 * @author     YZ
 * @version 1.0, 2013-11-6
 * @since 1.0, 2013-11-6
 */
public class Page {
	   
	/**
	 * 
	 * 默认每页显示数据量
	 */
	   public static final int DEFAULT_PER_PAGE_SIZE=30;
	
	   /**
	    * 
	    * 当前页码参数名称
	    */
	   public static final String PAGE_NO_PARAM_NAME="pageNo";
	   
	   /**
	    * 
	    * 每页数据容量参数名称
	    */
	   public static final String PAGE_SIZE_PARAM_NAME="pageSize";
	   
	   /**
	    * 
	    * page实例参数名称
	    */
	   public static final String PAGE_PARAM_NAME="page";
	   
	   
	   /**
	    * 记录总数 
	    */
	    private int	 totalCount;
	    
	   /**
	     * 
	     * 当前页码
	     */
	    private int currentPageNo;
	    
	    /**
	     * 
	     * 总页码
	     */
	    private int totalPageNo;
	    
	    /**
	     * 每页显示记录数
	     */
	    public int perPageSize; 	
	    
	    /**
	     * 每页显示记录数
	     */
	    public List records; 
	    
	    public Page(){
	    	this.currentPageNo=1;
	    	this.perPageSize=DEFAULT_PER_PAGE_SIZE;
	    }
	    
	    public Page(int currentPage, int pageSize){
	           this.currentPageNo=currentPage;
	           this.perPageSize=pageSize;
	    }
	    
		public Page(List records) {
			this.records = records;
			if(records!=null)
				this.totalCount = records.size();
		}
	    
		/**
		 * @return the records
		 */
		public List getRecords() {
		
			return records;
		}

		
		/**
		 * @param records the records to set
		 */
		public void setRecords(List records) {
		
			this.records = records;
		}

		/**
		 * @return the totalCount
		 */
		public int getTotalCount() {
		
			return totalCount;
		}


		
		/**
		 * @param totalCount the totalCount to set
		 */
		public void setTotalCount(int totalCount) {
		
			this.totalCount = totalCount;
			this.totalPageNo= totalCount / perPageSize + ((totalCount %perPageSize == 0) ? 0 : 1);
		}


		
		/**
		 * @return the perPageSize
		 */
		public int getPerPageSize() {
		
			return perPageSize;
		}


		
		/**
		 * @param perPageSize the perPageSize to set
		 */
		public void setPerPageSize(int perPageSize) {
		
			this.perPageSize = perPageSize;
		}


		/**
		 * @return the currentPageNo
		 */
		public int getCurrentPageNo() {
		
			return currentPageNo;
		}

		
		/**
		 * @param currentPageNo the currentPageNo to set
		 */
		public void setCurrentPageNo(int currentPageNo) {
		
			this.currentPageNo = currentPageNo;
		}

		
		/**
		 * @return the totalPageNo
		 */
		public int getTotalPageNo() {
		
			return totalPageNo;
		}

		
		/**
		 * @param totalPageNo the totalPageNo to set
		 */
		/*public void setTotalPageNo(int totalPageNo) {
		
			this.totalPageNo = totalPageNo;
		}*/
	    
	    
	    
	    

}
