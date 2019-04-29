package com.xfy.apiall.core.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具，存取
 */
@Configuration
public class RedisUtil {

   /* @Autowired
    private RedisTemplate redisTemplate;
    */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 存数据
     * @param key
     * @param value
     */
    public void set(String key , Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 存数据
     * @param key 键
     * @param value 值
     * @param exp 过期时间 默认秒
     * @param s 使用 时还是分还是秒，默认秒
     */
    public void set(String key , Object value , int exp, TimeUnit s){
        if( s==null ){
            s = TimeUnit.SECONDS;
        }
        redisTemplate.opsForValue().set(key,value,exp, s);
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public <T>T get(String key){
        return (T)redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断 key 是否在 redis 数据库中
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 删除 key 对应的 value
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

}
