package com.lc.sofa.core.framework.basis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.lc.sofa.core.framework.basis.context.PageThreadContext;
import com.lc.sofa.core.framework.basis.log.SofaLogger;
import com.lc.sofa.core.framework.basis.log.SofaLoggerFactory;
import com.lc.sofa.core.framework.basis.vo.Page;
import com.lc.sofa.core.framework.basis.vo.TreeVO;
import com.lc.sofa.core.framework.component.id.SerialNoGenerator;
import com.lc.sofa.core.framework.constants.SofaConstants;
import com.lc.sofa.core.framework.support.sql.SQLHelper;
import com.lc.sofa.core.framework.util.ClassUtil;
import com.lc.sofa.core.framework.util.DateUtil;
import com.lc.sofa.core.framework.util.TreeUtil;

public class BasisDao<T> extends SqlSessionDaoSupport implements IDao<T>{

	private SofaLogger logger = SofaLoggerFactory.getSofaLog(BasisDao.class);
	protected String sqlmapNamespace;

	public void setSqlmapNamespace(String sqlmapNamespace) {	
		this.sqlmapNamespace = sqlmapNamespace;
	}
	
	@SuppressWarnings("unchecked")
	public T load(String id) {
		return (T)getSqlSession().selectOne(sqlmapNamespace+ ".load", id);
	}

	public void save(T vo) {
		try {		
			ClassUtil.forceSetProperty(vo, "timetag", DateUtil.getCurrent());
			String id = (String)ClassUtil.forceGetProperty(vo, "id");
			if(StringUtils.isEmpty(id)){
				ClassUtil.forceSetProperty(vo, "id", new SerialNoGenerator().nextId());
    			getSqlSession().insert(sqlmapNamespace+".insert", vo);
			}else{
				getSqlSession().update(sqlmapNamespace+".update", vo);
			}
		} catch (Exception e) {
			logger.error("BasisDao save error",e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void insert(T vo) {
		try {		
			ClassUtil.forceSetProperty(vo, "timetag", DateUtil.getCurrent());
			String id = (String)ClassUtil.forceGetProperty(vo, "id");
			if(StringUtils.isEmpty(id)){
				ClassUtil.forceSetProperty(vo, "id", new SerialNoGenerator().nextId());
			}
			getSqlSession().insert(sqlmapNamespace+".insert", vo);
		} catch (Exception e) {
			logger.error("BasisDao insert error",e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void delete(String ids) {
		try {
			Map<String,String> condition = new HashMap<String,String>();
			condition.put("timetag", DateUtil.getCurrent());
			condition.put("ids", SQLHelper.strToSqlIn(ids, SofaConstants.DEFAULT_SEPARATOR));
			getSqlSession().update(sqlmapNamespace+".delete", condition);
		} catch (Exception e) {
			logger.error("BasisDao delete error",e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void batchSave(List<T> voList) {
		try {
			List<T> insertList = new ArrayList<T>();
			for(T vo:voList){
			    ClassUtil.forceSetProperty(vo, "timetag", DateUtil.getCurrent());
			    String id = (String)ClassUtil.forceGetProperty(vo, "id");
				if(StringUtils.isEmpty(id)){
					ClassUtil.forceSetProperty(vo, "id", new SerialNoGenerator().nextId());
	    			insertList.add(vo);
				}else{
					getSqlSession().update(sqlmapNamespace+".update", vo);
				}
			}
			if(insertList.size()>0)
				getSqlSession().insert(sqlmapNamespace+".batchInsert", insertList);
		} catch (Exception e) {
			logger.error("BasisDao batch save error",e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void batchInsert(List<T> voList) {
		try {
			for(T vo:voList){
			    ClassUtil.forceSetProperty(vo, "timetag", DateUtil.getCurrent());
			    String id = (String)ClassUtil.forceGetProperty(vo, "id");
				if(StringUtils.isEmpty(id))
					ClassUtil.forceSetProperty(vo, "id", new SerialNoGenerator().nextId());
			}
			getSqlSession().insert(sqlmapNamespace+".batchInsert", voList);
		} catch (Exception e) {
			logger.error("BasisDao batch insert error",e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public List<T> findAll(T vo,String orderBy){
		Map<String, Object> condition = ClassUtil.beanToMap(vo);
		if(StringUtils.isEmpty(orderBy))
			orderBy = SofaConstants.DEFAULT_ORDERBY;
		condition.put("orderBy", orderBy);
		return getSqlSession().selectList(sqlmapNamespace+".findAll",condition);
	}
	
	public Page findAllPage(T vo,String orderBy){
		Map<String, Object> condition = ClassUtil.beanToMap(vo);
		if(StringUtils.isEmpty(orderBy))
			orderBy = SofaConstants.DEFAULT_ORDERBY;
		condition.put("orderBy", orderBy);
		List<T> voList = getSqlSession().selectList(sqlmapNamespace+".findAll", condition);
		Page page = PageThreadContext.getCurrentPage();
		if(page == null)
			page = new Page();
		page.setRecords(voList);
		return page;
	}
	
	public List<T> findByIds(String ids, String orderBy) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("ids", SQLHelper.strToSqlIn(ids, SofaConstants.DEFAULT_SEPARATOR));
		if(StringUtils.isEmpty(orderBy))
			orderBy = SofaConstants.DEFAULT_ORDERBY;
		condition.put("orderBy", orderBy);
		return getSqlSession().selectList(sqlmapNamespace+".findByIds",condition);
	}
	
	public String getTreeCodeById(String id) {
		return (String)getSqlSession().selectOne(sqlmapNamespace+".getTreeCodeById", id);
	}

	public String getNextTreeCode(String parentid) {
		String maxCode = getSqlSession().selectOne(sqlmapNamespace+".getMaxTreeCode", parentid);
		if(!StringUtils.isEmpty(maxCode)){
			maxCode = (new Long(maxCode)+1)+"";
		}else{
			String parCode = StringUtils.isEmpty(parentid)?"":getTreeCodeById(parentid);
			maxCode = parCode+((int)(Math.pow(10,SofaConstants.TREE_LEVEL_CODE_LENGTH-1)));
		}
		return maxCode;
	} 
	
	public List<TreeVO> loadTree(T vo) {
		List<Map<String,Object>> list = getSqlSession().selectList(sqlmapNamespace+".loadTree",vo);
		List<TreeVO> treeList = new ArrayList<TreeVO>();
		for(Map<String,Object> map:list){
			TreeVO treeVO = new TreeVO();
			for(String key : map.keySet()){
				Object value = map.get(key);
				if(key.equals("id") || key.equals("text") || key.equals("parentid")||key.equals("treecode")){
					try {
						ClassUtil.forceSetProperty(treeVO, key, value);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
				}else{
					treeVO.getAttributes().put(key, value);
				}
			}
			treeList.add(treeVO);
		}
		return TreeUtil.fillChildren(treeList);
	}
	
	public void sortTree(List<TreeVO> treeList) {
		treeList = TreeUtil.fillTreecode(treeList);
		List<T> voList = findAll(null,null);
		Map<String,TreeVO> treeMap = new HashMap<String,TreeVO>();
		for(TreeVO node:treeList){
			treeMap.put(node.getId(), node);
		}
		try {
    		for(T vo:voList){
    			TreeVO node = treeMap.get(ClassUtil.forceGetProperty(vo, "id"));
    			ClassUtil.forceSetProperty(vo, "parentid", node.getParentid());
    			ClassUtil.forceSetProperty(vo, "treecode", node.getTreecode());
    			save(vo);
    		}
    	} catch (NoSuchFieldException e) {
    		logger.error("BasisDao sort tree error",e.getMessage());
    		e.printStackTrace();
    	}
	}
	
	public boolean repeatTreeName(String id,String name,String parentid) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("id",StringUtils.isEmpty(id)?null:id);
		condition.put("name", name);
		condition.put("parentid", parentid);
		int count = (Integer)getSqlSession().selectOne(sqlmapNamespace+".repeatTreeName", condition);
		return count>0;
	}
	
	public T loadByCode(String code){
		return getSqlSession().selectOne(sqlmapNamespace+".loadByCode", code);
	}
	
	public String getNameById(String id) {
		return getSqlSession().selectOne(sqlmapNamespace+".getNameById", id);
	}

	public String getCodeById(String id) {
		return getSqlSession().selectOne(sqlmapNamespace+".getCodeById", id);
	}

	public boolean repeatCode(String id,String code) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("id",StringUtils.isEmpty(id)?null:id);
		condition.put("code", code);
		int count = (Integer)getSqlSession().selectOne(sqlmapNamespace+".repeatCode", condition);
		return count>0;
	}

	public boolean repeatName(String id,String name) {
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("id",StringUtils.isEmpty(id)?null:id);
		condition.put("name", name);
		int count = (Integer)getSqlSession().selectOne(sqlmapNamespace+".repeatName", condition);
		return count>0;
	}
}
