package com.lc.sofa.core.framework.basis.dao;

import java.util.List;

import com.lc.sofa.core.framework.basis.vo.TreeVO;
import com.lc.sofa.core.framework.basis.vo.Page;


public interface IDao<T> {

	/** VO操作 **/
	public T load(String id);

	public void save(T vo);
	
	public void insert(T vo);
	
	public void delete(String ids);
	
	public void batchSave(List<T> voList);
	
	public void batchInsert(List<T> voList);
	
	public List<T> findAll(T vo,String orderBy);
	
	public Page findAllPage(T vo,String orderBy);
	
	/** 树操作 **/
	public List<TreeVO> loadTree(T vo);
	
	public void sortTree(List<TreeVO> treeList);
	
	public String getTreeCodeById(String id);
	
	public String getNextTreeCode(String parentid);
	
	public boolean repeatTreeName(String id,String name,String parentid);
	
	/** 编码名称操作 **/
	public T loadByCode(String code);
	
	public String getCodeById(String id);
	
	public String getNameById(String id);

	public boolean repeatCode(String id,String code);
	
	public boolean repeatName(String id,String name);
	
	/** 导入导出操作 **/
	public List<T> findByIds(String ids,String orderBy);
}
