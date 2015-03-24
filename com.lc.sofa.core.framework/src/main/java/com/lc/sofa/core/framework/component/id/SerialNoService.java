package com.lc.sofa.core.framework.component.id;

import java.util.Date;
import com.lc.sofa.container.util.osgi.OsgiServiceUtil;


/**
 * 
 * 带规则的ID编号生成服务
 * @author       YZ
 * @version 1.0, 2013-11-7
 * @since 1.0, 2013-11-7
 */
public abstract class SerialNoService {
	
	   public static SerialNoService getInstance(){
		   
		    SerialNoService service = (SerialNoService) OsgiServiceUtil.getOsgiService(SerialNoService.class);
			if (service == null) {
				throw new IdGenerateException("未获取到流水号生成器OSGI服务-SerialNoService.");
			}
			return service;
	   }
	   
	   /**
	    * ID生成
	    * @param date
	    * @param prefix
	    * @param size
	    * @param format
	    * @return
	    * @throws IdGenerateException
	    */
	   public abstract String[] generate(String prefix,Date date, int size, String format) throws IdGenerateException;

}
