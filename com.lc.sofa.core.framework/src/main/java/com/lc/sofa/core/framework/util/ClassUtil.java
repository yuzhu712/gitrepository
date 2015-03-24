package com.lc.sofa.core.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;
import java.lang.NoSuchFieldException;
import java.lang.NoSuchMethodException;
import java.lang.NullPointerException;
// import org.apache.commons.lang.StringUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.Assert;

/**
 * Class处理工具类
 * 
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class ClassUtil {

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @param obj
	 *            -对象实体,propertyName-对象属性名称
	 * @throws NoSuchFieldException
	 *             若没有该field时抛出
	 */
	public static Field getDeclaredFieldByPropertyName(Object obj, String propertyName) throws NoSuchFieldException {

		Assert.notNull(obj);
		Assert.hasText(propertyName);
		return getDeclaredFieldByPropertyName(obj.getClass(), propertyName);

	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @param
	 * @return
	 * @throws NoSuchFieldException
	 *             若没有该field时抛出
	 */
	public static Field getDeclaredFieldByPropertyName(Class clazz, String propertyName) throws NoSuchFieldException {

		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}

		}
		throw new NoSuchFieldException("NO SUCH FIELD " + clazz.getName() + "." + propertyName);
	}
	
	/**
	 * 循环向上转型,判断对象是否具有某个属性
	 * @param
	 * @return
	 */
	public static boolean isExistProperty(Object obj, String propertyName){
		try {
			Field field = getDeclaredFieldByPropertyName(obj,propertyName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 循环向上转型,判断对象是否具有某个属性
	 * @param
	 * @return
	 */
	public static boolean isExistProperty(Class clazz, String propertyName){
		try {
			Field field = getDeclaredFieldByPropertyName(clazz,propertyName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 强制获取对象的属性值,忽略private,ptotected的限制
	 * 
	 * @throws NoSuchFieldException
	 *             若没有该field时抛出
	 */
	public static Object forceGetProperty(Object obj, String propertyName) throws NoSuchFieldException {

		Field field = getDeclaredFieldByPropertyName(obj, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			throw new NoSuchFieldException(e.getMessage());
		}
		field.setAccessible(accessible);
		return result;
	}

	/**
	 * 强制设置对象的属性值,忽略private,ptotected的限制
	 * 
	 * @throws NoSuchFieldException
	 *             若没有该field时抛出
	 */
	public static void forceSetProperty(Object obj, String propertyName, Object value) throws NoSuchFieldException {

		Assert.notNull(obj);
		Assert.hasText(propertyName);
		Field field = getDeclaredFieldByPropertyName(obj, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			throw new NoSuchFieldException(e.getMessage());
		}
		field.setAccessible(accessible);
	}

	/**
	 * @param obj
	 *            -对象事例,methodName-要执行方法名,params-方法执行的参数
	 * @return Object-该方法执行的返回值
	 * @throws NoSuchMethodException
	 *             该方法不存在时抛出此异常
	 *             强制调用对象方法,忽略private,ptotected的限制
	 */
	public static Object invokeMethod(Object obj, String methodName, Object... params) throws NoSuchMethodException {

		Assert.notNull(obj);
		Assert.hasText(methodName);
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		Class clazz = obj.getClass();
		Method method = null;
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {
				throw e;
			}
		}
		if (method == null) {
			throw new NoSuchMethodException("No Such Method:" + clazz.getSimpleName() + methodName);
		}
		boolean isAccessable = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(obj, params);
		} catch (Exception e) {
			throw new NoSuchMethodException(e.getMessage());
		}
		method.setAccessible(isAccessable);
		return result;

	}

	/**
	 * @param
	 * @return
	 *         按Field类型取得Field列表
	 */
	public static List<Field> getFieldByType(Object obj, Class fieldType) {

		Assert.notNull(obj);
		Assert.notNull(fieldType);
		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getType().isAssignableFrom(fieldType)) {
				fieldList.add(fields[i]);
			}
		}
		return fieldList;
	}

	/**
	 * @param
	 * @return
	 *         根据字段名称获得Fileld类型
	 */
	public static Class getPropertyType(Class clazz, String propertyName) throws NoSuchFieldException {

		return getDeclaredFieldByPropertyName(clazz, propertyName).getType();
	}

	/**
	 * @param
	 * @return
	 *         获得Field的get方法名称
	 */
	public static String getGetterName(Class fieldType, String fieldName) {

		Assert.notNull(fieldType);
		Assert.hasText(fieldName);
		if (fieldType.getName().equals("boolean")) {
			return "is" + StringUtils.capitalize(fieldName);
		} else {
			return "get" + StringUtils.capitalize(fieldName);
		}

	}

	/**
	 * @param
	 * @return
	 *         获得field的getter方法,如果找不到该方法,返回null.
	 */
	public static Method getGetterMethod(Class clazz, String fieldName) throws NoSuchFieldException, NoSuchMethodException {

		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		Method method = null;
		try {
			Class fieldType = getPropertyType(clazz, fieldName);
			method = clazz.getMethod(getGetterName(fieldType, fieldName));
		} catch (NoSuchMethodException e) {
			throw e;
		}
		return method;
	}

	/**
	 * @param
	 * @return
	 *         获得Field的set方法名称
	 */
	public static String getSetterName(Class fieldType, String fieldName) {

		Assert.notNull(fieldType);
		Assert.hasText(fieldName);
		if (fieldType.getName().equals("boolean")) {
			return "is" + StringUtils.capitalize(fieldName);
		} else {
			return "set" + StringUtils.capitalize(fieldName);
		}
	}

	/**
	 * @param
	 * @return
	 *         获得field的setter方法,如果找不到该方法,返回null.
	 */

	public static Method getSetterMethod(Class clazz, String fieldName) throws NoSuchFieldException {

		Method method = null;
		try {
			Class fieldType = getPropertyType(clazz, fieldName);
			method = clazz.getMethod(getSetterName(fieldType, fieldName));
		} catch (NoSuchMethodException e) {
			throw new NoSuchFieldException(e.getMessage());
		}
		return null;

	}

	/**
	 * 判断一个类或接口是否是指定的一个类型。
	 * 
	 * @param cls
	 *            带检查的类或接口
	 * @param findClass
	 *            目标类或接口
	 * @return
	 */
	public static boolean isInheritClass(Class cls, String findClass) {

		if (cls == null) {
			return false;
		}
		if (cls.getCanonicalName().equals(findClass)) {
			return true;
		}
		Class[] inters = cls.getInterfaces();
		for (Class intec : inters) {
			if (intec.getCanonicalName().equals(findClass)) {
				return true;
			} else {
				boolean isFind = isInheritClass(intec, findClass);
				if (isFind) {
					return true;
				}
			}
		}
		if (cls.getSuperclass() == null || cls.getSuperclass().getCanonicalName().equals(Object.class.getCanonicalName())) {
			return false;
		} else {
			return isInheritClass(cls.getSuperclass(), findClass);
		}
	}

	/**
	 * 根据Class判断是否为基本数据类型
	 * @param clazz
	 * @return
	 */
	public static boolean isPrimitiveType(Class clazz) {

		if (clazz == null) {
			throw new NullPointerException("传入的Class类型为空");
		}
		if (String.class == clazz) {
			return true;

		} else if (Integer.class == clazz) {
			return true;

		} else if (Long.class == clazz) {
			return true;

		} else if (Float.class == clazz ) {
			return true;
			
		}else if (Double.class == clazz) {
			return true;
			
		}else if(Date.class==clazz){
			return true;
		}
        return false;
	}
	
    /**
     * 将javaBean转换成Map
     * @param Object bean
     * @return Map对象
     */
    public static Map<String, Object> beanToMap(Object bean) {
    	 Map<String, Object> map = new HashMap<String, Object>();
		try {
            if(bean == null)return map;
    		Field[] fields = bean.getClass().getDeclaredFields();
    		for (int i = 0; i < fields.length; i++) {
    			fields[i].setAccessible(true);
    			String name = fields[i].getName();
    			Object value = fields[i].get(bean);
				if(value instanceof String){
					value = value.toString().trim();
				}
    			if(value!=null && value.toString()!="")
    				map.put(name, value);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return map;
    }
    
    /**
     * 将Map转换成javaBean
     * @param map Map对象
     * @param clazz bean的class类型
     * @return Object bean
     */
    public static Object mapToBean(Map<String,Object> map,Class clazz) {
    	Object bean = null;
		try {
        	bean = clazz.newInstance();
            if(map == null)return bean;
    		Field[] fields = bean.getClass().getDeclaredFields();
    		for (int i = 0; i < fields.length; i++) {
    			fields[i].setAccessible(true);
    			String name = fields[i].getName();
    			Object value  = map.get(name);
				if(value instanceof String){
					value = value.toString().trim();
				}
				if(value!=null && value.toString()!="")
					fields[i].set(bean, value);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
    }

}
