package com.lc.sofa.core.framework.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import org.eclipse.osgi.service.urlconversion.URLConverter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.lc.sofa.container.util.config.ContainerConfig;
import com.lc.sofa.container.util.BundleUtil;
import com.lc.sofa.container.repository.InstalledBundleCache;
import com.lc.sofa.core.framework.basis.exception.CommonException;
import com.lc.sofa.core.framework.util.FileUtil;
import com.lc.sofa.core.framework.support.SofaEnvoronmentSupport;
import com.lc.sofa.container.util.osgi.OsgiServiceUtil;


/**
 * 组件-Bundle的支持工具
 * 
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class BundleSupport {

	private static final int FILLED_DIGIT = 12;

	/**
	 * 根据组件symbolName获取最新版本组件
	 * 
	 * @param bundleSymbolName
	 *            -组件名称
	 * @return
	 */
	public static Bundle getNewVersionBundle(String bundleSymbolName) {

		if (bundleSymbolName == null || bundleSymbolName.trim().equals("")) {
			throw new CommonException("bundleSymbolName is null in BundleSupport.getNewVersionBundle");
		}
		return InstalledBundleCache.getInstance().getNewerBundle(bundleSymbolName.trim());
	}

	/**
	 * 根据组件symbolName获取组件指定的资源.
	 * 使用方式为：BundleUtil.getBundleResource("com.lc.sofa.container.demo", "/META-INF/MANIFEST.MF")；
	 * BundleUtil.getBundleResource("com.lc.sofa.demo.crud", "/META-INF/sofa/functions.xml")；
	 * 
	 * @param bundleSymbolName
	 * @param resRelativePath.
	 * @return
	 */
	public static File getBundleResource(String bundleSymbolName, String resRelativePath) {

		if (bundleSymbolName == null || bundleSymbolName.trim().equals("")) {
			throw new CommonException("bundleSymbolName is null in BundleSupport.getBundleResource");
		}
		if (resRelativePath == null || resRelativePath.trim().equals("")) {
			throw new CommonException("resRelativePath is null in BundleSupport.getBundleResource");
		}
		Bundle bundle = getNewVersionBundle(bundleSymbolName.trim());
		if (bundle != null) {
			URL resUrl = bundle.getEntry(resRelativePath);
			if (resUrl == null) {
				throw new CommonException("URL is null in BundleSupport.getBundleResource");
			}
			return new File(FileUtil.toFileURL(resUrl).getFile());

		} else {
			throw new CommonException("Bundle is null in BundleSupport.getBundleResource");
		}

	}

	/**
	 * 获取一个组件的绝对路径
	 * 
	 * @param bundleSymbolName
	 * @return
	 */
	public static String getBundleFilePath(String bundleSymbolName) throws FileNotFoundException {
		if (bundleSymbolName == null || bundleSymbolName.trim().equals("")) {
			throw new CommonException("bundleSymbolName is null in BundleSupport.getBundleFilePath");
		}
		String basePath = SofaEnvoronmentSupport.getSofaHomePath() + File.separator + "bundles";
		File file = new File(basePath);
        String maxVersion = "";
		double maxVersionValue = 0.0;

		if( file.isDirectory() ){

			String[] files = file.list();

			for( String fileName : files ){

				if( fileName.indexOf(bundleSymbolName) > -1 ){

					String version = fileName.substring( fileName.indexOf( "-" ) + 1 );
					double accountVersion = apartAdnConvertDigit( version );

					if( maxVersionValue == 0 ){
						maxVersion = version;
						maxVersionValue = accountVersion;
					}
					else{
						if( maxVersionValue < accountVersion ){
							maxVersionValue = accountVersion;
							maxVersion = version;
						}
					}
				}
			}
		}
		else{
			throw new FileNotFoundException(basePath + " not found in BundleSupport.getBundleFilePath");
		}
        return assemblyPath(bundleSymbolName, basePath, maxVersion);
		

	}

	/**
	 * 获取一个组件中指定资源的绝对路径
	 * 
	 * @param symbolicName
	 * @param fileName
	 * @return
	 */
	public static String getBundleResourcePath(String symbolicName, String fileName)throws FileNotFoundException {
		if (symbolicName == null || symbolicName.trim().equals("")) {
			throw new CommonException("symbolicName is null in BundleSupport.getBundleResourcePath");
		}
		if(fileName==null || fileName.trim().equals("")){
			throw new CommonException("fileName is null in BundleSupport.getBundleResourcePath");
		}
		 return getBundleFilePath(symbolicName)+fileName;
		 
	}

	/**
	 * 获取一个组件中指定资源的文件流
	 * 
	 * @param symbolicName
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static FileInputStream getBundleResourceStream(String symbolicName, String fileName) throws FileNotFoundException {
		
		   return new FileInputStream(getBundleResourcePath(symbolicName,fileName));
	}

	/**
	 * 判断资源在改组件中是否存在
	 * 
	 * @param symbolicName
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static boolean isResourceExist(String symbolicName, String fileName) throws FileNotFoundException {
		
		String resourcePath=getBundleResourcePath(symbolicName,fileName);
        return new File(resourcePath).exists();
	}

	/**
	 * 根据属性KEY获取指定组件的属性
	 * 
	 * @param symbolicName
	 * @param propertyKey
	 * @return
	 */
	public static String getBundleProperty(String symbolicName, String propertyKey) {

		if (symbolicName == null || symbolicName.trim().equals("")) {
			throw new CommonException("symbolicName is null in BundleSupport.getBundleProperty");
		}
		if (propertyKey == null || propertyKey.trim().equals("")) {
			throw new CommonException("propertyKey is null in BundleSupport.getBundleProperty");
		}
		Bundle bundle = getNewVersionBundle(symbolicName);
		return getBundleProperty(bundle,propertyKey);

	}
	
	/**
	 * 根据属性KEY获取指定组件的属性
	 * @param bundle
	 * @param propertyKey
	 * @return
	 */
	public static String getBundleProperty(Bundle bundle,String propertyKey){
		if (bundle != null) {
			return bundle.getHeaders().get(propertyKey.trim());
		} else {
			throw new CommonException("bundle is null in BundleSupport.getBundleProperty");
		}
		
	}
	
	private static double apartAdnConvertDigit( String version ){

		String[] digits = version.split( "\\." );

		if( digits.length == 0 ){
			return Integer.parseInt( version );
		}

		StringBuffer buffer = new StringBuffer();

		for( String digit : digits ){
			try{
				Double.parseDouble( digit );
				buffer.append( digit );
			}
			catch( NumberFormatException ex ){
			}
		}

		int fileLength = buffer.toString().length();

		if( fileLength < FILLED_DIGIT ){
			for( int i = 0; i < FILLED_DIGIT - fileLength; i++ ){
				buffer.append( "0" );
			}
		}

		return Double.parseDouble( buffer.toString() );
	}
	
	/**
	 * 组合组件的路径(/basePath/componentName-maxVersion)
	 * @param componentName
	 * @param basePath
	 * @param maxVersion
	 * @return
	 */
	private static String assemblyPath( String componentName, String basePath, String maxVersion ){

		StringBuffer buffer = new StringBuffer();
        buffer.append( basePath );
		buffer.append( File.separator );
		buffer.append( componentName );
		buffer.append( "-" );
		buffer.append( maxVersion );
		buffer.append( File.separator );
		buffer.append( "sofa" );
		buffer.append( File.separator );

		return buffer.toString();
	}
	
	/**
	 * 
	 * 获取系统BundleContext上下文环境
	 * @return
	 */
	public static BundleContext getSystemBundleContext(){
		   return OsgiServiceUtil.getKernelBundleContext();
	}
	
	/**
	 * 
	 * 获取OSGI系统级bundle组件
	 * @return
	 */
	public static synchronized Bundle getSystemBundle(){
		   return getSystemBundleContext().getBundle(0);
	}
	
	/**
	 * 根据bundle的class获取bundle实力
	 * @param clazz
	 * @return
	 */
	public static Bundle getBundle(Class<?> clazz) {
           if(clazz==null){
			   throw new CommonException("clazz is null in SofaEnvoronmentSupport.getBundle");
		   }
		   Bundle bundle=FrameworkUtil.getBundle(clazz);
		   return bundle;
	}
	
	/**
	 * 获取组件的唯一标识
	 * @param clazz
	 * @return
	 */
	public static String getBundleIdentifier(Class<?> clazz) {
	       if(clazz==null){
			   throw new CommonException("clazz is null in SofaEnvoronmentSupport.getBundleIdentifier");
		   }
		   Bundle bundle=getBundle(clazz);
		   return bundle.getSymbolicName()+"-"+bundle.getVersion();
	}
	
	/**
	 * 
	 * 根据bundle-class获取指定组件SymbolName
	 * @param clazz
	 * @return
	 */
	public static String getBundleSymbolName(Class<?> clazz){
		 if(clazz==null){
			   throw new CommonException("clazz is null in SofaEnvoronmentSupport.getBundleSymbolName");
		   }
		   return getBundle(clazz).getSymbolicName();
	}
	
	/**
	 * 
	 * 根据bundle-class获取指定组件SymbolName
	 * @param clazz
	 * @return
	 */
	public static String getBundleVersion(Class<?> clazz){
		if(clazz==null){
			   throw new CommonException("clazz is null in SofaEnvoronmentSupport.getBundleVersion");
		   }
		   return getBundle(clazz).getVersion().toString();
	}
	
	/**
	 * 获取指定组件的部署路径
	 * @param b
	 * @return
	 */
	public static String getBundleLocation(Bundle b) {
		   String path = "";
		   String resource = b.getLocation();
		   path = resource.substring(resource.indexOf("file:") + 6);
		   if (System.getProperty("os.name").toLowerCase().indexOf("windows") < 0) {
			path = "/" + path;
		   }
		   return path;
	      
	}
	
	
	public static String getBundlePath(Bundle b) {

		URL url = b.getEntry("/META-INF");
		URLConverter urlc = BundleUtil.getURLConverter(url);
		String path = "";
		try {
			path = new File(urlc.toFileURL(url).getPath()).getParent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") < 0) {
			path = "/" + path;
		}
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		return path;
	}

    /**
	 * 根据bundle中类Class获取bundle路径
	 * @param clazz
	 * @return
	 */
	public static String getBundlePath(Class<?> clazz) {
		Bundle b = getBundle(clazz);
		String path = (b == null ? null : getBundlePath(b));
        return path;
		
	}
	
	/**
	 * 获得当前线程所在Bundle
	 *@return 当前线程的所在的Bundle
	 */
	public static Bundle getThreadBundle() {

		Bundle bundle = null;

		Class cls = Thread.currentThread().getContextClassLoader().getClass();
		try {
			bundle = (Bundle) cls.getDeclaredMethod("getBundle", null).invoke(Thread.currentThread().getContextClassLoader());
		} catch (Exception e) {
			throw new CommonException("获取当前线程所在组件发生异常",e);
		}
        if (bundle == null) {
			StackTraceElement[] stes = Thread.currentThread().getStackTrace();
			int extenderInd = 0;
			int sofaBundleInd = -1;

			for (int i = 0; i < stes.length; i++) {
				if (stes[i].getClassName().contains("com.lc.sofa.core.framework.extender")) {
					extenderInd = i;
					continue;
				}
				// 过滤掉代理类
				if (stes[i].getClassName().startsWith("com.lc") && !stes[i].getClassName().contains("com.lc.sofa.core.framework")
					&& !stes[i].getClassName().contains("$$") && !stes[i].getClassName().contains("com.lc.sofa.container")) {
					sofaBundleInd = i;
					break;
				}
			}

			Class caller = null;
			int clsIndex = (sofaBundleInd != -1) ? sofaBundleInd : extenderInd;
			try {
				Class reflectionCls = Thread.currentThread().getContextClassLoader().loadClass("sun.reflect.Reflection");
				Method callerMethod = reflectionCls.getDeclaredMethod("getCallerClass", int.class);
				caller = (Class) callerMethod.invoke(reflectionCls, clsIndex);

			} catch (Throwable e) {
				
			}
			bundle = FrameworkUtil.getBundle(caller);
			if (bundle == null) {
				bundle = FrameworkUtil.getBundle(BundleSupport.class);
			}
		}
		return bundle;
	}
	
	/**
	 * 获取Framework组件的路径
	 * @return
	 */
	public static String  getBundlePath(){
		   Bundle bundle=getBundle(BundleSupport.class);
		   String path=getBundlePath(bundle);
		   return path;
	}
	
	/**
	 * 获取当前服务器根目录
	 * @return
	 */
	public static File getServerRoot(){
		  Object obj=OsgiServiceUtil.getOsgiService(ContainerConfig.class);
		  if(obj!=null){
			  ContainerConfig containerConfig=(ContainerConfig)obj;
			  return containerConfig.getSofaContainerHome();
		  }
		  String path=getBundlePath();
		  int index=path.indexOf("work/storage/org.eclipse.osgi/bundles");
		  return new File(path.substring(0, index));
		
	}
		
}
