package com.zsebank.entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jianjiawen
 * 签名信息
 * **/
@Data
public class AuthSignatureInfo implements Serializable {

    /** 当前的时间戳 */
    private final long date;

    /** 6位随机数 */
    private final String nonce;

    public AuthSignatureInfo(long date, String nonce) {
        this.date = date;
        this.nonce = nonce;
    }


}
