package com.lc.sofa.core.framework.services;

import java.util.List;
import java.util.Map;
import com.lc.sofa.core.framework.basis.vo.ChartVO;
import com.lc.sofa.core.framework.basis.vo.TreeVO;
import com.lc.sofa.core.framework.services.dict.DictService;


public abstract class BasisService{
	
	DictService dictService = ServiceFactory.getInstance().getService(DictService.class);

	/**
	 * 根据参数字典编码串获取元数据列表
	 * @param dictcodeArr 字典编码数组
	 * @return Map<字典编码，数据列表>
	 */
	public Map<String, List<TreeVO>> loadDictList(String[] dictcodeArr) {
		return dictService.loadDictList(dictcodeArr);
	}

	/**
	 * 根据参数字典编码,排序字段,filter获取元数据
	 * @param dictcode 字典编码
	 * @param 过滤sql，例如a=1 and b=2 order by a desc
	 * @return 类别数据
	 */
	public List<TreeVO> loadDictData(String dictcode, String filter) {
		return dictService.loadDictData(dictcode, filter);
	}
	
	/**
	 * 根据维度条件查询报表数据
	 * @param condition
	 * @return 图标信息及数据
	 */
	public abstract ChartVO getChartData(Map<String,Object> condition);

}
