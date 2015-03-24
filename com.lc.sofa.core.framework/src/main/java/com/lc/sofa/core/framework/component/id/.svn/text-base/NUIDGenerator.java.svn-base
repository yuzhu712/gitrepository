package com.lc.sofa.core.framework.component.id;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * 平台NUID数值型ID生成器,ID由两部分组成:  前13位：当前时间精度到毫秒，不足13位前面补0
 * 后7位:ID的流水号,不足7位前面补0
 * @author       YZ
 * @version 1.0, 2013-11-7
 * @since 1.0, 2013-11-7
 */
public class NUIDGenerator extends AbsIdGenerator implements IdGenerator{

	private static AtomicInteger atomicInteger=new AtomicInteger();
	
	public NUIDGenerator(){
		   super();
		
	}
	
	public NUIDGenerator(int cacheSize){
		   super(cacheSize);
	}
	
	/**
	 * 生成ID
	 * {@inheritDoc}
	 * @see com.lc.sofa.core.framework.component.id.AbsIdGenerator#generate()
	 */
	protected String generate() {
		      long currentTime = System.currentTimeMillis();
		      int num = atomicInteger.incrementAndGet();
		      if(num>9999999){
		    	  atomicInteger.set(1); 
		      }
		      return String.format("%013d", currentTime) + String.format("%07d",num);
	}
	
	public static void main(String[] args) {
		
		/*Thread t1=new Thread(new Runnable(){

			public void run() {

				NUIDGenerator ui=new NUIDGenerator(100);
				for(int i=0;i<101;i++){
					System.out.println(ui.nextId());
				}
				
			}
			
			
			
		});
		
		t1.start();*/
		NUIDGenerator ui=new NUIDGenerator(100000);
		String []ids=ui.nextIds();
		for(String s:ids){
			System.out.println(s);
		}
		
	}

}
