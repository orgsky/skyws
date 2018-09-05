package com.inca.skyws.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("聊天记录表")
@Entity(name = "sys_message")
public class Message extends BaseEntity {
	private static final long serialVersionUID = 6524796452678450217L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@Title("发出人ID")
	private User from;

	@Title("发出时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromTime;

	@ManyToOne
	@Title("接收人ID")
	private User to;

	@Title("接收时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date toTime;

	@Title("消息类型")
	@Column(precision = 1)
	@OptionKey(name = "SYS_MSG_TYPE")
	private Integer msgType;

	@Title("消息性质")
	@Column(precision = 1)
	@OptionKey(name = "SYS_CHAT_TYPE")
	private Integer chatType;

	@Title("消息内容")
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
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

}
