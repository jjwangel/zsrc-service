package com.zsebank.util;

import com.alibaba.fastjson2.JSON;
import com.zsebank.config.SignatureInfoConfig;
import com.zsebank.entity.AuthSignatureInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * 解释签名工具
 * */
@Slf4j
@Component
public class SignatureParseUtil {
    private final SignatureInfoConfig signatureInfoConfig;

    public SignatureParseUtil(SignatureInfoConfig signatureInfoConfig) {
        this.signatureInfoConfig = signatureInfoConfig;
    }

    /**
     * 解释签名，获取签名对像
     * */
    public AuthSignatureInfo parse(String signature) throws Exception {
        // signature="eyJhbGciOiJSUzI1NiJ9.eyJTSUdOQVRVUkVfSU5GTyI6IntcInN0ck5vbmNlXCI6XCI4MzQ3NTdcIixcInRpbWVNaWxsaXNcIjoxNjgyMTU0MjA2OTY1fSIsImp0aSI6IjQ0MzliYjU5LTgyMDEtNGJlMy1iMTUyLTZjODFiNGE2YjdkOSIsImV4cCI6MTY4MjE3OTIwMH0.LgfgmWqKnxYQNwhudbVVmihoTI4eT8QbnabnnDfkDE4uTDlW9JBYDdvFdybcARRPgldjvp9n7ayVg534KdmrW7v8hhlt57gxy8wAIleUHFE_pLQdDC2dOwqO1pP0OTAjvgv8eFXLdNPxxBX0uzAziXYFwkQ1nl4Hu220uVHVZCNmDVOzgeoiKNi2crPKdAxUx4xxF06EpaU0GKdddBUwJh6raaG3N0W1V9_gj5Y5jWBwgmbUqMZIQyzuXRr0Czvl5F5PoT3fCaKSKppAfbP6BH_PEgb85PzWpEanb9nWseKgYuVoJBdDQr56fY8-fz31WEQI1GFkfeNrj8Emm7LpAQ";

        Jws<Claims> claimsJws = parseSignature(signature, getPublicKey());
        Claims body = claimsJws.getBody();

        // 如果 Token 已经过期了, 返回 null
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            return null;
        }

        // 返回 Token 中保存的用户信息
        return JSON.parseObject(
                body.get(signatureInfoConfig.getSignatureInfoKey()).toString(),
                AuthSignatureInfo.class
        );
    }

    /**
     * 通过公钥去解析 JWT Token
     * */
    private Jws<Claims> parseSignature(String signature, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(signature);
    }

    /**
     * 根据本地存储的公钥获取到 PublicKey 对象
     * */
    private PublicKey getPublicKey() throws Exception {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(signatureInfoConfig.getPublicKey())
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

}
