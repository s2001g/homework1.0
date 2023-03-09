package com.tristan.order.dao;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

/**
 * @author big uncle
 * @date 2020/7/15 12:08
 * @module
 **/
@Configuration
@Slf4j
public class RedisConfig {

    @Resource
    RedisProperties redisProperties;

    /**
     * GenericObjectPoolConfig 连接池配置
     */
    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        genericObjectPoolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
        genericObjectPoolConfig.setMaxWaitMillis(redisProperties.getLettuce().getPool().getMaxWait().toMillis());
        return genericObjectPoolConfig;
    }

    /**
     * 工厂创建了连接 但是一个单实例
     **/
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(GenericObjectPoolConfig genericObjectPoolConfig) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(),redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(redisProperties.getTimeout())
                .poolConfig(genericObjectPoolConfig)
                .build();
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration,clientConfig);
        return lettuceConnectionFactory;
    }
}
