package org.delivery.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    // 사전에 검증
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url: {}", request.getRequestURL());

        // WEB ,chrome 의 경우 GET, POST OPTIONS -> 검문 없이 통과!
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }

        // js. html. png resource 를 요청하는 경우 -> 검문 없이 통과!
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }


        var accessToken = request.getHeader("authorization-token"); // 헤더 값 꺼내오기
        if(accessToken == null){
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        var userId = tokenBusiness.validationAccessToken(accessToken);

        if (userId != null) {
            // 한가지 요청에 대해서 글로벌하게 저장할 수 있는 저장소에다가 저장
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            // 단일 리퀘스트 단위로 저장
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
            return true; // 인증 성공
        }
        throw new ApiException(ErrorCode.BAD_REQUEST, "인증 실패");
    }
}
