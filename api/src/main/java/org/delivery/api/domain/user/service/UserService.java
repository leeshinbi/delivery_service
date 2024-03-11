package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * User 도메인 로직을 처리 하는 서비스
 */
@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;


    //외부에서 해당 서비스를 사용하고 싶다면 항상 userEntity를 넣어야 된다.
    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it ->{
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }


    public UserEntity login(
        String email,
        String password
    ){
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public UserEntity getUserWithThrow(
        String email,
        String password
    ){
        //등록된 사용자가 있는지 체크 , 없으면 null
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
            email,
            password,
            UserStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

}
