package com.zsebank.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用响应对像定义
 * {
 *     “code": 0,
 *     "message": "",
 *     "data": {}
 * }
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
    /** 错误码 */
    private Integer code;

    /** 错误信息 */
    private String message;

    /** 泛型响应数据 */
    private T data;

    public CommonResponse(Integer code,String message)
    {
        this.code=code;
        this.message= message;
    }

}
