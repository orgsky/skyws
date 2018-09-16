package com.inca.skyws.rabbit;

import java.io.Serializable;
import java.util.Map;

public interface RabbitMessage extends Serializable {

	public Integer getId();

	public void setId(Integer id);

	public Integer getUserId();

	public String getUserName();

	public String getExchange();

	public String getRoutingKey();

	public Integer getComeFrom();

	public Class<? extends Object> getClz();

	public Map<String, Object> getData();

}
