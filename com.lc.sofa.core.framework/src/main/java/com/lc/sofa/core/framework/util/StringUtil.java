package com.lc.sofa.core.framework.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 字符串操作工具类
 * @author  YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class StringUtil extends StringUtils{
	
	public static boolean stringContains(String str,String value,String seperator){
		if(isEmpty(str))return false;
		String[] arr = str.split(seperator);
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals(value))
				return true;
		}
		return false;
	}

}
