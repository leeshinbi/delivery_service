package org.delivery.storeadmin.domain.sse.connection.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
public class UserSseConnection {
	private final String uniqueKey;
	private final SseEmitter sseEmitter;
	private final ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs;

	private final ObjectMapper objectMapper;

	private UserSseConnection(
		String uniqueKey,
		ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
		ObjectMapper objectMapper
	){
		// key(연결 고유 식별자) 초기화
		this.uniqueKey = uniqueKey;

		// 클라이언트에 이벤트를 전송하는데 사용되는 객체(sse) 초기화
		this.sseEmitter = new SseEmitter(60 * 1000L); //defalut ms

		// call back 초기화 (연결을 관리하는 커넥션 풀 인터페이스)
		this.connectionPoolIfs = connectionPoolIfs;

		// object mapper 초기화
		this.objectMapper = objectMapper;

		// 연결이 완료 되면,
		this.sseEmitter.onCompletion(()->{
			// 연결 풀에서 이 객체를 제거
			this.connectionPoolIfs.onCompletionCallback(this);
		});

		// 타임 아웃 발생 하면,
		this.sseEmitter.onTimeout(()->{
			// 연결 종료
			this.sseEmitter.complete();
		});

		// session 에 추가

		// onopen 메시지
		this.sendMessage("onopen","connect");
	}

	public static UserSseConnection connect(
		String uniqueKey,
		ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
		ObjectMapper objectMapper
	){ // 객체 생성 시 필요한 모든 초기화 진행
		return new UserSseConnection(uniqueKey, connectionPoolIfs, objectMapper);
	}

	// 이벤트 이름과 데이터 객체를 받아 클라이언트에게 JSON 형태로 이벤트 전송
	public void sendMessage(String eventName, Object data) {
		try {
			var json = this.objectMapper.writeValueAsString(data);
			var event = SseEmitter.event()
				.name(eventName)
				.data(json)
				;

			this.sseEmitter.send(event);
		} catch (IOException e) {
			this.sseEmitter.completeWithError(e);
		}
	}

	// 데이터 객체만 받아 클라이언트에게 JSON 형태로 이벤트 전송
	public void sendMessage(Object data){
		try {
			var json = this.objectMapper.writeValueAsString(data);

			var event = SseEmitter.event()
				.data(json)
				;
			this.sseEmitter.send(event);
		} catch (IOException e) {
			this.sseEmitter.completeWithError(e);
		}
	}
}