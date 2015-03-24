package com.lc.sofa.core.framework.basis.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeVO implements BasisTree {

	private static final long serialVersionUID = 1L;

	private String id;// id或者编码

	private String text;// 显示名称

	private List children;// 子级节点

	private String parentid;// 父级节点ID

	private String treecode;// 树级编码

	private Map<String, Object> attributes = new HashMap<String, Object>(); // 扩展属性

	public String getTreecode() {

		return treecode;
	}

	public void setTreecode(String treecode) {

		this.treecode = treecode;
	}

	public List getChildren() {

		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {

		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {

		this.attributes = attributes;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public String getParentid() {

		return parentid;
	}

	public void setParentid(String parentid) {

		this.parentid = parentid;
	}

}
