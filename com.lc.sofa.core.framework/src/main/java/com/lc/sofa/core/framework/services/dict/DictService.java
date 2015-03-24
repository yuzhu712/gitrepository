package com.lc.sofa.core.framework.services.dict;

import java.util.List;
import java.util.Map;

import com.lc.sofa.core.framework.basis.vo.TreeVO;
import com.lc.sofa.core.framework.services.Service;


public interface DictService extends Service{

	public Map<String,List<TreeVO>> loadDictList(String[] dictcodeArr);
	
	public List<TreeVO> loadDictData(String dictcode,String filter);
	
	public String getDictText(String dictcode,String itemcode);
}
