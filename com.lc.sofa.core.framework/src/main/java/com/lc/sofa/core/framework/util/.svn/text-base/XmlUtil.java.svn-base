package com.lc.sofa.core.framework.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import com.lc.sofa.core.framework.util.json.JSONUtil;
import  com.lc.sofa.core.framework.basis.exception.SofaException;
import  com.lc.sofa.core.framework.basis.exception.FileOperationException;

/**
 * 
 * XML文件操作工具类
 * @author  YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class XmlUtil {

	/**
	 * 加载XML文件
	 * 
	 * @param xmlPath
	 *            文件路径
	 * @return DOM4j Document
	 * @throws DocumentException
	 *             异常
	 *
	 */
	public static Document loadXmlFile(String xmlPath) throws FileOperationException {

		try {
			File f = new File(xmlPath);
			if (!f.exists()) {
				throw new FileNotFoundException("资源[" + xmlPath + "]不存在");
			}
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(f);

			return doc;

		} catch (DocumentException e) {
			throw new FileOperationException("资源[" + xmlPath + "]加载出错");
		} catch (FileNotFoundException e) {
			throw new FileOperationException(e);
		} catch (Exception ex) {
			throw new FileOperationException(ex);
		}

	}

	/**
	 * 保存文件.
	 * 
	 * @param xmlPath
	 *            xml文件路径
	 * @param document
	 *            document对象
	 * @return 保存成功返回true,保存失败返回false
	 * @author ldl
	 * @throws IOException
	 *             文件操作异常
	 */
	public static boolean saveXml(String xmlPath, Document document)
			throws IOException {

		// 1）设置document的字符编码及数据格式。
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		format.setLineSeparator("\r\n");

		// 2）将document对象保存到xmlpath文件中。
		XMLWriter writer = new XMLWriter(new FileWriter(xmlPath), format);
		writer.write(document);
		writer.close();

		return true;

	}

	/**
	 * 将content数据保存到xmlpath文件中.
	 * 
	 * @param xmlPath
	 *            xml的文件路径
	 * @param content
	 *            xml格式的字符串数据
	 * @throws SOFAException
	 *             SOFA异常
	 *
	 */
	public static void saveXml(String xmlPath, String content)
			throws FileOperationException {

		// 1）设置document的字符编码及数据格式。
		try {

			SAXReader saxReader = new SAXReader();
			Document document;
			document = saxReader.read(new ByteArrayInputStream(content
					.getBytes()));

			OutputFormat format = OutputFormat.createPrettyPrint();
			/** 指定XML字符集编码 */
			format.setEncoding("utf-8");
			/** 将document中的内容写入文件中 */
			XMLWriter writer = new XMLWriter(new FileWriter(new File(xmlPath)),
					format);
			writer.write(document);
			writer.close();

		} catch (Exception ex) {
			throw new FileOperationException(ex);
		}
	}

	/**
	 * 加载xml格式字符串.
	 * 
	 * @param xml
	 *            xml格式的字符串
	 * @return document对象
	 *
	 * @throws DocumentException
	 */
	public static Document loadXml(String xml) {

		// 1） 将xml内容加载到org.dom4j. Document对象。
		// 2） 返回org.dom4j. Document对象
		Document doc;
		try {
			SAXReader reader = new SAXReader();
			doc = reader.read(new ByteArrayInputStream(xml.getBytes()));
			return doc;
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * 封装对象.
	 * 
	 * @param xmlFile
	 *            xml文件
	 * @param nodeName
	 *            xml文件节点
	 * @param cls
	 *            转换的目标类
	 * @return 封装nodeName节点信息的对象
	 * @throws SOFAException
	 *             SOFA异常
	 *
	 */
	@SuppressWarnings("unchecked")
	public static Object getBeanForXml(String xmlFile, String nodeName,
			Class<?> cls) throws FileOperationException {

		Object obj = null;
		Map attributeMap = new HashMap();// key:属性名,value:为属性值
		try {
			// 第一步:根据类名实例化一个对象
			obj = cls.newInstance();

			// 第二步:从文件中获取名称为nodeName的节点
			Document doc = XmlUtil.loadXml(xmlFile);
			Element element = doc.getRootElement().element(nodeName);

			if (null == element) { // 当在文件中没有找到该结点时,返回null
				return null;
			}

			// 第三步 :把所有的属性节点名和属性值存放在一个map集合中
			for (Element propertyElement : (List<Element>) element
					.elements("property")) {
				attributeMap.put(propertyElement.attribute("name").getText(),
						propertyElement.attribute("value").getText());

			}

		} catch (Exception ex) {
			throw new FileOperationException(ex);
		}

		// 第四步:返回封装后的对象
		return XmlUtil.encapObject(obj, attributeMap);

	}

	/**
	 * 封装对象.
	 * 
	 * @param document
	 *            document对象
	 * @param nodeName
	 *            xml文件节点
	 * @param cls
	 *            转换的目标类
	 * @return 封装nodeName节点信息的对象
	 * @throws SOFAException
	 *             SOFA异常
	 *
	 */
	@SuppressWarnings("unchecked")
	public static Object getBeanForXml(Document document, String nodeName,
			Class<?> cls) throws FileOperationException {

		Object obj = null;
		// 1） 在document对象中查找nodeName节点
		// 2） 将nodeName节点信息封装到cls类对象中。
		try {
			Element element = document.getRootElement().element(nodeName);

			if (null == element) { // 当在文件中没有找到该结点时,返回null
				return null;
			}

			Map attributeMap = new HashMap();// key:属性名,value:为属性值

			// 把所有的属性节点名和属性值存放在一个map集合中
			for (Element propertyElement : (List<Element>) element
					.elements("property")) {
				attributeMap.put(propertyElement.attribute("name").getText(),
						propertyElement.attribute("value").getText());
			}

			obj = cls.newInstance();
			return XmlUtil.encapObject(obj, attributeMap);
		} catch (Exception ex) {
			throw new FileOperationException(ex);
		}

	}

	/**
	 * 把map中的属性值封装到一个对象中.
	 * 
	 * @param obj
	 *            要封装的对象
	 * @param attributeMap
	 *            属性集合
	 * @return 封装好的对象
	 *
	 */
	private static Object encapObject(Object obj, Map<?, ?> attributeMap) {

		Object obj2 = null;
		try {
			String json = JSONUtil.toJson(attributeMap);
			obj2 = JSONUtil.toObject(json, obj.getClass());
		} catch (Exception ex) {
			return null;
		}
		return obj2;
	}
	
}
