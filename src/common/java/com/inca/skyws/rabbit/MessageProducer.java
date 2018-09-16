package com.inca.skyws.rabbit;

public interface MessageProducer {

	void send(RabbitMessage msg);
}
