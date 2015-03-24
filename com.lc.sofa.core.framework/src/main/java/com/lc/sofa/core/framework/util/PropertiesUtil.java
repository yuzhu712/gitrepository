package com.lc.sofa.core.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.lc.sofa.core.framework.basis.exception.FileOperationException;

/**
 * 
 * 属性文件操作工具类
 * @author     YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class PropertiesUtil {

	
	 private String     filePath;
	    private Properties objProperties; // 属性对象

	    /**
	     * 构造函数
	     * 
	     * @desc 加载属性资源文件
	     * @param filePath
	     *            文件路径
	     * @throws SOFAException
	     *             异常
	     */
	    public PropertiesUtil(String filePath) throws FileOperationException {

		this.filePath = filePath;
		File file = new File(filePath);
		FileInputStream inStream = null;
		try {
		    inStream = new FileInputStream(file);
		    objProperties = new Properties();
		    objProperties.load(inStream);
		} catch (Exception e) {
		    throw new FileOperationException(e);
		} finally {
		    try {
			inStream.close();
		    } catch (IOException e) {
			throw new FileOperationException(e);
		    }
		}
	    }

	    /**
	     * 持久化属性文件
	     * 
	     * @desc 使用setValue()方法后，必须调用此方法才能将属性持久化到存储文件中
	     * @param desc
	     *            描述
	     * @throws Exception
	     *             异常
	     */
	    public void savefile(String desc) throws Exception {

		FileOutputStream outStream = null;
		try {
		    File file = new File(filePath);
		    outStream = new FileOutputStream(file);
		    objProperties.store(outStream, desc);// 保存属性文件
		} catch (Exception e) {

		    e.printStackTrace();
		    throw e;
		} finally {
		    outStream.close();
		}

	    }

	    /**
	     * 获取属性值
	     * 
	     * @desc 指定Key值，获取value
	     * @param key
	     *            Key
	     * @return String
	     */
	    public String getValue(String key) {

		return objProperties.getProperty(key);
	    }

	    /**
	     * 获取属性值,支持缺省设置
	     * 
	     * @desc 重载getValue()方法；指定Key值，获取value并支持缺省值
	     * @param key
	     *            Key
	     * @param defaultValue
	     *            默认值
	     * @return String 值
	     */
	    public String getValue(String key, String defaultValue) {

		return objProperties.getProperty(key, defaultValue);
	    }

	    /**
	     * 删除属性
	     * 
	     * @desc 根据Key,删除属性
	     * @param key
	     *            Key
	     * @return
	     */
	    public void removeValue(String key) {

		objProperties.remove(key);
	    }

	    /**
	     * 设置属性
	     * 
	     * @param key
	     *            Key
	     * @param value
	     *            Value
	     * @return
	     */
	    public void setValue(String key, String value) {

		objProperties.setProperty(key, value);
	    }

	    /**
	     * 打印所有属性值
	     */
	    public void printAllVlue() {

		objProperties.list(System.out);
	    }
	    
	    /**
	     * 获取指定properties的Int值,没有返回默认值
	     * @param prop
	     * @param key
	     * @param defaultValue
	     * @return
	     */
	    public static int getPropertyIntVal(Properties prop, String key, int defaultValue) {

			String val = prop.getProperty(key);
			if (val == null || val.trim().equals("")) {
				return defaultValue;
			}
			return Integer.parseInt(val.trim());
		}

	    /**
	     * 获取指定properties的boolean值,没有返回默认值
	     * @param prop
	     * @param key
	     * @param defaultValue
	     * @return
	     */
	    public static boolean getPropertyBooleanVal(Properties prop, String key, boolean defaultValue) {

			String val = prop.getProperty(key);
			if (val == null || val.trim().equals("")) {
				return defaultValue;
			}
			return Boolean.parseBoolean(val.trim());
		}
	    
	    /**
	     * 获取指定properties的string值,没有返回默认值
	     * @param prop
	     * @param key
	     * @param defaultValue
	     * @return
	     */
	    public static String getPropertyStringVal(Properties prop, String key, String defaultValue){
	    	String val = prop.getProperty(key);
			if (val == null || val.trim().equals("")) {
				return defaultValue;
			}
	    	
	    	   return val.trim();
	    }
	    
}
