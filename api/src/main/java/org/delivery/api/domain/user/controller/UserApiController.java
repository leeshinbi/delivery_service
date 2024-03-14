package org.delivery.api.domain.user.controller;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController { //로그인을 해야만 쓸 수 있는 API

    private final UserBusiness userBusiness;

    // 사용자가 로그인 했을 때 나의 정보를 가져가는 api
    @GetMapping("/me")
    public Api<UserResponse> me(
        @UserSession User user
    ){
        var response = userBusiness.me(user);
        return Api.OK(response);
    }
}
