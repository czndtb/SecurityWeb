package com.wl.web.config.redis;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @program: spring-boot-example
 * @description: Redis的配置文件
 * @author:
 * @create: 2018-05-16 15:01
 **/
@Configuration
public class RedisConfig {

    @Autowired
    private RedisConfigurationProperties redisConfigurationProperties;

    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodeSet = new HashSet<>();
        for(String node :redisConfigurationProperties.getNodes()) {
            String[] split = node.split(":");
            nodeSet.add(new HostAndPort(split[0],Integer.valueOf(split[1])));
        }
        return new JedisCluster(nodeSet);
    }

}