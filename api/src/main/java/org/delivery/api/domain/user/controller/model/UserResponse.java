package org.delivery.api.domain.user.controller.model;

import lombok.*;
import org.delivery.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    // password는 내려가면 안되니까 제외

    private UserStatus status;

    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}
