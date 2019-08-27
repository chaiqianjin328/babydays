package com.babydays.model;

/**
* @ClassName: Result
* @Description: TODO(封装接口返回数据类)
* @author chaiqianjin
* @date 2018年8月8日
*
*/
public class Result {

	private Integer code;
	
	private String msg;
	
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
