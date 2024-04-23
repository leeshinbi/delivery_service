package org.delivery.storeadmin.domain.sse.connection;

import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseConnectionPool implements ConnectionPoolIfs<String,UserSseConnection> {
	private static final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

	@Override // uniqueKey를 사용하여 UserSseConnection 객체 저장
	public void addSession(String uniqueKey, UserSseConnection userSseConnection) {
		connectionPool.put(uniqueKey, userSseConnection);
	}

	@Override // 지정된 키에 해당하는 UserSseConnection 객체를 반환
	public UserSseConnection getSession(String uniqueKey) {
		return connectionPool.get(uniqueKey);
	}

	@Override // 연결이 종료되면 로그를 기록하고, 연결 풀에서 uniqueKey를 사용하여 해당 세션을 제거
	public void onCompletionCallback(UserSseConnection session) {
		log.info("call back connection pool completion : {}", session);
		connectionPool.remove(session.getUniqueKey());
	}
}