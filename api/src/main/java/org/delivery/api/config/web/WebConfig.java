package org.delivery.api.config.web;

import lombok.RequiredArgsConstructor;
import org.delivery.api.interceptor.AuthorizationInterceptor;
import org.delivery.api.resolver.UserSessionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor; //@Component라서 주입받을 수 있다.
    private final UserSessionResolver userSessionResolver;

    // open-api의 하위들은 검증 제외 항목
    private List<String> OPEN_API = List.of(
            "/open-api/**"
    );

    // swagger도 검증 제외
    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    // 그 외의 검증 제외 항목들
    private List<String> DEFAULT_EXCLUDE = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authorizationInterceptor) // 리스트 외의 것들은 검증한다.
                .excludePathPatterns(OPEN_API) // 리스트로 넣은 값들을 여기에 넣어준다.
                .excludePathPatterns(SWAGGER)
                .excludePathPatterns(DEFAULT_EXCLUDE);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userSessionResolver);
    }

}
