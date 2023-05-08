package com.zsebank.util;

import com.alibaba.fastjson2.JSON;
import com.zsebank.constant.SignatureConstant;
import com.zsebank.entity.AuthSignatureInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * 解释签名工具
 * */
@Slf4j
public class SignatureParseUtil {
    /**
     * 解释签名，获取签名对像
     * @param signature 签名
     * @param strPublicKey 公钥
     * @return 返回 AuthSignatureInfo 对像
     * */
    public static AuthSignatureInfo parse(String signature,String strPublicKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getPublicKey(strPublicKey)).parseClaimsJws(signature);
        Claims body = claimsJws.getBody();

        // 如果 Signature 已经过期了, 返回 null
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            return null;
        }

        // 返回 Signature 中保存的 AuthSignatureInfo 对像
        return JSON.parseObject(
                body.get(SignatureConstant.SIGNATURE_INFO_KEY).toString(),
                AuthSignatureInfo.class
        );
    }

    /**
     * 根据公钥获取到 PublicKey 对象
     * @param strPublicKey 公钥
     * @return 返回 PublicKey 对像
     * */
    private static PublicKey getPublicKey(String strPublicKey) throws Exception {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(strPublicKey)
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

}
