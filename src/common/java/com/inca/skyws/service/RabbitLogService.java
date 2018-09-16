package com.inca.skyws.service;

import com.inca.skyws.bean.RabbitLog;
import com.inca.skyws.rabbit.RabbitMessage;

public interface RabbitLogService {
	public static final String NAME = "rabbitService";

	public RabbitLog saveRabbitLog(RabbitMessage msg, String exp);

	public RabbitLog saveRabbitLog(RabbitMessage msg);

	public RabbitLog getRabbitLog(Integer logId);

	public boolean deleteRabbitLog(Integer logId);

	// 存库未投递
	public void savedNotSend(RabbitLog log);

	// 投递未签收
	public void sendNotAck(RabbitLog log);

	// 投递异常
	public void sendNotArrive(RabbitLog log);

	// 收到未签收
	public void acceptNotAck(RabbitLog log);

	// 签收成功
	public void ackAndFinish(RabbitLog log);

	// 收到没结束
	public void acceptNotFinish(RabbitLog log);
}
