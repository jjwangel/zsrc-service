package com.zsebank.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.zsebank.constant.SignatureConstant;
import com.zsebank.entity.AuthSignatureInfo;

import java.util.Date;

/**
 * @author jianjiawen
 * 签名工具类
 */
public class SignatureUtil {
    /**
     * 生成签名
     * **/
    public static String generateSignature(String privateKey) {
        RSA rsa =new RSA(privateKey,null);
        long now = DateUtil.current();
        AuthSignatureInfo authSignatureInfo = new AuthSignatureInfo(now, RandomUtil.randomNumbers(6));

        return JWT.create()
                .addPayloads(BeanUtil.beanToMap(authSignatureInfo))
                .setSigner(JWTSignerUtil.rs256(rsa.getPrivateKey()))
                .setExpiresAt(DateUtil.offset(DateUtil.date(now), DateField.MINUTE,SignatureConstant.EXPIRE_TIME))
                .sign();
    }
}
