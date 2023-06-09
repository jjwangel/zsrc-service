package com.zsebank.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.jwt.signers.AlgorithmUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA 非对称加密算法：生成公钥和私钥
 * */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RSATest {

    @Test
    public void generateKeyBytes() throws Exception {
        KeyPairGenerator keyPairGenerator =KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        // 生成公钥和私钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥和私钥对像
        RSAPublicKey publicKey =(RSAPublicKey) keyPair.getPublic();
        RSAPrivateCrtKey privateCrtKey =(RSAPrivateCrtKey) keyPair.getPrivate();

        log.info("private key:[{}]", Base64.encode(privateCrtKey.getEncoded()));
        log.info("public key:[{}]",Base64.encode(publicKey.getEncoded()));
    }

    @Test
    public void generateKeyBytes2(){
        SecretKey secretKey = KeyUtil.generateKey(AlgorithmUtil.getAlgorithm("rs256"));
        log.info(secretKey.getAlgorithm());
    }

}
