package org.example.asyncDirectly;


import lombok.extern.slf4j.Slf4j;
import org.example.asyncConfig.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncTestTaskService {


    @Autowired
    private AsyncTestTask asyncTestTask;

    public void doShop() {
        try {
            createOrder();
            // 调用有结果返回的异步任务
            CompletableFuture<String> paySuccess = asyncTestTask.paySuccess();
            // 判断线程是否完成
            if (paySuccess.isDone()) {
                try {
                    String result = paySuccess.get();
                    log.info("异步任务返回结果{}", result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            asyncTestTask.vip();
            asyncTestTask.sendSms();
            }
        } catch (InterruptedException e) {
            log.error("异常", e);
        }
    }

    public void createOrder() {
        log.info("开始做任务1：下单成功");
    }
}
