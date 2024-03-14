package org.delivery.api.domain.token.service;


import java.util.HashMap;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;


/**
 *  Token에 대한 도메인 로직을 담당 (따라서 토큰만 처리)
 */
@RequiredArgsConstructor
@Service
public class TokenService {

	private final TokenHelperIfs tokenHelperIfs;

	public TokenDto issueAccessToken(Long userId){
		var data = new HashMap<String, Object>();
		data.put("user Id", userId);
		return tokenHelperIfs.issueAccessToken(data);
	}

	public TokenDto issueRefreshToken(Long userId){
		var data = new HashMap<String, Object>();
		data.put("user Id", userId);
		return tokenHelperIfs.issueRefreshToken(data);
	}

	// userId를 반환하기 때문에 반환타입은 Long
	public Long validationToken(String token) {
		var map = tokenHelperIfs.validationTokenWithThrow(token);
		var userId = map.get("userId");

		// map에 userId가 없는 경우
		Objects.requireNonNull(userId, ()->{
			throw new ApiException(ErrorCode.NULL_POINT);});

		return Long.parseLong(userId.toString());
	}
}
