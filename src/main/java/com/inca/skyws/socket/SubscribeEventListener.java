package com.inca.skyws.socket;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
	private static final Logger log = LoggerFactory.getLogger(SubscribeEventListener.class);

	@Override
	public void onApplicationEvent(SessionSubscribeEvent event) {
		log.info("[订阅事件监听]打印的日志.....");
		Message<byte[]> message = event.getMessage();
		Principal user = event.getUser();
		System.out.println("message=" + message);
		System.out.println("user=" + user);
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		System.out.println("accessor=" + accessor);
		StompCommand command = accessor.getCommand();
		System.out.println("command=" + command);
		SimpMessageType messageType = command.getMessageType();
		System.out.println("messageType=" + messageType);
		log.info("[订阅事件监听]打印的日志end");
	}

}
