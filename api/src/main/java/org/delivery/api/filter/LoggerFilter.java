package org.delivery.api.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

//우리 서버로 어떠한 정보가 왔다 갔다 했는지 로그로서 볼 수 있도록 필터를 설정해서 로그를 수집한다.
@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper( (HttpServletRequest) request ); //request 형변환해서 새로운 객체 생성
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response); //response 형변환해서 새로운 객체 생성

        chain.doFilter(req,res); //만든 새로운 객체를 필터에 넘겨준다. 뒷단에서 받는 모든 리퀘스트는 랩핑된 객체들이 넘어간다.

        //request 정보
        var headerNames = req.getHeaderNames(); //http 요청에서 모든 헤더의 이름을 가져옴
        var headerValues = new StringBuilder(); //헤더 정보를 문자열로 변환하여 저장하기 위한 객체 생성

        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = req.getHeader(headerKey);

            // authrization-token : ??? , user-agent: ??? 형식
            headerValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        // 들어올 때 로그
        log.info(">>>>> uri : {} , method : {} , header : {} , body : {}", uri, method, headerValues, requestBody);


        // response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey ->{
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        var responseBody = new String(res.getContentAsByteArray());

        // 나갈 때 로그
        log.info("<<<<< uri : {} , method : {} , header : {} , body : {}", uri, method, responseHeaderValues, responseBody);


        res.copyBodyToResponse(); //추가를 하지 않는다면, response 바디가 빈 상태로 나간다.

    }
}
