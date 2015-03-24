package com.lc.sofa.core.framework.services.component;

import com.lc.sofa.core.framework.basis.vo.BasisVO;

/**
 * 
 * 组件信息实体
 * @author   YZ
 * @version 1.0, 2013-10-24
 * @since 1.0, 2013-10-24
 */
public class Component extends BasisVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6241493198909724548L;

	/**
	 * 组件标识名
	 */
	private String symbolicName;

	/**
	 * 组件版本
	 */
	private String version;

	/**
	 * 组件描述
	 */
	private String desc;

	/**
	 * 组件状态信息
	 */
	private String state;

	/**
	 * 组件启动器类名
	 */
	private String activator;

	/**
	 * 组件提供者
	 */
	private String vendor;

	/**
	 * web组件组件webContextPath
	 */
	private String contextPath;
	/**
	 * 组件分类
	 */
	private String category;

	/**
	 * 组件导入包
	 */
	//private Set<ImportPackage> importPackages = new HashSet<ImportPackage>(0);

	/**
	 * 组件导出包
	 */
	//private Set<ExportPackage> exportPackages = new HashSet<ExportPackage>(0);

	
	/**
	 * set symbolicName.
	 * 
	 * @param symbolicName
	 *            symbolicName
	 */
	public void setSymbolicName(String symbolicName) {

		this.symbolicName = symbolicName;
	}

	/**
	 * return symbolicName.
	 * 
	 * @return symbolicName symbolicName
	 */
	public String getSymbolicName() {

		return this.symbolicName;
	}

	/**
	 * set version.
	 * 
	 * @param version
	 *            version
	 */
	public void setVersion(String version) {

		this.version = version;
	}

	/**
	 * return version.
	 * 
	 * @return version version
	 */
	public String getVersion() {

		return this.version;
	}

	/**
	 * set desc.
	 * 
	 * @param desc
	 *            desc
	 */
	public void setDesc(String desc) {

		this.desc = desc;
	}

	/**
	 * return desc.
	 * 
	 * @return desc desc
	 */
	public String getDesc() {

		return this.desc;
	}

	/**
	 * set state.
	 * 
	 * @param state
	 *            state
	 */
	public void setState(String state) {

		this.state = state;
	}

	/**
	 * return state.
	 * 
	 * @return state state
	 */
	public String getState() {

		return this.state;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {

		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {

		this.vendor = vendor;
	}

	/**
	 * set activator.
	 * 
	 * @param activator
	 *            activator
	 */
	public void setActivator(String activator) {

		this.activator = activator;
	}

	/**
	 * return activator.
	 * 
	 * @return activator activator
	 */
	public String getActivator() {

		return this.activator;
	}

	
	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbolicName == null) ? 0 : symbolicName.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	/**
	 * @return the contextPath
	 */
	public String getContextPath() {

		return contextPath;
	}

	/**
	 * @param contextPath
	 *            the contextPath to set
	 */
	public void setContextPath(String contextPath) {

		this.contextPath = contextPath;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {

		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {

		this.category = category;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Component other = (Component) obj;
		if (symbolicName == null) {
			if (other.symbolicName != null) return false;
		} else if (!symbolicName.equals(other.symbolicName)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) return false;
		return true;
	}

	public String toString() {

		// TODO Auto-generated method stub
		return null;
	}
	

}
