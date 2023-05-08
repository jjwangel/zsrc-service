package com.zsebank.service;


import com.zsebank.entity.AuthSignatureInfo;

/**
 * @author micha
 * 签名解释服务接口
 */
public interface SignatureParseService {
    /**
     * 签名解释，返回AuthSignatureInfo对像
     * @param strAppId 应用ID
     * @param strSignature 签名
     * @return 返回 AuthSignatureInfo 对像
     * **/
    AuthSignatureInfo signatureParse(String strAppId,String strSignature) throws Exception;
}
