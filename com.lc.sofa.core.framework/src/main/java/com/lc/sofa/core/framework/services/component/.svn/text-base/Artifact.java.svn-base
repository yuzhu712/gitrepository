package com.lc.sofa.core.framework.services.component;

import java.io.File;
import java.io.Serializable;

/**
 * 
 * 组件信息实体类
 * @author       YZ
 * @version 1.0, 2014-1-10
 * @since 1.0, 2014-1-10
 */
public class Artifact implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6837195543209396L;
	
	private String symbolicName;
    private String version;
    private String type;
    private File   file;

    public Artifact(String symbolicName, String version, String type, File file) {

	super();
	this.symbolicName = symbolicName;
	this.version = version;
	this.type = type;
	this.file = file;
    }

    public Artifact(String symbolicName, String version, String type) {

	super();
	this.symbolicName = symbolicName;
	this.version = version;
	this.type = type;
    }

    public Artifact(String symbolicName, String version) {

	super();
	this.symbolicName = symbolicName;
	this.version = version;
    }

    public Artifact(String symbolicName) {

	super();
	this.symbolicName = symbolicName;
    }

    /**
     * @return the symbolicName
     */
    public String getSymbolicName() {

	return symbolicName;
    }

    /**
     * @param symbolicName
     *            the symbolicName to set
     */
    public void setSymbolicName(String symbolicName) {

	this.symbolicName = symbolicName;
    }

    /**
     * @return the version
     */
    public String getVersion() {

	return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {

	this.version = version;
    }

    /**
     * @return the type
     */
    public String getType() {

	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {

	this.type = type;
    }

    /**
     * @return the file
     */
    public File getFile() {

	return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public void setFile(File file) {

	this.file = file;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    public String toString() {

	return "Artifact [symbolicName=" + symbolicName + ", version=" + version + ", type=" + type + ", file=" + file + "]";
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
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    
    public boolean equals(Object obj) {

	if (this == obj) return true;
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	Artifact other = (Artifact) obj;
	if (symbolicName == null) {
	    if (other.symbolicName != null) return false;
	} else if (!symbolicName.equals(other.symbolicName)) return false;
	if (version == null) {
	    if (other.version != null) return false;
	} else if (!version.equals(other.version)) return false;
	return true;
    }

}
