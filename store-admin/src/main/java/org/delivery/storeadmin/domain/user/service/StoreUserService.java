package org.delivery.storeadmin.domain.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.db.storeuser.StoreUserRepository;
import org.delivery.db.storeuser.enums.StoreUserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreUserService {

	public final StoreUserRepository storeUserRepository;
	private final PasswordEncoder passwordEncoder; // @Bean으로 등록했기 때문에 인터페이스 형식으로 불러올 수 있다.

	// 등록
	public StoreUserEntity register(
		StoreUserEntity storeUserEntity
	){
		storeUserEntity.setStatus(StoreUserStatus.REGISTERED);
		storeUserEntity.setPassword(passwordEncoder.encode(storeUserEntity.getPassword()));
		storeUserEntity.setRegisteredAt(LocalDateTime.now());

		return storeUserRepository.save(storeUserEntity);
	}

	// 조회
	public Optional<StoreUserEntity> getRegisterUser(String email) {
		return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email,
			StoreUserStatus.REGISTERED);
	}

}
