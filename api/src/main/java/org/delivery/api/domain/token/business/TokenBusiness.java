package org.delivery.api.domain.token.business;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.db.user.UserEntity;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

	private final TokenService tokenService;
	private final TokenConverter tokenConverter;

	/**
	 * 1. UserEntity 에서 'userId' 추출
	 * 2. Access,Refresh Token 발행
	 * 3. converter 통해서 TokenResponse 로 변환
	 */
	public TokenResponse issueToken(UserEntity userEntity){

		// UserEntity가 null 값일 수도 있음
		return Optional.ofNullable(userEntity)
			// 먼저 UserEntity(=ue)가 값으로 넘어옴
			.map(ue -> {
				return ue.getId();
			})
			// 그 다음엔 'userId'가 값으로 넘어옴
			.map(userId -> {
				var accessToken = tokenService.issueAccessToken(userId);
				var refreshToken = tokenService.issueRefreshToken(userId);
				return tokenConverter.toResponse(accessToken, refreshToken);

			})
			.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));


	}

}
