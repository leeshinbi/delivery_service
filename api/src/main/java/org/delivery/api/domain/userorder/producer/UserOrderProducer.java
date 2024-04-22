package org.delivery.api.domain.userorder.producer;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.rabbitmq.Producer;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.userorder.UserOrderEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserOrderProducer {

	private final Producer producer;

	// 고정 값
	private static final String EXCHANGE = "delivery.exchange";
	private static final String ROUTE_KEY = "delivery.key";

	public void sendOrder(UserOrderEntity userOrderEntity) {
		sendOrder(userOrderEntity.getId());

	}

	// 주문이 들어 오면, 사용자 id로 메시지를 생성 후 queue에다 넣는 과정
	public void sendOrder(Long userOrderId) {
		var message = UserOrderMessage.builder() // common 모듈에 있는 클래스
			.userOrderId(userOrderId)
			.build();

		producer.producer(EXCHANGE,ROUTE_KEY,message);
	}

}
