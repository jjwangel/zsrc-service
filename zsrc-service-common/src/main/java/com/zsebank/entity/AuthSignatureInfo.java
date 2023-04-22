package com.zsebank.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthSignatureInfo implements Serializable {

    private final Long timeMillis;

    private final String strNonce;

    public AuthSignatureInfo(Long timeMillis, String strNonce) {
        this.timeMillis = timeMillis;
        this.strNonce = strNonce;
    }


}
