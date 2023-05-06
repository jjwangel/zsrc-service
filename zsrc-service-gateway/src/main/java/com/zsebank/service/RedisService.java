package com.zsebank.service;


import com.zsebank.entity.AppAccount;

import java.util.List;

/**
 * @author micha
 * Redis 相关的服务接口
 */
public interface RedisService {

    /**
     * 刷新AppAccount，并返回对应的AppId的AppAccount对像
     * @param strAppId 应用id
     * @param appAccountList AppAccount数组
     * @return AppAccount 对像
     * **/
    public AppAccount refreshAppAccount(String strAppId, List<AppAccount> appAccountList);

    /**
     * 刷新AppAccount，失败抛出异常
     * @param appAccountList AppAccount数组
     * **/
    public void refreshAppAccount(List<AppAccount> appAccountList) throws Exception ;

    /**
     * 通过AppId查询
     * @param strAppId 应用id
     * @return AppAccount 对像
     * **/
    public AppAccount getAppAccountByAppId(String strAppId);


}
