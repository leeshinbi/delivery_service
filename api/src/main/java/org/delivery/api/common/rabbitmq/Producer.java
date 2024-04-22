package org.delivery.api.common.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Producer {

	private final RabbitTemplate rabbitTemplate;

	public void producer(String exchane, String routeKey, Object object) {
		rabbitTemplate.convertAndSend(exchane,routeKey,object);
	}
}
