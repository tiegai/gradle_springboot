package org.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void doSet() {
        redisTemplate.opsForValue().set("key", "value");
        String value = (String) redisTemplate.opsForValue().get("key");
        log.info("String value:{}", value);
        redisTemplate.delete("key");

        // String
        redisTemplate.opsForSet().add("key1", "value1");
        redisTemplate.expire("key1", 10, TimeUnit.MINUTES);
        log.info("过期时间：" + redisTemplate.getExpire("key1").toString());

        // hash
        redisTemplate.opsForHash().put("hashKey", "field1", "value1");
        // Retrieve the value from the hash
        String value_hash = (String) redisTemplate.opsForHash().get("hashKey", "field1");
        log.info("hash value:{}", value_hash);
        log.info(redisTemplate.hasKey("field1").toString());

        // list
        redisTemplate.opsForList().leftPush("listKey1", "value1");
        redisTemplate.opsForList().leftPush("listKey1", "value0");
        redisTemplate.opsForList().rightPush("listKey2", "value2");
        redisTemplate.opsForList().rightPush("listKey2", "value3");
        log.info("listKey1:{}", redisTemplate.opsForList().range("listKey1", 0, -1));

        // set
        redisTemplate.boundSetOps("setKey1").add("value1", "value2", "value3");
        log.info("setKey1:{}", redisTemplate.boundSetOps("setKey1").members());
        log.info("set1:" + redisTemplate.boundSetOps("setKey1").isMember("value1").toString());
        log.info("set2:" + redisTemplate.boundSetOps("setKey1").size().toString());
        log.info("set3:" + redisTemplate.boundSetOps("setKey1").remove("value1").toString());

        // zset
        redisTemplate.boundZSetOps("zSetKey").add("zSetVaule", 100D);
        BoundZSetOperations zSetKey = redisTemplate.boundZSetOps("zSetKey");
        zSetKey.add("zSetVaule", 1000D);
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add("zSetKey", "zSetVaule", 10000D);


        DefaultTypedTuple<Object> p1 = new DefaultTypedTuple<>("zSetVaule1", 2.1D);
        DefaultTypedTuple<Object> p2 = new DefaultTypedTuple<>("zSetVaule2", 3.3D);
        redisTemplate.boundZSetOps("zSetKey").add(new HashSet<>(Arrays.asList(p1,p2)));
        log.info("zset1:" + redisTemplate.boundZSetOps("zSetKey").range(0, -1).toString());
        log.info("zset2:" + redisTemplate.boundZSetOps("zSetKey").score("zSetVaule").toString());
        log.info("zset3:" + redisTemplate.boundZSetOps("zSetKey").size().toString());
        log.info("zset4:" + redisTemplate.boundZSetOps("zSetKey").count(0D, 2.2D).toString());
        log.info("zset5:" + redisTemplate.boundZSetOps("zSetKey").rangeByScore(0D, 2.2D).toString());
        log.info("zset6:" + redisTemplate.opsForZSet().rangeByScore("zSetKey",0D, 2.2D, 1,3).toString());
        log.info("zset7:" + redisTemplate.boundZSetOps("zSetKey").rank("zSetVaule").toString());
        log.info("zset8:" + redisTemplate.boundZSetOps("zSetKey").reverseRank("zSetVaule").toString());

        // 如果key1==value1，则删除key1，返回删除的状态，否则返回0
        redisTemplate.opsForValue().set("key_lua", "value_lua");
        String script2 = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long lua_result1 = (Long)redisTemplate.execute(new DefaultRedisScript<Long>(script2, Long.class), Arrays.asList("key_lua"), "value");
        log.info("lua_result1:{}", lua_result1);
        Long lua_result2 = (Long)redisTemplate.execute(new DefaultRedisScript<Long>(script2, Long.class), Arrays.asList("key_lua"), "value_lua");
        log.info("lua_result2:{}", lua_result2);

    }
}
