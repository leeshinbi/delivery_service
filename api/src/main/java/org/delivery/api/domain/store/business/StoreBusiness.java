package org.delivery.api.domain.store.business;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.contoller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.contoller.model.StoreResponse;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.enums.StoreCategory;

@RequiredArgsConstructor
@Business
public class StoreBusiness {

	private final StoreService storeService;
	private final StoreConverter storeConverter;

	// 상점 등록 요청
	public StoreResponse register(
		StoreRegisterRequest storeRegisterRequest
	){ // request -> entity -> response
		var entity = storeConverter.toEntity(storeRegisterRequest);
		var newEntity = storeService.register(entity);
		var response = storeConverter.toResponse(newEntity);
		return response;
	}

	//등록된 상점 조회
	public List<StoreResponse> searchCategory (
		StoreCategory storeCategory
	){
		//entity list -> response list
		var storeList = storeService.searchByCategory(storeCategory);

		// 스트림 통해서 데이터 변환
		// (entity의 내용이 response가 들어간 List로 바뀜)
		return storeList.stream()
			.map(storeConverter::toResponse)
			.collect(Collectors.toList());
	}

}
