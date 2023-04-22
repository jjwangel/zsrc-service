package com.zsebank.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取签名配置属性
 * */
@Data
@Configuration
@ConfigurationProperties(prefix = "signature")
public class SignatureInfoConfig {

    /** 公钥，最终应该保存到数据库 */
    private String publicKey;

    /** 接口请求头保存签名的key */
    private String  signatureInfoKey;

    /** 过期时间，单位为分钟 */
    private Integer expireTime;

}
