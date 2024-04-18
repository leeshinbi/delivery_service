package org.delivery.storeadmin.domain.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession implements UserDetails { //UserDetail 인터페이스를 재정의

	// user 정보를 담는다.
	private Long userId;

	private String email;

	private String password;

	private StoreUserStatus status;

	private StoreUserRole role;

	private LocalDateTime registeredAt;

	private LocalDateTime unregisteredAt;

	private LocalDateTime lastLoginAt;

	// store 정보를 담는다.
	private Long storeId;
	private String storeName;


	//메서드 재정의
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(this.role.toString())); //role을 문자 형태로 반환
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override // 만료가 안된 상태 == REGISTERED
	public boolean isAccountNonExpired() {
		return this.status == StoreUserStatus.REGISTERED;
	}

	@Override // 잠긴 상태가 아닌 상태 == REGISTERED
	public boolean isAccountNonLocked() {
		return this.status == StoreUserStatus.REGISTERED;
	}

	@Override // 암호가 만료되지 않은 상태 == REGISTERED
	public boolean isCredentialsNonExpired() {
		return this.status == StoreUserStatus.REGISTERED;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}