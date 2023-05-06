package com.zsebank.service.impl;

import com.zsebank.entity.AppAccount;
import com.zsebank.service.RedisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author micha
 * Redis 相关的服务接口实现类
 */
@Service
public class RedisServiceImpl implements RedisService {
    /**
     * 刷新AppAccount，并返回对应的AppId的AppAccount对像
     * @param strAppId 应用id
     * @param appAccountList AppAccount数组
     * @return AppAccount 对像
     * **/
    @Override
    public AppAccount refreshAppAccount(String strAppId, List<AppAccount> appAccountList) {
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
}
