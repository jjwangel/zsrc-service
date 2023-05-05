package com.zsebank;


import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.zsebank.entity.AuthSignatureInfo;
import com.zsebank.utils.SignatureUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 授权中心测试入口
 * 验证授权中心环境可用性
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorityCenterApplicationTest {

    @Test
    public void contextLoad() {
        log.info(String.valueOf(System.currentTimeMillis()));
        log.info(RandomUtil.randomNumbers(6));
    }

    @Test
    public void generateSignature() throws Exception {
        log.info(SignatureUtil.generateSignature("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQxCcS5u3GexTVveuRr8/mB7J4r9tBv5VI/HHMVcpmpa/waNRXfTErik6qezJb8uF5EYnkqgZzWdNhW6/FPm8TXNvdYVTkjlCmTAUF0Xe0RnEnCdlfkfIBcYUSEbRMPFp8CtbXzC2tmpMZ30j4Su4d21wFw5gebCR2n7AHuBIUKDYCPgQZeWdv++Cr66PVY6DOgVZb32bFgb1TxhLqL0fuJ334m0uoQReC03bcxq2NU2vFdWNtbe1Xqktx++apuABbfOyiu/ZEzS/e9QezrtHHjV9iQHJgCnAe3IAbynSyM3G2T78PGIoiTunvkJEqm9gt+M8ip54NguzcDzrvI2A7AgMBAAECggEAZygfX8zGqqSSGC0Za9sIHjZl6rIFXziRvEyHGsAXVkXCtWE9zoobO5d2ruzS2cqc+JSS2ip+Jjxf8ARRoA/5Pqbw1TPCNoti1N1exuKYlhGMF/FgwEVTWcv2swWCoMBxCer/ZQ7NjeOT5t/BM4zRWN0zGbhi8WrGIPFxkk6MM2fOYTrwBI49zPpyZCu7roaVm7AYGLSIzMDsB5OVKjpn77huk1MNGwPjP5+PI+6WiElB/BHDnTmXH1Isns5E9KAosyBboyu9huTUTDkpZpdI7nn+xAh7E01jbj7ASoA3dKLijEEdTTfdC89CvYQZ7QQBpafdb1abvq/mTHk2CxaaoQKBgQDgU8jIXg71z+HCyCD4XtD6NYgW/f/o3a8jLjsvpxin/mn0/Q6WUY4EvUdQzUihzhW8rYYpuq7hAtXeQLLEOudcmoyfvlQKwec7LQuoBXr7GHqY5Cl2eb6bMqAE0zjh6qJFrWMeCqmbLrriCyN5XLr9ZIVvTbt0v/SlmeCVcoKs/wKBgQClNKrFz3d/4eHa40wxzHifWWAb8yv80CdeTfp4nsSxYP4IRXURh/yEW3LDhcIYKfvS+yU72kY/1VaFWdXMe/y0wEwHUd6yHQiVU+x04qumw2feNOajsAu5OxMRsnlmG/O3oMs/TATsyFBv7rz/rZzjPeA0ORBTBfcmlb/bQCvAxQKBgGT8ctP4u+Ve+zQTofwNuygYdzZajB0JxzEgO9a883WVK5/1V97FwhhSGUu/zmXA/7obp90bRGTZQOw31gJOvh7LGGlFZlIdbnoOUkQGi5GEOJShiiTis7LsPGtPpzjUxs51OryocigBBnKCLysQWsF9wjAUH0J0s67lMdw1W6yDAoGAdnHjOj171NETSAcM2utZVA4VLRTUZcarYx6PRKm4JeB/YVjDDVWtAww5pNsihIadSO/NCzN8iEmhZaw58Ai47rKsD9dvzc2pGL752felz8NrldHhxlvym9Qx4M9P4JiLzQzYi8IqCcW4fffxw3Iq+HWlaGu9O3kccKqOylggxm0CgYEA3U56l5JGf0PmQsZk2lx7t25mGouezhyFywOsEGCI43mxaS53/UlweSSQUIuBQ5M/of5G2lTzjoMQcTPTDg0jJyxgCy4SpSxWH/Rp/ghsk4IE/nigQHh936jIfOyOQmzT4NPWotYeR8p6orBUL/WmmP/g98sI0/pmM4pl701A7N8="));
    }

//    @Test
//    public void generateSignature1() throws Exception {
//        int expire = 1;
//        AuthSignatureInfo authSignatureInfo = new AuthSignatureInfo(System.currentTimeMillis(), RandomUtil.randomNumbers(6));
//
//        log.info(JSON.toJSONString(authSignatureInfo));
//
//        // 计算超时时间
//        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS)
//                .atStartOfDay(ZoneId.systemDefault());
//        Date expireDate = Date.from(zdt.toInstant());
//
//        String signature = Jwts.builder()
//                // jwt payload --> KV
//                .claim("SIGNATURE_INFO", JSON.toJSONString(authSignatureInfo))
//                // jwt id
//                .setId(UUID.randomUUID().toString())
//                // jwt 过期时间
//                .setExpiration(expireDate)
//                // jwt 签名 --> 加密
//                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
//                .compact();
//
//        log.info("signature:[{}]", signature);
//    }
//
//    /**
//     * 根据本地存储的私钥获取到 PrivateKey 对象
//     */
//    private PrivateKey getPrivateKey() throws Exception {
//
//        String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQxCcS5u3GexTVveuRr8/mB7J4r9tBv5VI/HHMVcpmpa/waNRXfTErik6qezJb8uF5EYnkqgZzWdNhW6/FPm8TXNvdYVTkjlCmTAUF0Xe0RnEnCdlfkfIBcYUSEbRMPFp8CtbXzC2tmpMZ30j4Su4d21wFw5gebCR2n7AHuBIUKDYCPgQZeWdv++Cr66PVY6DOgVZb32bFgb1TxhLqL0fuJ334m0uoQReC03bcxq2NU2vFdWNtbe1Xqktx++apuABbfOyiu/ZEzS/e9QezrtHHjV9iQHJgCnAe3IAbynSyM3G2T78PGIoiTunvkJEqm9gt+M8ip54NguzcDzrvI2A7AgMBAAECggEAZygfX8zGqqSSGC0Za9sIHjZl6rIFXziRvEyHGsAXVkXCtWE9zoobO5d2ruzS2cqc+JSS2ip+Jjxf8ARRoA/5Pqbw1TPCNoti1N1exuKYlhGMF/FgwEVTWcv2swWCoMBxCer/ZQ7NjeOT5t/BM4zRWN0zGbhi8WrGIPFxkk6MM2fOYTrwBI49zPpyZCu7roaVm7AYGLSIzMDsB5OVKjpn77huk1MNGwPjP5+PI+6WiElB/BHDnTmXH1Isns5E9KAosyBboyu9huTUTDkpZpdI7nn+xAh7E01jbj7ASoA3dKLijEEdTTfdC89CvYQZ7QQBpafdb1abvq/mTHk2CxaaoQKBgQDgU8jIXg71z+HCyCD4XtD6NYgW/f/o3a8jLjsvpxin/mn0/Q6WUY4EvUdQzUihzhW8rYYpuq7hAtXeQLLEOudcmoyfvlQKwec7LQuoBXr7GHqY5Cl2eb6bMqAE0zjh6qJFrWMeCqmbLrriCyN5XLr9ZIVvTbt0v/SlmeCVcoKs/wKBgQClNKrFz3d/4eHa40wxzHifWWAb8yv80CdeTfp4nsSxYP4IRXURh/yEW3LDhcIYKfvS+yU72kY/1VaFWdXMe/y0wEwHUd6yHQiVU+x04qumw2feNOajsAu5OxMRsnlmG/O3oMs/TATsyFBv7rz/rZzjPeA0ORBTBfcmlb/bQCvAxQKBgGT8ctP4u+Ve+zQTofwNuygYdzZajB0JxzEgO9a883WVK5/1V97FwhhSGUu/zmXA/7obp90bRGTZQOw31gJOvh7LGGlFZlIdbnoOUkQGi5GEOJShiiTis7LsPGtPpzjUxs51OryocigBBnKCLysQWsF9wjAUH0J0s67lMdw1W6yDAoGAdnHjOj171NETSAcM2utZVA4VLRTUZcarYx6PRKm4JeB/YVjDDVWtAww5pNsihIadSO/NCzN8iEmhZaw58Ai47rKsD9dvzc2pGL752felz8NrldHhxlvym9Qx4M9P4JiLzQzYi8IqCcW4fffxw3Iq+HWlaGu9O3kccKqOylggxm0CgYEA3U56l5JGf0PmQsZk2lx7t25mGouezhyFywOsEGCI43mxaS53/UlweSSQUIuBQ5M/of5G2lTzjoMQcTPTDg0jJyxgCy4SpSxWH/Rp/ghsk4IE/nigQHh936jIfOyOQmzT4NPWotYeR8p6orBUL/WmmP/g98sI0/pmM4pl701A7N8=";
//
//        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
//                new BASE64Decoder().decodeBuffer(PRIVATE_KEY));
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//        return keyFactory.generatePrivate(priPKCS8);
//    }

    @Test
    public void parseSignatureInfoFromSignature() throws Exception {
        String signature="eyJhbGciOiJSUzI1NiJ9.eyJTSUdOQVRVUkVfSU5GTyI6IntcInN0ck5vbmNlXCI6XCI4MzQ3NTdcIixcInRpbWVNaWxsaXNcIjoxNjgyMTU0MjA2OTY1fSIsImp0aSI6IjQ0MzliYjU5LTgyMDEtNGJlMy1iMTUyLTZjODFiNGE2YjdkOSIsImV4cCI6MTY4MjE3OTIwMH0.LgfgmWqKnxYQNwhudbVVmihoTI4eT8QbnabnnDfkDE4uTDlW9JBYDdvFdybcARRPgldjvp9n7ayVg534KdmrW7v8hhlt57gxy8wAIleUHFE_pLQdDC2dOwqO1pP0OTAjvgv8eFXLdNPxxBX0uzAziXYFwkQ1nl4Hu220uVHVZCNmDVOzgeoiKNi2crPKdAxUx4xxF06EpaU0GKdddBUwJh6raaG3N0W1V9_gj5Y5jWBwgmbUqMZIQyzuXRr0Czvl5F5PoT3fCaKSKppAfbP6BH_PEgb85PzWpEanb9nWseKgYuVoJBdDQr56fY8-fz31WEQI1GFkfeNrj8Emm7LpAQ";

        Jws<Claims> claimsJws = parseToken(signature, getPublicKey());
        Claims body = claimsJws.getBody();

        // 如果 Token 已经过期了, 返回 null
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            return;
        }

        // 返回 Token 中保存的用户信息
        AuthSignatureInfo authSignatureInfo = JSON.parseObject(
                body.get("SIGNATURE_INFO").toString(),
                AuthSignatureInfo.class
        );

        log.info("AuthSignatureInfo:[{}]",JSON.toJSONString(authSignatureInfo));

    }

    /**
     * <h2>通过公钥去解析 JWT Token</h2>
     * */
    private static Jws<Claims> parseToken(String signature, PublicKey publicKey) {

        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(signature);
    }

    /**
     * 根据本地存储的公钥获取到 PublicKey 对象
     * */
    private static PublicKey getPublicKey() throws Exception {

        String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkMQnEubtxnsU1b3rka/P5geyeK/bQb+VSPxxzFXKZqWv8GjUV30xK4pOqnsyW/LheRGJ5KoGc1nTYVuvxT5vE1zb3WFU5I5QpkwFBdF3tEZxJwnZX5HyAXGFEhG0TDxafArW18wtrZqTGd9I+EruHdtcBcOYHmwkdp+wB7gSFCg2Aj4EGXlnb/vgq+uj1WOgzoFWW99mxYG9U8YS6i9H7id9+JtLqEEXgtN23MatjVNrxXVjbW3tV6pLcfvmqbgAW3zsorv2RM0v3vUHs67Rx41fYkByYApwHtyAG8p0sjNxtk+/DxiKIk7p75CRKpvYLfjPIqeeDYLs3A867yNgOwIDAQAB";
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(PUBLIC_KEY)
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }




}
