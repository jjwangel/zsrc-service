package com.zsebank;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String,Object> objectRedisTemplate;

    static class Info implements Serializable {
        String name;
        String name2;
        String web;

        public Info() {}

        public Info(String name, String name2, String web) {
            this.name = name;
            this.name2 = name2;
            this.web = web;
        }

    }

    @Test
    public void RedisTest(){
//        redisTemplate.opsForValue().set("name","jianjiawen");
//        log.info(redisTemplate.opsForValue().get("name"));
        stringRedisTemplate.opsForValue().set("name","jianjiawen");
        log.info(stringRedisTemplate.opsForValue().get("name"));
    }

    @Test
    public void RedisTest2(){
        objectRedisTemplate.opsForValue().set("info",new Info("jian","jia","www.jjw.com"));

    }

}
