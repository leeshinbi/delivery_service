package org.delivery.api.domain.userorder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderDetailResponse {

	private UserOrderResponse userOrderResponse; // 사용자가 주문한 건이 무엇인지
	private StoreResponse storeResponse; // 그 가게가 어디인지
	private List<StoreMenuResponse> storeMenuResponseList; // 어떠한 메뉴를 주문했는지
}