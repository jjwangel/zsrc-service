package com.zsebank.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.fastjson2.JSON;
import com.zsebank.constant.RedisConstant;
import com.zsebank.constant.SignatureConstant;
import com.zsebank.entity.AppAccount;
import com.zsebank.entity.AuthSignatureInfo;
import com.zsebank.service.SignatureParseService;
import com.zsebank.util.SignatureParseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @author micha
 * 签名解释服务接口实现
 */
@Service
public class SignatureParseServiceImpl implements SignatureParseService {

    private final RedisTemplate<String,String> stringRedisTemplate;
    private final RedisTemplate<String,Object> objectRedisTemplate;

    public SignatureParseServiceImpl(RedisTemplate<String, Object> objectRedisTemplate, RedisTemplate<String, String> stringRedisTemplate) {
        this.objectRedisTemplate = objectRedisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 签名解释，成功返回AuthSignatureInfo对像
     * @param strAppId 应用id
     * @param strSignature 应用签名
     * @return 返回AuthSignatureInfo 对像，失败抛出异常
     * **/
    @Override
    public AuthSignatureInfo signatureParse(String strAppId,String strSignature) throws Exception {

        // 1.通过app_id 在redis中查找是否存在public_key
        AppAccount appAccount = getAppAccountByAppId(strAppId);
        if (null == appAccount){
            throw new RuntimeException("getAppAccountByAppId error");
        }

        // 2.使用public_key解释签名，并获取AuthSignatureInfo对像和过期时间
        AuthSignatureInfo authSignatureInfo = SignatureParseUtil.parse(strSignature,appAccount.getAppPublicKey());
        if(null == authSignatureInfo){
            throw new RuntimeException("parse error");
        } else {
            // 4.判断本次请求是否存在于redis，如果存在则认为是重复请求。
            if(!setIfAbsentApiRequest(CharSequenceUtil.format("{}{}{}",
                    RedisConstant.API_REQUEST_PREFIX,authSignatureInfo.getDate(),authSignatureInfo.getNonce()),authSignatureInfo.getDate())){
                throw new RuntimeException("setIfAbsentApiRequest error");
            }
        }

        return authSignatureInfo;
    }

    /**
     * 通过app_id 在redis中查找是否存在public_key
     * @param appId 应用APP_ID
     * @return 返回 AppAccount
     * **/
    private AppAccount getAppAccountByAppId(String appId) {
        String val = stringRedisTemplate.opsForValue().get(CharSequenceUtil.format("{}{}",RedisConstant.APP_ACCOUNT_PREFIX,appId));
        if(CharSequenceUtil.isNotEmpty(val)){
            return JSON.parseObject(val,AppAccount.class);
        } else {
            return null;
        }
    }

    /**
     * 如果键不存在则新增,存在则不改变已经有的值。
     * @param key key
     * @param date date
     * @return key存在返回 false，不存在返回 true。
     * **/
    private Boolean setIfAbsentApiRequest(String key, long date){
        return stringRedisTemplate.opsForValue().setIfAbsent(key,String.valueOf(date), SignatureConstant.EXPIRE_TIME*60 + SignatureConstant.LEEWAY_TIME, TimeUnit.SECONDS);
    }
}
