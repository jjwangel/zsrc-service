package com.zsebank.service.impl;

import com.zsebank.entity.AppAccount;
import com.zsebank.entity.AuthSignatureInfo;
import com.zsebank.service.SignatureParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author micha
 * 签名解释服务接口实现
 */
@Service
public class SignatureParseServiceImpl implements SignatureParseService {

    private final RedisServiceImpl redisService;

    public SignatureParseServiceImpl(RedisServiceImpl redisService, AppAccountServiceImpl appAccountService) {
        this.redisService = redisService;
        this.appAccountService = appAccountService;
    }

    private final AppAccountServiceImpl appAccountService;

    /**
     * 签名解释，成功返回AuthSignatureInfo对像
     * **/
    @Override
    public AuthSignatureInfo SignatureParse(String strAppId,String strSignature) {

        // 1.通过app_id 在redis中查找是否存在public_key
        AppAccount appAccount = redisService.getAppAccountByAppId(strAppId);
        if (null == appAccount){
            // 2.如果redis没有则从数据库中读取对应的appid的public_key并写入到redis中
            List<AppAccount> appAccountList = appAccountService.list();
            if(null == appAccountList){
                throw new RuntimeException("error");
            } else {
                appAccount = redisService.refreshAppAccount(strAppId,appAccountList);
                if(null == appAccount) {
                    throw new RuntimeException("error");
                }
            }
        }

        // 3.使用public_key解释签名，并获取AuthSignatureInfo对像和过期时间

        // 4.判断本次请求是否存在于redis，如果存在则认为是重复请求。

        // 5.判断过期时间是过期，如果没有过期则返回AuthSignatureInfo对像

        throw new RuntimeException("error");
    }
}
