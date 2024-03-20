package org.delivery.api.domain.store.converter;

import java.util.Optional;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.contoller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.contoller.model.StoreResponse;
import org.delivery.db.store.StoreEntity;

@Converter
public class StoreConverter  {

	public StoreEntity toEntity( //상점 등록 시 데이터를 엔티티로 변환
		StoreRegisterRequest request
	){
		return Optional.ofNullable(request)
			.map(it ->{
				return StoreEntity.builder()
					.name(request.getName())
					.address(request.getAddress())
					.category(request.getStoreCategory())
					.minimumAmount(request.getMinimumAmount())
					.minimumDeliveryAmount(request.getMinimumDeliveryAmount())
					.thumbnailUrl(request.getThumbnailUrl())
					.phoneNumber(request.getPhoneNumber())
					.build()
					;
			})
			.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
	}

	public StoreResponse toResponse(
		StoreEntity entity
	){
		return Optional.ofNullable(entity)
			.map(it ->{
				return StoreResponse.builder()
					.id(entity.getId())
					.name(entity.getName())
					.status(entity.getStatus())
					.category(entity.getCategory())
					.address(entity.getAddress())
					.minimumAmount(entity.getMinimumAmount())
					.minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
					.thumbnailUrl(entity.getThumbnailUrl())
					.phoneNumber(entity.getPhoneNumber())
					.star(entity.getStar())
					.build();
			})
			.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
	}
}
