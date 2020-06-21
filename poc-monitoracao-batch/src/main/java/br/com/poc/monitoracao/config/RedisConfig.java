package br.com.poc.monitoracao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories("br.com.poc.monitoracao.repository")
public class RedisConfig {

    /**
     * Factory para conexão principal Redis, gerenciada pelo Spring.
     */
    @Bean("redisTemplate")
    @Primary
    public <T> RedisTemplate<String, T> redisTemplate(
                    final StringRedisSerializer serializer,
                    final RedisConnectionFactory factory) {
        RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean("serializer")
    public StringRedisSerializer serializer() {
        return new StringRedisSerializer();
    }


}
