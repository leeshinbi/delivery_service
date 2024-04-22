package org.delivery.storeadmin.domain.userorder.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserOrderConsumer {

	@RabbitListener(queues = "delivery.queue") // 어떤 큐에서 받아올 것인지
	public void userOrderConsumer(
		UserOrderMessage userOrderMessage
	) {
		log.info("message queue >> {}", userOrderMessage);
	}

}
