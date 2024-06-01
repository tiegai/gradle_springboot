package org.example.asyncDirectly;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/testAsync")
@Api(value = "test async api", tags = "test async")
public class AsyncTestController {

    @Resource
    AsyncTestService asyncTestService;

    @Resource
    AsyncTestTaskService asyncTestTaskService;

    @PostMapping("/push")
    public ResponseEntity<Void> asyncPush(){
        asyncTestService.test();
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/doShop")
    public ResponseEntity<Void> doShop(){
        asyncTestTaskService.doShop();
        return ResponseEntity.accepted().build();
    }
}
