package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){ //데이터를 제너릭 형태로 받아서
        var api = new Api<T>();
        api.result = Result.OK(); //Result의 OK로 세팅해서 return 한다.
        api.body = data;
        return api;
    }

    // 1. 에러는 바디에 넣을 내용이 없어서 제너릭 타입이 아니다.
    public static Api<Object> ERROR(Result result){ //데이터를 제너릭 형태로 받아서
        var api = new Api<Object>();
        api.result = result;
        return api;
    }

    // 2. result에 에러 코드 인터페이스 넘긴다.
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){ //데이터를 제너릭 형태로 받아서
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs);
        return api;
    }

    // 3. 예외 처리
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, tx);
        return api;
    }


    // 4. 메세지 세팅
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String description){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, description);
        return api;
    }


}
