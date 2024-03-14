package org.delivery.api.domain.token.converter;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDto;

@RequiredArgsConstructor
@Converter
public class TokenConverter {


	public TokenResponse toResponse(
		TokenDto accessToken,
		TokenDto refreshToken
	){
		// null 이라면, 에러 코드 던짐
		Objects.requireNonNull(accessToken, ()->{throw new ApiException(ErrorCode.NULL_POINT);});
		Objects.requireNonNull(refreshToken, ()->{throw new ApiException(ErrorCode.NULL_POINT);});

		return TokenResponse.builder()
			.accessToken(accessToken.getToken())
			.accessTokenExpiredAt((accessToken.getExpiredAt()))
			.refreshToken(refreshToken.getToken())
			.refreshTokenExpiredAt(refreshToken.getExpiredAt())
			.build();
	}

}
