package com.lc.sofa.core.framework.basis.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadInfo {

	private String filegrpCode;
	private String dataId;
	private CommonsMultipartFile file;

	public String getFilegrpCode() {

		return filegrpCode;
	}

	public void setFilegrpCode(String filegrpCode) {

		this.filegrpCode = filegrpCode;
	}

	public String getDataId() {

		return dataId;
	}

	public void setDataId(String dataId) {

		this.dataId = dataId;
	}

	public CommonsMultipartFile getFile() {

		return file;
	}

	public void setFile(CommonsMultipartFile file) {

		this.file = file;
	}

}
