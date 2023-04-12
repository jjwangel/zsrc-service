package com.zsebank.advice;

import com.zsebank.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获外得
 * */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @SuppressWarnings("all")
    public CommonResponse<String> handlerServiceException(
            HttpServletRequest req,Exception ex
    ){
        CommonResponse<String> stringCommonResponse = new CommonResponse<>(
                -1,"error"
        );
        stringCommonResponse.setData(ex.getMessage());
        log.error("service has error:[{}]",ex.getMessage(),ex);
        return stringCommonResponse;
    }
}
