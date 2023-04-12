package com.zsebank.advice;

import com.zsebank.annotation.IgnoreResponseAdvice;
import com.zsebank.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现统一响应
 * */
@RestControllerAdvice(value = "com.zsebank")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否需要对响应进行处理
     * */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }

        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }

        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        CommonResponse<Object> commonResponse =new CommonResponse<>(0,"");

        if(null == o){
            return commonResponse;
        } else if(o instanceof CommonResponse){
            commonResponse=(CommonResponse<Object>) o;
        } else{
            commonResponse.setData(o);
        }
        return commonResponse;
    }
}
