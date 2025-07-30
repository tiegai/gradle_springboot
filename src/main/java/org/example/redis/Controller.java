package org.example.redis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class Controller {

    @Resource
    private RedisService redisService;

    @RequestMapping("/doSet")
    public void test() {
        redisService.doSet();
    }



}
