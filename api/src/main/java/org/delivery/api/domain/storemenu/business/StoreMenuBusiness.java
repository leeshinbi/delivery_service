package org.delivery.api.domain.storemenu.business;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {

	private final StoreMenuService storeMenuService;
	private final StoreMenuConverter storeMenuConverter;

	// 메뉴 등록 요청
	public StoreMenuResponse register(
		StoreMenuRegisterRequest request
	){
		// req -> entity -> save -> response
		var entity = storeMenuConverter.toEntity(request);
		var newEntity = storeMenuService.register(entity);
		var response = storeMenuConverter.toResponse(newEntity);
		return response;
	}

	// 특정 가게에 있는 메뉴 내용을 검색
	public List<StoreMenuResponse> search(
		Long storeId
	){
		var list = storeMenuService.getStoreMenuByStoreId(storeId);

		// 스트림 통해서 데이터 변환
		// (entity의 내용이 response가 들어간 List로 바뀜)
		return list.stream()
			.map(storeMenuConverter::toResponse)
			.collect(Collectors.toList());
	}
}
