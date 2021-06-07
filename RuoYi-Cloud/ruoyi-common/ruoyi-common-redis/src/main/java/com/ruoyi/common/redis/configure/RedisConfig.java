package com.ruoyi.common.redis.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 *
 * @author ruoyi
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  //缓存出现异常时处理，一般忽略报错，不能因为redis导致业务失败
  //如果缓存写发生了异常，就可能导致mysql的数据和redis缓存的数据不一致的问题
  //CacheErrorHandler的handleCachePutError和handleCacheEvictError 把这些key删除
  //或者通过canal处理缓存不一致的情况
  @Override
  public CacheErrorHandler errorHandler() {
    return new IgnoreExceptionCacheErrorHandler();
  }

  @Bean
  @SuppressWarnings(value = { "unchecked", "rawtypes" })
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    serializer.setObjectMapper(mapper);
    template.setValueSerializer(serializer);
    // 使用StringRedisSerializer来序列化和反序列化redis的key值
    // 存到redis的值可以直接字符串或者json形式查看，否则就是二进制的数据
    template.setKeySerializer(new StringRedisSerializer());
    template.afterPropertiesSet();
    return template;
  }
}
