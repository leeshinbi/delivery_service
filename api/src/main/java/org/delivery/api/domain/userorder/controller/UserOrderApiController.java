package org.delivery.api.domain.userorder.controller;


import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-order")
public class UserOrderApiController {

	private final UserOrderBusiness userOrderBusiness;

	// 사용자 주문
	@PostMapping("")
	public Api<UserOrderResponse> userOrder(
		@Valid
		@RequestBody Api<UserOrderRequest> userOrderRequest,

		@Parameter(hidden = true)
		@UserSession
		User user
	){
		var response = userOrderBusiness.userOrder(
			user,
			userOrderRequest.getBody()
		);
		return Api.OK(response);
	}

	//현재 진행 중인 주문 건 확인
	@GetMapping("/current")
	public Api<List<UserOrderDetailResponse>> current(
		@Parameter(hidden = true)
		@UserSession
		User user
	){
		var response = userOrderBusiness.current(user);
		return Api.OK(response);
	}

	//과거 주문 내역 확인
	@GetMapping("/history")
	public Api<List<UserOrderDetailResponse>> history(
		@Parameter(hidden = true)
		@UserSession
		User user
		) {
		var response = userOrderBusiness.history(user);
		return Api.OK(response);

	}

	//주문 1건에 대한 내역 확인
	@GetMapping("/id/{orderId}")
	public Api<UserOrderDetailResponse> read(
		@Parameter(hidden = true)
		@UserSession User user,
		@PathVariable Long orderId
		) {
		var response = userOrderBusiness.read(user, orderId);
		return Api.OK(response);

	}


}
