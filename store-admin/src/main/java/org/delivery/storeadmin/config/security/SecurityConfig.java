package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/* config 설정 */
@Configuration
@EnableWebSecurity  // security 활성화
public class SecurityConfig {

	private List<String> SWAGGER = List.of(
		"/swagger-ui.html",
		"/swagger-ui/**",
		"/v3/api-docs/**"
	);

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()  // 정적 자원에 대해 모든 요청 허용
				.requestMatchers(SWAGGER.toArray(new String[0])).permitAll()  // Swagger UI에 대해 인증 없이 접근 허용
				.requestMatchers("/open-api/**").permitAll()  // Open API 경로에 대해 인증 없이 접근 허용
				.anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
			)
			.formLogin(Customizer.withDefaults());  // 기본 로그인 폼 사용

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		// 비밀번호 해시를 위한 BCryptPasswordEncoder 반환
		return new BCryptPasswordEncoder();
	}
}
