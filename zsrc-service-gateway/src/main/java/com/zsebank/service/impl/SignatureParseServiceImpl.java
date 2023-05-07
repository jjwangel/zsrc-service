package com.zsebank.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsebank.constant.RedisConstant;
import com.zsebank.constant.SignatureConstant;
import com.zsebank.entity.AppAccount;
import com.zsebank.entity.AuthSignatureInfo;
import com.zsebank.service.SignatureParseService;
import com.zsebank.util.SignatureParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.AppContext;

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
     * @param strAppId 应用id
     * @param strSignature 应用签名
     * @return 返回AuthSignatureInfo 对像，失败抛出异常
     * **/
    @Override
    public AuthSignatureInfo signatureParse(String strAppId,String strSignature) throws Exception {

        // 1.通过app_id 在redis中查找是否存在public_key
        AppAccount appAccount = redisService.getAppAccountByAppId(strAppId);
        if (null == appAccount){
            // 2.如果redis没有则从数据库中读取对应的appid的public_key并写入到redis中
            List<AppAccount> appAccountList = appAccountService.list(new QueryWrapper<AppAccount>().eq("is_enable",1));
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
        AuthSignatureInfo authSignatureInfo = SignatureParseUtil.parse(strSignature,appAccount.getAppPublicKey());
        if(null == authSignatureInfo){
            throw new RuntimeException("error");
        } else {
            // 4.判断本次请求是否存在于redis，如果存在则认为是重复请求。
            if(!StrUtil.hasEmpty(redisService.saveApiRequest(StrUtil.format("{}{}{}",
                    RedisConstant.API_REQUEST_PREFIX,authSignatureInfo.getTimeMillis(),authSignatureInfo.getStrNonce()), authSignatureInfo.getTimeMillis().toString(), 0))){
                throw new RuntimeException("error");
            }
        }

        return authSignatureInfo;
    }
}
