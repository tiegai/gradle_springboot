package org.example.asyncConfig;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/executorAsync")
@Api(value = "executor async api", tags = "executor async")
public class AsyncExecutorController {

    @Resource
    AsyncExecutorService asyncExecutorService;

    @PostMapping("/doShop")
    public ResponseEntity<Void> asyncPush(){
        asyncExecutorService.doShop();
        return ResponseEntity.accepted().build();
    }
}
