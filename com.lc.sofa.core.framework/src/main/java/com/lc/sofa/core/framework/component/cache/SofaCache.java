package com.lc.sofa.core.framework.component.cache;

import java.util.Map;
import java.util.Date;
import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
/**
 * 
 * 平台缓存服务抽象类
 * @author       YZ
 * @version 1.0, 2013-12-11
 * @since 1.0, 2013-12-11
 */
public abstract class SofaCache {

	/**
     * 获取SOFACache实例
     * 
     * @return SOFACache实例
     * @throws SOFAException 
     */
    public static SofaCache getInstance() throws SofaCacheException {
    	SofaCache sofaCache = (SofaCache) OsgiServiceUtil.getOsgiService(SofaCache.class);
		if (sofaCache == null) {
			throw new SofaCacheException("未获取到SofaCache Osgi服务....");
		}
		return sofaCache;
    }
    
    /**
     * 根据key从缓存获取value
     * @param key
     * @return
     * @throws SofaCacheException
     */
    public abstract Object get(String key) throws SofaCacheException;
    
    /**
     * 将key-value放置缓存
     * @param key
     * @param value
     * @return
     * @throws SofaCacheException
     */
    public abstract Object put(String key, Object value) throws SofaCacheException;
    
    /**
     * 若key值存在,依据isOverride判断是否覆盖value(true-覆盖、false-不覆盖),若key不存在,则直接put
     * @param key
     * @param value
     * @param isOverride-是否覆盖
     * @return
     * @throws SofaCacheException
     */
    public abstract Object put(String key,Object value,boolean isOverride)throws SofaCacheException;
    
    /**
     * 若key值存在,依据isOverride判断是否覆盖value(true-覆盖、false-不覆盖),若key不存在,则直接put
     * 并可以设置超时时间
     * @param key
     * @param value
     * @param isOverride-是否覆盖
     * @return
     * @throws SofaCacheException
     */
    public abstract Object put(String key,Object value,int TTL,boolean isOverride)throws SofaCacheException;
    
    /**
     * 根据key从缓存获取value
     * @param key
     * @return
     * @throws SofaCacheException
     */
    public abstract Object remove(String key) throws SofaCacheException;
    
    /**
     * 根据多个key从缓存获取多个value
     * @param keys
     * @return
     * @throws SofaCacheException
     */
    public abstract Map<String,Object> getMulti(String[] keys)throws SofaCacheException;
    
    /**
     * 将key-value放置缓存,并设置缓存的超时时间，超时后，数据将被清除
     * @param key
     * @param obj
     * @param TTL-超时时间(单位:秒)
     * @return
     */
    public abstract Object put(String key,Object obj,int TTL)throws SofaCacheException;
    
    /**
     * 替换cache中key的value,前提是key必须存在于cache中,否则替换不成功
     * @param key
     * @param value
     * @throws SofaCacheException
     */
    public abstract void replace(String key,Object value)throws SofaCacheException;	
    
    /**
     * 对某个key的累加计数,只有当该key的value存在时,才累加成功. 
     * @param key
     * @param by
     * @return
     */
	 public abstract long inscr(String key,int by)throws SofaCacheException;
    
	 /**
      * 对某个key的value累加计数,当该key-value存在时,进行累加,value不存在时,则以by值作为key的当前值 . 
      * @param key
      * @param by
      * @return
      */
	 public abstract long addOrInscr(String key,int by)throws SofaCacheException;
	 
	 /**
	  * 判断key是否包含在缓存中
	  * @param key
	  * @return
	  */
	 public abstract boolean isContains(String key);
	 
	 /**
	  * 获取缓存中数据个数
	  * @return
	  */
	 public abstract int getKeySize();
	 
    
}
