package com.inca.skyws.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inca.skyws.bean.RabbitLog;
import com.inca.skyws.service.RabbitLogService;

@Service
public class MessageProducerImpl implements MessageProducer {
	private static final Logger logger = LoggerFactory.getLogger(MessageProducerImpl.class);
	@Autowired
	RabbitLogService logService;

	@Autowired
	RabbitTemplate template;

	public void send(RabbitMessage msg) {
		logger.info("发送rabbit消息...");
		RabbitLog log = logService.saveRabbitLog(msg);
		try {
			// 存库未投递
			logService.savedNotSend(log);
			msg.setId(log.getId());
			CorrelationData correlationData = new CorrelationData();
			correlationData.setId(msg.getId().toString());
			template.convertSendAndReceive(msg.getExchange(), msg.getRoutingKey(), msg, correlationData);
			// 投递未签收
			logService.sendNotAck(log);
		} catch (AmqpException e) {
			log.setMsg(e.getMessage());
			// 投递异常
			logService.sendNotArrive(log);
		}
		logger.info("发送rabbit消息end");
	}

}
