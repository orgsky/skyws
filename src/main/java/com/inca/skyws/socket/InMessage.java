package com.inca.skyws.socket;

import java.util.Date;

public class InMessage {

	private String from;
	private Integer fromId;
	private String fromName;
	private String fromImg;

	private String to;
	private Integer toId;
	private String toName;
	private String toImg;

	private String content;

	private Date time;
	// 1.群聊2.单聊.3加好友.4删好友5.加群6退群7拉入群8踢出群
	private Integer type;

	public String getFrom() {
		return from;
	}

	public InMessage() {
	}

	public InMessage(String content) {
		this.content = content;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getFromId() {
		return fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromImg() {
		return fromImg;
	}

	public void setFromImg(String fromImg) {
		this.fromImg = fromImg;
	}

	public Integer getToId() {
		return toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToImg() {
		return toImg;
	}

	public void setToImg(String toImg) {
		this.toImg = toImg;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
