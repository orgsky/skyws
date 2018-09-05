package com.inca.skyws.exception;

public enum ScExceptionEnum implements ExceptionEnum {

	SC_NOT_SELLER(100, "不是卖家");

	private int code;
	private String msg;

	private ScExceptionEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
