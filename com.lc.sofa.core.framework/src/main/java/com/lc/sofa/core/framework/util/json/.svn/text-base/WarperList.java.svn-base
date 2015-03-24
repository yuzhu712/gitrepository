package com.lc.sofa.core.framework.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lc.sofa.core.framework.util.json.JsonWraperException;

@SuppressWarnings("unchecked")
public class WarperList<T> extends ArrayList<Map>{

/**
	 * 
	 */
	private static final long serialVersionUID = -6955419202165001917L;

private PropertyDecorator decorator;
	
	private List<T> objList;
	public WarperList(List<T> objList) throws JsonWraperException
	{
		this.objList=objList;
		
	}
/**
 * 根据decorator的值设置对象的属性
 * @param decorator
 */
	public void setDecrator(PropertyDecorator decorator)throws JsonWraperException {
		
		try {
			this.decorator = decorator;
			if(this.objList!=null&&!this.objList.isEmpty())
			{
				for(T obj:this.objList)
				{
					Map<String,Object> map=new HashMap<String,Object>();
					this.decorator.convertMap(obj, map);
					this.decorator.updatePinYin(obj, map);
					this.decorator.operation(map);
					this.add(map);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JsonWraperException(e);
		}
		
	}
	
//     public void setReference(String referenceAttr, List<Object> referencedObjs, String referencedObjAttr)throws SOFARuntimeException {
//		
//    	try
//    	{
//    		Map<String, Object> referencedObjMap = new HashMap<String, Object>();
//    		for(Object referencedObj : referencedObjs){
//    			PropertyDescriptor propertyDescriptor=BeanUtils.getPropertyDescriptor(referencedObj.getClass(), referencedObjAttr);
//    			Method readMethod=propertyDescriptor.getReadMethod();
//    			//通过反射获取值
//    			String attrValue = (String) readMethod.invoke(referencedObj);
//    			referencedObjMap.put(attrValue, referencedObj);
//    		}
//    		
//    		for(Object referenceObj : objList){
//    			PropertyDescriptor propertyDescriptor=BeanUtils.getPropertyDescriptor(referenceObj.getClass(), referenceAttr);
//    			Method readMethod=propertyDescriptor.getReadMethod();
//    			//通过反射获取值
//    			String attrValue = (String) readMethod.invoke(referenceObj);
//    			Object referencedObj=referencedObjMap.get(attrValue);
//    			
//    			Wraper<Object> wraper = new Wraper<Object>(referenceObj);
//    			
//    			wraper.setDecorator(new PropertyDecorator().addProperty(referenceAttr, referencedObj));
//    			
//    			this.add(wraper);
//    		}
//    	}catch(Exception ex)
//    	{
//    		throw new SOFARuntimeException(ex);
//    	}
//	}
	
}
