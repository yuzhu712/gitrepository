package com.lc.sofa.core.framework.basis.vo;

import java.io.Serializable;

public class ResultVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;// 提示信息
	private Object data;// 数据
	private boolean success;// 操作是否成功

	public ResultVO(boolean success, String message) {

		super();
		this.message = message;
		this.success = success;
	}

	public ResultVO(boolean success, Object data, String message) {

		super();
		this.message = message;
		this.data = data;
		this.success = success;
	}

	public ResultVO(boolean success, Object data) {

		super();
		this.data = data;
		this.success = success;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public Object getData() {

		return data;
	}

	public void setData(Object data) {

		this.data = data;
	}

	public boolean isSuccess() {

		return success;
	}

	public void setSuccess(boolean success) {

		this.success = success;
	}

}
