package com.zsebank;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.zsebank.constant.SignatureConstant;
import com.zsebank.entity.AuthSignatureInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class HutoolTest {

    @Test
    public void signature1(){
        RSA rsa = new RSA();

//获得私钥
        rsa.getPrivateKey();
        log.info(StrUtil.format("getPrivateKeyBase64:[{}]",rsa.getPrivateKeyBase64()));

//获得公钥
        rsa.getPublicKey();
        rsa.getPublicKeyBase64();
        log.info(StrUtil.format("getPublicKeyBase64:[{}]",rsa.getPublicKeyBase64()));

//公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);


//Junit单元测试
//Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

//私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        log.info(StrUtil.format("encrypt:[{}]",encrypt2.toString()));
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        log.info(StrUtil.format("decrypt:[{}]",decrypt2.toString()));
    }

    @Test
    public void signature2(){
        RSA rsa =new RSA("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQxCcS5u3GexTVveuRr8/mB7J4r9tBv5VI/HHMVcpmpa/waNRXfTErik6qezJb8uF5EYnkqgZzWdNhW6/FPm8TXNvdYVTkjlCmTAUF0Xe0RnEnCdlfkfIBcYUSEbRMPFp8CtbXzC2tmpMZ30j4Su4d21wFw5gebCR2n7AHuBIUKDYCPgQZeWdv++Cr66PVY6DOgVZb32bFgb1TxhLqL0fuJ334m0uoQReC03bcxq2NU2vFdWNtbe1Xqktx++apuABbfOyiu/ZEzS/e9QezrtHHjV9iQHJgCnAe3IAbynSyM3G2T78PGIoiTunvkJEqm9gt+M8ip54NguzcDzrvI2A7AgMBAAECggEAZygfX8zGqqSSGC0Za9sIHjZl6rIFXziRvEyHGsAXVkXCtWE9zoobO5d2ruzS2cqc+JSS2ip+Jjxf8ARRoA/5Pqbw1TPCNoti1N1exuKYlhGMF/FgwEVTWcv2swWCoMBxCer/ZQ7NjeOT5t/BM4zRWN0zGbhi8WrGIPFxkk6MM2fOYTrwBI49zPpyZCu7roaVm7AYGLSIzMDsB5OVKjpn77huk1MNGwPjP5+PI+6WiElB/BHDnTmXH1Isns5E9KAosyBboyu9huTUTDkpZpdI7nn+xAh7E01jbj7ASoA3dKLijEEdTTfdC89CvYQZ7QQBpafdb1abvq/mTHk2CxaaoQKBgQDgU8jIXg71z+HCyCD4XtD6NYgW/f/o3a8jLjsvpxin/mn0/Q6WUY4EvUdQzUihzhW8rYYpuq7hAtXeQLLEOudcmoyfvlQKwec7LQuoBXr7GHqY5Cl2eb6bMqAE0zjh6qJFrWMeCqmbLrriCyN5XLr9ZIVvTbt0v/SlmeCVcoKs/wKBgQClNKrFz3d/4eHa40wxzHifWWAb8yv80CdeTfp4nsSxYP4IRXURh/yEW3LDhcIYKfvS+yU72kY/1VaFWdXMe/y0wEwHUd6yHQiVU+x04qumw2feNOajsAu5OxMRsnlmG/O3oMs/TATsyFBv7rz/rZzjPeA0ORBTBfcmlb/bQCvAxQKBgGT8ctP4u+Ve+zQTofwNuygYdzZajB0JxzEgO9a883WVK5/1V97FwhhSGUu/zmXA/7obp90bRGTZQOw31gJOvh7LGGlFZlIdbnoOUkQGi5GEOJShiiTis7LsPGtPpzjUxs51OryocigBBnKCLysQWsF9wjAUH0J0s67lMdw1W6yDAoGAdnHjOj171NETSAcM2utZVA4VLRTUZcarYx6PRKm4JeB/YVjDDVWtAww5pNsihIadSO/NCzN8iEmhZaw58Ai47rKsD9dvzc2pGL752felz8NrldHhxlvym9Qx4M9P4JiLzQzYi8IqCcW4fffxw3Iq+HWlaGu9O3kccKqOylggxm0CgYEA3U56l5JGf0PmQsZk2lx7t25mGouezhyFywOsEGCI43mxaS53/UlweSSQUIuBQ5M/of5G2lTzjoMQcTPTDg0jJyxgCy4SpSxWH/Rp/ghsk4IE/nigQHh936jIfOyOQmzT4NPWotYeR8p6orBUL/WmmP/g98sI0/pmM4pl701A7N8=",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkMQnEubtxnsU1b3rka/P5geyeK/bQb+VSPxxzFXKZqWv8GjUV30xK4pOqnsyW/LheRGJ5KoGc1nTYVuvxT5vE1zb3WFU5I5QpkwFBdF3tEZxJwnZX5HyAXGFEhG0TDxafArW18wtrZqTGd9I+EruHdtcBcOYHmwkdp+wB7gSFCg2Aj4EGXlnb/vgq+uj1WOgzoFWW99mxYG9U8YS6i9H7id9+JtLqEEXgtN23MatjVNrxXVjbW3tV6pLcfvmqbgAW3zsorv2RM0v3vUHs67Rx41fYkByYApwHtyAG8p0sjNxtk+/DxiKIk7p75CRKpvYLfjPIqeeDYLs3A867yNgOwIDAQAB");

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        log.info(StrUtil.format("encrypt:[{}]",encrypt2.toString()));
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        log.info(StrUtil.format("decrypt:[{}]",StrUtil.str(decrypt2,CharsetUtil.CHARSET_UTF_8)));
    }

    @Test
    public void signature3(){

//        RSA rsa =new RSA("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQxCcS5u3GexTVveuRr8/mB7J4r9tBv5VI/HHMVcpmpa/waNRXfTErik6qezJb8uF5EYnkqgZzWdNhW6/FPm8TXNvdYVTkjlCmTAUF0Xe0RnEnCdlfkfIBcYUSEbRMPFp8CtbXzC2tmpMZ30j4Su4d21wFw5gebCR2n7AHuBIUKDYCPgQZeWdv++Cr66PVY6DOgVZb32bFgb1TxhLqL0fuJ334m0uoQReC03bcxq2NU2vFdWNtbe1Xqktx++apuABbfOyiu/ZEzS/e9QezrtHHjV9iQHJgCnAe3IAbynSyM3G2T78PGIoiTunvkJEqm9gt+M8ip54NguzcDzrvI2A7AgMBAAECggEAZygfX8zGqqSSGC0Za9sIHjZl6rIFXziRvEyHGsAXVkXCtWE9zoobO5d2ruzS2cqc+JSS2ip+Jjxf8ARRoA/5Pqbw1TPCNoti1N1exuKYlhGMF/FgwEVTWcv2swWCoMBxCer/ZQ7NjeOT5t/BM4zRWN0zGbhi8WrGIPFxkk6MM2fOYTrwBI49zPpyZCu7roaVm7AYGLSIzMDsB5OVKjpn77huk1MNGwPjP5+PI+6WiElB/BHDnTmXH1Isns5E9KAosyBboyu9huTUTDkpZpdI7nn+xAh7E01jbj7ASoA3dKLijEEdTTfdC89CvYQZ7QQBpafdb1abvq/mTHk2CxaaoQKBgQDgU8jIXg71z+HCyCD4XtD6NYgW/f/o3a8jLjsvpxin/mn0/Q6WUY4EvUdQzUihzhW8rYYpuq7hAtXeQLLEOudcmoyfvlQKwec7LQuoBXr7GHqY5Cl2eb6bMqAE0zjh6qJFrWMeCqmbLrriCyN5XLr9ZIVvTbt0v/SlmeCVcoKs/wKBgQClNKrFz3d/4eHa40wxzHifWWAb8yv80CdeTfp4nsSxYP4IRXURh/yEW3LDhcIYKfvS+yU72kY/1VaFWdXMe/y0wEwHUd6yHQiVU+x04qumw2feNOajsAu5OxMRsnlmG/O3oMs/TATsyFBv7rz/rZzjPeA0ORBTBfcmlb/bQCvAxQKBgGT8ctP4u+Ve+zQTofwNuygYdzZajB0JxzEgO9a883WVK5/1V97FwhhSGUu/zmXA/7obp90bRGTZQOw31gJOvh7LGGlFZlIdbnoOUkQGi5GEOJShiiTis7LsPGtPpzjUxs51OryocigBBnKCLysQWsF9wjAUH0J0s67lMdw1W6yDAoGAdnHjOj171NETSAcM2utZVA4VLRTUZcarYx6PRKm4JeB/YVjDDVWtAww5pNsihIadSO/NCzN8iEmhZaw58Ai47rKsD9dvzc2pGL752felz8NrldHhxlvym9Qx4M9P4JiLzQzYi8IqCcW4fffxw3Iq+HWlaGu9O3kccKqOylggxm0CgYEA3U56l5JGf0PmQsZk2lx7t25mGouezhyFywOsEGCI43mxaS53/UlweSSQUIuBQ5M/of5G2lTzjoMQcTPTDg0jJyxgCy4SpSxWH/Rp/ghsk4IE/nigQHh936jIfOyOQmzT4NPWotYeR8p6orBUL/WmmP/g98sI0/pmM4pl701A7N8=",
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkMQnEubtxnsU1b3rka/P5geyeK/bQb+VSPxxzFXKZqWv8GjUV30xK4pOqnsyW/LheRGJ5KoGc1nTYVuvxT5vE1zb3WFU5I5QpkwFBdF3tEZxJwnZX5HyAXGFEhG0TDxafArW18wtrZqTGd9I+EruHdtcBcOYHmwkdp+wB7gSFCg2Aj4EGXlnb/vgq+uj1WOgzoFWW99mxYG9U8YS6i9H7id9+JtLqEEXgtN23MatjVNrxXVjbW3tV6pLcfvmqbgAW3zsorv2RM0v3vUHs67Rx41fYkByYApwHtyAG8p0sjNxtk+/DxiKIk7p75CRKpvYLfjPIqeeDYLs3A867yNgOwIDAQAB");
//
//        Calendar nowTime = Calendar.getInstance();
//        nowTime.add(Calendar.MINUTE, SignatureConstant.EXPIRE_TIME);
//
//        AuthSignatureInfo authSignatureInfo = new AuthSignatureInfo(System.currentTimeMillis(), RandomUtil.randomNumbers(6));
//        Map<String, Object> map = BeanUtil.beanToMap(authSignatureInfo);
//
//        String token = JWT.create()
//                .addPayloads(map)
//                .setSigner(JWTSignerUtil.rs256(rsa.getPrivateKey()))
//                .setExpiresAt(nowTime.getTime())
//                .sign();
//
//        log.info(token);
//
//        JWT jwt = JWT.of(token).setSigner(JWTSignerUtil.rs256(rsa.getPublicKey()));
//        log.info(jwt.getPayloads().toString());
//        log.info(String.valueOf(jwt.validate(120)));

    }


}
