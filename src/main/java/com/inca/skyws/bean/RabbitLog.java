package com.inca.skyws.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("消息队列日志")
@Entity(name = "pub_rabbit_log")
public class RabbitLog extends BaseEntity {
	private static final long serialVersionUID = 3338119428902284423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("类名")
	private String clzName;

	@Title("发生时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expTime;

	@Title("异常信息")
	private String msg;

	@Title("线程ID")
	private String threadId;

	@Title("来源")
	@OptionKey(name = "PUB_EXP_COMEFROM")
	private Integer comeFrom;

	// 0存库未投递 1投递未签收 2投递异常 
	// 3收到未签收 4签收成功(这个是代码没写签收,正常不应该出现这状态,虚定时处理) 
	// 5签收异常
	@Title("投递状态")
	@OptionKey(name = "PUB_DELIVERY_STATUS")
	private Integer deliveryStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClzName() {
		return clzName;
	}

	public void setClzName(String clzName) {
		this.clzName = clzName;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public Integer getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(Integer comeFrom) {
		this.comeFrom = comeFrom;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

}
