package org.delivery.api.common.exception;

import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeIfs;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription;


    // 에러 코드 인터페이스는 무조건 필수
    public ApiException(ErrorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getDescription()); //부모한테 바로 넘겨줌
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    // 에러 코드 인터페이스의 description이 아니라 내가 정의한 메시지를 지정하는 코드
    public ApiException(ErrorCodeIfs errorCodeIfs , String errorDescription){
        super(errorDescription);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    // Throwalbe 던져 주는 코드
    public ApiException(ErrorCodeIfs errorCodeIfs , Throwable tx){
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    // 위의 세 가지를 모두 다 받는 코드
    public ApiException(ErrorCodeIfs errorCodeIfs , Throwable tx , String errorDescription){
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }
}
