package org.delivery.api.domain.store.contoller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.store.business.StoreBusiness;
import org.delivery.api.domain.store.contoller.model.StoreResponse;
import org.delivery.db.store.enums.StoreCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreApiController {

	private final StoreBusiness storeBusiness;

	// 가맹점 조회 (로그인된 사용자만 가능)
	@GetMapping("/search")
	public Api<List<StoreResponse>> search(
		@RequestParam(required = false) //필수 값 아님
		StoreCategory storeCategory
	){
		var response = storeBusiness.searchCategory(storeCategory);
		return Api.OK(response);
	}
}
