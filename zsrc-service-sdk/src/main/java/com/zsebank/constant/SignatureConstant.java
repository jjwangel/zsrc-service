package com.zsebank.constant;


/**
 * @author jianjiawen
 * 签名相关常量
 */
public class SignatureConstant {
    /** 签名过期时间，单位：分钟 */
    public static final Integer EXPIRE_TIME = 5;

    /** 容忍空间，单位：秒。当不能晚于当前时间时，向后容忍；不能早于向前容忍。 l*/
    public static final long LEEWAY_TIME = 600;

    /** 签名信息key */
    public static final String SIGNATURE_INFO_KEY = "SIGNATURE_INFO";
}
