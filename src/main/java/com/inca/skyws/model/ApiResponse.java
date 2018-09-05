package com.inca.skyws.model;

public class ApiResponse {

	private Boolean code;
	private Object data;
	private String msg;

	public ApiResponse(Boolean code) {
		super();
		this.code = code;
	}

	public ApiResponse(String msg) {
		super();
		this.msg = msg;
	}

	public ApiResponse(Object data) {
		super();
		this.data = data;
	}

	public ApiResponse() {
		super();
	}

	public Boolean getCode() {
		return code;
	}

	public void setCode(Boolean code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static ApiResponse sucess() {
		return new ApiResponse(true);
	}

	public static ApiResponse fail() {
		return new ApiResponse(false);
	}

	public ApiResponse putData(Object data) {
		this.data = data;
		return this;
	}

	public ApiResponse putMsg(String msg) {
		this.msg = msg;
		return this;
	}

}
