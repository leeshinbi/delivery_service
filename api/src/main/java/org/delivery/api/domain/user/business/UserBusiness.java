package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;


    /**
     * 사용자에 대한 가입처리 로직
     * 1. request를 entity 변환
     * 2. entity를 저장한다.
     * 3. 저장된 entity를 다시 response로 변환
     * 4. response 반환
     */
    public UserResponse register(UserRegisterRequest request) {

        var entity = userConverter.toEntity(request); // 1번
        var newEntity = userService.register(entity); // 2번
        var response = userConverter.toResponse(newEntity); // 3번

        return response; // 4번


        // 위의 내용을 함수형으로 작성하면? (가독성의 차이!)

        /** return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));
         */

    }


    /** 로그인 로직
     * 1. email 과 password를 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. token 생성
     * 4. token response 를 내려준다.
     */
	public TokenResponse login(UserLoginRequest request) {

        var userEntity = userService.login(request.getEmail(), request.getPassword());
        // 토큰 생성 로직
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;

	}
}
