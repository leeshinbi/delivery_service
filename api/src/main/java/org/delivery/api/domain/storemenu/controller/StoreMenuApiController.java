package org.delivery.api.domain.storemenu.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.storemenu.business.StoreMenuBusiness;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-menu")
public class StoreMenuApiController {

	private final StoreMenuBusiness storeMenuBusiness;

	// 특정 가게를 누르면 해당 가게의 메뉴 정보가 나오는데 그 메뉴 정보를 내려준다.
	@GetMapping("/search")
	public Api<List<StoreMenuResponse>> search(
		@RequestParam(value = "storeId") Long storeId
	){
		var response = storeMenuBusiness.search(storeId);
		return Api.OK(response);
	}
}
