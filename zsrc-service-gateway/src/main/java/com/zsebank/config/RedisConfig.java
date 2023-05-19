package com.zsebank.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsebank.entity.AppAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory rcf) {
        RedisTemplate<String, Object> rt = new RedisTemplate<>();
        rt.setConnectionFactory(rcf);

        Jackson2JsonRedisSerializer<Object> jsonrs = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonrs.setObjectMapper(om);

        // 键 key,hashKey 用String序列化
        rt.setKeySerializer(RedisSerializer.string());
        rt.setHashKeySerializer(RedisSerializer.string());

        // 值 value,hashValue 用JSON序列化
        rt.setValueSerializer(jsonrs);
        rt.setHashValueSerializer(jsonrs);

        return rt;
    }
}
