package com.inca.skyws.exception;

public enum ChatExceptionEnum implements ExceptionEnum {

	CHAT_NOT_FRIEND(200, "不是好友"),
	CHAT_NO_OWNER(201, "没有创建人"),
	CHAT_SEND_FAIL(202, "消息传递失败");

	private int code;
	private String msg;

	private ChatExceptionEnum(int code, String msg) {
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
