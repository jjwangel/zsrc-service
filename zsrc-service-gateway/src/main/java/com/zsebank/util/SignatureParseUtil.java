package com.zsebank.util;

import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.alibaba.fastjson2.JSON;
import com.zsebank.constant.SignatureConstant;
import com.zsebank.entity.AuthSignatureInfo;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jianjiawen
 * 解释签名工具
 *
 **/
@Slf4j
public class SignatureParseUtil {
    /**
     * 解释签名，获取签名对像
     * @param signature 签名
     * @param strPublicKey 公钥
     * @return 返回 AuthSignatureInfo 对像
     * */
    public static AuthSignatureInfo parse(String signature,String strPublicKey) throws Exception {
        RSA rsa = new RSA(null,strPublicKey);
        JWT jwt = JWT.of(signature).setSigner(JWTSignerUtil.rs256(rsa.getPublicKey()));

        return jwt.validate(SignatureConstant.LEEWAY_TIME)? JSON.parseObject(jwt.getPayloads().toString(),AuthSignatureInfo.class):null;
    }

}
