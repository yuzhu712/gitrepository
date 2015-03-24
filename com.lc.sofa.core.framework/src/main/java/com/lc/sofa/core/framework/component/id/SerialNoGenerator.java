package com.lc.sofa.core.framework.component.id;

import java.util.Date;

import com.lc.sofa.core.framework.component.id.IdGenerator;
import com.lc.sofa.core.framework.util.DateUtil;
import com.lc.sofa.core.framework.util.StringUtil;
//import com.lc.sofa.core.framework.basis.config.SofaConfigFactory;

/**
 * 
 * 规则ID编号生成器,基于数据库生成ID
 * @author      YZ
 * @version 1.0, 2013-11-7
 * @since 1.0, 2013-11-7
 */
public class SerialNoGenerator implements IdGenerator{

	/**
	 * ID前缀
	 * 
	 */
	private String prefix = "L";
	
	/**
	 * 格式
	 */
	private  String format = FORMAT_PREFIXYYYYMMDD00000000000;
	
	private Date date = DateUtil.stringtoDate(DateUtil.getCurrent(), "yyyy-MM-dd");
    
	/**
	 * 默认生成ID个数
	 */
	private int cacheSize = 1;

    /**
     * ID进行Format格式
	 * prefix+yyyyMMdd+00000000000
	 */
	public static final String FORMAT_PREFIXYYYYMMDD00000000000 = "%1$s%2$tY%2$tm%2$td%3$011d";
	
	public SerialNoGenerator(String idPrefix, String format,int size) {
        this.prefix = StringUtil.isEmpty(idPrefix)? this.prefix : idPrefix;
		this.format = StringUtil.isEmpty(format)? this.format : format;
		this.cacheSize=(size<=0 || size>3000)?this.cacheSize:size;
	}

	public SerialNoGenerator(String idPrefix,int size) {
         this.prefix = StringUtil.isEmpty(idPrefix)? this.prefix : idPrefix;
         this.cacheSize=(size<=0 || size>3000)?this.cacheSize:size;
	}
	
	
	public SerialNoGenerator(int size){
		this.cacheSize=(size<=0 || size>3000)?this.cacheSize:size;
	}
	public SerialNoGenerator() {

	}
	
	/**
	 * 获取下一个ID
	 * {@inheritDoc}
	 * @see com.lc.sofa.core.framework.component.id.IdGenerator#nextId()
	 */
	public String nextId()throws IdGenerateException{
           return this.generate(prefix, this.cacheSize, this.format)[0];
	}

	/**
	 * 批量获取ID
	 * {@inheritDoc}
	 * @see com.lc.sofa.core.framework.component.id.IdGenerator#nextIds()
	 */
	public String[] nextIds()throws IdGenerateException {
           return this.generate(prefix, cacheSize, format);
	}
	
	private String[] generate(String prefix, int size, String format)throws IdGenerateException{
		if (prefix.length() >= 20) {
			throw new IdGenerateException("自定义规则ID前缀长度超出限定长度20个字符.");
		}
		SerialNoService serialNoService = SerialNoService.getInstance();
		String[] ids = serialNoService.generate(prefix,date,size,format);
		return ids;
	}

}
