package org.delivery.api.domain.userorder.converter;


import java.math.BigDecimal;
import java.util.List;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;

@Converter
public class UserOrderConverter {

	public UserOrderEntity toEntity(
		User user,
		List<StoreMenuEntity> storeMenuEntityList
	){
		var totalAmount = storeMenuEntityList.stream()
			.map(it -> it.getAmount()) // 주문된 각 항목의 금액을 가져온다.
			.reduce(BigDecimal.ZERO, BigDecimal::add); // 금액의 총합을 계산하기 위해 각 항목의 금액을 더한다.

		return UserOrderEntity.builder()
			.userId(user.getId())
			.amount(totalAmount) //위에서 구한 총 금액으로 설정
			.build()
			;
	}

	public UserOrderResponse toResponse(
		UserOrderEntity userOrderEntity
	){
		return UserOrderResponse.builder()
			.id(userOrderEntity.getId())
			.status(userOrderEntity.getStatus())
			.amount(userOrderEntity.getAmount())
			.orderedAt(userOrderEntity.getOrderedAt())
			.acceptedAt(userOrderEntity.getAcceptedAt())
			.cookingStartedAt(userOrderEntity.getCookingStartedAt())
			.deliveryStartedAt(userOrderEntity.getDeliveryStartedAt())
			.receivedAt(userOrderEntity.getReceivedAt())
			.build()
			;
	}
}