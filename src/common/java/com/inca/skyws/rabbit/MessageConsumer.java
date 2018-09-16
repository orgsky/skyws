package com.inca.skyws.rabbit;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.inca.skyws.bean.RabbitLog;
import com.inca.skyws.service.RabbitLogService;
import com.rabbitmq.client.Channel;

@Component
public class MessageConsumer {
	private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	@Autowired
	RabbitLogService logService;

	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "sky-common-queue"), exchange = @Exchange(name = "sky-common-exchange", durable = "true", type = "topic"), key = "sky-common-msg-key"))
	@RabbitHandler
	public void messageHandler(@Payload RabbitMessage msg, @Headers Map<String, Object> headers, Channel channel) {
		RabbitLog rabbitLog = logService.getRabbitLog(msg.getId());
		try {
			// 收到未签收
			logService.acceptNotAck(rabbitLog);
			log.info("监听到rabbit消息:" + JSONObject.toJSONString(msg));
			Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
			channel.basicAck(deliveryTag, false);
			// 签收成功
			logService.ackAndFinish(rabbitLog);
		} catch (IOException e) {
			log.info("处理rabbit消息异常:" + e.getMessage(), e);
			// 签收异常
			rabbitLog.setMsg(e.getMessage());
			logService.acceptNotFinish(rabbitLog);
		}
	}
}
