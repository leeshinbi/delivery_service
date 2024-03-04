package org.delivery.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    // 지정된 데이터가 들어갈 수 있도록 Enum으로 관리한다.
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;

    private final String description;
}
