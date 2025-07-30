package org.example.shutdown;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Slf4j
public class AppDestroyConfig {

    @PreDestroy
    public void PreDestroy(){
       log.info("应用程序正在关闭。。。");
    }
}
