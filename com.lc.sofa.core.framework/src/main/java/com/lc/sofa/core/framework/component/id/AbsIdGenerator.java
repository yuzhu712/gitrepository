package com.lc.sofa.core.framework.component.id;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ID生成器抽象类
 * 
 * @author YZ
 * @version 1.0, 2013-11-7
 * @since 1.0, 2013-11-7
 */
public abstract class AbsIdGenerator implements IdGenerator {

	private ArrayBlockingQueue<String> arrayBlockingQueue;

	private int cacheSize = 50;

	/**
	 * 默认构造器采用默认一次生成50个ID
	 */
	public AbsIdGenerator() {
     try{
		this.arrayBlockingQueue = new ArrayBlockingQueue<String>(cacheSize);
		this.batchCreateQueueNum();
     }catch(Exception e){
    	 throw new IdGenerateException("AbsIdGenerator() 构造函数时发生异常..."); 
    	 
     }
	}

	/**
	 * 定制生成ID的个数,限制生成个数不能超过1000个.
	 * 
	 * @param size
	 */
	public AbsIdGenerator(int size) {
	try{
		if (size <= 0) {
			this.cacheSize = 1;

		} else if (size > 1000) {
			this.cacheSize = 1000;
		} else {
			this.cacheSize = size;
		}
		this.arrayBlockingQueue = new ArrayBlockingQueue(cacheSize);
		this.batchCreateQueueNum();
	 }catch(Exception e){
		 throw new IdGenerateException("AbsIdGenerator(int size) 构造函数时发生异常..."); 
	 }
	}

	/**
	 * 批量产生队列中ID
	 */
	protected synchronized void batchCreateQueueNum() {

		if (this.arrayBlockingQueue != null) {
			arrayBlockingQueue.clear();
		}
		for (int i = 0; i < this.cacheSize; i++) {
			String id = generate();
			if (!arrayBlockingQueue.offer(id)) {
				break;
			}

		}
	}

	/**
	 * 获取下个ID
	 * 
	 * @return
	 */
	public String nextId()throws IdGenerateException {
     try{
		if (this.cacheSize == 1) {
			return this.generate();
		}

		while (true) {
			String id = this.arrayBlockingQueue.poll();
			if (id != null) {
				return id;
			}
			this.batchCreateQueueNum();
		}
     }catch(Exception e){ 	
    	 throw new IdGenerateException("AbsIdGenerator nextId发生异常...",e);
     }
	}

	
	public String[] nextIds()throws IdGenerateException{
        String []result=new String[cacheSize];
     try{   
		if (this.cacheSize == 1) {
			return new String[] { this.generate() };
		}

		while (true) {
			if (this.arrayBlockingQueue.size() != 0) {
				return  arrayBlockingQueue.toArray(result);
			}
			this.batchCreateQueueNum();
		}
     }catch(Exception e){	
    	 throw new IdGenerateException("AbsIdGenerator String[] nextId发生异常...",e);
     }
	}

	/**
	 * 生成ID
	 * 
	 * @param order
	 * @return
	 */
	protected abstract String generate();

}
