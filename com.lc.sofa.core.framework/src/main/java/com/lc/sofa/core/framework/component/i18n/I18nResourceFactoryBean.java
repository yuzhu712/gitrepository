package com.lc.sofa.core.framework.component.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.io.File;
import org.osgi.framework.Bundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.osgi.context.support.OsgiBundleXmlApplicationContext;

import com.lc.sofa.core.framework.constants.SofaConstants;
import com.lc.sofa.core.framework.support.SofaEnvoronmentSupport;
import com.lc.sofa.core.framework.util.PropertiesSequenceReader;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
/**
 * 
 * I18n资源文件获取工厂BEAN
 * @author        YZ
 * @version 1.0, 2013-12-10
 * @since 1.0, 2013-12-10
 */
public class I18nResourceFactoryBean implements FactoryBean, InitializingBean, ApplicationContextAware{
	
	private SofaLogger logger=SofaLoggerFactory.getSofaLog(I18nResourceFactoryBean.class);
	
	/**
	 * 组件bean容器上下文
	 */
	private OsgiBundleXmlApplicationContext osgiContext;
    
	/**
	 * I18N国际化配置信息	
	 */
	private Properties i18nProperties;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
           this.osgiContext=(OsgiBundleXmlApplicationContext)applicationContext;
	}

	public void afterPropertiesSet() throws Exception {
		Bundle bundle = osgiContext.getBundle();
		String symbolicName = bundle.getSymbolicName();
		String version = bundle.getVersion().toString();
        String filePath =
			SofaEnvoronmentSupport.getSofaHomePath() + "/bundles/" + symbolicName + "-" + version + "/"
				+ SofaConstants.I18N_CONFIG_FILE;
        if(filePath==null || filePath.trim().equals("")){
        	logger.warn("该组件没有国际化文件,组件名称="+symbolicName);	
        }
        PropertiesSequenceReader sequenceReader=new PropertiesSequenceReader(new File(filePath));
        this.i18nProperties=sequenceReader.getProperties();
        
	}

	public Object getObject() throws Exception {
           return this.i18nProperties;
	}

	public Class getObjectType() {
           return i18nProperties.getClass();
	}

	public boolean isSingleton() {
           return true;
	}
	

}
