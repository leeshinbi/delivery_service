package org.delivery.storeadmin.domain.user.controller.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreUserResponse {

	private UserResponse user;
	private StoreResponse store;

	/*inner (static) class를 둔다.*/
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserResponse{ //store user 정보
		private Long id;
		private String email;

		private StoreUserStatus status;

		private StoreUserRole role;

		private LocalDateTime registeredAt;

		private LocalDateTime unregisteredAt;

		private LocalDateTime lastLoginAt;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class StoreResponse{ // 어떤 스토어 속해있는지에 대한 정보
		private Long id;
		private String name;
	}

}
