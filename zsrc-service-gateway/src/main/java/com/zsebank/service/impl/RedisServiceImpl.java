package com.zsebank.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zsebank.constant.RedisConstant;
import com.zsebank.entity.AppAccount;
import com.zsebank.service.RedisService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author micha
 * Redis 相关的服务接口实现类
 */
@Service
public class RedisServiceImpl implements RedisService {


    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String,Object> objectRedisTemplate;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate, RedisTemplate<String, Object> objectRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectRedisTemplate = objectRedisTemplate;
    }




    /**
     * 刷新AppAccount，并返回对应的AppId的AppAccount对像
     * @param strAppId 应用id
     * @param appAccountList AppAccount数组
     * @return AppAccount 对像
     * **/
    @Override
    public AppAccount refreshAppAccount(@NotNull String strAppId, @NotNull List<AppAccount> appAccountList) {
        appAccountList.stream()
                .peek(v -> {
                    objectRedisTemplate.opsForValue().set(StrUtil.format("{}{}", RedisConstant.APP_ACCOUNT_PREFIX,strAppId),v);
                });
        return null;
    }

    /**
     * 刷新AppAccount，失败抛出异常
     * @param appAccountList AppAccount数组
     * **/
    @Override
    public void refreshAppAccount(List<AppAccount> appAccountList) throws Exception {
        // TODO document why this method is empty
    }

    /**
     * 通过AppId查询
     * @param strAppId 应用id
     * @return AppAccount 对像
     * **/
    @Override
    public AppAccount getAppAccountByAppId(String strAppId) {
        return null;
    }

    /**
     * 保存请求到Resis,防止请求重复提交
     * @param key key
     * @param value value
     * @param time 过期时间
     * @return 返回null 表示请求可用，不为null表示请求已重复
     **/
    @Override
    public String saveApiRequest(String key, String value, long time) {
        return null;
    }
}
