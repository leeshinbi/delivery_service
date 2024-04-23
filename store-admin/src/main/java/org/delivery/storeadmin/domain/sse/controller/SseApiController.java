package org.delivery.storeadmin.domain.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseApiController {

	private final SseConnectionPool sseConnectionPool;
	private final ObjectMapper objectMapper;

	// 사용자 인증 정보를 가져와서 POOL에 등록한 후 'SseEmitter'를 반환
	@GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseBodyEmitter connect(
		@Parameter(hidden = true)
		@AuthenticationPrincipal UserSession userSession
	){
		log.info("login user {}", userSession);

		var userSseConnection = UserSseConnection.connect(
			userSession.getStoreId().toString(),
			sseConnectionPool,
			objectMapper
		);

		sseConnectionPool.addSession(userSseConnection.getUniqueKey(), userSseConnection);

		return userSseConnection.getSseEmitter();
	}

	// 특정 사용자의 SSE 연결을 조회 후, 해당 연결이 존재하면 "hello world" 메시지 전송
	@GetMapping("/push-event")
	public void pushEvent(
		@Parameter(hidden = true)
		@AuthenticationPrincipal UserSession userSession
	){
		var userSseConnection = sseConnectionPool.getSession(userSession.getStoreId().toString());

		Optional.ofNullable(userSseConnection)
			.ifPresent(it ->{
				it.sendMessage("hello world");
			});
	}
}