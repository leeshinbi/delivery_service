package org.delivery.api.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.userorder.UserOrderEntity;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {

	private final UserOrderService userOrderService;

	private final UserOrderConverter userOrderConverter;

	private final StoreMenuService storeMenuService;
	private final StoreMenuConverter storeMenuConverter;

	private final UserOrderMenuConverter userOrderMenuConverter;
	private final UserOrderMenuService userOrderMenuService;

	private final StoreService storeService;
	private final StoreConverter storeConverter;


	// 1. 사용자 , 메뉴 id
	// 2. userOrder 생성
	// 3. userOrderMenu 생성
	// 4. 응답 생성
	public UserOrderResponse userOrder(User user, UserOrderRequest body) {
		var storeMenuEntityList = body.getStoreMenuIdList()
			.stream()
			.map(it -> storeMenuService.getStoreMenuWithThrow(it))
			.collect(Collectors.toList());

		var userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

		// 주문
		var newUserOrderEntity = userOrderService.order(userOrderEntity);

		// 맵핑
		var userOrderMenuEntityList = storeMenuEntityList.stream()
			.map(it -> {
				// menu +user order
				var userOrderMenuEntity = userOrderMenuConverter.toEntity(newUserOrderEntity, it);

				return userOrderMenuEntity;
			})
			.collect(Collectors.toList());

		// 주문내역 기록 남기기
		userOrderMenuEntityList.forEach(it -> {
			userOrderMenuService.order(it);
		});

		// response
		return userOrderConverter.toResponse(newUserOrderEntity);
	}
}