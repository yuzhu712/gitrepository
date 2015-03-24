package com.lc.sofa.core.framework.services.component;

import java.io.Serializable;
/**
 * 
 * 组件版本描述信息BEAN
 * @author       YZ
 * @version 1.0, 2014-1-10
 * @since 1.0, 2014-1-10
 */
public class ArtifactDescriptor implements Serializable{

    /**
	 * 
	 */
    private static final long serialVersionUID      = 2630152402184979636L;

    private String	    name		  = null;

    private boolean	   isInclusionMinVersion = false;
    private boolean	   isInclusionMaxVersion = false;
    private String	    maxVersion	    = null;
    private String	    minVersion	    = null;

    private String	    type		  = "bundle";

    public ArtifactDescriptor() {

    }

    public ArtifactDescriptor(String name, String version, String type) {

	super();
	this.name = name;
	this.type = type;
	this.version = version;
    }

    public String getName() {

	return name;
    }

    public void setName(String name) {

	this.name = name;
    }

    public boolean isInclusionMinVersion() {

	return isInclusionMinVersion;
    }

    public void setInclusionMinVersion(boolean isInclusionMinVersion) {

	this.isInclusionMinVersion = isInclusionMinVersion;
    }

    public boolean isInclusionMaxVersion() {

	return isInclusionMaxVersion;
    }

    public void setInclusionMaxVersion(boolean isInclusionMaxVersion) {

	this.isInclusionMaxVersion = isInclusionMaxVersion;
    }

    public String getMaxVersion() {

	return maxVersion;
    }

    public void setMaxVersion(String maxVersion) {

	this.maxVersion = maxVersion;
    }

    public String getMinVersion() {

	return minVersion;
    }

    public void setMinVersion(String minVersion) {

	this.minVersion = minVersion;
    }

    public String getType() {

	return type;
    }

    public void setType(String type) {

	this.type = type;
    }

    private String  version = null;
    private boolean isScope = true;

    public String getVersion() {

	return version;
    }

    public void setVersion(String version) {

	this.version = version;
    }

    public boolean isScope() {

	return isScope;
    }

    public void setScope(boolean isScope) {

	this.isScope = isScope;
    }

}
