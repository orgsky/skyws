package com.inca.skyws.model;

public class MessageInfo {
	private UserInfo from;

	private String fromTime;
	private UserInfo to;
	private String toTime;
	private Integer msgType;
	private Integer chatType;
	private String content;

	public UserInfo getFrom() {
		return from;
	}

	public void setFrom(UserInfo from) {
		this.from = from;
	}

	public UserInfo getTo() {
		return to;
	}

	public void setTo(UserInfo to) {
		this.to = to;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Integer getChatType() {
		return chatType;
	}

	public void setChatType(Integer chatType) {
		this.chatType = chatType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

}
