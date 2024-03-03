package org.delivery.api.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUND(400 , 1404 , "사용자를 찾을 수 없음."),

    ;
    private final Integer httpStatusCode; //enum 클래스에 들어간 값은 변경되지 않아야 하기 때문에 final로 선언

    private final Integer errorCode;

    private final String description;

}
