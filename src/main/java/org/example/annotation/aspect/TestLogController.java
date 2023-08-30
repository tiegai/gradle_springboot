package org.example.annotation.aspect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志注解测试类
 */

@RestController
public class TestLogController {
    @Log(state = "测试日志注解")
    @GetMapping("/testLog/{username}")
    public String testLog(@PathVariable("username") String username) {
        return "测试成功";
    }
}
