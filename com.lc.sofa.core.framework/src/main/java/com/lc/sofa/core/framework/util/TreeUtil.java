package com.lc.sofa.core.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.lc.sofa.core.framework.basis.vo.BasisTree;
import com.lc.sofa.core.framework.constants.SofaConstants;


public class TreeUtil {
	
	public static List fillChildren(List voList){
		List resultList = new ArrayList();
        if (voList==null || voList.size()==0) return voList;
        Map<String,BasisTree> treeMap = new HashMap<String,BasisTree>();
        for (int i = 0; i < voList.size(); i++){
        	BasisTree treeVO = (BasisTree)voList.get(i);
        	treeVO.setChildren(null);
        	treeMap.put(treeVO.getId(), treeVO);
        }
        for (int i = 0; i < voList.size(); i++){
        	BasisTree treeVO = (BasisTree)voList.get(i);
        	if(StringUtils.isEmpty(treeVO.getParentid())){
        		resultList.add(treeVO);
        		continue;
        	}
        	BasisTree parentVO = treeMap.get(treeVO.getParentid());
        	List children = parentVO.getChildren()==null?new ArrayList():parentVO.getChildren();
        	children.add(treeVO);
        	parentVO.setChildren(children);
        }
        return resultList;
	}
	
	public static List fillTreecode(List treeList){
		List resultList = new ArrayList();
        if (treeList==null || treeList.size()==0) return resultList;
        Map<String,String> codeMap = new HashMap<String,String>();
        Map<String,String> maxCodeMap = new HashMap<String,String>();
        String code = ((int)(Math.pow(10,SofaConstants.TREE_LEVEL_CODE_LENGTH-1)))+"";
        treeList = fillChildren(treeList);
        for (Object obj:treeList){
        	BasisTree treeVO = (BasisTree)obj;
    		treeVO.setTreecode(code);
    		code =  (new Long(code)+1)+"";
    		codeMap.put(treeVO.getId(), code);
    		resultList.add(treeVO);
    		recurTreecode(treeVO,codeMap,maxCodeMap,resultList);
        }
        return resultList;
	}
	
	private static void recurTreecode(BasisTree parentVO,Map<String,String> codeMap,Map<String,String> maxCodeMap,List resultList){
		if(parentVO.getChildren()==null || parentVO.getChildren().size()==0)return;
		for(Object obj:parentVO.getChildren()){
			BasisTree treeVO = (BasisTree)obj;
			String parCode = codeMap.get(treeVO.getParentid());
			String maxCode = maxCodeMap.get(treeVO.getParentid());
			if(maxCode!=null){
				maxCode = (new Long(maxCode)+1)+"";
			}else{
				maxCode = parCode+ ((int)(Math.pow(10,SofaConstants.TREE_LEVEL_CODE_LENGTH-1)));
			}
			codeMap.put(treeVO.getId(), maxCode);
			maxCodeMap.put(treeVO.getParentid(), maxCode);
			treeVO.setTreecode(maxCode);
			resultList.add(treeVO);
			recurTreecode(treeVO,codeMap,maxCodeMap,resultList);
		}
	}

}
