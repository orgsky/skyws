package com.inca.skyws.rabbit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DefaultMessageImpl implements RabbitMessage {
	private static final long serialVersionUID = 845946420072821412L;
	private Integer id;
	private Integer userId;
	private String userName;
	private String exchange;
	private String routingKey;
	private Integer comeFrom;
	private Class<? extends Object> clz;
	private Map<String, Object> data = new HashMap<String, Object>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public Class<? extends Object> getClz() {
		return clz;
	}

	public void setClz(Class<? extends Object> clz) {
		this.clz = clz;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Integer getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(Integer comeFrom) {
		this.comeFrom = comeFrom;
	}

}
