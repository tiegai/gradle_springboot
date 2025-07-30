package org.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/lua")
@Slf4j
public class LuaController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    // 预加载脚本，避免频繁读取脚本。同时mylua.lua文件需要放在resources下
    public static final DefaultRedisScript luaScript;
    static {
        luaScript = new DefaultRedisScript();
        luaScript.setLocation(new ClassPathResource("mylua.lua")); // 指定脚本文件路径
        luaScript.setResultType(Long.class); // 指定脚本返回值类型
    }

    @RequestMapping("/doLua")
    public void doLua() {
        Object execute = redisTemplate.opsForValue().getOperations().execute(luaScript, Collections.singletonList("Key中第一个参数"), "我是其他参数中第一个");
        log.info(execute.toString());
    }


}
