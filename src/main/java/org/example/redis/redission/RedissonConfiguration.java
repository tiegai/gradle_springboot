package org.example.redis.redission;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password:}")
    private String password;

    private static final int DATABASE = 0;
    private static final int CONNECTION_POOL_SIZE = 10; // Example value
    private static final int CONNECTION_MIN_IDLE_SIZE = 5; // Example value

    @Bean
    public RedissonClient redisson() {
        String address = "redis://" + host + ":" + port;
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setPassword(password)
                .setDatabase(DATABASE)
                .setConnectionPoolSize(CONNECTION_POOL_SIZE)
                .setConnectionMinimumIdleSize(CONNECTION_MIN_IDLE_SIZE);
        return Redisson.create(config);
    }


}
