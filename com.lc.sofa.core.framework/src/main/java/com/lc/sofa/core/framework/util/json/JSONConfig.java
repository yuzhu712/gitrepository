package com.lc.sofa.core.framework.util.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Json转换数据格式配置类.
 * 
 * @author  YZ
 *
 */
public class JSONConfig {

    /* 保存属性名及其格式化信息 */
    private Map<String, String> map = new HashMap<String, String>();

    /**
     * 将属性名及其格式化表达式保存到map. 备注:为数值时,表达式格式参照JDK中的格式,详见 DecimalFormat类
     * 
     * @param property
     *            要格式化的属性名
     * @param format
     *            格式化表达式
     * @author lidaolong
     */
    public void addPropertyFormat(String property, String format) {

	map.put(property, format);
    }

    /**
     * 通过property在map中获取该属性对应的格式化表达式.
     * 
     * @param property
     *            要格式化的属性名
     * @return 性对应的格式化表达式
     * @author lidaolong
     */
    public String getFormat(String property) {

	return map.get(property);

    }
}
