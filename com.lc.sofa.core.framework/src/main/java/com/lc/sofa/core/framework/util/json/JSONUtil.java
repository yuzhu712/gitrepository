package com.lc.sofa.core.framework.util.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.map.type.TypeFactory;

import com.lc.sofa.core.framework.util.json.JsonWraperException;
import com.lc.sofa.core.framework.util.json.PropertyDecorator;
import com.lc.sofa.core.framework.util.json.WarperList;
import com.lc.sofa.core.framework.util.json.Wraper;


/**
 * JSON 工具
 * 
 * @see
 * @author  YZ
 *
 */
public class JSONUtil {

	/* JACKSON中对象,用来java与jackson之间的转换 */
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.setDeserializationConfig(mapper.getDeserializationConfig()
				.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		mapper.setSerializationConfig(mapper.getSerializationConfig()
				.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		/**
		 * 启用包含unquotes控制字符（ASCII代码32以下，包括选项卡，换行）;
		 */
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/**
		 * 启用单引号"'"
		 */
//		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		
		/**
		 * 启用键值可以不用""号
		 */
//		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
	
	private static ObjectMapper mapperNumbAsString;
	static {
		mapperNumbAsString = new ObjectMapper();
		mapperNumbAsString.setDeserializationConfig(mapperNumbAsString.getDeserializationConfig()
				.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		mapperNumbAsString.setSerializationConfig(mapperNumbAsString.getSerializationConfig()
				.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		/**
		 * 启用包含unquotes控制字符（ASCII代码32以下，包括选项卡，换行）;
		 */
		mapperNumbAsString.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		// 所有的数字类型都加上引号，防止前台数字精度问题
		mapperNumbAsString.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		mapperNumbAsString.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true); 
		
		mapperNumbAsString.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/**
		 * 启用单引号"'"
		 */
//		mapperNumbAsString.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		
		/**
		 * 启用键值可以不用""号
		 */
//		mapperNumbAsString.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
	
	private static String EXCLUSION_FILTER_NAME = "defaultExclusionFilter";

	/**
	 * 将JAVA对象包装成JSON格式.
	 * 
	 * @param obj
	 *            要转换为json格式的对象
	 * @return 转换后的JSON字符串
	 * @author lidaolong
	 * @throws SOFARuntimeException
	 *             异常
	 */
	public static String toJson(Object obj) throws JsonWraperException {

		String jsonStr = "";
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}
		return jsonStr;

	}
	
	public static String toJsonWithNumberAsString(Object obj) throws JsonWraperException {

		String jsonStr = "";
		try {
			jsonStr = mapperNumbAsString.writeValueAsString(obj);
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}
		return jsonStr;

	}
	@SuppressWarnings("unchecked")
	public static String toJson(Object obj,PropertyDecorator decorator) throws JsonWraperException {
		
		if(obj instanceof List){
			WarperList<?> wraperList = new WarperList((List) obj);
			wraperList.setDecrator(decorator);
			return JSONUtil.toJson(wraperList);
		}
		else{
			Wraper<?> wraper = new Wraper(obj);
			wraper.setDecorator(decorator);
			return JSONUtil.toJson(wraper);
		}
	}
    
	
	/**
	 * 将JAVA对象包装成JSON格式，并隐藏指定的字段。 注意：无法隐藏关联对象中的字段
	 * 
	 * @param vo
	 *            要转换的ValueObject对象
	 * @param hidePropertys
	 *            要隐藏的字段
	 * @return 转换后的JSON字符串
	 * @throws SOFARuntimeException
	 *             SOFA 运行时异常
	 * @author jiangjin 异常
	 */
	@Deprecated
	public static String toJson(Object vo, String... hidePropertys)
			throws JsonWraperException {

		String json = ""; // 转换后的字符串

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationConfig(objectMapper
				.getSerializationConfig().withDateFormat(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));

		FilterProvider filter = new SimpleFilterProvider().addFilter(
				EXCLUSION_FILTER_NAME, SimpleBeanPropertyFilter
						.serializeAllExcept(hidePropertys));

		objectMapper.setFilters(filter);
		objectMapper.getSerializationConfig().addMixInAnnotations(
				vo.getClass(), MixIn.class);

		try {
			json = objectMapper.writeValueAsString(vo);
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}

		return json;
	}

	/**
	 * 将VO集合转换为JSON串，并隐藏指定的字段。 注意：无法隐藏关联对象中的字段
	 * 
	 * @param voList
	 *            VO集合
	 * @param hidePropertys
	 *            集合中的VO需要隐藏的字段
	 * @return JSON字符串
	 * @throws SOFARuntimeException
	 *             SOFA运行时异常
	 */
	@Deprecated
	public static String toJson(List<?> voList,
			String... hidePropertys) throws JsonWraperException {

		String json = ""; // 转换后的字符串

		if (voList.size() == 0) {
			return json;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationConfig(objectMapper
				.getSerializationConfig().withDateFormat(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		FilterProvider filter = new SimpleFilterProvider().addFilter(
				EXCLUSION_FILTER_NAME, SimpleBeanPropertyFilter
						.serializeAllExcept(hidePropertys));

		objectMapper.setFilters(filter);
		objectMapper.getSerializationConfig().addMixInAnnotations(
				voList.get(0).getClass(), MixIn.class);

		try {
			json = objectMapper.writeValueAsString(voList);
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}

		return json;
	}

	/**
	 * 将JAVA对象包装成JSON格式.
	 * 
	 * @param jsonString
	 *            Json格式字符串
	 * @param beanClass
	 *            要转换的Java对象类型
	 * @return 转换后的Java对象
	 * @author lidaolong
	 * @throws SOFARuntimeException
	 *             异常
	 */
	public static Object toObject(String jsonString, Class<?> beanClass)
			throws JsonWraperException {

		Object obj = "";
		try {
			obj = mapper.readValue(jsonString, beanClass);
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}
		return obj;
	}

	public static Object toObjectWithNumStr2Mum(String jsonString, Class<?> beanClass){
		//mapperNumbAsString
		Object obj = "";
		try {
			obj = mapperNumbAsString.readValue(jsonString, beanClass);
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}
		return obj;
	}
	/**
	 * 将 JSON 数组转换为List
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param beanClass
	 *            实体类型
	 * @return 储存类型为 beanClass的对象List
	 */
	public static List<?> toList(String jsonString, Class<?> beanClass) {
		List<?> list = null;
		TypeFactory factory = TypeFactory.defaultInstance();
		try {
			list = mapper.readValue(jsonString, factory
					.constructCollectionType(ArrayList.class, beanClass));
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}
		return list;

	}

	public static List<?> toListWithNumStr2Mum(String jsonString, Class<?> beanClass) {
		List<?> list = null;
		TypeFactory factory = TypeFactory.defaultInstance();
		try {
			list = mapperNumbAsString.readValue(jsonString, factory
					.constructCollectionType(ArrayList.class, beanClass));
		} catch (Exception ex) {
			throw new JsonWraperException(ex);
		}
		return list;

	}
	
	/**
	 * jackson 混合注解接口
	 * 
	 * @see
	 * @author jiangjin
	 * @version 1.0, 2011-5-10
	 * @since 1.0, 2011-5-10
	 */
	@JsonFilter("defaultExclusionFilter")
	interface MixIn {
	}

	//test
	public static void main(String[] args) {
		try {
			/*User user = new User();
			user.setCode("aaa");
			user.setId("111");
			user.setName("Orlando");
			user.setChecked(true);
			user.setExpiryDate(new Date(System.currentTimeMillis()));
			user.setEffectiveDate(new Date(System.currentTimeMillis()));*/
//			String ids = JSONUtil.toJson(new String[]{"2","3"});
//			
//			JSONUtil.toObject(ids, String[].class);
//			
			//String s = JSONUtil.toJson(user);
			
			//s = JSONUtil.toJson(user,new PropertyDecorator().addPinYinProperty("pinyin", "pinyin"));
			//System.out.print(s);
			
			
			System.out.print("");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
