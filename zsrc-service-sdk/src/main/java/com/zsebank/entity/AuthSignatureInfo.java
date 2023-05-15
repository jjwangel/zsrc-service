package com.zsebank.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 签名信息
 * **/
@Data
public class AuthSignatureInfo implements Serializable {

    /** 当前时间 */
    private final Date now;

    /** 6位随机数 */
    private final String nonce;

    public AuthSignatureInfo(Date now, String nonce) {
        this.now = now;
        this.nonce = nonce;
    }


}
