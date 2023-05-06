package com.zsebank.service;


import com.zsebank.entity.AuthSignatureInfo;

/**
 * @author micha
 * 签名解释服务接口
 */
public interface SignatureParseService {
    /** 签名解释，返回AuthSignatureInfo对像 */
    public AuthSignatureInfo SignatureParse(String strAppId,String strSignature);
}
