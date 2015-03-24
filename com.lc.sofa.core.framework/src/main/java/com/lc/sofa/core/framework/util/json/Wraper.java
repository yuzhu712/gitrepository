package com.lc.sofa.core.framework.util.json;

import java.util.HashMap;

import com.lc.sofa.core.framework.util.json.JsonWraperException;;

@SuppressWarnings("unchecked")
public class Wraper<T> extends HashMap{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6472965699910721152L;

	private PropertyDecorator decorator;
	
	private T obj;
	public Wraper(T obj) throws JsonWraperException
	{
		this.obj=obj;
		
	}
/**
 * 根据decorator的值设置对象的属性
 * @param decorator
 */
	public void setDecorator(PropertyDecorator decorator)throws JsonWraperException {
		
		try {
			this.decorator = decorator;
			this.decorator.convertMap(this.obj,this);
			this.decorator.updatePinYin(this.obj,this);
			this.decorator.operation(this);
		} catch (Exception e) {
			throw new JsonWraperException(e);
		}
		
	}
	

	
	
}
