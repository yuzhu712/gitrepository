package com.lc.sofa.core.framework.web.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.util.JavaScriptUtils;
import com.lc.sofa.core.framework.constants.SofaConstants;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
/**
 * 
 * WEB相关资源操作工具类
 * @author       YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class WebUtil {

	private static SofaLogger logger=SofaLoggerFactory.getSofaLog(WebUtil.class);
	
    /**
     * 
     * 将传入的url字符串编码为code类型的字符串.
     * @param url
     * @param code
     * @return
     * @throws UnsupportedEncodingException
     */
	public static String encode(String url, String code) throws UnsupportedEncodingException {

		return URLEncoder.encode(url, code);
	}
	
	/**
	 * 将传入的url字符串解码为code类型的字符串.
	 * 
	 * @param url
	 *            待解码的url路径
	 * @param code
	 *            目标字符解码类型
	 * @return 解码后的url
	 * @throws UnsupportedEncodingException
	 *            
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String url, String code) throws UnsupportedEncodingException {

		return URLDecoder.decode(url, code);
	}
	
	/**
	 * 通过给定的path路径返回绝对路径.
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param path
	 *            路径
	 * @return 返回绝对路径
	 */
	public static String getRealPath(HttpServletRequest request, String path) {

		String realPath = request.getSession().getServletContext().getContextPath();
		if (!"".equals(path) && "/".equals(path.substring(0, 1))) {
			realPath = realPath + path.substring(1);

		} else {
			realPath = realPath + path;
		}
        return realPath;
	}
	
	/**
	 * 处理javascript的特殊字符.
	 * 
	 * @param input
	 *            待处理的javascript代码
	 * @return 处理后的javascript代码
	 * 
	 */
	public static String javaScriptEscape(String input) {

		return JavaScriptUtils.javaScriptEscape(input);
	}

	/**
	 * 判断是否异步请求
	 * 
	 * @param request
	 *            Http 请求对象
	 * @return 是异步请求 true，否则为false
	 */
	public static boolean isAJAXRequest(HttpServletRequest request) {

		if (request.getHeader("x-requested-with") != null
			&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取应用程序根地址(不包括contextPath)
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 如:http://127.0.0.1:9090
	 */
	public static String getApplicationRootUrl(HttpServletRequest request) {

		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}

	/**
	 * 获取当前web组件跟地址.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * http://127.0.0.1:8088/sofa/sofa-portal/
	 */
	public static String getBundleWebContextBaseUrl(HttpServletRequest request) {

		return getApplicationRootUrl(request) + request.getContextPath() + "/";
	}
	
	/**
	 * 将相对地址path转换为绝对地址。
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param path
	 *            相对地址。 以/开头表示相对于应用程序跟目录，否则相对于本组件web跟目录地址
	 * @return 如 1 http://127.0.0.1:9090/acs+path（path以/开头）
	 *         2 http://127.0.0.1:9090/acs/sofa-deploy/+path（path以不以/开头）
	 */
	public static String toAbsolutPath(HttpServletRequest request, String path) {

		String bundleCtxUrl = null;
		String realRootCtx = null;
		if (System.getProperty("COM.LC.SOFA.CONTAINER.HOME") == null) {
			
			realRootCtx = "";
		} else {
			realRootCtx = request.getContextPath();
			int i = realRootCtx.lastIndexOf("/");
			if (i != -1) {
				realRootCtx = realRootCtx.substring(0, i);
			}
			if (!realRootCtx.startsWith("/")) {
				realRootCtx = "/" + realRootCtx;
			}
		}
		if (path.startsWith("/")) {// 相对于应用跟目录
			bundleCtxUrl = getApplicationRootUrl(request) + realRootCtx + path;

		} else {
			bundleCtxUrl = getApplicationRootUrl(request) + request.getContextPath() + "/" + path; // 相对于当前上下文
		}
        return bundleCtxUrl;
	}

	
    /**
	 * 获取组件的完全应用上下文路径，如果bundleContextPath传入为空则返回当前应用组件的web上下文路径.
	 * 用法：WebUtil.getFullWebContextPath(request, "sofa-deploy") 返回:acs/sofa-deploy
	 * WebUtil.getFullWebContextPath(request, null) 返回:acs/当前组件contextpath
	 * WebUtil.getFullWebContextPath(request, "/") 返回:acs/
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param bundleContextPath
	 *            目标组件ContextPath，如:acs-parameter
	 * 格式为web应用的ContextPath+目标组件的ContextPath；如acs/sofa-deploy
	 */
	public static String getFullWebContextPath(HttpServletRequest request, String bundleContextPath) {

		String realRootCtx = request.getContextPath();
		if (bundleContextPath == null || bundleContextPath.trim().equals("")) {
			return realRootCtx;
		}

		int i = realRootCtx.lastIndexOf("/");
		if (i != -1) {
			realRootCtx = realRootCtx.substring(0, i);
		}
		if (!bundleContextPath.startsWith("/")) {
			realRootCtx = realRootCtx + "/";
		}
		return realRootCtx + bundleContextPath;
	}

	/**
	 * 获取应用程序跟WebContextPath，如请求地址为http://127.0.0.1:8088/sofa/sofa-deploy,则返回的结果为/sofa
	 * 
	 * @param request
	 * @return
	 */
	public static String getAppRootWebContextPath(HttpServletRequest request) {

		String realRootCtx = request.getContextPath();

		int i = realRootCtx.lastIndexOf("/");
		if (i > 0) {
			realRootCtx = realRootCtx.substring(0, i);
		}
		if (!realRootCtx.startsWith("/")) {
			realRootCtx = "/" + realRootCtx;
		}
		return realRootCtx;
	}
	
	/**
	 * 根据URL参数key获取URL请求中的对应的值
	 * @param request
	 * @param parameter
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String parameter) {

		String val = null;
		try {
			String str = URLDecoder.decode(request.getQueryString(), "UTF-8");
			String[] arr = str.split("&");
			for (String s : arr) {
				if (s.startsWith(parameter)) {
					val = s.substring(s.indexOf("=") + 1);
					break;
				}
			}
		} catch (Exception e) {
			val = request.getParameter(parameter);
			logger.warn("获取参数[" + parameter + "]解码时出错！IN WebUtil.getParameter", e);
			
		}
		return val;
	}
	
	
	/**
	 * 
	 *根据请求的request获取用户IP 
	 * @param request
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
		
	}
	
	/**
	 * 
	 * 获取basePath.
	 * @param httpRequest
	 */
	public static String getFullContextPath(HttpServletRequest httpRequest) {
		HttpSession session=httpRequest.getSession();
        
	    String basePath =(String)session.getAttribute(SofaConstants.FULL_CONTEXT_PATH_KEY);
		if(basePath==null || basePath.trim().equals("")){
			basePath=httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort()
					+ httpRequest.getContextPath();
			session.setAttribute(SofaConstants.FULL_CONTEXT_PATH_KEY, basePath);
		}
	    return basePath;
	}
	
	
	
}
