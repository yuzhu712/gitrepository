package com.lc.sofa.core.framework.support.sql;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * SQL数据值类型转换支持类
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public class SQLHelper {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static TypeValue getTypeValue(Object value){
		if(value==null)
		   return new TypeValue("String",value);
		if(value instanceof String)
			return new TypeValue("String",value);
		if(value instanceof Date)
			return new TypeValue("Date",value);
		if(value instanceof Integer)
			return new TypeValue("Integer",value);
		if(value instanceof Float)
			return new TypeValue("Float",value);
		if(value instanceof Double){
			return new TypeValue("Double",value);
		}
		return null;
	}
	
	public static String getTranslateValue(Object value){
		if(value==null)
			return "null";
		if(value instanceof String)
			return "'"+StringUtils.replace(value.toString(), "'", "''")+"'";
		if(value instanceof Date){
			return "to_date('"+format.format((Date)value)+"','yyyy-mm-dd hh24:mi:ss')";    //to_date仅限于oracle
		}
		return value.toString();
	}
    
    /**
     * 将以分隔符分隔的字符串转成标准SQL语句
     * @param str 字符串
     * @param seperator 分隔符
     * @return Map对象
     */
    public static String strToSqlIn(String str,String seperator) {
    	if(StringUtils.isEmpty(str))
    		return "";
    	String[] arr = str.split(seperator);
    	StringBuffer sb = new StringBuffer("(");
    	for(int i=0;i<arr.length;i++){
    		sb.append("'").append(arr[i]).append("'");
    		if(i<arr.length-1)
    			sb.append(",");
    	}
        return sb.append(")").toString();
    }

}
