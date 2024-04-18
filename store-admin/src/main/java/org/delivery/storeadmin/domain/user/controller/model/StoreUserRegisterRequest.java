package org.delivery.storeadmin.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storeuser.enums.StoreUserRole;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserRegisterRequest { //상점 가입 신청 시 필요 정보

	@NotBlank
	private String storeName;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private StoreUserRole role;
}