package com.lc.sofa.core.framework.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 数组工具类，添加jdk1.6中的数组复制方法copyOfRange
 * 注意:JDK1.5无此方法
 * @author       YZ
 * @version 1.0, 2014-1-2
 * @since 1.0, 2014-1-2
 */
public final class ArraysUtil {

	public static <T> T[] copyOfRange(T[] original, int from, int to) {

		return copyOfRange(original, from, to, (Class<T[]>) original.getClass());
	}

	public static <T, U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType) {

		int newLength = to - from;
		if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
		@SuppressWarnings("unchecked")
		T[] copy =
			((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength]
					: (T[]) Array.newInstance(newType.getComponentType(), newLength);
		System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
		return copy;
	}
	
	/**
	 * 将列表中的对象的某个属性以分隔符连接起来
	 * @param list
	 * @param propertyName
	 * @param seperator
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static String concatListProperty(List list,String propertyName,String seperator){
		if(list==null || list.size()==0)return null;
		StringBuffer sb= new StringBuffer();
		for(Object obj:list){
			try {
				Object value = ClassUtil.forceGetProperty(obj, propertyName);
				if(value!=null && value.toString().length()>0)
					sb.append(value.toString()+seperator);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			
		}
		return sb.toString().substring(0,sb.length()-1);
	}
	
	/**
	 * 批量设置List中某个属性的值
	 * @param list
	 * @param propertyName
	 * @param value
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static void setListProperty(List list,String propertyName,Object value){
		if(list==null || list.size()==0)return;
		StringBuffer sb= new StringBuffer();
		for(Object obj:list){
			try {
				ClassUtil.forceSetProperty(obj, propertyName, value);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Array转成List
	 * @param array
	 * @return
	 */
	public static List ArrayToList(Object[] array){
		if(array==null || array.length==0)return null;
		List list = new ArrayList();
		for(Object obj:array){
			list.add(obj);
		}
		return list;
	}
	
	/**
	 * Array转成List
	 * @param array
	 * @return
	 */
	public static Object[] listToArray(List list){
		if(list==null || list.size()==0)return null;
		Object[] array = new Object[list.size()];
		for(int i=0;i<list.size();i++){
			list.add(array[i]);
		}
		return array;
	}
}
