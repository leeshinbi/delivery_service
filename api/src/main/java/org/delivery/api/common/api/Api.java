package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
