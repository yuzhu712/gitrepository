package com.lc.sofa.core.framework.util.json;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.lc.sofa.core.framework.util.json.JsonWraperException;
import com.lc.sofa.core.framework.util.CnToSpell;

/**
 * 属性描述.
 * 
 * @author   YZ
 */
public class PropertyDecorator {

	private Map<String, Object> addProperties;
	private Map<String, String> addPinYinProperties;
	private List<String> removeProperties;
	private Map<String, Map<String, Object>> referencedObjMaps;

	public PropertyDecorator() {
		this.addProperties = new HashMap<String, Object>();
		this.addPinYinProperties = new HashMap<String, String>();
		this.removeProperties = new ArrayList<String>();
		this.referencedObjMaps = new HashMap<String, Map<String, Object>>();
	}

	public PropertyDecorator addProperty(String key, Object value) {
		this.addProperties.put(key, value);
		return this;
	}

	public PropertyDecorator addPinYinProperty(String key, String value) {
		// value=CnToSpell.getFullSpell(value);
		this.addPinYinProperties.put(key, value);
		return this;
	}

	public PropertyDecorator removeProperty(String key) {
		this.removeProperties.add(key);
		return this;
	}

	/**
	 * 添加集合引用关系
	 * 
	 * @param referenceAttr
	 *            引用属性名称
	 * @param referencedObjs
	 *            被引用属性集合
	 * @param referencedObjAttr
	 *            被引用属性名称
	 * @return
	 * @throws SOFARuntimeException
	 */
	public PropertyDecorator addReference(String referenceAttr,
			List<Object> referencedObjs, String referencedObjAttr)
			throws JsonWraperException {
		try {
			Map<String, Object> referencedObjMap = new HashMap<String, Object>();
			for (Object referencedObj : referencedObjs) {
				PropertyDescriptor propertyDescriptor = BeanUtils
						.getPropertyDescriptor(referencedObj.getClass(),
								referencedObjAttr);
				if (propertyDescriptor.getPropertyType() == Boolean.class) {
					propertyDescriptor = new PropertyDescriptor(
							propertyDescriptor.getName(),
							referencedObj.getClass());
				}
				Method readMethod = propertyDescriptor.getReadMethod();
				// 通过反射获取值
				String attrValue = (String) readMethod.invoke(referencedObj);
				referencedObjMap.put(attrValue, referencedObj);
			}
			this.referencedObjMaps.put(referenceAttr, referencedObjMap);
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}
		return this;
	}

	/**
	 * 将Vo对象转换成map对象
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	void convertMap(Object obj, Map<String, Object> map) throws Exception {

		if (obj != null && !Map.class.isInstance(obj)) {

			for (PropertyDescriptor propertyDescriptor : BeanUtils
					.getPropertyDescriptors(obj.getClass())) {
				String propertyName = propertyDescriptor.getName();
				if (propertyDescriptor.getPropertyType() == Boolean.class) {
					propertyDescriptor = new PropertyDescriptor(
							propertyDescriptor.getName(), obj.getClass());
				}
				// 获取属性的get方法
				Method readMethod = propertyDescriptor.getReadMethod();
				// 判定get方法是否为public
				if (readMethod != null
						&& readMethod.getModifiers() == Modifier.PUBLIC) {
					Object value = readMethod.invoke(obj);
					// 忽略空值
					if (value != null) {
						map.put(propertyName, value);
					}
				}

			}
		}
	}

	void updatePinYin(Object obj, Map<String, Object> map) throws Exception {

		if (obj != null && !Map.class.isInstance(obj)) {

			for (PropertyDescriptor propertyDescriptor : BeanUtils
					.getPropertyDescriptors(obj.getClass())) {
				String propertyName = propertyDescriptor.getName();

				if (this.addPinYinProperties.containsKey(propertyName)) {
					// 获取属性的get方法
					Method readMethod = propertyDescriptor.getReadMethod();
					// 判定get方法是否为public
					if (readMethod.getModifiers() == Modifier.PUBLIC) {
						Object value = readMethod.invoke(obj);
						// 忽略空值
						if (value != null) {
							map.put(propertyName, value);
							String py = this.addPinYinProperties
									.get(propertyName);
							if (py == null || py.equalsIgnoreCase("")) {
								this.addPinYinProperties.put(propertyName,
										(String) value);
							}
						}
					}
				}
			}
		}
	}

	void operation(Map<String, Object> properties) {
		// TODO Auto-generated method stub
		// 添加属性
		properties.putAll(this.addProperties);
		// 删除属性

		for (String removeProperty : this.removeProperties) {
			properties.remove(removeProperty);
		}
		// 添加拼音属性
		for (String key : this.addPinYinProperties.keySet()) {
			String value = this.addPinYinProperties.get(key);
			if (value != null) {
				value = CnToSpell.getFullSpell(value);
				this.addPinYinProperties.put(key, value);
			}
		}
		properties.putAll(this.addPinYinProperties);

		// 添加引用集合
		for (String key : this.referencedObjMaps.keySet()) {
			Map<String, Object> referencedObjMap = this.referencedObjMaps
					.get(key);
			if (!referencedObjMap.isEmpty()) {
				Object attrValue = properties.get(key);
				Object referencedObj = referencedObjMap.get(attrValue);
				properties.put(key, referencedObj);
			}
		}

	}
}
