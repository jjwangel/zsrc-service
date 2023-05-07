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
    AppAccount refreshAppAccount(String strAppId, List<AppAccount> appAccountList);

    /**
     * 刷新AppAccount，失败抛出异常
     * @param appAccountList AppAccount数组
     * **/
    void refreshAppAccount(List<AppAccount> appAccountList) throws Exception ;

    /**
     * 通过AppId查询
     * @param strAppId 应用id
     * @return AppAccount 对像
     * **/
    AppAccount getAppAccountByAppId(String strAppId);

    /**
     * 保存请求到Resis,防止请求重复提交
     * @param key key
     * @param value value
     * @param time 过期时间
     * @return 返回null 表示请求可用，不为null表示请求已重复
    **/
    String saveApiRequest(String key,String value,long time);
}
