package com.lc.sofa.core.framework.component.i18n;

import java.util.Properties;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
import com.lc.sofa.core.framework.component.i18n.SofaI18nException;


/**
 * 
 * 国际化I18n服务抽象类(简化版)
 * @author   YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public abstract class SofaI18n {
     
	public static final String GLOBAL_I18N_CONFIG_BUNDLE="com.lc.sofa.basis.resource";
	
	/**
	 * 获取I18n实例
	 * @return
	 */
	public static SofaI18n getInstance(){
		   
		SofaI18n i18n = (SofaI18n) OsgiServiceUtil.getOsgiService(SofaI18n.class);
		if (i18n == null) {
			throw new SofaI18nException("未获取到I18n OSGI服务-SofaI18n.");
		}
		return i18n;
   }
	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 * @throws SofaI18nException
	 */
	public abstract String getString(String key)throws SofaI18nException;
	
	/**
	 * 根据key获取含有占位符的value
	 * @param key
	 * @param placeholders
	 * @return
	 * @throws SofaI18nException
	 */
    public abstract String getString(String key,String... placeholders)throws SofaI18nException;
    
    /**
     * 
     * 获取当前组件的国际化配置信息集合
     * @return
     * @throws SofaI18nException
     */
    public abstract Properties  getProperties()throws SofaI18nException;

}
