package com.lc.sofa.core.framework.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import com.lc.sofa.core.framework.web.util.AjaxException;
import com.lc.sofa.core.framework.basis.vo.ValueObject;
import com.lc.sofa.core.framework.util.json.JSONUtil;

/**
 * AJAX操作相关工具类
 * 
 * @author        YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class AjaxUtil {

	 /* 错误状态 */
    private static final int RESPONSE_STATUS_ERROR = 500;

    /**
     * 访问成功将数据返回到前台
     * 
     * @param response
     *            响应对象
     * @param msg
     *            返回的JSON数据
     * @throws SOFARuntimeException
     *             异常
     * 
     */
    public static void success(HttpServletResponse response, String msg) throws AjaxException {

	response.setContentType("text/json;charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
	    PrintWriter out = response.getWriter();
	    out.write(msg);
	} catch (IOException ex) {
	    throw new AjaxException(ex);
	}
    }

    /**
     * 访问成功，将VO数组翻译成JSON字符串返回到前台
     * 
     * @param response
     *            响应对象
     * @param vo
     *            VO数组
     * @throws SOFARuntimeException
     *             SOFA异常基类
     * 
     */
    public static void success(HttpServletResponse response, ValueObject... vo) throws AjaxException {

	response.setContentType("text/json;charset=UTF-8");
	try {
	    PrintWriter out = response.getWriter();
	    out.write(JSONUtil.toJson(vo));
	} catch (IOException ex) {
	    throw new AjaxException(ex);
	}
    }

    
    /**
     * 访问成功，向前台发送不分页的数据
     * 
     * @param response
     *            响应对象
     * @param vos
     *            当前显示的数据
     * @throws SOFARuntimeException
     *             SOFA运行时异常
     */
    public static void success(HttpServletResponse response, Collection<? extends ValueObject> vos) throws AjaxException {

	response.setContentType("text/json;charset=UTF-8");
	try {
	    PrintWriter out = response.getWriter();
	    out.write(JSONUtil.toJson(vos));
	} catch (IOException ex) {
	    throw new AjaxException(ex);
	}
    }

   

    /**
     * 访问失败，将异常信息返回到前台
     * 
     * @param response
     *            响应对象
     * @param msg
     *            异常消息
     * @throws SOFARuntimeException
     *             SOFA异常基类
     * 
     */
    public static void failure(HttpServletResponse response, String msg) throws AjaxException {

	response.setContentType("text/json;charset=UTF-8");
	response.setStatus(RESPONSE_STATUS_ERROR);
	try {
	    PrintWriter out = response.getWriter();
	    out.write(msg);
	} catch (IOException ex) {
	    throw new AjaxException(ex);
	}
    }

	
}
