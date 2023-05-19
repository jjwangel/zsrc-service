package com.zsebank.config;


import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsebank.constant.RedisConstant;
import com.zsebank.entity.AppAccount;
import com.zsebank.service.impl.AppAccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;

/**
 * 初始化GateWay应用
 * @author micha
 */
@Slf4j
@Component
public class GateWayInit {

    private final RedisTemplate<String,String> redisTemplate;
    private final AppAccountServiceImpl appAccountService;


    public GateWayInit(AppAccountServiceImpl appAccountService, RedisTemplate<String, String> redisTemplate) {
        this.appAccountService = appAccountService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 初始化Redis
     * **/
    @EventListener(ApplicationReadyEvent.class)
    public void initRedisData(){
        initAppAccount();

        log.info("initRedisData finish......");
    }

    /**
     * 初始化AppAccount到Redis
     * **/
    private void initAppAccount(){
        QueryWrapper<AppAccount> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("is_enable",1);

        // 1.查询数据库AppAccount记录并插入到redis中
        // 2.读取redis中的所有AppAccount记录，与数据库查询出来的记录比较，数据库查询中没有的key就删除。
        List<AppAccount> appAccountList = appAccountService.list(queryWrapper);
        Set<String> allKeys = redisTemplate.keys(CharSequenceUtil.format("{}{}", RedisConstant.APP_ACCOUNT_PREFIX,"*"));

        if(ArrayUtil.isNotEmpty(appAccountList)){
            appAccountList.forEach(appAccount -> {
                this.redisTemplate.opsForValue().set(
                        CharSequenceUtil.format("{}{}", RedisConstant.APP_ACCOUNT_PREFIX,appAccount.getAppId()),JSON.toJSONString(appAccount));
                // 把数据库查询出来的AppAccount从redis现有的key中移除，则最后剩下的就是没用的key
                if(ArrayUtil.isNotEmpty(allKeys)){
                    allKeys.remove(CharSequenceUtil.format("{}{}", RedisConstant.APP_ACCOUNT_PREFIX,appAccount.getAppId()));
                }
            });
        }

        if(ArrayUtil.isNotEmpty(allKeys)) {
            allKeys.forEach(this.redisTemplate::delete);
        }
    }
}
