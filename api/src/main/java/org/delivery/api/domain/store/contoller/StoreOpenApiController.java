package org.delivery.api.domain.store.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.store.business.StoreBusiness;
import org.delivery.api.domain.store.contoller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.contoller.model.StoreResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store")
public class StoreOpenApiController {

	private final StoreBusiness storeBusiness;

	//가맹점 등록 (가맹점 점주들이 등록, 로그인 필요 x)
	@PostMapping("/register")
	public Api<StoreResponse> register(
		@Valid
		@RequestBody Api<StoreRegisterRequest> request
	){
		var response = storeBusiness.register(request.getBody());
		return Api.OK(response);
	}

}
