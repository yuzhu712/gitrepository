package com.lc.sofa.core.framework.services.poi;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lc.sofa.core.framework.basis.vo.ResultVO;
import com.lc.sofa.core.framework.services.Service;


public interface PoiService extends Service{
	
	/**
	 * 根据上传文件及模板编码等导入数据集
	 * @param file 上传文件
	 * @param tptcode 模板编码
	 * @param voClass 对应VO的Class
	 * @return 导入的结果如导入信息和VO数据列表
	 */
	public ResultVO importList(CommonsMultipartFile file,String tptcode,Class voClass);

	/**
	 * 根据模板编码和VO列表导出数据集到文件上
	 * @param response
	 * @param tptcode 模板编码
	 * @param list VO数据列表
	 * @param voClass 对应VO的Class
	 */
	public void exportList(HttpServletResponse response,String tptcode,List list, Class voClass);
}
