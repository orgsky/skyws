package com.inca.skyws.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

public class SocketChannelInteceptor implements ChannelInterceptor {
	private static final Logger log = LoggerFactory.getLogger(SocketChannelInteceptor.class);

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		log.info("消息发送处理--postSend");
	}

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		log.info("消息发送处理--preSend");
		return message;
	}
	
	@Override
	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
		log.info("消息发送处理--afterSendCompletion");
	}
	
	@Override
	public boolean preReceive(MessageChannel channel) {
		log.info("消息发送处理--preReceive");
		return ChannelInterceptor.super.preReceive(channel);
	}
	
	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		log.info("消息发送处理--postReceive");
		return ChannelInterceptor.super.postReceive(message, channel);
	}

	@Override
	public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
		log.info("消息发送处理--afterReceiveCompletion");
	}
}
