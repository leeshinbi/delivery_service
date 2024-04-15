package org.delivery.api.domain.userorder.controller.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {

	//주문
	// 특정 사용자가, 특정 메뉴를 주문
	// 특정 사용자? 로그인 된 세션에 들어있는 사용자를 말함
	// 따라서 필요한 건 특정 메뉴 id

	@NotNull
	private List<Long> storeMenuIdList; //아메리카노 + 카페라떼 등등 받을 수 있기 때문에 리스트로 받는다.

}
