package com.inca.skyws.exception;

public enum CommonExceptionEnum implements ExceptionEnum {
	CM_NOT_EMPTY(100, "参数不能为空"),
	CM_NO_RULE(101, "没有定义规则"),
	CM_FAIL(102, "处理失败");

	private int code;
	private String msg;

	private CommonExceptionEnum(int code, String msg) {
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
