package com.zsebank.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 签名信息
 * **/
@Data
public class AuthSignatureInfo implements Serializable {

    /** 时间戳 */
    private final Long timeMillis;

    /** 6位随机数 */
    private final String strNonce;

    public AuthSignatureInfo(Long timeMillis, String strNonce) {
        this.timeMillis = timeMillis;
        this.strNonce = strNonce;
    }


}
