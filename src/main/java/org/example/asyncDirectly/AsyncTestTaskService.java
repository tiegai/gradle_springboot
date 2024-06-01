package org.example.asyncDirectly;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
                    System.out.println("异步任务返回结果:"+result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            asyncTestTask.vip();
            asyncTestTask.sendSms();
            }
        } catch (InterruptedException e) {
            System.out.println("异常:"+e);
        }
    }

    public void createOrder() {
        System.out.println("开始做任务1：下单成功");
    }
}
