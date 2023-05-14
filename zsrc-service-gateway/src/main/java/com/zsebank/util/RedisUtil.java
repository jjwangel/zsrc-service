package com.zsebank.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jianjiawen
 * Redis 工具类
 */
@ser
public class RedisUtil {


    private final StringRedisTemplate stringRedisTemplate;

    private final RedisTemplate<String,Object> objectRedisTemplate;


    public RedisUtil(StringRedisTemplate stringRedisTemplate, RedisTemplate<String, Object> objectRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectRedisTemplate = objectRedisTemplate;
    }

    public StringRedisTemplate stringRedisTemplate(){
        return this.stringRedisTemplate;
    }


}
