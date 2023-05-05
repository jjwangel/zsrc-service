package com.zsebank.utils;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.zsebank.constant.SignatureConstant;
import com.zsebank.entity.AuthSignatureInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author jianjiawen
 * 签名工具类
 */
public class SignatureUtil {
    /**
     * 生成签名
     * **/
    public static String generateSignature(String strPrivateKey) throws Exception {
        AuthSignatureInfo authSignatureInfo = new AuthSignatureInfo(System.currentTimeMillis(), RandomUtil.randomNumbers(6));
        // 计算超时时间
//        ZonedDateTime zdt = LocalDate.now().plus(SignatureConstant.EXPIRE_TIME, ChronoUnit.MINUTES)
//                .atStartOfDay(ZoneId.systemDefault());
//        Date expireDate = Date.from(zdt.toInstant());

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, SignatureConstant.EXPIRE_TIME);
        System.out.println(nowTime.getTime().toString());

        return Jwts.builder()
                // jwt payload --> KV
                .claim(SignatureConstant.SIGNATURE_INFO_KEY, JSON.toJSONString(authSignatureInfo))
                // jwt id
                .setId(UUID.randomUUID().toString())
                // jwt 过期时间
                .setExpiration(nowTime.getTime())
                // jwt 签名 --> 加密
                .signWith(getPrivateKey(strPrivateKey), SignatureAlgorithm.RS256)
                .compact();

    }

    private static PrivateKey getPrivateKey(String strPrivateKey) throws Exception {

        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(strPrivateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(priPKCS8);
    }

}
